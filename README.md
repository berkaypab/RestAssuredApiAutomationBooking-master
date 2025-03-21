# Rest Assured API Automation Framework

This project is an API test automation framework developed using Rest Assured. It contains end-to-end test scenarios for the Booking API.

## 🚀 Technologies

- Java 1.8
- Rest Assured
- TestNG
- Maven
- Allure Reports
- Extent Reports
- Lombok
- Jackson
- GitHub Actions (CI/CD)

## 📋 Features

- **Parallel Test Execution**: TestNG parallel execution support
- **Multiple Reporting**: Allure and Extent Reports integration
- **Environment Support**: Configuration management for different environments
- **Page Object Model**: POM structure for API endpoints
- **Data Driven Testing**: Dynamic test data generation with Faker
- **CI/CD**: Automated test execution with GitHub Actions

## 🛠️ Installation

```bash
# Clone the project
git clone https://github.com/yourusername/RestAssuredApiAutomationBooking.git

# Navigate to project directory
cd RestAssuredApiAutomationBooking

# Install Maven dependencies
mvn clean install
```

## 📊 Test Reporting

### Allure Reports
```bash
# Generate Allure report
mvn allure:report

# View Allure report
mvn allure:serve
```

### Extent Reports
- Test results are generated in the `extent-test-output` directory
- Provides detailed reports in HTML format

## 🔄 Parallel Test Execution

Tests can be executed in parallel using TestNG's parallel execution feature:

```xml
<!-- testng.xml -->
<suite name="Booking API Test Suite" parallel="methods" thread-count="3">
    <test name="Booking Tests">
        <classes>
            <class name="com.booking.tests.BookingTest"/>
        </classes>
    </test>
</suite>
```

## 🌍 Environment Support

Configuration management for different environments:

```properties
# config.properties
base.url=https://restful-booker.herokuapp.com
```

## 📝 Tested Endpoints

### 1. Create Booking
- **Endpoint**: POST /booking
- **Test Scenarios**:
  - Successful booking creation
  - Booking creation with invalid data
  - Booking creation with missing fields

### 2. Get Booking
- **Endpoint**: GET /booking/{id}
- **Test Scenarios**:
  - Get booking by ID
  - Get booking with invalid ID
  - Get non-existent booking

### 3. Get All Bookings
- **Endpoint**: GET /booking
- **Test Scenarios**:
  - List all bookings
  - Filter bookings

### 4. Update Booking
- **Endpoint**: PUT /booking/{id}
- **Test Scenarios**:
  - Update booking
  - Update with invalid data

### 5. Partial Update Booking
- **Endpoint**: PATCH /booking/{id}
- **Test Scenarios**:
  - Partial booking update
  - Partial update with invalid data

### 6. Delete Booking
- **Endpoint**: DELETE /booking/{id}
- **Test Scenarios**:
  - Delete booking
  - Attempt to get deleted booking

## 🚀 CI/CD

Automated test execution with GitHub Actions:

- Tests run on every push and pull request
- Test results and reports are automatically saved as artifacts

## 📁 Project Structure

```
src
├── main
│   ├── java
│   │   └── com
│   │       └── booking
│   │           ├── api
│   │           ├── constants
│   │           ├── requests
│   │           └── utils
│   └── resources
│       └── config.properties
└── test
    ├── java
    │   └── com
    │       └── booking
    │           └── tests
    └── resources
        └── testng.xml
```

## 🔧 Configuration

### TestNG Configuration
```xml
<suite name="Booking API Test Suite">
    <test name="Booking Tests">
        <classes>
            <class name="com.booking.tests.BookingTest"/>
        </classes>
    </test>
</suite>
```

## 📈 Test Reports

### Allure Reports
- Detailed test steps
- Test durations
- Screenshots
- Test categories

### Extent Reports
- Test summary
- Success/failure statistics
- Detailed error messages
- Test graphs

