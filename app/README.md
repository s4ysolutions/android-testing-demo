# Demo repository for Android testing

## Subjects to test

### 3rd party services

The lowest level of the application are the services provided by 3rd parties. The are not to be
tested, but they still relate to the testing because they have to be mocked in many cases.

In this repo the role of such service plays `Numbers provider` located in the path
[app/src/main/java/com/example/androidtestsdemo/backends](app/src/main/java/com/example/androidtestsdemo/backends)

### Android API independent codebase

Some services do not require to call Android API and thus can be tested without instrumentation as
plain java code with unit or integration tests.

`Table Generator` [app/src/main/java/com/example/androidtestsdemo/application/core](app/src/main/java/com/example/androidtestsdemo/application/core)
which uses `Numbers provide` is a sample of this.

### Code that uses Android API

The application code in rare cases can avoid using Android frameworks and because of this has to be
tested by integration tests. `Store` providing input data for `Table generator`
[app/src/main/java/com/example/androidtestsdemo/application/android](app/src/main/java/com/example/androidtestsdemo/application/android)
as almost any android service requires `Context` and thus it is good sample for Robolectric tests.

### UI elements
User interface represented by `MainActivity` [app/src/main/java/com/example/androidtestsdemo/MainActivity.kt](app/src/main/java/com/example/androidtestsdemo/MainActivity.kt)
should shows the data received from the `Table Generator`and ends up the list of the components
under the tests.

## Tests

### Local unit test

#### Testing the implementation of `ITableGenerator`

[app/src/test/java/com/example/androidtestsdemo/unit/core/DefaultTableGeneratorUnitTest.kt](app/src/test/java/com/example/androidtestsdemo/unit/core/DefaultTableGeneratorUnitTest.kt)

This local test demonstrates use of JUnit4 and [Mockk](https://mockk.io) frameworks.

The rule to restore the mocks after each tests:

```kotlin
    @get:Rule
val mockkRule = MockKRule(this) 
```

a Mockk spy wrapped around `DefaultNumbersProvider` instance playing the role of 3rd party service:

```kotlin
private val spyNumbersProvider: INumbersProvider = spyk(DefaultNumbersProvider());
```

replacing its methods to return fixtures

```kotlin
every { spyNumbersProvider.numbers(3) } returns listOf(1, 2, 3)
    ...
every { spyNumbersProvider.numbers(any()) } answers {
    val qty = firstArg<Int>()
    generateSequence(1) {it +1  }.take(qty).toList()
}
```

within Junit4 test functions

```kotlin
@Test
fun tableGenerator_works() {
...
@Test
fun tableGenerator_works() {
...
```

and asserting the tested service `DefaultTableGenerator` handles the fixture as expected with
[Google Truth](https://truth.dev) assertions.

```kotlin
val tableGenerator = DefaultTableGenerator(spyNumbersProvider)
val table = tableGenerator.rows(3)
assertThat(table[0].first).isEqualTo(1);
assertThat(table[2].second).isEqualTo(6);
```

### Local instrumented test

[Robolectric](https://robolectric.org) framework allows to instrument local testing environment with
Android API mockups.

#### Testign the implementation of [IStore]
[app/src/test/java/com/example/androidtestsdemo/unit/android/DefaultStoreTest.kt](app/src/test/java/com/example/androidtestsdemo/unit/android/DefaultStoreTest.kt)
uses the Android Shared preferences and because of that requires the context instance. 

```groovy
dependencies {
    testImplementation 'org.robolectric:robolectric:4.9'
...
```

```kotlin
@RunWith(RobolectricTestRunner::class)
class DefaultStoreTest {
...
```

```kotlin
@Test
fun defaultStore_restoresValue() {
    val app = ApplicationProvider.getApplicationContext<TheApplication>()
    val store = DefaultStore(app)

```

#### Local UI/integration tests

Robolectric has (limited) capabilities to instantiate activities and layouts useful for [Espresso 
testing framework](https://developer.android.com/training/testing/espresso) coupled with androidx
test framework.

```kotlin
@RunWith(RobolectricTestRunner::class)
class MainActivityTest {
...
    @Test
    fun textView_shouldReflectTheLengtOfNumbers() {
        every { spyNumbersProvider.numbers(any()) } returns listOf(1, 2, 3)

        launchActivity<MainActivity>().use { scenario ->
            assertThat(scenario.state).isEqualTo(Lifecycle.State.RESUMED)
            Espresso.onView(ViewMatchers.withId(R.id.text_view))
                .check(ViewAssertions.matches(ViewMatchers.withText("3")))
```

### Instrumented with Android UI/integration test

Android gradle plugin adds the ability to run tests on the real devices/emulators

```kotlin
class NoMocksUITest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun storeLengthDefault_shouldBe_10() {
        onView(withId(R.id.text_view)).check(matches(withText("10")))
    }
}
```

## Mocking

To inject the mocked instances into the application for integration tests is used
[Dagger 2](https://dagger.dev) DI framework.

The original modules are replaced with the [mocked cousins](app/src/test/java/com/example/androidtestsdemo/mocks/dagger)
having the every object wrapped by Mockk `spyk`.

```kotlin
@Provides
fun provideTableProvider(numbersProvider: INumbersProvider): ITableGenerator {
    return spyk(DefaultTableGenerator(numbersProvider))
}

```
and the production Dagger component extended by additional `inject`s to be used within the tests
and decorated with mocked modules:

 ```kotlin
@Component(modules = [BackendsModule::class, CoreModule::class, AndroidModule::class])
interface MockkApplicationComponent: TheApplicationComponent {
    ...
    fun inject(test: MainActivityTest)
    ...
```
and the production Application class is extended in order to override the method creating
the Dagger component:

```kotlin
class MockkApplication: TheApplication() {
    override fun initializeComponent(): TheApplicationComponent {
        return DaggerMockkApplicationComponent.factory().create(spyk(this))
    }
}
```

Next the Robolectric instructed to use this application class via annotiation

```kotlin
@Config(application = MockkApplication::class)
@RunWith(RobolectricTestRunner::class)
class DaggerRoboelectricTest {
...
```

and the Android Junit runner to use theMockkApplication via additional
[gradle configuration](app/build.gradle):

```kotlin
android {
    defaultConfig {
        ... 
        testInstrumentationRunner "com.example.androidtestsdemo.mocks.MockkTestRunner"

```

where the `MockkTestRunner` is pretty trivial class extending default `AndroidJUnitRunner`

```kotlin
class MockkTestRunner : AndroidJUnitRunner() {

    override fun newApplication(cl: ClassLoader?, name: String?, context: Context?): Application {
        return super.newApplication(cl, MockkApplication::class.java.name, context)
    }
}
```