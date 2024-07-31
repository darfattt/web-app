package my.com.mandrill.container.annotation;

import my.com.mandrill.container.PostgresAndKafkaContainerInit;
import org.springframework.test.context.ContextConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ContextConfiguration(initializers = PostgresAndKafkaContainerInit.class)
public @interface EnablePostgresAndKafkaContainer {

}
