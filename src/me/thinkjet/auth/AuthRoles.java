package me.thinkjet.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @ClassName AuthRoles
 * @author johnny_zyc
 * @Modified 2013-4-13 下午1:22:32
 * 
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface AuthRoles {
	final static String ANONYMOUS = "Anonymous";

	String value() default ANONYMOUS;
}
