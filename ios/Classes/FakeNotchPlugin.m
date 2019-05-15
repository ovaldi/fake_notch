#import "FakeNotchPlugin.h"

@implementation FakeNotchPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
    FlutterMethodChannel* channel = [FlutterMethodChannel
                                     methodChannelWithName:@"v7lin.github.io/fake_notch"
                                     binaryMessenger:[registrar messenger]];
    FakeNotchPlugin* instance = [[FakeNotchPlugin alloc] init];
    [registrar addMethodCallDelegate:instance channel:channel];
}

- (void)handleMethodCall:(FlutterMethodCall*)call result:(FlutterResult)result {
    result(FlutterMethodNotImplemented);
}

@end
