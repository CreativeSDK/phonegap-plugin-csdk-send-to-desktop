<!--
#
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
#  KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
#
-->

phonegap-plugin-csdk-send-to-desktop
------------------------

[![Stories in Ready](https://badge.waffle.io/CreativeSDK/phonegap-plugin-csdk-send-to-desktop.png?label=ready&title=Ready)](http://waffle.io/CreativeSDK/phonegap-plugin-csdk-send-to-desktop)

# Prerequisites

Before you can work with the Creative SDK, you must register your application and get Client ID and Client Secret values. For details, see [Registering Your Application](https://creativesdk.adobe.com/docs/ios/#/articles/gettingstarted/index.html).

To get the iOS SDK, go to the [Downloads page](https://creativesdk.adobe.com/downloads.html), download the ZIP files, and extract them to the src/ios folder of this plugin. It should create a AdobeCreativeSDKFrameworks folder. The ZIP files contain all the frameworks in the Creative SDK but for this plugin we will only be using the AdobeCreativeSDKCore.framework.

The following software is required:
- Xcode 7 or higher
- iOS 8.2 or higher

# Installation

To add to your app:

```
phonegap plugin add --save https://github.com/CreativeSDK/phonegap-plugin-csdk-send-to-desktop
```

***

<!--
#
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
#  KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
#
-->

phonegap-plugin-csdk-user-auth
------------------------

[![Stories in Ready](https://badge.waffle.io/CreativeSDK/phonegap-plugin-csdk-user-auth.png?label=ready&title=Ready)](http://waffle.io/CreativeSDK/phonegap-plugin-csdk-user-auth)

The Creative SDK provides a simple way for your mobile users to send their work from your app directly to Adobe desktop apps.

You can specify which application the shared data will open in. Three applications support this feature: Photoshop, Illustrator, and InDesign.

This plugin makes it possible for you to use the Creative SDK Send To Desktop API in your PhoneGap apps. Read on to learn how!

### Contents

- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Setup guide](#setup-guide)
- [Sample code](#sample-code)
- [API guide](#api-guide)

# Prerequisites

**Required:** You must first install the following plugins for this plugin to work:

- [Client Auth plugin](https://github.com/CreativeSDK/phonegap-plugin-csdk-client-auth)
- [User Auth plugin](https://github.com/CreativeSDK/phonegap-plugin-csdk-user-auth)

**Required:** This guide will assume that you have installed all software and completed all of the steps in the [Client Auth guide](https://github.com/CreativeSDK/phonegap-plugin-csdk-client-auth).


# Installation

## Adding the plugin

Use the command below to add the plugin to your app.

```
phonegap plugin add --save https://github.com/CreativeSDK/phonegap-plugin-csdk-send-to-desktop
```

## Downloading the Creative SDK

**iOS**

To get the iOS SDK, go to the [Downloads page](https://creativesdk.adobe.com/downloads.html), download the ZIP files, and extract them to the `src/ios` folder of this plugin. Extracting the ZIP will create an `AdobeCreativeSDKFrameworks` folder.

The ZIP files contain all the frameworks in the Creative SDK, but for this plugin we will only be using the `AdobeCreativeSDKCore.framework`.


**Android**

No action is required for Android. The Creative SDK for Android is delivered as a remote Maven repository, and the required framework will be downloaded automatically by the plugin.


# Setup guide

1. `cd` into your existing PhoneGap app (must already include [Client Auth](https://github.com/CreativeSDK/phonegap-plugin-csdk-client-auth) and [User Auth](https://github.com/CreativeSDK/phonegap-plugin-csdk-user-auth))
1. Add this plugin (see "Adding the plugin" above)
1. **iOS only:** download and add the Creative SDK to this plugin's `src/ios` directory (see "Downloading the Creative SDK" above)
1. Build and run for your platform

# Sample code

## `www/index.html`

Add two buttons within the `body`. The PhoneGap "Hello World" example includes a `div` with an ID of `app`, so for this example, we are including it in there.

```
// ...

<div class="app">

    // ...

    <button id="send-to-desktop">Send to Photoshop</button>
</div>

// ...

```

## `www/js/index.js`

_**Note:** Most of the code below comes from the PhoneGap "Hello World" example, and we are providing it here for context._

This plugin provides access to a global `CSDKSendToDesktop` object.

The `CSDKSendToDesktop` object exposes a `.send()` function.

See comments **#1-3** below for relevant code:

```
var app = {
    initialize: function() {
        this.bindEvents();
    },
    bindEvents: function() {
        // ...

        /* 1) Add a click handlers for your button */
        document.getElementById('send-to-desktop').addEventListener('click', this.sendToDesktop, false);
    },
    onDeviceReady: function() {
        app.receivedEvent('deviceready');
    },
    receivedEvent: function(id) {
        // ...
    },

    /* 2) Make a helper function to login to Creative Cloud */
    sendToDesktop: function() {

        /* 2.a) Prep work for calling `.login()` */
        function success(userObject) {
            console.log("Login Success!", successObject);
        }

        function error(error) {
            console.log("Error!", error);
        }

        var imageUri = "<YOUR_IMAGE_HERE>";

        var ccApplication = CSDKSendToDesktop.AppType.PHOTOSHOP;

        var mimeType = "image/jpeg";


        /* 2.b) Launch User Auth */
        CSDKSendToDesktop.send(success, error, uri, ccApplication, mimeType);
    }
};
```


# API guide

[See the full API guide](docs/api.md).
