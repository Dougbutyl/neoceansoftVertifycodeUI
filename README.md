# 新海内部UI
## 密码输入框
## VertifyCodeView
![image](https://github.com/Dougbutyl/neoceansoftVertifycodeUI/blob/master/screenshots/device-2019-05-29-140949.png)

## Dependency
Add it in your root build.gradle at the end of repositories:
``` Java
 maven { url 'https://jitpack.io' }
 ```
 and then add dependency
``` Java
 compile 'com.github.Dougbutyl:neosceansoftDateTool:1.6'
 ```
 ## Usage
 ### Xml
 ```
<com.neocean.app.neoceansoftuivertifycode.VertifyCodeView
        android:id="@+id/vc_line"
        android:layout_width="230dp"
        android:layout_height="45dp"
        android:layout_centerInParent="true"
        app:code_frametype="line"
        android:layout_marginTop="5dp"
        app:code_length="4"
        app:code_type="plaincode"></com.neocean.app.neoceansoftuivertifycode.VertifyCodeView>
```
 |列名1|列名2|
|:---|:---|
|列1的内容1|列2的内容1|
|列1的内容2|列2的内容2|

 
