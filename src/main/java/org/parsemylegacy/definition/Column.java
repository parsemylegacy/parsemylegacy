package org.parsemylegacy.definition;

import org.parsemylegacy.utils.TrimDirection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Column {

    int from();

    int to();

    boolean trim() default true;

    char trimCharacter() default ' ';

    TrimDirection trimDirection() default TrimDirection.RIGHT;

}
