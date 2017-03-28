package com.mobileappscompany.training.mycustompermissionapp;

import android.*;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String AWESOME_PERMISSION = "mycustompermissionapp.AWESOME_PERMISSION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        sendMySMS("");
    }

    public void sendMySMS(String message) {
        int pCk = ContextCompat.checkSelfPermission(this, AWESOME_PERMISSION);

        if(pCk != PackageManager.PERMISSION_GRANTED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, AWESOME_PERMISSION)) {
                requestPermission();
            } else {
                requestPermission();
            }
        } else {
            showToastMessage("Yep");
        }

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{AWESOME_PERMISSION}, 555);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 555:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    showToastMessage("Yes");
                } else {
                    showToastMessage("No");
                }
                return;
        }
    }

    private void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
