package org.t1.services;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.t1.model.LogInfo;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Сервис для логирования HTTP запросов и ответов.
 */
@Service
public class LoggingService {

    private final Logger logger;

    public LoggingService(Logger logger) {
        this.logger = logger;
    }

    public LoggingService() {
        this(LoggerFactory.getLogger(LoggingService.class));
    }

    /**
     * Логирование информации о HTTP запросе и ответе в зависимости от кода.
     */
    public void log(HttpServletRequest request, HttpServletResponse response, long processingTime) {
        LogInfo logInfo = new LogInfo.Builder()
                .withMethod(request.getMethod())
                .withUrl(request.getRequestURI())
                .withRequestHeaders(getHeadersInfo(request))
                .withResponseHeaders(getHeadersInfo(response))
                .withResponseStatus(response.getStatus())
                .withProcessingTime(processingTime).build();
        if (isErrorResponse(logInfo.getResponseStatus())) {
            logger.error(logInfo.toString());
        } else {
            logger.info(logInfo.toString());
        }
    }

    /**
     * Получение заголовков запроса и добавление их в мапу.
     */
    private Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * Получение заголовков ответа и добавление их в мапу.
     */
    private Map<String, String> getHeadersInfo(HttpServletResponse response) {
        Map<String, String> map = new HashMap<>();
        for (String headerName : response.getHeaderNames()) {
            String value = response.getHeader(headerName);
            map.put(headerName, value);
        }
        return map;
    }

    /**
     * Проверка, является ли статус ответа ошибочным.
     */
    private boolean isErrorResponse(int statusCode) {
        return statusCode >= 400 && statusCode < 600;
    }
}
