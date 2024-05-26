# Spring Boot Starter для логирования HTTP запросов

Этот стартер предназначен для логирования входящих и исходящих HTTP запросов с помощью интерсептора.

## Инструкция по запуску

1. Склонируйте проект:

    git clone https://github.com/Ilnur42/SpringBoot_Starter.git

2. Откройте проект в IntelliJ IDEA и пересоберите его с помощью Maven:
   
    - mvn clean install

3. В приложении, куда необходимо добавить стартер, в файл `pom.xml` добавьте следующую зависимость:


```xml
    <dependency>
        <groupId>org.t1</groupId>
        <artifactId>http_logging_starter</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
```

4. Теперь любой HTTP запрос будет сопровождаться логированием в следующем формате:

 ```
    HTTP запрос:
    Метод: GET
    URL: http://host:port/v3/api-docs
    Заголовки запроса: {sec-fetch-mode=cors, referer=http://localhost:8080/api-doc/swagger/swagger-ui/index.html, ...}
    Заголовки ответа: {Keep-Alive=timeout=60, Connection=keep-alive, Content-Length=12408, Date=Sun, 26 May 2024 14:09:48 GMT, Content-Type=application/json, ...}
    Статус: 200
    Время выполнения: 247 мс
 ```
