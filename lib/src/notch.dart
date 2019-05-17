import 'dart:async';
import 'dart:io';

import 'package:flutter/services.dart';

class Notch {
  static const MethodChannel _channel =
      MethodChannel('v7lin.github.io/fake_notch');

  static const String _METHOD_HASSPECIALNOTCH = 'hasSpecialNotch';
  static const String _METHOD_GETSPECIALNOTCHSIZE = 'getSpecialNotchSize';


  static Future<bool> hasSpecialNotch() async {
    if (Platform.isAndroid) {
      return (await _channel.invokeMethod(_METHOD_HASSPECIALNOTCH)) as bool;
    }
    return false;
  }

  static Future<List<int>> getSpecialNotchSize() async {
    if (Platform.isAndroid) {
      return (await _channel.invokeMethod(_METHOD_GETSPECIALNOTCHSIZE))
          as List<int>;
    }
    return <int>[0, 0];
  }
}
