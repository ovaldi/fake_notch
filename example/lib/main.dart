import 'dart:async';
import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

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
    DeviceOrientation.portraitDown,
    DeviceOrientation.landscapeLeft,
    DeviceOrientation.landscapeRight,
  ]);

  SystemChrome.setSystemUIOverlayStyle(SYSTEM_UI_OVERLAY_STYLE_CUSTOM);

  runZoned(() {
    runApp(MyApp());
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
                  SystemChrome.setEnabledSystemUIOverlays(<SystemUiOverlay>[]);
                } else {
                  SystemChrome.setEnabledSystemUIOverlays(
                      SystemUiOverlay.values);
                }
                _enable = !_enable;
                setState(() {

                });
              },
            ),
            ListTile(
              title: const Text('是否刘海屏'),
              onTap: () async {
                if (await Notch.hasNotch()) {
                  _showTips('是否刘海屏', '是');
                } else {
                  _showTips('是否刘海屏', '否');
                }
              },
            ),
          ],
        ),
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
