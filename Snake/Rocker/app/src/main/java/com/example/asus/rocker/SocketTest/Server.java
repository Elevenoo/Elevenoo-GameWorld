package com.example.asus.rocker.SocketTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args){
        BufferedReader br=null;
        PrintWriter pw=null;
        try{
            ServerSocket server=new ServerSocket(1500);
            Socket socket = server.accept();
            if(socket==null)
                System.out.println("没有建立连接");
            else{
                System.out.println("建立连接");
                //获取输入流
                br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //获取输出流
                pw=new PrintWriter(socket.getOutputStream(),true);
                //获取接收的数据
                String str=br.readLine();
                if(str==null)
                    System.out.println("没有接收到数据");
                else{
                    //发送相同的数据给客户端
                    pw.println();
                    //打印
                    System.out.println(str);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
//            try {
//                br.close();
//                pw.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        }
    }
}
