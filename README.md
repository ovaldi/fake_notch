# fake_notch

[![Build Status](https://cloud.drone.io/api/badges/v7lin/fake_notch/status.svg)](https://cloud.drone.io/v7lin/fake_notch)
[![Codecov](https://codecov.io/gh/v7lin/fake_notch/branch/master/graph/badge.svg)](https://codecov.io/gh/v7lin/fake_notch)
[![GitHub Tag](https://img.shields.io/github/tag/v7lin/fake_notch.svg)](https://github.com/v7lin/fake_notch/releases)
[![Pub Package](https://img.shields.io/pub/v/fake_notch.svg)](https://pub.dartlang.org/packages/fake_notch)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/v7lin/fake_notch/blob/master/LICENSE)

A simple notch plugin for Flutter. 

## docs

* 华为 [Android O](https://developer.huawei.com/consumer/cn/devservice/doc/50114) [Android P](https://developer.huawei.com/consumer/cn/devservice/doc/50115)
* 小米 [Android O](https://dev.mi.com/console/doc/detail?pId=1293) [Android P](https://dev.mi.com/console/doc/detail?pId=1341)
* Oppo [Android O](https://open.oppomobile.com/wiki/doc#id=10159) [Android p](https://open.oppomobile.com/wiki/doc#id=10293)
* Vivo [Android O](https://dev.vivo.com.cn/documentCenter/doc/103) [Android P](https://dev.vivo.com.cn/documentCenter/doc/145)

## android

````
Android O 特殊刘海屏适配
华为 - 官网适配方案，在华为"DevEco"上的"麦芒7"适配通过
小米 - 官网适配方案，在"Testin"上的"小米 Play"上适配失败，没有其他机型做适配
Oppo - 官网适配方案，在"Testin"上的"OPPO R17"适配通过
Vivo - 官网适配方案，在"Testin"上的"vivo X21A"适配通过
````

## ios

````
````

## flutter

* snapshot

````
dependencies:
  fake_notch:
    git:
      url: https://github.com/v7lin/fake_notch.git
````

* release

````
dependencies:
  fake_notch: ^${latestTag}
````

* example

[示例](./example/lib/main.dart)

## Getting Started

This project is a starting point for a Flutter
[plug-in package](https://flutter.dev/developing-packages/),
a specialized package that includes platform-specific implementation code for
Android and/or iOS.

For help getting started with Flutter, view our 
[online documentation](https://flutter.dev/docs), which offers tutorials, 
samples, guidance on mobile development, and a full API reference.
