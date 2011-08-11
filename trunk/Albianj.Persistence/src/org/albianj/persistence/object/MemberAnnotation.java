package org.albianj.persistence.object;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.sql.Types;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MemberAnnotation
{
	String Name() default "";
	String FieldName() default "";
	boolean AllowNull() default true;
	int Length() default 200;
	boolean PrimaryKey() default false;
	int DatabaseType() default Types.NVARCHAR;
	boolean IsSave() default true;
}
