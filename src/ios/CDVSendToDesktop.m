/*
 Licensed to the Apache Software Foundation (ASF) under one
 or more contributor license agreements.  See the NOTICE file
 distributed with this work for additional information
 regarding copyright ownership.  The ASF licenses this file
 to you under the Apache License, Version 2.0 (the
 "License"); you may not use this file except in compliance
 with the License.  You may obtain a copy of the License at
 http://www.apache.org/licenses/LICENSE-2.0
 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an
 "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 KIND, either express or implied.  See the License for the
 specific language governing permissions and limitations
 under the License.
 */

#import <AdobeCreativeSDKCore/AdobeCreativeSDKCore.h>
#import <AdobeCreativeSDKAssetModel/AdobeCreativeSDKAssetModel.h>
#import "CDVSendToDesktop.h"

#define ADB_PHOTO_PREFIX @"adb_photo_"
#define isEqualIgnoreCaseToString(string1, string2) ([string1 caseInsensitiveCompare:string2] == NSOrderedSame)

@implementation CDVSendToDesktop

@synthesize callbackId;

- (void)send:(CDVInvokedUrlCommand*)command
{
    self.callbackId = command.callbackId;

    NSURL *url = [NSURL URLWithString:[command.arguments objectAtIndex:0]];
    NSData *imageData = [NSData dataWithContentsOfURL:url];
    UIImage *image = [UIImage imageWithData:imageData];

    NSString *mimeType = [command.arguments objectAtIndex:2];
    //AdobeCreativeCloudApplication *ccApplication = AdobePhotoshopCreativeCloud;

    [AdobeSendToDesktopApplication 
        sendImage:image
        //withType:mimeType
        toApplication: AdobePhotoshopCreativeCloud
        withName: @"untitled"
        onSuccess:^{
            CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        }
        onProgress:nil
        onCancellation:nil
        onError:^(NSError *error){
            CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:[error localizedDescription]];
            [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
        }
    ];
}

@end