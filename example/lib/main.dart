import 'dart:async';
import 'dart:io';
import 'dart:math' as math;

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

import 'package:device_info/device_info.dart';
import 'package:fake_notch/fake_notch.dart';

// ignore: non_constant_identifier_names
final SystemUiOverlayStyle SYSTEM_UI_OVERLAY_STYLE_CUSTOM =
    SystemUiOverlayStyle.light.copyWith(
  systemNavigationBarColor: Colors.transparent,
  systemNavigationBarIconBrightness: Brightness.dark,
  statusBarColor: Colors.transparent,
  statusBarIconBrightness: Brightness.light,
  statusBarBrightness: Brightness.light,
);

void main() {
  SystemChrome.setPreferredOrientations(<DeviceOrientation>[
    DeviceOrientation.portraitUp,
//    DeviceOrientation.portraitDown, // iOS 没有此功能
    DeviceOrientation.landscapeLeft,
    DeviceOrientation.landscapeRight,
  ]);

  SystemChrome.setSystemUIOverlayStyle(SYSTEM_UI_OVERLAY_STYLE_CUSTOM);

  runZoned(() {
    runApp(NotchFixedWidget(
      splash: Container(
        color: Colors.blue,
      ),
      child: MyApp(),
    ));
  }, onError: (Object error, StackTrace stack) {
    print(error);
    print(stack);
  });
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData.light().copyWith(
        primaryColor: Colors.white,
        primaryColorBrightness: Brightness.light,
        primaryColorLight: Colors.white,
        primaryColorDark: Colors.white,
        primaryTextTheme: ThemeData.light().primaryTextTheme.copyWith(
              title: ThemeData.light().primaryTextTheme.title.copyWith(
                    color: Colors.black,
                  ),
            ),
      ),
      home: Home(),
    );
  }
}

class Home extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _HomeState();
  }
}

class _HomeState extends State<Home> {
  bool _enable = true;

  @override
  Widget build(BuildContext context) {
    return AnnotatedRegion<SystemUiOverlayStyle>(
      child: OrientationBuilder(
        builder: (BuildContext context, Orientation orientation) {
          MediaQueryData mediaQuery = MediaQuery.of(context);
          EdgeInsets padding = mediaQuery.padding;
          return MediaQuery(
            data: mediaQuery.copyWith(
              padding: orientation == Orientation.portrait
                  ? padding.copyWith(
                      top: math.max(
                          padding.top, NotchFixedProvider.of(context).height),
                    )
                  : MediaQuery.of(context).padding.copyWith(
                        left: math.max(padding.left,
                            NotchFixedProvider.of(context).height),
                        right: math.max(padding.right,
                            NotchFixedProvider.of(context).height),
                      ),
            ),
            child: Scaffold(
              appBar: AppBar(
                title: const Text('Fake Notch Demo'),
              ),
              body: ListView(
                children: <Widget>[
                  ListTile(
                    title: Text(_enable ? '进入全屏' : '退出全屏'),
                    onTap: () {
                      if (_enable) {
                        SystemChrome.setEnabledSystemUIOverlays(
                            <SystemUiOverlay>[]);
                      } else {
                        SystemChrome.setEnabledSystemUIOverlays(
                            SystemUiOverlay.values);
                      }
                      _enable = !_enable;
                      setState(() {});
                    },
                  ),
                  ListTile(
                    title: const Text('notch fixed size'),
                    onTap: () {
                      _showTips('提示',
                          'notch fixed size: ${NotchFixedProvider.of(context).width} - ${NotchFixedProvider.of(context).height}');
                    },
                  ),
                  ListTile(
                    title: const Text('device info'),
                    onTap: () async {
                      if (Platform.isAndroid) {
                        AndroidDeviceInfo deviceInfo =
                            await DeviceInfoPlugin().androidInfo;
                        _showTips('提示',
                            'manufacturer: ${deviceInfo.manufacturer}; model: ${deviceInfo.model}; product:${deviceInfo.product}');
                      } else if (Platform.isIOS) {
                        IosDeviceInfo deviceInfo =
                            await DeviceInfoPlugin().iosInfo;
                        _showTips('提示', 'name: ${deviceInfo.name}');
                      } else {
                        _showTips('提示',
                            '${Platform.operatingSystem} - ${Platform.operatingSystemVersion}');
                      }
                    },
                  )
                ],
              ),
              bottomNavigationBar: Theme(
                data: Theme.of(context).copyWith(primaryColor: Colors.blue),
                child: BottomNavigationBar(
                  items: <BottomNavigationBarItem>[
                    const BottomNavigationBarItem(
                      icon: Icon(Icons.home),
                      title: Text('Home'),
                    ),
                    const BottomNavigationBarItem(
                      icon: Icon(Icons.contacts),
                      title: Text('Contacts'),
                    ),
                    const BottomNavigationBarItem(
                      icon: Icon(Icons.group),
                      title: Text('Group'),
                    ),
                  ],
                ),
              ),
            ),
          );
        },
      ),
      value: SYSTEM_UI_OVERLAY_STYLE_CUSTOM,
    );
  }

  void _showTips(String title, String content) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: Text(title),
          content: Text(content),
        );
      },
    );
  }
}
