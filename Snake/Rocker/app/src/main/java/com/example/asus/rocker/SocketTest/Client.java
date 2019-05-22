package com.example.asus.rocker.SocketTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args){
        BufferedReader br=null;
        PrintWriter pw=null;
        try{
            Socket socket=new Socket("localhost",1500);
            //获取输入流
            br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //获取输出流
            pw=new PrintWriter(socket.getOutputStream(),true);
            //向服务器发送数据
            pw.println("hello");
            String s="";
            while(true){
                s=br.readLine();
                if(s!=null)
                    break;
                System.out.println(s);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                br.close();
                pw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

