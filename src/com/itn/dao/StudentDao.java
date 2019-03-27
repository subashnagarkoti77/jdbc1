/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itn.dao;

import com.itn.model.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Script
 */
public class StudentDao {
    
    private static final String DRIVER_CLASS="com.mysql.jdbc.Driver";
    private static final String URL ="jdbc:mysql://localhost:3306/jdbc_demo";
    private static final String USER="root";
    private static final String PASS="";
    
    private static PreparedStatement connect(String sql) throws ClassNotFoundException,
            SQLException{
        Class.forName(DRIVER_CLASS);
        Connection conn = DriverManager.getConnection(URL, USER, PASS);
        return conn.prepareStatement(sql);
    }
    public static void saveStudent(Student s){
        PreparedStatement ps = null;
        
        try{
            String sql = "insert into student_table (name,address,phone) values(?,?,?)";
            ps=connect(sql);
            
            if(ps!=null){
               //if preparedStatement is available to Database operation
               ps.setString(1, s.getName());
               ps.setString(2, s.getAddress());
               ps.setLong(3, s.getPhone());
               
               //execute prepared statement after adding values dynamically
               int i = ps.executeUpdate();
               
               if(i>0){
                   System.out.println("Record inserted!");
               }
            }else{
                System.out.println("Database connection failure!");
            }
        }
        catch(ClassNotFoundException | SQLException se){
            System.out.println(se);
        }
        finally{
            try{
                if(ps!=null){
                    ps.close();
                }
            }catch(SQLException se){
                System.out.println(se);
            }
        }
    } 
        
        public static ArrayList<Student> getAllStudents(){
            PreparedStatement ps= null;
            ArrayList<Student> sl = new ArrayList();
                    
            try{
                String sql="select * from student_table";
                
                    ps=connect(sql);
                    
                    if(ps!=null){
                        ResultSet rs= ps.executeQuery();
                        
                        while(rs.next()){
                            sl.add(new Student(rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getLong("phone")
                            ));
                        }
                        
                    }
                    else{
                        System.out.println("Connection problem");
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
            
        return sl;
        }

        public static Student getStudentById(int id){
           PreparedStatement ps = null;
           Student s = null;
           try{
                String sql="select * from student_table where id=?";
                
                    ps=connect(sql);
                    
                    if(ps!=null){
                        ps.setInt(1,id);
                        ResultSet rs = ps.executeQuery();
                        if(rs.next())
                        {
                            s=new Student (rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getLong("phone"));
                        }
                    }
                    else{
                        System.out.println("Connection problem!");
                    }
               
           }catch (ClassNotFoundException |SQLException ex) {
               
                    Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
               try{
                   if(ps!=null){
                       ps.close();
                   }
               }
            
           catch (SQLException se) {
                    Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, se);
                
               
           }
           }
       
           return s;
        }      
        
        public static void updateStudent(Student s){
            PreparedStatement ps = null;
            try{
                String sql="update student_table set name =?,address=?,phone=? where id=?";
                
                    ps=connect(sql);
                    
                    if(ps!=null){
                        ps.setString(1, s.getName());
                        ps.setString(2,s.getAddress());
                        ps.setLong(3,s.getPhone());
                        ps.setInt(4,s.getId());
                        int i = ps.executeUpdate();
                        if(i>0){
                            System.out.println("Record updated");
                        }
                    }
        
                    else{
                        System.out.println("connection problem!");
                    }
                               }catch (ClassNotFoundException |SQLException ex) {
               
                    Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
               try{
                   if(ps!=null){
                       ps.close();
                   }
               }
            
           catch (SQLException se) {
                    Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, se);
                }
            }
        }
        
public void deleteStudent(int id){
    PreparedStatement ps = null;
    Student s = null;
            try{
                String sql="delete from student_table where id="+id;
                
                    ps=connect(sql);
                    
                        if(ps!=null){
                        ps.setInt(1,id);
                        ResultSet rs = ps.executeQuery();
                        if(rs.next())
                        {
                            s=new Student (rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("address"),
                            rs.getLong("phone"));
                        }
                    }
                    else{
                        System.out.println("Connection problem!");
                    }
                        
                    }catch (ClassNotFoundException |SQLException ex) {
               
                    Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, ex);
                }finally{
               try{
                   if(ps!=null){
                       ps.close();
                   }
               }
            
           catch (SQLException se) {
                    Logger.getLogger(StudentDao.class.getName()).log(Level.SEVERE, null, se);
                }
                        
                   
                            
                            }
    
    
}        
}

