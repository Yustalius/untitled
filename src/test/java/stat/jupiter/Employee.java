package stat.jupiter;

import helpers.utils.jupiter.client.ClientExtension;
import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(EmployeeExtension.class)
public @interface Employee {
  String firstName() default "";

  String lastName() default "";

  String surname() default "";

  String email() default "";

  int sellerTypeId() default 4;

  int sellerGroupId() default -2;
}
