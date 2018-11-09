# SNOMED Browser
> SNOMED CT Browser Android Client.

[![Build Status][travis-image]][travis-url]


This is a simple Android Client for [SNOMED International SNOMED CT Browser Site](http://browser.ihtsdotools.org/?) based on Java and Python examples from [SNOMED-in-5-minutes](https://github.com/IHTSDO/SNOMED-in-5-minutes) repository


## Building the Project

### Android Studio:

Just open the project directory `android-client-snomed-browser` with Android Studio and it will automatically start building it.

## What It Does

You can search concepts and descriptions by ID or by Term and see details like preferred term, synonyms and parent/children relationships.

## Dependencies

### Retrofit 2
Type-safe HTTP client for Android and Java by Square, Inc. [see GitHub Project Link](https://github.com/square/retrofit)

### RxJava 2 with Retrofit Adapter
RxJava is a Java VM implementation of Reactive Extensions: a library for composing asynchronous and event-based programs by using observable sequences.
[GitHub Project Link](https://github.com/ReactiveX/RxJava)

### Butterknife

Field and method binding for Android views which uses annotation processing to generate boilerplate code for you. [GitHub Project Link](https://github.com/JakeWharton/butterknife)

### UI Dependencies

#### BottomNavigationViewEx

An android lib for enhancing BottomNavigationView. [GitHub Project Link](https://github.com/ittianyu/BottomNavigationViewEx)

### Other

All dependencies are stated in the `gradle.build` file.


## Release History

* 0.1.0
    * The first proper release

<!-- Markdown link & img dfn's -->

[travis-image]: https://img.shields.io/travis/dbader/node-datadog-metrics/master.svg?style=flat-square
[travis-url]: https://travis-ci.org/dbader/node-datadog-metrics
