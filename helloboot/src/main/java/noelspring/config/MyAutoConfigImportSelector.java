package noelspring.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
                "noelspring.config.autoconfig.DispatcherServletConfig",
                "noelspring.config.autoconfig.TomcatWebServerConfig"
        };
    }

}
