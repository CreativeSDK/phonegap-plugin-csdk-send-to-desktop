## Members

<dl>
<dt><a href="#CSDKSendToDesktop">CSDKSendToDesktop</a></dt>
<dd><p>A global object that lets you interact with the Creative SDK Send To Desktop API.</p>
</dd>
</dl>

## Typedefs

<dl>
<dt><a href="#successCallback">successCallback</a> : <code>function</code></dt>
<dd><p>A callback to be used upon sending of an image. This callback has no parameters.</p>
</dd>
<dt><a href="#failureCallback">failureCallback</a> : <code>function</code></dt>
<dd><p>A callback to handle errors when attempting to send an image.</p>
</dd>
</dl>

<a name="CSDKSendToDesktop"></a>

## CSDKSendToDesktop
A global object that lets you interact with the Creative SDK Send To Desktop API.

**Kind**: global variable  

* [CSDKSendToDesktop](#CSDKSendToDesktop)
    * [.AppType](#CSDKSendToDesktop.AppType) : <code>enum</code>
    * [.send(successCallback, failureCallback, uri, ccApplication, mimeType)](#CSDKSendToDesktop.send)

<a name="CSDKSendToDesktop.AppType"></a>

### CSDKSendToDesktop.AppType : <code>enum</code>
**Kind**: static enum property of <code>[CSDKSendToDesktop](#CSDKSendToDesktop)</code>  
**Properties**

| Name | Type | Default | Description |
| --- | --- | --- | --- |
| PHOTOSHOP | <code>number</code> | <code>0</code> | Send to Photoshop |
| INDESIGN | <code>number</code> | <code>1</code> | Send to InDesign |
| ILLUSTRATOR | <code>number</code> | <code>2</code> | Send to Illustrator |

<a name="CSDKSendToDesktop.send"></a>

### CSDKSendToDesktop.send(successCallback, failureCallback, uri, ccApplication, mimeType)
sends an image to an Adobe desktop app.

**Kind**: static method of <code>[CSDKSendToDesktop](#CSDKSendToDesktop)</code>  

| Param | Type | Description |
| --- | --- | --- |
| successCallback | <code>[successCallback](#successCallback)</code> | See type definition. |
| failureCallback | <code>[failureCallback](#failureCallback)</code> | See type definition. |
| uri | <code>string</code> | URI of the image to be sent. |
| ccApplication | <code>[AppType](#CSDKSendToDesktop.AppType)</code> | The Adobe Creative Cloud desktop application you are sending to. |
| mimeType | <code>string</code> | The mime type of the image you are sending. |

<a name="successCallback"></a>

## successCallback : <code>function</code>
A callback to be used upon sending of an image. This callback has no parameters.

**Kind**: global typedef  
<a name="failureCallback"></a>

## failureCallback : <code>function</code>
A callback to handle errors when attempting to send an image.

**Kind**: global typedef  

| Param | Type | Description |
| --- | --- | --- |
| error | <code>Object</code> | Error object. |

