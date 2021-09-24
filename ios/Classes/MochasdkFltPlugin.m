#import "MochasdkFltPlugin.h"
#if __has_include(<mochasdk_flt/mochasdk_flt-Swift.h>)
#import <mochasdk_flt/mochasdk_flt-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "mochasdk_flt-Swift.h"
#endif

@implementation MochasdkFltPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftMochasdkFltPlugin registerWithRegistrar:registrar];
}
@end
