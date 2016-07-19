#import <Cordova/CDVPlugin.h>

enum CDVAppType {
    AppTypePHOTOSHOP = 0,
    AppTypeINDESIGN,
    AppTypeILLUSTRATOR
};
typedef NSUInteger CDVAppType;

@interface CDVSendToDesktop : CDVPlugin
{
    NSString *callbackId;
}

@property (nonatomic, retain) NSString *callbackId;

- (void)send:(CDVInvokedUrlCommand*)command;
- (AdobeCreativeCloudApplication)getAppType:(NSNumber*)type;

@end
