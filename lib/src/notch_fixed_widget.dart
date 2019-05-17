import 'dart:io';
import 'dart:ui' as ui;

import 'package:flutter/widgets.dart';
import 'package:meta/meta.dart';

import 'package:device_info/device_info.dart';
import 'package:fake_notch/src/notch_fixed_provider.dart';
import 'package:fake_notch/src/notch.dart';

class NotchFixedWidget extends StatelessWidget {
  const NotchFixedWidget({
    Key key,
    @required this.splash,
    @required this.child,
  })  : assert(splash != null),
        assert(child != null),
        super(key: key);

  final Widget splash;
  final Widget child;

  Future<List<int>> _androidNotchFixed() async {
    AndroidDeviceInfo deviceInfo = await DeviceInfoPlugin().androidInfo;
    if (deviceInfo.version.sdkInt == 26 || deviceInfo.version.sdkInt == 27) {
      if (await Notch.hasSpecialNotch()) {
        return await Notch.getSpecialNotchSize();
      }
    }
    return <int>[0, 0];
  }

  @override
  Widget build(BuildContext context) {
    if (Platform.isAndroid) {
      return FutureBuilder<List<int>>(
        future: _androidNotchFixed(),
        builder: (BuildContext context, AsyncSnapshot<List<int>> snapshot) {
          if (!snapshot.hasData) {
            return splash;
          }
          return NotchFixedProvider(
            child: child,
            width: snapshot.data[0] / ui.window.devicePixelRatio,
            height: snapshot.data[1] / ui.window.devicePixelRatio,
          );
        },
      );
    }
    return NotchFixedProvider(
      child: child,
    );
  }
}
