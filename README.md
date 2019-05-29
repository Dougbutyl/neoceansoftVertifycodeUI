# 新海内部UI
## 密码输入框
## VertifyCodeView
![image](https://github.com/Dougbutyl/neoceansoftVertifycodeUI/blob/master/screenshots/device-2019-05-29-140949.png)

## Dependency
Add it in your root build.gradle at the end of repositories:
``` Java
 	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
 ```
 and then add dependency
``` Java
 	dependencies {
	        implementation 'com.github.Dougbutyl:neoceansoftVertifycodeUI:1.3'
	}

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
        app:code_type="plaincode">
        </com.neocean.app.neoceansoftuivertifycode.VertifyCodeView>
```
### Listener
```
setVertifyCodeListener(new VertifyCodeView.VertifyCodeListener() {
            @Override
            public void onInputFinish(String inputContent) {
                
            }
        });
 ```
 |Attributes|default value|Description|
|:---|:---|:---|
|app:code_length|6||
|app:code_linebg|color|line color|
|app:code_type|password<br>plaincode||
|app:code_frametype|line<br>boder<br>nointboder||
|app:code_bodercolor|color|boder color|


 
