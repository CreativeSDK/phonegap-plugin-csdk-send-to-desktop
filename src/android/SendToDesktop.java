package com.adobe.phonegap.csdk;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.util.Log;

import com.adobe.creativesdk.foundation.sendtodesktop.AdobeCreativeCloudApplication;
import com.adobe.creativesdk.foundation.sendtodesktop.AdobeSendToDesktopApplication;
import com.adobe.creativesdk.foundation.sendtodesktop.AdobeSendToDesktopException;
import com.adobe.creativesdk.foundation.sendtodesktop.IAdobeSendToDesktopCallBack;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.net.URL;

public class SendToDesktop extends CordovaPlugin {

    private static final int PHOTOSHOP = 0;
    private static final int INDESIGN = 1;
    private static final int ILLUSTRATOR = 2;

    public CallbackContext callbackContext;

    @SuppressLint("NewApi")
    public boolean execute(String action, final JSONArray args, final CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;

        if (action.equals("send")) {
            String path = args.getString(0);
            Log.d("SendToDesktop", "!!! Url: " + path);

            AdobeCreativeCloudApplication creativeCloudApplication = getAppType(args.getInt(1));
            String mimeType = args.getString(2);

            final IAdobeSendToDesktopCallBack sendToDesktopCallBack = new IAdobeSendToDesktopCallBack() {
                @Override
                public void onSuccess() {
                    callbackContext.success();
                }

                @Override
                public void onError(AdobeSendToDesktopException e) {
                    Log.e("SendToDesktop", e.getMessage(), e);
                    callbackContext.error(e.getLocalizedMessage());
                }
            };

            if (path.startsWith("content:")) {
                AdobeSendToDesktopApplication.sendToDesktop(Uri.parse(path), mimeType,
                    creativeCloudApplication, sendToDesktopCallBack);
            } else {
                InputStream input = null;
                String name = path.substring(path.lastIndexOf("/")+1);

                try {
                    if (path.startsWith("http:") || path.startsWith("https:")) {
                        URL httpUrl = new URL(path);
                        input = httpUrl.openStream();
                    } else if (path.startsWith("file:///android_asset/")) {
                        input = cordova.getActivity().getApplicationContext()
                            .getAssets().open(path.substring(22));
                    } else if (path.startsWith("file://")) {
                        input = new FileInputStream(path.substring(7));
                    }
                } catch (Exception e) {
                    Log.e("SendToDesktop", e.getMessage(), e);
                    callbackContext.error(e.getLocalizedMessage());
                    return true;
                }

                AdobeSendToDesktopApplication.sendToDesktop(input, name, mimeType,
                    creativeCloudApplication, sendToDesktopCallBack);
            }

            PluginResult r = new PluginResult(PluginResult.Status.NO_RESULT);
            r.setKeepCallback(true);
            callbackContext.sendPluginResult(r);
        } else {
            return false;
        }
        return true;
    }

    private AdobeCreativeCloudApplication getAppType(int type) {
        if (type == PHOTOSHOP) {
            return AdobeCreativeCloudApplication.AdobePhotoshopCreativeCloud;
        } else if (type == INDESIGN) {
            return AdobeCreativeCloudApplication.AdobeInDesignCreativeCloud;
        } else if (type == ILLUSTRATOR) {
            return AdobeCreativeCloudApplication.AdobeIllustratorCreativeCloud;
        }
        return AdobeCreativeCloudApplication.AdobeUnknownCreativeCloud;
    }

}
