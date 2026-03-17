# SurePay API Technical

Java-based API test automation project built with Gradle, JUnit 5, and Rest Assured.

The current test flow validates that comment email addresses are correctly formatted for comments made on posts created by the user with username `Delphine`, using the JSONPlaceholder API as the default QA environment.

## Tech Stack

- Java 21
- Gradle
- JUnit 5
- Rest Assured
- AssertJ
- Gson
- Apache Commons Validator

## Prerequisites

- JDK 21 installed

## Project Structure

```text
src
|- main
|  |- java/com/surepay/qa
|  |  |- config        # environment and runtime configuration
|  |  |- core          # shared Rest Assured client setup and utilities
|  |  |- domains       # users, posts, comments clients, models, assertions
|  |  `- support       # shared constants such as API paths
|  `- resources/config # environment-specific properties
`- test
   |- java/com/surepay/qa/tests
   `- resources
```

## Configuration

The active environment is selected with the JVM system property `env`.

Available environments:

- `qa`: uses `https://jsonplaceholder.typicode.com`
- `local`: uses `http://localhost:3000`

Configuration files:

- `src/main/resources/config/application-qa.properties`
- `src/main/resources/config/application-local.properties`

Example:

```bash
./gradlew test -Denv=qa
```

Windows:

```powershell
.\gradlew.bat test -Denv=qa
```

## Running Tests

Run the full test suite:

```bash
./gradlew clean test -Denv=qa
```

Run smoke tests only:

```bash
./gradlew smokeTest -Denv=qa
```

Run regression tests only:

```bash
./gradlew regressionTest -Denv=qa
```

Windows equivalents:

```powershell
.\gradlew.bat clean test -Denv=qa
.\gradlew.bat smokeTest -Denv=qa
.\gradlew.bat regressionTest -Denv=qa
```

## Current Test Scenario

The existing automated scenario performs these steps:

1. Fetch the user with username `Delphine`.
2. Fetch all posts created by that user.
3. Fetch comments for each of those posts.
4. Assert that each comment email has a valid format.

The scenario is tagged with both `smoke` and `regression`.

## Assertions and Framework Design

The framework is organized by domain:

- `UsersClient` retrieves user data and validates expected user selection.
- `PostsClient` retrieves posts and validates title quality and duplicate handling.
- `CommentsClient` retrieves comments for a single post or a list of posts.
- Domain-specific assertion classes keep API validations separate from test flow.

Shared request configuration is defined in `RequestSpecs`, which applies the configured base URL and JSON content type to all requests.

## Test Reporting

Gradle test execution produces:

- JUnit XML reports in `build/test-results`
- HTML reports in `build/reports/tests`

## CI Pipeline

On every push, CircleCI triggers the `test-on-push` workflow defined in `.circleci/config.yml`.

That pipeline builds the project and runs the automated test suite against the `qa` environment with:

```bash
./gradlew clean build smokeTest regressionTest -Denv=qa --no-daemon
```

## Useful Commands

```bash
./gradlew clean build
./gradlew test
./gradlew smokeTest
./gradlew regressionTest
```

## Notes

- JUnit parallel execution is enabled in `src/test/resources/junit-platform.properties`.
- The default environment is `qa` when `-Denv` is not provided.
