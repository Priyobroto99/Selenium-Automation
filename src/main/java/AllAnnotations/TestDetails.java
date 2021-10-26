package AllAnnotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.allEnums.DriverType;
import com.allEnums.TestGroup;

@Retention(RetentionPolicy.RUNTIME)
public @interface TestDetails 
{
	String TestCaseID() default "";
	TestGroup testgroup() default TestGroup.Functional;	
}
