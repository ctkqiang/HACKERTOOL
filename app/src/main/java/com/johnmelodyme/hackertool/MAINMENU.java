package com.johnmelodyme.hackertool;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * @AUTHOR : JOHN MELODY ME
 * @COPYRIGHT : JOHN MELODY ME
 * @PROJECT: HACKER TOOL
 */
public class MAINMENU extends AppCompatActivity {
    private Dialog DIALOGUE;
    private int WIFI_REQUEST_CODE = 0;
    private static final String TAG = MAINMENU.class.getName();
    private Button DEAUTHER;
    private ListView WIFILIST;
    private AlertDialog.Builder alertdialogbuilder;
    ArrayList list;
    private ArrayAdapter Adapter;


    private void INIT(){
        DEAUTHER = (Button) findViewById(R.id.wifi_deauther);
        WIFILIST = (ListView) findViewById(R.id.wifilist);

        list = new ArrayList();
        Adapter = new ArrayAdapter(MAINMENU.this, R.layout.support_simple_spinner_dropdown_item, list);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        INIT();

        DEAUTHER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertdialogbuilder = new AlertDialog.Builder(MAINMENU.this);
                alertdialogbuilder.setMessage("ARE YOU CONNECTED TO WIFI NAMED \"pwned\". ");
                alertdialogbuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent DEAUTHER;
                        DEAUTHER = new Intent(Intent.ACTION_VIEW,
                                Uri.parse("http://192.168.4.1/"));
                        startActivity(DEAUTHER);
                    }
                });

                alertdialogbuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent WIFI;
                        WIFI = new Intent(android.provider.Settings.ACTION_WIFI_SETTINGS);
                        startActivityForResult(WIFI, WIFI_REQUEST_CODE);


                        DIALOGUE = new Dialog(MAINMENU.this);
                        DIALOGUE.setContentView(R.layout.wifi);
                        View rowlist = getLayoutInflater().inflate(R.layout.wifi, null);
                        WIFILIST = (ListView) rowlist.findViewById(R.id.wifilist);
                        //list

                        DIALOGUE.setContentView(rowlist);
                        DIALOGUE.show();
                    }
                });
                AlertDialog alertDialog;
                alertDialog = alertdialogbuilder.create();
                alertDialog.show();
            }
        });

        DEAUTHER.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String MSG;
                MSG = getResources().getString(R.string.wifideauther);
                showtoast(MSG);
                return false;
            }
        });
    }

    public void showtoast(String message){
        Toast.makeText(MAINMENU.this, message,
                Toast.LENGTH_LONG)
                .show();
    }
}
