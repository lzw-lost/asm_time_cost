package com.upuphone.durationanotation;

/**
 * <p>
 * <p>
 * author : lzw
 * date   : 2023-1-12 10:17
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface Duration {
    String tag();

    String endClassName() default "";

    String endMethodName() default "";
}
