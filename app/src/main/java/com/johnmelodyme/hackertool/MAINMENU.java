package com.johnmelodyme.hackertool;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
// https://github.com/johnmelodyme/sweet-alert-dialog
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * @AUTHOR : JOHN MELODY ME
 * @COPYRIGHT : JOHN MELODY ME
 * @PROJECT: HACKER TOOL
 */

public class MAINMENU extends AppCompatActivity {

    private Dialog DIALOGUE;
    private int WIFI_REQUEST_CODE = 0b0;
    private static final String TAG = MAINMENU.class.getName();
    private Button DEAUTHER, TERMINAL;
    private ListView WIFILIST;
    private AlertDialog.Builder alertdialogbuilder;
    ArrayList list;
    private ArrayAdapter Adapter;


    private void INIT(){
        DEAUTHER = (Button) findViewById(R.id.wifi_deauther);
        WIFILIST = (ListView) findViewById(R.id.wifilist);
        TERMINAL = (Button) findViewById(R.id.TERMUX);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        INIT();

        // https://bit.ly/36L6ES6
        DEAUTHER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SweetAlertDialog(MAINMENU.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("ARE YOU CONNECTED TO WIFI NAMED \"pwned\"?")
                        .setConfirmText("YES, I AM.")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                Intent DEAUTHER;
                                DEAUTHER = new Intent(Intent.ACTION_VIEW,
                                        Uri.parse("http://192.168.4.1/"));
                                startActivity(DEAUTHER);
                            }
                        })
                        .setCancelButton("NO", new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                sDialog.dismissWithAnimation();
                                Intent WIFI;
                                WIFI = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
                                startActivityForResult(WIFI, WIFI_REQUEST_CODE);
                            }
                        })
                        .show();
            }
        });

        DEAUTHER.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String MSG;
                MSG = getResources().getString(R.string.wifideauther);
                new SweetAlertDialog(MAINMENU.this, SweetAlertDialog.BUTTON_POSITIVE)
                        .setTitleText(MSG)
                        .setConfirmText("OKAY")
                        .show();
                return false;
            }
        });

        TERMINAL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isTermuxInstalled;
                isTermuxInstalled = termuxInstalled("com.termux");
                if (isTermuxInstalled){
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.termux");
                    if (launchIntent != null) {
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
            Log.e(TAG, "TERMUX NOT INSTALLED.");
        }
        return false;
    }

    public void showtoast(String message){
        Toast.makeText(MAINMENU.this, message,
                Toast.LENGTH_LONG)
                .show();
    }
}
