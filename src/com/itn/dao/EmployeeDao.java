/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itn.dao;

import com.itn.model.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Script
 */
public class EmployeeDao {

    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_demo";
    private static final String USER = "root";
    private static final String PASS = "";

    public void insertEmployee(Employee e) {

        //inserts a record of employee into database
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName(DRIVER_CLASS);
            conn = DriverManager.getConnection(URL, USER, PASS);
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            String sql = "insert into employee_table (name,address,salary)"
                    + "values('" + e.getName() + "','" + e.getAddress() + "'," + e.getSalary() + ")";
            System.out.println("sql: " + sql);
            int i=stmt.executeUpdate(sql);
            
            if(i>=0){
                System.out.println("record inserted");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            try {
                conn.rollback();
            } catch (SQLException exx) {
                System.out.println(exx);
            }
        } finally {
            try {
                if (conn != null) {
                    conn.commit();
                    conn.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se) {
                System.out.println(se);
            }
        }
    }
    
    public ArrayList<Employee> getAllEmployee(){
        ArrayList<Employee> al = new ArrayList();
       
        Connection conn = null;
        Statement stmt= null;
        
        try{
            Class.forName(DRIVER_CLASS);
            conn= DriverManager.getConnection(URL, USER, PASS);
            stmt=conn.createStatement();
            String sql = "select * from employee_table";
            
            ResultSet rs = stmt.executeQuery(sql);
            
            
            while(rs.next()){
                int id= rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                double salary = rs.getDouble("Salary");
                
                //employee table's field to employee to object Mapping
                Employee e = new Employee (id,name,address,salary);
                al.add(e);
            }
            
            
        }
        catch(ClassNotFoundException | SQLException ex){
            System.out.println(ex);
            
        }
        finally{
            try{
                if (stmt!=null){
                    stmt.close();
                }
                if(conn!=null){
                    conn.close();
                }
            }
        catch(SQLException se){
                System.out.println(se);
        }  
        }
        return al;
    }

        public Employee getEmployeeById(int id){
            //get specific employee from employee_table with provided id
            Connection conn = null;
            Statement stmt = null;
            Employee e = null;
            
            try{
            Class.forName(DRIVER_CLASS);
            conn= DriverManager.getConnection(URL,USER,PASS);
            stmt = conn.createStatement();
            
           String sql="select id,name,address,salary from employee_table where id="+id;
           ResultSet rs = stmt.executeQuery(sql);
            
            if(rs.next()){
            int eid = rs.getInt(1);
            String name = rs.getString(2);
            String address = rs.getString(3);
            double salary = rs.getDouble(4);
            
            e=new Employee(eid,name,address,salary);
            }
            
            
            }
            
            
            catch(ClassNotFoundException | SQLException se)
            {
                System.out.println(se);
                
            }
            finally{
                try{
                   if(stmt!=null){
                       stmt.close();
                       
                   } 
                   if(conn!=null)
                   {
                       conn.close();
                   }
                    
                }
                catch(SQLException se){
                    System.out.println(se);
                }
            
        }
        return e;
    }
        public void updateEmployee(Employee emp){
            //update specific employee in employee_table
            //the emp object has a id of employee whose information is to be 
            //updated
            Connection conn = null;
            Statement stmt = null;
            Employee e = null;
            
            try{
            Class.forName(DRIVER_CLASS);
            conn= DriverManager.getConnection(URL,USER,PASS);
            stmt = conn.createStatement();
            
           String sql="update employee_table set "
                   + "name='"+emp.getName()+"',"
                   + "address='"+emp.getAddress()+"',"
                   + "salary="+emp.getSalary()+"where "
                   + "id="+emp.getId();
           
           int i = stmt.executeUpdate(sql);
           
           if(i>0){
               System.out.println("one record updated");
           }
            
            
            }
            
            
            catch(ClassNotFoundException | SQLException se)
            {
                System.out.println(se);
                
            }
            finally{
                try{
                   if(stmt!=null){
                       stmt.close();
                       
                   } 
                   if(conn!=null)
                   {
                       conn.close();
                   }
                    
                }
                catch(SQLException se){
                    System.out.println(se);
                }
            
        }
       
    }
           public void  deleteEmployee(int id){
            //get specific employee from employee_table with provided id
            Connection conn = null;
            Statement stmt = null;
            
            
            try{
            Class.forName(DRIVER_CLASS);
            conn= DriverManager.getConnection(URL,USER,PASS);
            stmt = conn.createStatement();
            
           String sql="Delete from employee_table where id="+id;
           int i = stmt.executeUpdate(sql);
           if(i>=0){
               System.out.println("record has deleted");
           }
            
            }
            catch(ClassNotFoundException | SQLException se)
            {
                System.out.println(se);
                
            }
            finally{
                try{
                   if(stmt!=null){
                       stmt.close();
                       
                   } 
                   if(conn!=null)
                   {
                       conn.close();
                   }
                    
                }
                catch(SQLException se){
                    System.out.println(se);
                }
            
        }
     
    }
          
}