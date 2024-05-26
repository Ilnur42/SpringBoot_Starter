package org.t1.model;

import java.util.Map;

public class LogInfo {

    private String method;

    private String url;

    private Map<String, String> requestHeaders;

    private Map<String, String> responseHeaders;

    private int responseStatus;

    private long processingTime;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public void setRequestHeaders(Map<String, String> requestHeaders) {
        this.requestHeaders = requestHeaders;
    }

    public Map<String, String> getResponseHeaders() {
        return responseHeaders;
    }

    public void setResponseHeaders(Map<String, String> responseHeaders) {
        this.responseHeaders = responseHeaders;
    }

    public int getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(int responseStatus) {
        this.responseStatus = responseStatus;
    }

    public long getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(long processingTime) {
        this.processingTime = processingTime;
    }

    private LogInfo(LogInfo.Builder builder) {
        setMethod(builder.method);
        setUrl(builder.url);
        setRequestHeaders(builder.requestHeaders);
        setResponseHeaders(builder.responseHeaders);
        setResponseStatus(builder.responseStatus);
        setProcessingTime(builder.processingTime);
    }

    public static final class Builder {
        private String method;

        private String url;

        private Map<String, String> requestHeaders;

        private Map<String, String> responseHeaders;

        private int responseStatus;

        private long processingTime;

        public Builder() {
        }

        public LogInfo.Builder withMethod(String val) {
            method = val;
            return this;
        }

        public LogInfo.Builder withUrl(String val) {
            url = val;
            return this;
        }

        public LogInfo.Builder withRequestHeaders(Map<String, String> val) {
            requestHeaders = val;
            return this;
        }

        public LogInfo.Builder withResponseHeaders(Map<String, String> val) {
            responseHeaders = val;
            return this;
        }

        public LogInfo.Builder withResponseStatus(int val) {
            responseStatus = val;
            return this;
        }

        public LogInfo.Builder withProcessingTime(long val) {
            processingTime = val;
            return this;
        }

        public LogInfo build() {
            return new LogInfo(this);
        }
    }

    @Override
    public String toString() {
        return "\n" +
                "Http запрос" + "\n" +
                "Метод: " + method + "\n" +
                "Url: " + url + "\n" +
                "Request Headers: " + requestHeaders + "\n" +
                "Response Headers: " + responseHeaders + "\n" +
                "Статус: " + responseStatus + "\n" +
                "Время выполнения: " + processingTime + " мс" + "\n";
    }
}
