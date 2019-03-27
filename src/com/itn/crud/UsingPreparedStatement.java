/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itn.crud;

import com.itn.model.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 *
 * @author Script
 */
public class UsingPreparedStatement {

    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/jdbc_demo";
    private static final String USER = "root";
    private static final String PASS = "";

    public PreparedStatement connect(String sql) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {

            Class.forName(DRIVER_CLASS);
            conn = DriverManager.getConnection(URL, USER, PASS);
            ps = conn.prepareStatement(sql);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return ps;
    }

    public void insert(Employee e) {
        //first write sql then prepare it to obtain preparedStatement
        String sql = "insert into employee _table (name,address,salary) values"
                + "(?,?,?)";
        PreparedStatement ps = connect(sql);

        if (ps != null) {
            //if preapred-statement is properly created
            try {
                //
                ps.setString(1, e.getName());
                ps.setString(2, e.getAddress());
                ps.setDouble(3, e.getSalary());
                int i = ps.executeUpdate();
                if (i > 0) {
                    System.out.println("Record inserted");
                }

            } catch (SQLException se) {
                System.out.println(se);

            } finally {
                try {
                    ps.close();

                } catch (SQLException se) {
                    System.out.println(se);
                }
            }
        } else {
            System.out.println("Database operation couldnot be preformed!");
        }
    }
}
