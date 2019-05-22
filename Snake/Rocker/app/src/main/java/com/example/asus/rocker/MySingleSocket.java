package com.example.asus.rocker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Only one socket can be create by this project
 *
 * modify by Elevenoo , 2019/5/20
 */
public class MySingleSocket extends Socket {

    private static final String hort="192.168.196.157";  // default ip and port
    private static final int port=2000;
    private static MySingleSocket socket=null;
    private static PrintWriter pw=null;
    private static BufferedReader br=null;

    /**
     * Overload constructor to implement the specified IP and port creation socket
     *
     * @param host   //Specified ip and port
     * @param port
     * @throws UnknownHostException
     * @throws IOException
     */
    private MySingleSocket(String host, int port) throws UnknownHostException, IOException {
        super(host, port);
    }

    /**
     * private constructors that denies the creation of an instance of the class in the main program
     *
     * @param hort
     * @param port
     * @return
     * @throws IOException
     */
    public static MySingleSocket getMySingleSocket(String hort,int port) throws IOException {
        if(socket==null)
            socket=new MySingleSocket(hort,port);
        return socket;
    }

    /**
     * Provide the main program to get the socket, printwrite and bufferedreader methods
     *
     * @return
     * @throws IOException
     */
    public static MySingleSocket getMySingleSocket() throws IOException {
        if(socket==null)
            socket=new MySingleSocket(hort,port);
        return socket;
    }

    public static BufferedReader getBR() throws IOException{
        if(br==null){
            if(socket==null)
                socket=new MySingleSocket(hort,port);
            InputStream is=socket.getInputStream();
            InputStreamReader isr=new InputStreamReader(is,"GBK"); //Java GBK encoding to prevent garbled
            br=new BufferedReader(isr);
        }
        return br;
    }
    public static PrintWriter getPW() throws IOException{
        if(pw==null) {
            if(socket==null)
                socket=new MySingleSocket(hort,port);
            OutputStream os = socket.getOutputStream(); //Formatted representation of the print object to the text output stream, supporting Chinese
            OutputStreamWriter osw=new OutputStreamWriter(os);
            BufferedWriter bw=new BufferedWriter(osw);
            pw=new PrintWriter(bw,true); //Formatted representation of the print object to the text output stream, supporting Chinese
        }
        return pw;
    }


}
