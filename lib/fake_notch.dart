import 'dart:async';

import 'package:flutter/services.dart';

class FakeNotch {
  static const MethodChannel _channel =
      const MethodChannel('v7lin.github.io/fake_notch');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }
}
