#import <Cordova/CDVPlugin.h>


@interface CDVSendToDesktop : CDVPlugin
{
    NSString *callbackId;
}

@property (nonatomic, retain) NSString *callbackId;

- (void)send:(CDVInvokedUrlCommand*)command;

@end