package AllAnnotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FooterDetails {
	
	String isFooterDetail() default "";

}
