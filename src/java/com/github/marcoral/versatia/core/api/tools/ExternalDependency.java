package com.github.marcoral.versatia.core.api.tools;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ExternalDependency {
    String value();
}