# android-ws-debugger
a android websocket debugger to log message to local

### install
```
implementation 'org.ithot.android.debug:wslog:0.0.1'
```

### permission
```xml
<uses-permission android:name="android.permission.INTERNET" />
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
```

### Manifest
```xml
<service android:name="org.ithot.debug.wslog.DebugWSService" />
```

### usage
```java
// start debugger server listen in 27016
Debugger.startDebugger(context);
// stop debugger server
Debugger.stopDebugger(context);
// get ip address
Debugger.getIpAddress(context);
```

### log client
**recommend** [client](https://github.com/dtboy1995/wslogcli)
