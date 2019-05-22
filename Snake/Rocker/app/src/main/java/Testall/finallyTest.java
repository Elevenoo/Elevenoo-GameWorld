package Testall;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class finallyTest {
    public static void main(String[] args){
        Connection conn=null;
        Statement stmt=null;
        String url="";
        String username="";
        String password="";
        String sql="";
        try{
            conn = DriverManager.getConnection(url,username,password);
            stmt=conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        }catch(Exception e){
            System.out.println(e);
        }finally {
            try{
                if(stmt!=null)
                    stmt.close();
                if(conn!=null)
                    conn.close();
            }catch (Exception e1){
                System.out.println(e1);
            }
        }
    }

}
