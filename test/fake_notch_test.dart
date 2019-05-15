import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:fake_notch/fake_notch.dart';

void main() {
  const MethodChannel channel = MethodChannel('fake_notch');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await FakeNotch.platformVersion, '42');
  });
}
