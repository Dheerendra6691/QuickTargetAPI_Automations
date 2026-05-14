# QuickTarget API Automation - AI Agent Guide

## Quick Commands

### Build & Test
```bash
# Compile
mvn clean compile

# Run all tests (5 parallel threads)
mvn clean test

# Generate Allure report
mvn allure:report

# Run specific test suite
mvn test -Dgroups=smoke
```

**Test Execution:** Controlled by [testng.xml](testng.xml) with parallel method execution (5 threads).

## Format on Save Configuration

### Java Code Formatting
This project uses **Google Java Style** conventions with Lombok support.

#### VS Code Setup
1. Install extension: **Extension Pack for Java** (Microsoft) or **Language Support for Java (Red Hat)**
2. Install **Spotless Maven Plugin** (see Maven Integration below)
3. Settings in `.vscode/settings.json`:
```json
{
  "java.format.settings.url": "https://raw.githubusercontent.com/google/styleguide/gh-pages/eclipse-java-google-style.xml",
  "[java]": {
    "editor.formatOnSave": true,
    "editor.defaultFormatter": "redhat.java",
    "editor.rulers": [100, 120]
  }
}
```

#### IntelliJ IDEA Setup
1. Go to **Settings → Code Style → Java**
2. Import style: `Scheme → Import Scheme → Eclipse XML Profile`
3. Download: https://raw.githubusercontent.com/google/styleguide/gh-pages/eclipse-java-google-style.xml
4. Enable **Reformat Code on Save** (Settings → Tools → Actions on Save)

### Maven Integration (Optional but Recommended)
Add Spotless plugin to `pom.xml` for automatic formatting on build:
```xml
<plugin>
    <groupId>com.diffplug.spotless</groupId>
    <artifactId>spotless-maven-plugin</artifactId>
    <version>2.43.0</version>
    <configuration>
        <java>
            <googleJavaFormat>
                <version>1.21.0</version>
            </googleJavaFormat>
        </java>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>apply</goal>
            </goals>
            <phase>process-sources</phase>
        </execution>
    </executions>
</plugin>
```

Then run: `mvn spotless:apply` to auto-format all Java files.

## Project Architecture

### Core Packages

| Package | Purpose | Key Patterns |
|---------|---------|--------------|
| `auth/` | Authentication handlers | Strategy pattern for different auth types |
| `builder/` | Request/response builders | Builder pattern for complex HTTP requests |
| `client/` | REST API client wrappers | Wraps Rest Assured with custom logic |
| `config/` | Configuration management | Environment configs, secrets, schemas |
| `constants/` | Constant values | API endpoints, headers, timeouts |
| `context/` | Test state/context | Thread-safe test data sharing |
| `factory/` | Object factories | Creation of models, clients, requests |
| `filters/` | Request/response filters | Logging, auth, request/response interception |
| `models/` | DTOs/POJOs | Request/response data models |
| `utils/` | Utility classes | Helpers for common operations |
| `wrappers/` | Wrapper classes | Additional abstraction over Rest Assured |

### Test Packages

| Package | Purpose |
|---------|---------|
| `tests/smoke/` | Quick smoke tests |
| `tests/contract/` | API contract validation (JSON Schema) |
| `tests/integration/` | End-to-end integration tests |
| `tests/regression/` | Regression test suites |
| `dataproviders/` | Data-driven test providers (@DataProvider) |
| `fixtures/` | Setup/teardown fixtures |
| `hooks/` | TestNG lifecycle hooks (@BeforeMethod, @AfterMethod) |
| `listeners/` | TestNG listeners (reporting, custom logging) |
| `workflows/` | Reusable test workflows and helper methods |
| `validators/` | Custom response validators |

## Key Technologies

| Technology | Version | Usage |
|-----------|---------|-------|
| Java | 17 | Source/target |
| Rest Assured | 5.5.6 | HTTP API testing |
| TestNG | 7.11.0 | Test framework |
| Jackson | 2.20.0 | JSON serialization |
| Lombok | 1.18.42 | Boilerplate reduction |
| Allure | 2.32.0 | Test reporting |
| Log4j2 | 2.25.1 | Logging |
| Apache POI | 5.4.1 | Excel test data |

## Development Conventions

### Naming Conventions
- **Test classes:** `*Test`, `*Tests`, `*Spec`
- **Builder classes:** `*Builder`
- **Factory classes:** `*Factory`
- **Wrapper classes:** `*Wrapper`, `*Client`
- **Constants:** `CONSTANT_NAME`
- **Lombok annotations:** Use `@Getter`, `@Setter`, `@Builder`, `@Data` to reduce boilerplate

### Test Organization
- **Smoke tests:** Fast tests for critical paths (~5-10 seconds)
- **Contract tests:** Validate API response schemas using JSON Schema Validator
- **Integration tests:** Full workflow tests with real/mock backends
- **Regression tests:** Comprehensive test suites

### Lombok Best Practices
- Use `@Data` for DTOs/POJOs (includes @Getter, @Setter, @ToString, @EqualsAndHashCode)
- Use `@Builder` for complex model construction
- Use `@Slf4j` for logging instead of manual Logger declaration
- Exclude sensitive fields from @ToString with `@ToString.Exclude`

## File Locations

| Purpose | Location |
|---------|----------|
| Environment configs | `config/env/` |
| JSON schemas | `config/schema/` |
| Secrets/credentials | `config/secrets/` |
| Test data (CSV) | `test-data/csv/` |
| Test data (Excel) | `test-data/excel/` |
| Test data (JSON) | `test-data/json/` |
| API payloads | `test-data/payloads/` |
| Allure results | `reports/allure-results/` |
| Test logs | `reports/logs/` |

## AI Agent Workflow

### When Creating Tests
1. Place in appropriate `tests/` subdirectory (smoke, contract, integration, regression)
2. Use `@DataProvider` from `dataproviders/` for data-driven tests
3. Leverage `workflows/` for reusable test steps
4. Use custom validators from `validators/`
5. Ensure code passes format-on-save rules (Google Java Style)

### When Implementing Client Logic
1. Create wrapper in `client/` that uses Rest Assured
2. Use `builder/` for complex request construction
3. Apply `filters/` for cross-cutting concerns (logging, auth)
4. Define models in `models/` with Lombok annotations
5. Use `factory/` to instantiate clients

### When Adding Test Data
1. CSV/Excel data → `test-data/csv/` or `test-data/excel/`
2. JSON payloads → `test-data/json/` or `test-data/payloads/`
3. Create data providers in `dataproviders/` to load them
4. Use Apache POI for Excel, Jackson for JSON

## Related Documentation

- **Maven:** See [pom.xml](pom.xml) for dependencies and build configuration
- **Test Configuration:** See [testng.xml](testng.xml) for test suite setup
- **Build Pipeline:** See [Jenkinsfile](Jenkinsfile) for CI/CD configuration

## Tips for AI Agents

✅ **Use Lombok** to reduce boilerplate—@Data, @Builder, @Slf4j are preferred over manual implementations

✅ **Format on save enabled**—All Java files should follow Google Java Style Guide automatically

✅ **Test organization**—Always place tests in the correct subdirectory (smoke/contract/integration/regression)

✅ **Factory pattern**—Use factories to create complex objects consistently

✅ **Custom validators**—Extend validators for API-specific assertions

⚠️ **Parallel execution**—Tests run with 5 parallel threads; ensure no shared state (use ThreadLocal or fixtures)

⚠️ **Secrets management**—Credentials go in `config/secrets/` (ensure .gitignore covers this)

⚠️ **Build before commit**—Always run `mvn clean test` to verify changes
