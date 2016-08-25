/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/

/* global cordova:false */
/* globals window */

var exec = cordova.require('cordova/exec'),
    utils = cordova.require('cordova/utils');

/**
    @description A global object that lets you interact with the Creative SDK Send To Desktop API.
    @global
*/
var CSDKSendToDesktop = {
    /**
     * @description sends an image to an Adobe desktop app.
     * @function send
     * @memberof CSDKSendToDesktop
     * @param {!successCallback} successCallback - See type definition.
     * @param {!failureCallback} failureCallback - See type definition.
     * @param {!string} uri URI of the image to be sent.
     * @param {!CSDKSendToDesktop.AppType} ccApplication The Adobe Creative Cloud desktop application you are sending to.
     * @param {!string} mimeType The mime type of the image you are sending.
     */
    send: function(successCallback, failureCallback, uri, ccApplication, mimeType) {
        exec(successCallback, failureCallback, 'CSDKSendToDesktop', 'send', [uri, ccApplication, mimeType]);
    },

    /**
    * @enum {number}
    */
    AppType:{
        /** Send to Photoshop */
        PHOTOSHOP: 0,
        /** Send to InDesign */
        INDESIGN: 1,
        /** Send to Illustrator */
        ILLUSTRATOR: 2
    }
};

/**
 * @description A callback to be used upon sending of an image. This callback has no parameters.
 *
 * @callback successCallback
 */

/**
 * @description A callback to handle errors when attempting to send an image.
 *
 * @callback failureCallback
 * @param {Object} error - Error object.
 */

module.exports = CSDKSendToDesktop;
