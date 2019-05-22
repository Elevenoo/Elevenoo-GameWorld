package com.example.asus.rocker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.Socket;

/**
 * initialization
 *
 * write by Elevenoo , 2019/5/20
 */
public class MainActivity extends AppCompatActivity {


    static int screenWidth;    //Window width (pixels)
    static int screenHeight;   //Window height (pixels)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Get the width and height of the window
         */
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        screenWidth = dm.widthPixels;
        screenHeight = dm.heightPixels;

        /**
         * Login
         */
        Button connbtn=findViewById(R.id.connbtn);
        connbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Socket socket = MySingleSocket.getMySingleSocket();  //singleton:MySingleSocket, Use the default ip and port
                            Log.d("MainActivity","Server successfully connected");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

                //jump
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, GameMain.class);
                startActivity(intent);
            }
        });

    }
}
