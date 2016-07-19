package com.adobe.phonegap.csdk;

import android.annotation.SuppressLint;
import android.widget.Toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import io.cordova.hellocordova.MainActivity;

/**
 * Created by ash on 7/19/16.
 */

public class SendToDesktop extends CordovaPlugin {

    public CallbackContext callbackContext;

    @SuppressLint("NewApi")
    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;

        if (action.equals("send")) {

            /* 1) Specify the Adobe desktop app to send to */
            AdobeCreativeCloudApplication creativeCloudApplication = AdobeCreativeCloudApplication.AdobePhotoshopCreativeCloud;

            /* 2) Make a callback to handle success and error */
            final IAdobeSendToDesktopCallBack sendToDesktopCallBack = new IAdobeSendToDesktopCallBack() {
                @Override
                public void onSuccess() {
                    Toast.makeText(MainActivity.this, "Opening in Photoshop on your desktop!", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onError(AdobeSendToDesktopException e) {
                    Toast.makeText(MainActivity.this, "Failed!", Toast.LENGTH_LONG).show();

                    e.printStackTrace();
                }
            };

            /* 3) Send the image to the desktop! */
            AdobeSendToDesktopApplication.sendToDesktop(mSendToPhotoshopUri, "image/jpeg", creativeCloudApplication, sendToDesktopCallBack);


            PluginResult r = new PluginResult(PluginResult.Status.NO_RESULT);
            r.setKeepCallback(true);
            callbackContext.sendPluginResult(r);
        } else {
            return false;
        }
        return true;
    }

}