package noelspring.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

public class ConditionalTest {

    @Test
    void conditional() {
        // true
        ApplicationContextRunner acr = new ApplicationContextRunner();
        acr.withUserConfiguration(Config1.class).run(context -> {
            Assertions.assertThat(context).hasSingleBean(MyBean.class);
            Assertions.assertThat(context).hasSingleBean(Config1.class);
        });

        // false
        ApplicationContextRunner acr2 = new ApplicationContextRunner();
        acr2.withUserConfiguration(Config2.class).run(context -> {
            Assertions.assertThat(context).doesNotHaveBean(MyBean.class);
            Assertions.assertThat(context).doesNotHaveBean(Config1.class);
        });
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional {
        boolean value();
    }

//    @Retention(RetentionPolicy.RUNTIME)
//    @Target(ElementType.TYPE)
//    @Conditional(TrueCondition.class)
//    @interface TrueConditional {}
//
//    @Retention(RetentionPolicy.RUNTIME)
//    @Target(ElementType.TYPE)
//    @Conditional(FalseCondition.class)
//    @interface FalseConditional {}

    @Configuration
    @BooleanConditional(true)
    static class Config1 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    @Configuration
    @BooleanConditional(false)
    static class Config2 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    static class MyBean {
    }

//    static class TrueCondition implements Condition {
//        @Override
//        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//            return true;
//        }
//    }
//
//    static class FalseCondition implements Condition {
//        @Override
//        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//            return false;
//        }
//    }

    static class BooleanCondition implements Condition {
        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            Map<String, Object> allAnnotationAttributes = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
            Boolean value = (Boolean) allAnnotationAttributes.get("value");
            return value;
        }
    }

}
