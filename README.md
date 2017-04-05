# IbAsync-InternetChecker
 
An Asynchronous Internet Checker library for android.when internet connection is lost or poor internet connection or internet connection is get back, this will automatically fire on registered listeners with a boolean value.Everything will be done automatically.Users just need to put their code on listener when internet available or when internet connection is lost or poor internet connection by checking the boolean value.

# User-Manual

1) On your android manifest file add permissions and register this service
              
              <uses-permission android:name="android.permission.INTERNET"></uses-permission>
              <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"></uses-permission>
              
        <service android:name="com.nab.ibrahim.ibinternetchecker.IbAsyncInternetChecker" android:enabled="true" android:exported="true"></service>

2) on your activity-

        public class MainActivity extends AppCompatActivity implements IbAsyncInternet.onIbAsyncInternetChanged {
        (...)
         IbAsyncInternet.initService();
         IbAsyncInternet.getIb_AsyncInternetService().registerIb_AsyncInternetListener(this);
         startService(new Intent(MainActivity.this,IbAsyncInternetChecker.class));
         (...)
	
             
	   @Override
          public void onInternetChanged(boolean flag) {
                //do your code
          if(flag){
              //keep running your network based works.
          }else{
              //stop your network based works and notify app users about internet connection lost.
                  }  
                }
               
               }
               
               
3) Include into your project- 

Add it in your root build.gradle at the end of repositories:
                    
                    allprojects {
	                 repositories {
	             	   ...
	           maven {url  "http://dl.bintray.com/ibnrahkm/ibasyncinternetchecker"}
	               	}
	              }
             
Add the dependency:
 
                dependencies {
	         compile 'com.nab.ibrahim.ibasyncinternetchecker:ibasyncinternetchecker:1.0'
	           }
