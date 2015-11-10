# GooglePlusSignIn2
Google Sign in for Android Applications


This is a demo application for integrating google sign in authentication for android applications

Steps :
    
    1. Go to Tools > Android > SDK Manager and make sure to have google-play-services installed
    2. Generate a client auth using the java Keytool utility to get a SHA-1 fingerprint
        https://developers.google.com/android/guides/client-auth
    3. Enable google services for the app by registering the app name, app package name and SHA1 in google developer 
        which gives a google-services.json file (https://console.developers.google.com/project/_/apiui/credential)
    4. Moove the google-services.json to "app" folder
    5. Add the dependency to project-level build.gradle:
        classpath 'com.google.gms:google-services:1.5.0-beta2'
    6. Add the plugin to app-level build.gradle:
        apply plugin: 'com.google.gms.google-services'
    7. Add the following dependency
        dependencies {
          compile 'com.google.android.gms:play-services-auth:8.3.0'
        }
