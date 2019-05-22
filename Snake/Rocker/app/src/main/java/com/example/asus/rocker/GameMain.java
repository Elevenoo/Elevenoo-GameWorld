package com.example.asus.rocker;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Control snake object
 * communicate with the server
 *
 * write by Elevenoo , 2019/5/20
 */
public class GameMain extends AppCompatActivity {

    PrintWriter pw=null;
    BufferedReader br=null;
    MoveBall mb=null;
    double rad=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_main);

        /**
         * get printwrite and bufferedreader of socket
         */
        try {
            pw=MySingleSocket.getPW();   //singleton:MySingleSocket
            br=MySingleSocket.getBR();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /**
         *  Control snake movement by the server
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String info;
                    while (null!=(info=br.readLine())){    //If no data is received, check the sent data to end with "\r"
                                                            // (end identifier, anti-sticking phenomenon)
                        Log.d("MainActivity","Server sent a message："+info);
                        for(int i=0;i<5;i++){
                            mb.Moveball(Double.valueOf(info));
                        }
                    }
                }catch (Exception e){
                    Log.d("MainActivity",e.toString());
                }
            }
        }).start();

        /**
         *  Control snake movement while sending location information to the server
         */

        mb = findViewById(R.id.moveball);
        RockerView rockerview = findViewById(R.id.rockerview);
        rockerview.setRadListener(new RadListener(){   //Implement the report method of the interface RadListener
            @Override
            public void report(double tempRad) {
                rad= tempRad;
                new Thread() {
                    @Override
                    public void run() {
                        while (rad != 888888) {
                            try {
                                Thread.sleep(500);
                                if(rad==888888)       //Prevent rocker lifting during sleep then sending stop flag data(888888)
                                    return;
                                mb.Moveball(rad);     // control snake body movement
                                if(pw!=null){
                                    pw.write("rad="+rad+"\n");
                                    pw.flush();
//                                  pw.close();       //The socket is also closed when the printwrite is closed.
//                                  os.close();
                                }

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();
            }
        });
    }
}
