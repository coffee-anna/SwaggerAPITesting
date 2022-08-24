# Globalsqa.com Testing

Java project for petstore.swagger.io website tests execution.

- Java
- Rest-Assured
- TestNG
- Maven
- Allure

## Usage

Clone the repo:
```java
git clone https://github.com/coffee-anna/SwaggerAPITesting
```

Tests execution:
```java
./mvn clean test
```
Get pretty allure report:
```java
allure generate allure-results --clean
allure open
```