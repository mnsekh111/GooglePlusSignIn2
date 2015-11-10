package com.mns.googleplussignin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    private final int LOGIN_RES = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

         SignInData.GAPP_CLEINT= new GoogleApiClient.Builder(this)
                .enableAutoManage(this,mOnFailListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        SignInButton signInButton = (SignInButton) findViewById(R.id.btn_signin);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(SignInData.GAPP_CLEINT);
                startActivityForResult(signInIntent, LOGIN_RES);
            }
        });

    }

    GoogleApiClient.OnConnectionFailedListener mOnFailListener = new GoogleApiClient.OnConnectionFailedListener() {
        @Override
        public void onConnectionFailed(ConnectionResult connectionResult) {
            Toast.makeText(getBaseContext(), "Failed to connect", Toast.LENGTH_LONG).show();
        }
    };


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == LOGIN_RES) {
           SignInData.GRESULT = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(SignInData.GRESULT != null){
                if (!SignInData.GRESULT.isSuccess()) {
                    Toast.makeText(getBaseContext(), "Sign in failed. Try again", Toast.LENGTH_LONG).show();
                }else{
                    goToNext(data);
                }

            }
        }
    }

    private void goToNext(Intent data){
        Intent profileIntent = new Intent(getBaseContext(),ProfileActivity.class);
        startActivity(profileIntent);
    }


}
