/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itn.crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Script
 */
public class JdbcInsert {
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_demo";
    private static final String USER = "root";
    private static final String PASS = "";
    
    public static void main(String[] args) {
 
        Connection conn = null;
        Statement stmt = null;
        //imppot from java.sql package
        
        
        
        try{
            //1. Driver registration
        Class.forName(DRIVER_CLASS);
        
            //2. Creating connection object
            conn= DriverManager.getConnection(URL,USER,PASS);
            
            //3. Create a statement object using connection
            stmt = conn.createStatement();
            
            //4. Write sql query
            String sql= "insert into employee_table (name,address,salary)"
                    +"values('harim','hariya',12000.00)";
            
            //5. Execute sql query using statement object
            
                int i = stmt.executeUpdate(sql);
                if(i==1){
                    System.out.println("One record has been inserted!");
                }
    } 
        catch(ClassNotFoundException | SQLException e)
    {
        System.out.println(e);
    }
        finally{
            //6. Closing open resources
            try{
                if(stmt!= null){
                stmt.close();
            }
                if(conn!= null){
                    conn.close();
            }
            }
                catch(SQLException e){
                    System.out.println(e);
                    }
        }
    
}
}