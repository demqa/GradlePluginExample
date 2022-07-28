# GradlePluginExample

## Installation

You can use every Gradle project to test this Plugin.

**Add a mavenLocal() to your project dependencies.**

Run `clean build publishToMavenLocal` configuration in <kbd>Intellij IDEA</kbd>.

#### Kotlin DSL

```
plugins {
    id("my.project.group.plugin") version "1.0.0"
}
```

#### Groovy DSL

```
plugins {
    id 'my.project.group.plugin' version '1.0.0'
}
```


## Usage

```
./gradlew my-task
```

### Expected output

```shell
> Task :my-task
coroutine println 1
coroutine logger 1

where this message will be?
and this?

coroutine println 2
coroutine logger 2
```

### Current output

```shell
coroutine println 1
coroutine println 2

> Task :my-task
coroutine logger 1
where this message will be?
and this?
coroutine logger 2
```
