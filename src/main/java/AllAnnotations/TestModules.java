package AllAnnotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.allEnums.Modules;
import com.allEnums.PlaceOrder;

@Retention(RetentionPolicy.RUNTIME)
public @interface TestModules {
	
	PlaceOrder isPlaceOrderTc()default PlaceOrder.N;
	
	Modules modules()default Modules.test;

}
