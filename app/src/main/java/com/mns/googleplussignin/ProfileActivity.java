package com.mns.googleplussignin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class ProfileActivity extends AppCompatActivity {

    Button btSignOut, btRevoke;
    TextView tvInfo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        initViews();
        fillDetails();

    }

    private void fillDetails() {
        if (SignInData.GRESULT != null) {
            GoogleSignInAccount acct = SignInData.GRESULT.getSignInAccount();
            String details = "Name : " + acct.getDisplayName() + "\nEmail: " + acct.getEmail() + "\nId: " + acct.getId();
            tvInfo.setText(details);
        }
    }

    private void initViews() {
        tvInfo = (TextView) findViewById(R.id.tvProfileInfo);
        btSignOut = (Button) findViewById(R.id.btnSignOut);
        btRevoke = (Button) findViewById(R.id.btnRevoke);
        btSignOut.setOnClickListener(mOnClickListener);
        btRevoke.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnSignOut:
                    signOut();
                    break;
                case R.id.btnRevoke:
                    revoke();
                    break;
            }
        }
    };

    private void signOut() {
        Auth.GoogleSignInApi.signOut(SignInData.GAPP_CLEINT).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(Status status) {
                if (status.isSuccess()) {
                    goBack();
                }
            }
        });

    }

    private void revoke() {
        Auth.GoogleSignInApi.revokeAccess(SignInData.GAPP_CLEINT).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(Status status) {
                if (status.isSuccess()) {
                    goBack();
                }
            }
        });
    }

    private void goBack() {
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
