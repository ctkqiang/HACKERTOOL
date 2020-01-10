package com.johnmelodyme.hackertool;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
/**
 * @AUTHOR : JOHN MELODY ME
 * @COPYRIGHT : JOHN MELODY ME
 * @PROJECT: HACKER TOOL
 */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private Button DEAUTHER;

    private void INIT(){
        DEAUTHER = (Button) findViewById(R.id.wifi_deauther);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        INIT();

        DEAUTHER.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent DEAUTHER;
                DEAUTHER =  new Intent(MainActivity.this, DEAUTHER.class);

            }
        });
    }
}
