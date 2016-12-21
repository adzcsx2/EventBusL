# EventBusL
add a log to show where to post message and where to receive message

The Log like this:

![](https://github.com/adzcsx2/EventBusL/blob/master/readme.png)

##How to use
Add it in your root build.gradle at the end of repositories:
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Add it in your app module dependency
	dependencies {
	        ...
	        compile 'com.github.adzcsx2:EventBusL:ff51df3214'
    }

[![](https://jitpack.io/v/adzcsx2/EventBusL.svg)](https://jitpack.io/#adzcsx2/EventBusL)
