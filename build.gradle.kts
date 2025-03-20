import net.darkhax.curseforgegradle.TaskPublishCurseForge
import net.minecraftforge.gradle.userdev.DependencyManagementExtension
import java.text.SimpleDateFormat
import java.util.Date

plugins {
    eclipse
    idea
    alias(libs.plugins.forgegradle)
    alias(libs.plugins.parchmentForgegradle)
    alias(libs.plugins.mixin)
    alias(libs.plugins.curseforgegradle)
    alias(libs.plugins.minotaur)
}

val javaVersion = properties["java_version"].toString()
val mcVersion = libs.versions.minecraft.get()
val modLoader = properties["mod_loader"].toString()

group = properties["mod_group_id"].toString()
version = "${properties["mod_version"]}+$mcVersion-${modLoader.lowercase()}"

base {
    archivesName = "${properties["mod_file_name"]}"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaVersion))
    }
}

minecraft {
    mappings(
        properties["mapping_channel"].toString(),
        properties["mapping_version"].toString()
    )

    accessTransformer(file("src/main/resources/META-INF/accesstransformer.cfg"))
    copyIdeResources = true

    runs {
        configureEach {
            workingDirectory = project.file("run").path

            property("forge.logging.markers", "REGISTRIES")
            property("forge.logging.console.level", "debug")

            mods {
                create(extra["mod_id"].toString()) {
                    source(sourceSets["main"])
                }
            }
        }

        create("client") {
            property("forge.enabledGameTestNamespaces", properties["mod_id"].toString())
        }

        create("server") {
            property("forge.enabledGameTestNamespaces", properties["mod_id"].toString())
            args("--nogui")
        }

        create("gameTestServer") {
            property("forge.enabledGameTestNamespaces", properties["mod_id"].toString())
        }

        create("data") {
            workingDirectory = project.file("run-data").path

            args(
                "--mod", properties["mod_id"],
                "--all",
                "--output", file("src/generated/resources/"),
                "--existing", file("src/main/resources/")
            )
        }
    }
}

mixin {
    add(sourceSets["main"], "${properties["mod_id"]}.refmap.json")

    config("${properties["mod_id"]}.mixins.json")
}

sourceSets["main"].resources.srcDir("src/generated/resources")

repositories {
    maven { url = uri("https://maven.shedaniel.me/") }
    maven { url = uri("https://cursemaven.com/") }

    mavenCentral()
}

dependencies {
    val forgeVersion = "${libs.versions.minecraft.get()}-${libs.versions.forge.get()}"
    val mixinVersion = libs.versions.mixin.processor.get()

    minecraft("net.minecraftforge:forge:$forgeVersion")

    implementation(fg.providerDeobf(libs.cloth.config))
    implementation(fg.providerDeobf(libs.citadel))
    implementation(fg.providerDeobf(libs.alexscaves))

    annotationProcessor("org.spongepowered:mixin:$mixinVersion:processor")
}

tasks.named<ProcessResources>("processResources") {
    val replaceProperties = mapOf(
        "minecraft_version" to mcVersion,
        "minecraft_version_range" to properties["minecraft_version_range"],
        "forge_version" to libs.versions.forge.get(),
        "forge_version_range" to properties["forge_version_range"],
        "loader_version_range" to properties["loader_version_range"],
        "mod_id" to properties["mod_id"],
        "mod_name" to properties["mod_name"],
        "mod_license" to properties["mod_license"],
        "mod_version" to properties["mod_version"],
        "mod_authors" to properties["mod_authors"],
        "mod_logo" to properties["mod_logo"],
        "mod_url" to properties["mod_url"],
        "mod_issue_url" to properties["mod_issue_url"],
        "mod_description" to properties["mod_description"],
        "cloth_config_version" to libs.versions.cloth.config.get()
    )

    inputs.properties(replaceProperties)

    filesMatching(listOf("META-INF/mods.toml", "pack.mcmeta")) {
        expand(replaceProperties + mapOf("project" to project))
    }
}

tasks.named<Jar>("jar") {
    manifest {
        attributes(
            "Specification-Title" to properties["mod_id"],
            "Specification-Vendor" to properties["mod_authors"],
            "Specification-Version" to "1",
            "Implementation-Title" to project.name,
            "Implementation-Version" to project.version.toString(),
            "Implementation-Vendor" to properties["mod_authors"],
            "Implementation-Timestamp" to SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(Date())
        )
    }

    finalizedBy("reobfJar")
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}

tasks.register("printGitReleaseTitle") {
    doLast {
        println("${properties["mod_file_name"]}-${mcVersion}-(Neo)Forge-${properties["mod_version"]}")
    }
}

tasks.register("printGitTag") {
    doLast {
        println("v${properties["mod_version"]}-mc${mcVersion}-${modLoader.lowercase()}")
    }
}

val changelogFile = file("${project.rootDir}/CHANGELOG.md")

if (!System.getenv("CURSEFORGE_TOKEN").isNullOrBlank() && changelogFile.exists()) {
    tasks.register<TaskPublishCurseForge>("curseforge") {
        apiToken = System.getenv("CURSEFORGE_TOKEN")

        val mainFile = upload("1020415", tasks.jar.get())
        mainFile.releaseType = "release"
        mainFile.addModLoader(modLoader, "NeoForge")
        mainFile.addGameVersion(mcVersion)
        mainFile.addJavaVersion("Java $javaVersion")
        mainFile.addEnvironment("Server", "Client")

        mainFile.changelog = changelogFile
        mainFile.changelogType = "markdown"

        mainFile.addRequirement("cloth-config")
        mainFile.addOptional("catalogue")
    }
}

if (!System.getenv("MODRINTH_TOKEN").isNullOrBlank() && changelogFile.exists()) {
    modrinth {
        token = System.getenv("MODRINTH_TOKEN")
        projectId = "3fmH6nwV"
        versionNumber.set(version.toString())
        uploadFile.set(tasks.jar.get())
        changelog = changelogFile.readText()
        versionName = "${properties["mod_file_name"]} $version"
        gameVersions = listOf(mcVersion)
        loaders = listOf(modLoader.lowercase(), "neoforge")

        dependencies {
            required.version(
                "cloth-config",
                "${libs.versions.cloth.config.get()}+${modLoader.lowercase()}"
            )
        }
    }
}

fun DependencyManagementExtension.providerDeobf(
    dependency: Provider<MinimalExternalModuleDependency>
): Dependency {
    return this.deobf(dependency.get())
}