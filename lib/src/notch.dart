import 'dart:async';
import 'dart:io';
import 'dart:ui';

import 'package:flutter/services.dart';
import 'package:flutter/widgets.dart';

import 'package:device_info/device_info.dart';

class Notch {
  static const MethodChannel _channel =
  MethodChannel('v7lin.github.io/fake_notch');

  static const String _METHOD_HASNOTCH = 'hasNotch';
  static const String _METHOD_GETNOTCHRECTS = 'getNotchRects';

  static Future<bool> hasNotch() async {
    if (Platform.isAndroid) {
      AndroidDeviceInfo androidInfo = await DeviceInfoPlugin().androidInfo;
      if (androidInfo.version.sdkInt == 26 || androidInfo.version.sdkInt == 27) {
        return (await _channel.invokeMethod(_METHOD_HASNOTCH)) as bool;
      }
    }
    MediaQueryData mediaQuery = MediaQueryData.fromWindow(window);
    return mediaQuery.padding.top != null && mediaQuery.padding.top > 0;
  }

  static Future<List<WindowPadding>> getNotchRects() async {
    List<Map<dynamic, dynamic>> notchRects = (await _channel.invokeMethod(_METHOD_GETNOTCHRECTS)) as List<Map<dynamic, dynamic>>;
    if (notchRects != null && notchRects.isNotEmpty) {

    }
    return <WindowPadding>[];
  }
}
