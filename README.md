# InMemory API Mock MVC Test

This project demonstrates testing for a simple in-memory data store API using JUnit 5, Mockito, and Spring's `MockMvc`.

## Project Components

- **Controller**: `PresentationController` handles HTTP requests for presentations and slides.
- **Service Layer**: `InMemoryDataStoreImpl` manages presentations and slides in memory.
- **Repository Interface**: `InMemoryDataStore` defines CRUD operations.
- **Tests**: Includes unit tests for the service layer and integration tests for the controller endpoints.


## Test Structure

### Controller Tests

**`PresentationControllerTest`**: This class tests the HTTP endpoints of the `PresentationController` using `MockMvc`. It covers operations for:

- Creating presentations
- Retrieving presentations
- Updating presentations
- Deleting presentations

### Service Layer Tests

**`InmemoryApiMockMvcTestApplicationTests`**: This class contains unit tests for `InMemoryDataStoreImpl`. It verifies the service methods with Mockito, covering:

- Adding presentations
- Retrieving presentations
- Updating presentations
- Deleting presentations

## Running Application and Tests

### Prerequisites

- JDK 11 or higher
- Maven or Gradle

### Maven
To clean and compile the project, then run tests:

```sh 
mvn clean install

mvn clean test
```
### Gradle
```sh 
./gradlew clean build
./gradlew test

