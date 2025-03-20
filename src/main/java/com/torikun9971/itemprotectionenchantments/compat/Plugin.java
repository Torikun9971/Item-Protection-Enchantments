package com.torikun9971.itemprotectionenchantments.compat;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Plugin {
    String[] requireMods();
}
