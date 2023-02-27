package noelspring.config;

import noelspring.config.autoconfig.TomcatWebServerConfig;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.DispatcherServlet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({DispatcherServlet.class, TomcatWebServerConfig.class})
public @interface EnableMyAutoConfiguration {
}
