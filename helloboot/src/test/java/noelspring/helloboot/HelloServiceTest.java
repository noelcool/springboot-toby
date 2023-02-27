package noelspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface FastUnitTest {

}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Test
@interface UnitTest {

}
**/

public class HelloServiceTest {

    @Test
    void simpleHelloService() {
        SimpleHelloService simpleHelloService = new SimpleHelloService();
        String ret = simpleHelloService.sayHello("Test");
        Assertions.assertThat(ret).isEqualTo("Hello Test");
    }

    @Test
    void helloDecorator() {
        HelloDecorator decorator = new HelloDecorator(name -> name);
        String ret = decorator.sayHello("Test");
        Assertions.assertThat(ret).isEqualTo("* Test *");
    }
}
