<h1 align="center"> MarvelCompose </h1> <br>

<p align="center">
  Marvel + Compose.
</p>

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Feedback](#feedback)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Introduction

This is a sample repo to play with modern Android technologies.

Note that the app is so simple right now, its a playground to learn `Jetpack Compose` and other libs that I wanted to learn (`ktor`, `room`, `Hilt` etc).

## Features

The project contains following features:

* Marvel api usage [Marvel API](https://developer.marvel.com/)
* Characters list (both vertical list and vertical grid)
* Character Detail
* DI with [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
* UI with [Jetpack Compose](https://developer.android.com/jetpack/compose/documentation)
* Network handled with [Ktor](https://github.com/ktorio/ktor)
* Image handling with [Coil](https://github.com/coil-kt/coil)

## TODO

* Add pagination for characters list
* Modularize project in different modules (domain, data, features, etc)
* Characters Series list + Detail
* Characters Stories list + Detail
* Characters Comics list + Detail
* Github Actions to apply ktlint and others static analysis tools
* UI Testing
* Error handling (error views, modals, retry mechanism)

## Setup

**Development Keys**: You must add `marvelPrivateKey` and `marvelPublicKey` as `gradle project properties` in order the app to work.

## License

```
Copyright 2021 David Sastre (Sekthdroid)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.

You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```