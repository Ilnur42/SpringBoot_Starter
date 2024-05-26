import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.t1.services.LoggingService;

import static org.mockito.Mockito.*;

/**
 * Тестовый класс для проверки функциональности логирования в классе LoggingService.
 */
@SpringBootTest(classes = {LoggingService.class})
class LoggingServiceTest {

    @MockBean
    private Logger logger;

    @Autowired
    private LoggingService loggingService;

    @BeforeEach
    void setUp() {
        loggingService = new LoggingService(logger);
    }

    // Тестирование логирования информации для успешного HTTP ответа.
    @Test
    void successfulResponseTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        response.setStatus(200);
        long processingTime = 100;

        loggingService.log(request, response, processingTime);

        verify(logger, times(1)).info(anyString());
        verify(logger, never()).error(anyString());
    }

    // Тестирование логирования ошибки для ответа клиенту с ошибкой.
    @Test
    void errorForClientErrorResponseTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        response.setStatus(400);
        long processingTime = 100;

        loggingService.log(request, response, processingTime);

        verify(logger, never()).info(anyString());
        verify(logger, times(1)).error(anyString());
    }

    // Тестирование логирования ошибки для ответа сервера с ошибкой.
    @Test
    void errorForServerErrorResponseTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        response.setStatus(500);
        long processingTime = 100;

        loggingService.log(request, response, processingTime);

        verify(logger, never()).info(anyString());
        verify(logger, times(1)).error(anyString());
    }
}