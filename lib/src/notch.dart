import 'dart:async';
import 'dart:ui';

import 'package:flutter/services.dart';

class Notch {
  static const MethodChannel _channel =
  MethodChannel('v7lin.github.io/fake_notch');

  static const String _METHOD_HASNOTCH = 'hasNotch';
  static const String _METHOD_GETNOTCHRECTS = 'getNotchRects';

  static Future<bool> hasNotch() async {
    return (await _channel.invokeMethod(_METHOD_HASNOTCH)) as bool;
  }

  static Future<List<WindowPadding>> getNotchRects() async {
    List<Map<dynamic, dynamic>> notchRects = (await _channel.invokeMethod(_METHOD_GETNOTCHRECTS)) as List<Map<dynamic, dynamic>>;
    if (notchRects != null && notchRects.isNotEmpty) {

    }
    return <WindowPadding>[];
  }
}
