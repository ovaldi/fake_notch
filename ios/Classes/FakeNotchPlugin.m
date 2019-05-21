#import "FakeNotchPlugin.h"

@implementation FakeNotchPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
    FlutterMethodChannel* channel = [FlutterMethodChannel
                                     methodChannelWithName:@"v7lin.github.io/fake_notch"
                                     binaryMessenger:[registrar messenger]];
    FakeNotchPlugin* instance = [[FakeNotchPlugin alloc] init];
    [registrar addMethodCallDelegate:instance channel:channel];
}

static NSString * const METHOD_HASCONVENTIONALNOTCH = @"hasConventionalNotch";
static NSString * const METHOD_HASSPECIALNOTCH = @"hasSpecialNotch";
static NSString * const METHOD_GETSPECIALNOTCHSIZE = @"getSpecialNotchSize";

- (void)handleMethodCall:(FlutterMethodCall*)call result:(FlutterResult)result {
    if ([METHOD_HASCONVENTIONALNOTCH isEqualToString:call.method]) {
        if (@available(iOS 11.0, *)) {
            BOOL hasNotch = !UIEdgeInsetsEqualToEdgeInsets([UIApplication sharedApplication].delegate.window.safeAreaInsets, UIEdgeInsetsZero);
            result([NSNumber numberWithBool:hasNotch]);
        } else {
            result([NSNumber numberWithBool:NO]);
        }
    } else if ([METHOD_HASSPECIALNOTCH isEqualToString:call.method]) {
        result(FlutterMethodNotImplemented);
    } else if ([METHOD_GETSPECIALNOTCHSIZE isEqualToString:call.method]) {
        result(FlutterMethodNotImplemented);
    } else {
        result(FlutterMethodNotImplemented);
    }
}

@end
