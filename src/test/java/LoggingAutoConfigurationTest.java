import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.t1.configs.LoggingAutoConfiguration;
import org.t1.configs.LoggingInterceptorConfig;
import org.t1.interceptors.LoggingInterceptor;
import org.t1.services.LoggingService;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Тест для проверки конфигурации логирования.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = LoggingAutoConfiguration.class)
class LoggingAutoConfigurationTest {

    @Autowired
    private ApplicationContext context;

    //Проверка создания бинов.
    @Test
    void loggingAutoConfiguration_ShouldCreateBeans() {
        assertThat(context.getBean(LoggingInterceptor.class)).isNotNull();
        assertThat(context.getBean(LoggingInterceptorConfig.class)).isNotNull();
        assertThat(context.getBean(LoggingService.class)).isNotNull();
    }
}