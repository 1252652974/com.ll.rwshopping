package com.teach.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表名或字段名
 * @author J.Zhou
 *
 */
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Name {

	/**
	 * 表名或字段名
	 * @return
	 */
	String value();
	
	/**
	 * 字段的中文含义
	 * @return
	 */
	String name() default "";
}
