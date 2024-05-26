import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.t1.interceptors.LoggingInterceptor;
import org.t1.services.LoggingService;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Тестовый класс для проверки функциональности LoggingInterceptor, интерсептора для логирования HTTP запросов.
 */
@SpringBootTest(classes = {LoggingInterceptor.class})
class LoggingInterceptorTest {

    @Autowired
    private LoggingInterceptor loggingInterceptor;

    @MockBean
    private LoggingService loggingService;


    // Тест проверяет, что метод preHandle правильно устанавливает атрибут startTime в HttpServletRequest. Должен возвращать true и устанавливать атрибут startTime.
    @Test
    void preHandle_ShouldSetStartTimeAttribute() {
        HttpServletRequest request = new MockHttpServletRequest();
        HttpServletResponse response = new MockHttpServletResponse();
        Object handler = new Object();

        boolean result = loggingInterceptor.preHandle(request, response, handler);

        assertTrue(result);
        assertNotNull(request.getAttribute("startTime"));
    }

     // Тест проверяет, что метод afterCompletion вызывает логирование после завершения обработки запроса. Проверяется вызов метода log у LoggingService с корректными аргументами.
    @Test
    void afterCompletion_ShouldLogRequestAndResponse() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.setAttribute("startTime", System.currentTimeMillis() - 100);
        MockHttpServletResponse response = new MockHttpServletResponse();
        Object handler = new Object();
        Exception ex = null;

        loggingInterceptor.afterCompletion(request, response, handler, ex);

        verify(loggingService, times(1)).log(any(HttpServletRequest.class), any(HttpServletResponse.class), anyLong());
    }
}