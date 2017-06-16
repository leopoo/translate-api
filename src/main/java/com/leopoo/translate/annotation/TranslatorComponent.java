package com.leopoo.translate.annotation;

import com.leopoo.translate.enums.Trans;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface TranslatorComponent {
    public Trans id();
}
