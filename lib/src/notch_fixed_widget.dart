import 'dart:io';
import 'dart:ui' as ui;

import 'package:flutter/widgets.dart';
import 'package:meta/meta.dart';

import 'package:device_info/device_info.dart';
import 'package:fake_notch/src/notch_fixed_provider.dart';
import 'package:fake_notch/src/notch.dart';

final DeviceInfoPlugin _deviceInfoPlugin = DeviceInfoPlugin();

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

  @override
  Widget build(BuildContext context) {
    if (Platform.isAndroid) {
      return FutureBuilder<AndroidDeviceInfo>(
        future: _deviceInfoPlugin.androidInfo,
        builder:
            (BuildContext context, AsyncSnapshot<AndroidDeviceInfo> snapshot) {
          if (!snapshot.hasData) {
            return splash;
          }
          if (snapshot.data.version.sdkInt == 26 ||
              snapshot.data.version.sdkInt == 27) {
            return FutureBuilder<bool>(
              future: Notch.specialNotch,
              builder: (BuildContext context, AsyncSnapshot<bool> snapshot) {
                if (!snapshot.hasData) {
                  return splash;
                }
                if (snapshot.data) {
                  return FutureBuilder<List<int>>(
                    future: Notch.specialNotchSize,
                    builder: (BuildContext context,
                        AsyncSnapshot<List<int>> snapshot) {
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
              },
            );
          }
          return NotchFixedProvider(
            child: child,
          );
        },
      );
    }
    return NotchFixedProvider(
      child: child,
    );
  }
}
