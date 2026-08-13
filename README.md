# Schedule App Backend

Schedule App 後端系統，提供行程管理、使用者登入、AI 作業小幫手等 RESTful API。

## 🛠️ 技術

- Java 17
- Spring Boot 4.1.0
- Spring Data JPA
- Spring Security
- MySQL
- Gradle
- LangChain4j
- Ollama
- Qwen 2.5 3B

## 📁 專案架構

```text
ScheduleAppBackEnd
├── src
│   └── main
│       ├── java
│       │   └── com.example.scheduleApp
│       │       ├── controller
│       │       ├── entity
│       │       ├── repository
│       │       ├── service
│       │       └── request
│       │
│       └── resources
│           └── application.properties
│
├── build.gradle
└── settings.gradle
