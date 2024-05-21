package com.item_protection_enchantments.mixins;

import com.item_protection_enchantments.ItemProtectionEnchantments;
import com.item_protection_enchantments.init.ModEnchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {
    @Shadow
    public abstract ItemStack getItem();

    @Shadow
    private int pickupDelay;

    @Shadow
    protected abstract void setUnderwaterMovement();

    @Shadow
    protected abstract void setUnderLavaMovement();

    @Shadow
    protected abstract boolean isMergable();

    @Shadow
    protected abstract void mergeWithNeighbours();

    @Shadow
    private int age;

    @Shadow
    public int lifespan;

    public ItemEntityMixin(EntityType<?> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void protection_enchantments$tick(CallbackInfo ci) {
        ItemEntity itemEntity = (ItemEntity) (Object) this;

        if (getItem().onEntityItemUpdate(itemEntity)) return;
        if (this.getItem().isEmpty()) {
            this.remove();
        } else {
            super.tick();
            if (this.pickupDelay > 0 && this.pickupDelay != 32767) {
                --this.pickupDelay;
            }

            this.xo = this.getX();
            this.yo = this.getY();
            this.zo = this.getZ();
            Vector3d vector3d = this.getDeltaMovement();
            float f = this.getEyeHeight() - 0.11111111F;
            if (this.isInWater() && this.getFluidHeight(FluidTags.WATER) > (double)f) {
                this.setUnderwaterMovement();
            } else if (this.isInLava() && this.getFluidHeight(FluidTags.LAVA) > (double)f) {
                this.setUnderLavaMovement();
            } else if (!this.isNoGravity()) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
            }

            if (this.level.isClientSide) {
                this.noPhysics = false;
            } else {
                this.noPhysics = !this.level.noCollision(this);
                if (this.noPhysics) {
                    this.moveTowardsClosestSpace(this.getX(), (this.getBoundingBox().minY + this.getBoundingBox().maxY) / 2.0D, this.getZ());
                }
            }

            if (!this.onGround || getHorizontalDistanceSqr(this.getDeltaMovement()) > (double)1.0E-5F || (this.tickCount + this.getId()) % 4 == 0) {
                this.move(MoverType.SELF, this.getDeltaMovement());
                float f1 = 0.98F;
                if (this.onGround) {
                    f1 = this.level.getBlockState(new BlockPos(this.getX(), this.getY() - 1.0D, this.getZ())).getSlipperiness(level, new BlockPos(this.getX(), this.getY() - 1.0D, this.getZ()), this) * 0.98F;
                }

                this.setDeltaMovement(this.getDeltaMovement().multiply((double)f1, 0.98D, (double)f1));
                if (this.onGround) {
                    Vector3d vector3d1 = this.getDeltaMovement();
                    if (vector3d1.y < 0.0D) {
                        this.setDeltaMovement(vector3d1.multiply(1.0D, -0.5D, 1.0D));
                    }
                }
            }

            boolean flag = MathHelper.floor(this.xo) != MathHelper.floor(this.getX()) || MathHelper.floor(this.yo) != MathHelper.floor(this.getY()) || MathHelper.floor(this.zo) != MathHelper.floor(this.getZ());
            int i = flag ? 2 : 40;
            if (this.tickCount % i == 0) {
                if (this.level.getFluidState(this.blockPosition()).is(FluidTags.LAVA) && !this.fireImmune()) {
                    if (!ItemProtectionEnchantments.hasEnchantment(this.getItem(), true, ModEnchantments.FIRE_PROTECTION_ITEM.get())) {
                        this.playSound(SoundEvents.GENERIC_BURN, 0.4F, 2.0F + this.random.nextFloat() * 0.4F);
                    }
                }

                if (!this.level.isClientSide && this.isMergable()) {
                    this.mergeWithNeighbours();
                }
            }

            if (this.age != -32768) {
                ++this.age;
            }

            this.hasImpulse |= this.updateInWaterStateAndDoFluidPushing();
            if (!this.level.isClientSide) {
                double d0 = this.getDeltaMovement().subtract(vector3d).lengthSqr();
                if (d0 > 0.01D) {
                    this.hasImpulse = true;
                }
            }

            ItemStack item = this.getItem();
            if (!this.level.isClientSide && this.age >= lifespan) {
                int hook = net.minecraftforge.event.ForgeEventFactory.onItemExpire(itemEntity, item);
                if (hook < 0) this.remove();
                else          this.lifespan += hook;
            }

            if (item.isEmpty()) {
                this.remove();
            }

        }

        ci.cancel();
    }
}
