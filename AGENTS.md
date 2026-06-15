# Agent Instructions: Kuna Project

This file contains guidelines, constraints, and instructions for AI agents working on the Kuna Android project.

## 1. Project Context & Environment
- **Project Name:** Kuna (Croatian commemorative 25 kuna coins collection app)
- **Minimum SDK:** 24
- **Target / Compile SDK:** 36
- **JVM Toolchain:** JDK 17
- **CLI Commands:**
  - Build project: `./gradlew assembleDebug`
  - Run unit tests: `./gradlew test`
  - Format Kotlin code: `./gradlew ktfmtFormat`
  - Check Kotlin formatting: `./gradlew ktfmtCheck`
  - Clean project: `./gradlew clean`
- For general project overview, see the [README.md](README.md).

## 2. Technology Stack & Rules
Always stick to the following tech stack. **Do not introduce alternatives without explicit developer approval.**
- **UI:** Jetpack Compose (using Material 3)
  - **Compose Compiler:** Managed via the `jetbrains-kotlin-compose` plugin (no separate compiler version is needed in `libs.versions.toml`).
- **Dependency Injection:** Koin (BOM-based, configured in [Modules.kt](app/src/main/kotlin/com/digrec/kuna/di/Modules.kt))
  - Do NOT use Hilt, Dagger, or manual constructor injection at the application level.
- **Networking:** Ktor Client (Android engine + Kotlinx Serialization)
  - Do NOT use Retrofit, OkHttp directly, or Volley.
  - Mock networking during testing using Ktor's `MockEngine`.
- **Database:** Room (using KSP for annotation processing)
  - **Room Schemas:** Exported to `app/schemas/`. When modifying the database, ensure schemas are updated and committed for migration tracking.
- **Image Loading:** Coil (using `coil-compose`)
- **Date/Time:** kotlinx-datetime (use `LocalDate`, `Instant`, etc.)
- **Logging:** Timber (use `Timber.d()`, `Timber.e()`, etc.)
- **Serialization:** kotlinx-serialization-json

## 3. Architecture & Directory Structure
The project follows a Clean Architecture structure:
- `/app/src/main/kotlin/com/digrec/kuna/core/`
  - Shared business logic, database components, global repository interfaces, and shared UI styling.
  - Includes `data/`, `domain/`, and `ui/`.
- `/app/src/main/kotlin/com/digrec/kuna/feature/`
  - Grouped by feature (e.g., `kunalist/`, `settings/`).
  - **Self-Contained Features:** If a feature is self-contained, place its respective `data/`, `domain/`, and `ui/` packages directly inside its feature directory.
  - **Shared Features:** If logic or assets are shared across multiple features, place them under `core/`.
  - Each feature defines its navigation routes in `<FeatureName>Navigation.kt`.

### Clean Architecture Rules:
1. **Dependency Direction:** Dependencies must flow inwards: **UI (Presentation) -> Domain <- Data**. The Data layer implements interfaces defined in the Domain layer.
2. **Pure Domain Layer:** The `domain/` package must remain pure Kotlin. Do not import Android SDK libraries, Room annotations, Ktor classes, or UI elements.
3. **No Layer Bypassing:** ViewModels must not directly access database entities, DAOs, or the HttpClient. They must interact only with UseCases or Domain Repositories.
4. **Custom Result Wrapper:** Always use the project's custom Result wrapper (`com.digrec.kuna.core.domain.result.Result`) for domain and data layer operations, rather than `kotlin.Result`.

## 4. UI & State Modeling Preferences
- **UI State Modeling:**
  - Expose screen states from ViewModels as a `sealed interface` with separate subclasses (e.g. `Loading`, `Success`, `Error`) rather than a single large data class.
  - Example structure:
    ```kotlin
    sealed interface KunaListUiState {
        data object Loading : KunaListUiState
        data class Success(val coins: List<Kuna>) : KunaListUiState
        data class Error(val exception: Throwable) : KunaListUiState
    }
    ```
  - Expose state from ViewModels using `StateFlow` (e.g., `state: StateFlow<UiState> = _state.asStateFlow()`).
- **Jetpack Compose:**
  - Screen composables must accept a `UiState` and event lambdas (Stateless pattern).
  - Provide good, basic previews using mock data lists (like `previewKunaList`) or fake implementations.

## 5. Dependency Management
- Never hardcode dependencies in `build.gradle.kts`. Always declare them in [libs.versions.toml](gradle/libs.versions.toml) and reference them via catalog accessors.

## 6. Testing Strategy & Conventions
Agents must automatically write unit tests and maintain high test coverage for any new or edited code.
- **Preferred Mocking Library:** Mockk (`io.mockk`)
- **Coroutine Testing:** Use `kotlinx-coroutines-test` along with `runTest` and `StandardTestDispatcher` (or `UnconfinedTestDispatcher`).
- **Flow Assertions:** Use CashApp's **Turbine** (`app.cash.turbine`) library to assert emitted flow states.
- **Koin Configuration Verification:** Use `koin-test` to write a unit test to verify that Koin modules resolve their dependencies correctly (using `checkModules()` or Koin 4.x's modern `verify()` API). Always maintain a unit test to check dependency bindings (e.g. `ModulesTest`).
- **Android Context in JVM:** Use **Robolectric** if a local unit test requires Android Context or components (e.g., testing Room database DAOs on JVM) to avoid running them on device.
- **Test Placement:** Write JVM unit tests inside the `test` source set (mirroring the package structure of `main`).

## 7. Git & Releases
- This project uses **Release Please** via GitHub Actions to automate versioning and changelogs.
- **Commit Messages:** You must strictly follow Conventional Commits (e.g., `feat(kunalist): add search bar`, `fix(db): resolve migration issue`).

## 8. Agent Behavior Rules & Guardrails
- **Code is Documentation:** Write clean, self-documenting code. Do not write excessive or verbose comments explaining obvious code blocks. Keep comments helpful but highly concise.
- **Transparency & Honesty:** Actively point out any gaps, architectural issues, code smells, or security concerns you notice. Do not hide or try to patch over critical issues.
- **No Assumptions:** If a requirement is ambiguous or not common sense, stop and ask the developer for confirmation.
- **No Unauthorized Changes:** Do not make architectural changes or upgrade compiler/library/SDK versions without a verified technical reason and explicit developer approval.
- **Error Recovery:** If a build fails or tests fail, clearly explain the error, your hypothesis, and your proposed fix before proceeding.
- **Incremental Work:** Work in small, logical steps. Verify each step compiles and passes tests before moving to the next.
- **Code Formatting:** Run `./gradlew ktfmtFormat` to format all Kotlin files and script files before submitting code. Ensure your changes compile and pass `./gradlew ktfmtCheck`.
