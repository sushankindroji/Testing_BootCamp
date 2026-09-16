# CommonFloor Automation — Capstone Project

**Application Under Test:** CommonFloor Real Estate Property Portal  
**URL:** https://www.commonfloor.com/  
**Author:** Sushank Indroji  
**Project Type:** College / Training Capstone — QA Automation Demo

---

## Testing Areas Demonstrated

| Tool / Area | Description |
|---|---|
| **Manual Testing** | Test cases, RTM, defect reports documented |
| **Selenium WebDriver** | Browser automation using Microsoft Edge |
| **Cucumber BDD** | Feature file + step definitions + JUnit runner |
| **TestNG** | Test framework with assertions |
| **Maven** | Build tool for compilation and test execution |
| **REST Assured** | API automation (GET, POST — JSONPlaceholder) |
| **Postman + Newman** | API collection execution with HTML report |
| **Apache JMeter** | Performance testing — 50 samples, 0 errors |
| **JDBC + MySQL** | Database testing — selenium_automation DB |
| **Allure Reports** | Test reporting and evidence generation |
| **Git / GitHub** | Version control and project hosting |

---

## Selenium Execution Result

```
Tests run: 5
Failures:  0
Errors:    0
Skipped:   0
BUILD SUCCESS
```

## Cucumber Execution Result

```
Scenario: Verify CommonFloor home page loads successfully
  ✔ Given I open the CommonFloor website
  ✔ Then the page title should not be empty
  ✔ And the URL should contain "commonfloor"

Tests run: 1, Failures: 0
BUILD SUCCESS
```

## REST Assured Execution Result

```
✅ GET  /users  — Status: 200 | Users: 10
✅ GET  /posts/1 — Status: 200 | Title present
✅ POST /posts  — Status: 201 | ID: 101

Total tests run: 3, Passes: 3, Failures: 0
```

## JMeter Execution Result

```
Samples: 50 | Errors: 0 (0.00%) | Avg: 9ms | Throughput: 10.9/s
```

## JDBC Execution Result

```
Database  : selenium_automation
Table     : book_detl
Query     : SELECT * FROM selenium_automation.book_detl
Rows found: 9

Total tests run: 2, Passes: 2, Failures: 0
```

---

## Project Structure

```
CommonFloorAutomation/
├── pom.xml                          Maven build file
├── testng.xml                       TestNG suite (Selenium)
├── testng-api.xml                   TestNG suite (REST Assured)
├── testng-db.xml                    TestNG suite (JDBC)
├── README.md
│
├── src/
│   ├── main/java/com/commonfloor/automation/
│   │   ├── BaseTest.java            Browser setup/teardown
│   │   ├── HomePage.java            Page Object — Home Page
│   │   └── SearchPage.java          Page Object — Search Results
│   │
│   └── test/java/com/commonfloor/
│       ├── automation/
│       │   ├── HomePageTest.java     TC_03 — Home Page (3 tests)
│       │   ├── PropertySearchTest.java  TC_04 — Search (2 tests)
│       │   └── RestAssuredTest.java  API tests (3 tests)
│       ├── cucumber/
│       │   ├── CucumberRunner.java   JUnit Cucumber runner
│       │   └── StepDefinitions.java  Gherkin step implementations
│       └── jdbc/
│           └── DatabaseTest.java     MySQL JDBC tests (2 tests)
│
├── src/test/resources/
│   ├── features/
│   │   └── CommonFloor.feature      Cucumber BDD feature file
│   └── config.properties
│
├── postman/
│   ├── CBIT-API Testing.postman_collection.json
│   └── Newman-HTML-Report.html      Newman execution report
│
├── jmeter/
│   ├── HTTP Request.jmx             JMeter test plan
│   ├── results.jtl                  JMeter raw results
│   └── html-report/                 JMeter HTML dashboard
│
├── screenshots/                     Auto-captured during Selenium runs
└── allure-results/                  Allure test evidence
```

---

## How to Run

### Selenium Tests
```bash
mvn clean test -Dtest=CucumberRunner
mvn clean test -Dtest=HomePageTest
mvn clean test -Dtest=PropertySearchTest
```

### Cucumber BDD
```bash
mvn clean test -Dtest=CucumberRunner
```

### REST Assured API Tests
```bash
mvn clean test-compile -q
java -cp "target/test-classes:target/classes:$(mvn dependency:build-classpath -q -Dmdep.outputFile=/dev/stdout 2>/dev/null)" org.testng.TestNG testng-api.xml
```

### JDBC Database Tests
```bash
java -cp "target/test-classes:target/classes:$(mvn dependency:build-classpath -q -Dmdep.outputFile=/dev/stdout 2>/dev/null)" org.testng.TestNG testng-db.xml
```

### JMeter Performance Test
```bash
cd jmeter
jmeter -n -t "HTTP Request.jmx" -l results.jtl -e -o html-report
open html-report/index.html
```

### Postman / Newman
```bash
cd postman
newman run "CBIT-API Testing.postman_collection.json" --reporters cli,html --reporter-html-export Newman-HTML-Report.html
```

### Allure Report
```bash
mvn allure:report
open target/site/allure-maven-plugin/index.html
```

---

## Technologies

- Java 17
- Selenium WebDriver 4.13.0
- TestNG 7.7.1
- Cucumber 7.34.3 + JUnit 4.13.2
- REST Assured 5.3.2
- Allure 2.20.1
- Apache JMeter 5.6.3
- MySQL 8.x + JDBC Connector 8.3.0
- Postman + Newman 6.2.2
- Maven 3.x
- Microsoft Edge browser
