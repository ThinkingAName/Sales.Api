package com.GetJDBCData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
 
public class DBUtils {
	 //这里可以设置数据库名称
   private final static String URL = "jdbc:sqlserver://192.168.1.211:3388;DatabaseName=DBC";
// private final static String URL = "jdbc:sqlserver://101.200.177.45;DatabaseName=master";

    private static final String USER="cardata";
    private static final String PASSWORD="internet.xajx.0601";
    //private static final String PASSWORD="428a4d3b179045ff8d92da18bcfecb3f";

    private static Connection conn=null;
    //静态代码块（将加载驱动、连接数据库放入静态块中）
    static{
        try {
            //1.加载驱动程序
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //2.获得数据库的连接
            conn=(Connection)DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //对外提供一个方法来获取数据库连接
    public static Connection getConnection(){
        return conn;
    }
    
    
    //关闭资源的方法 
    public static void closeAll(ResultSet rs,Statement stmt,Connection conn){
        //关闭资源
        if(rs!=null){
            try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs = null;
        }
        if(stmt!=null){
            try {
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }
    
    //测试用例
//    public static void main(String[] args) throws Exception{
//        
//        //3.通过数据库的连接操作数据库，实现增删改查
//        Statement stmt = conn.createStatement();
//        //ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句   ，返回一个结果集（ResultSet）对象。
//        ResultSet rs = stmt.executeQuery("select top 10 * from T_Sal_order");
//        //ResultSet rs = stmt.executeQuery("select top 10 * from T_Appr_MyApprDetails");
//        if(rs.next()){
//        while(rs.next()){//如果对象中有数据，就会循环打印出来
//            System.out.println(rs.getString(1));
//        }}
//        else 
//        	 System.out.println("shui ju ku biao wei kong");
//    }

}