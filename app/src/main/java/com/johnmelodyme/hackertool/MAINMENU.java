package com.johnmelodyme.hackertool;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MAINMENU extends AppCompatActivity {
    private static final String TAG = "hackertool";
    private Button Termux;

    private void declaractionInit() {
        Termux = findViewById(R.id.termux);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        declaractionInit();

        Termux.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isTermuxInstalled;
                isTermuxInstalled = termuxInstalled("com.termux");
                if (isTermuxInstalled){
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.termux");
                    if (launchIntent != null) {
                        Log.d(TAG, "Opening Termux");
                        startActivity(launchIntent);
                    } else {
                        showtoast("PLEASE INSTALL TERMUX");
                    }
                } else {
                    Intent TERMUX;
                    TERMUX = new Intent(Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store/apps/details?id=com.termux&hl=en"));
                    startActivity(TERMUX);
                }
            }
        });
    }

    private boolean termuxInstalled(String s) {
        PackageManager pm = getPackageManager();
        try {
            pm.getPackageInfo(s, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG, "TERMUX NOT INSTALLED.");
        }
        return false;
    }

    public void showtoast(String message){
        Toast.makeText(MAINMENU.this, message,
                Toast.LENGTH_LONG)
                .show();
    }
}
