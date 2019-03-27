/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itn.app;

import com.itn.dao.EmployeeDao;
import com.itn.model.Employee;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Script
 */
public class EmpMgmt {
     private static Scanner sc= new Scanner (System.in);
//     public Employee newEmployee(Employee e()){
     // it is used when we try not to pass new method for update
     //if we use this this the updateEmployee(int id) method is not required
     public Employee newEmployee(){
     
 
  Employee emp = new Employee();
  sc.nextLine();
      System.out.println("ENter your name: ");
      emp.setName(sc.nextLine());
      System.out.println("ENter your address:");
      emp.setAddress(sc.nextLine());
      System.out.println("Enter your salary: ");
      emp.setSalary(sc.nextDouble());
      return emp;
  }
  public Employee updateEmployee(int id){
      Employee emp = new Employee();
      emp.setId(id);
      sc.nextLine();
      System.out.println("ENter your name: ");
      emp.setName(sc.nextLine());
      System.out.println("ENter your address:");
      emp.setAddress(sc.nextLine());
      System.out.println("Enter your salary: ");
      emp.setSalary(sc.nextDouble());
      return emp;
  }
  public void displayEmployee(Employee e){
      System.out.println(e.toString());
  }
  public void menu(){
      
      System.out.println("Welcome to employee management system:");
      System.out.println("Employee management services");
      System.out.println("1.Add new employee. \n2. View all employees.  \n"
      +"3.View selected employee. \n 4.Update employee record \n 5.Delete employee table");
  }
    public static void main(String[] args) {
        System.out.println("Welcome to employee management: ");
        EmpMgmt em= new EmpMgmt();
        EmployeeDao empDao = new EmployeeDao();
//        Employee e= em.newEmployee();
//        EmployeeDao empDao=new EmployeeDao();
//        //insert operation
//        empDao.insertEmployee(e);
//        
//        //view database records
//        ArrayList<Employee> al= empDao.getAllEmployee();
//        
//        for(Employee emp:al){
//            em.displayEmployee(emp);
//            
//        }
    while(true){
    em.menu();
    int choice = sc.nextInt();
    switch (choice){
        case 1: //Insert employee record
            Employee e = em.newEmployee();
            //newEmployee fills a employee information into default employee object
            //and returns that object
            empDao.insertEmployee(e);
            break;
        case 2: //Select all employee record
            ArrayList<Employee> al = empDao.getAllEmployee();
            for(Employee e1:al){
                em.displayEmployee(e1);
            }
            break;
        case 3: //select one employee with given id
            System.out.println("Enter id for employee: ");
            int id = sc.nextInt();
            e= empDao.getEmployeeById(id);
            
            if(e!=null){
                em.displayEmployee(e);
                
            } else{
                System.out.println("Record of employee with id "+id+"not found!");
            }
            break;
            
        case 4: //update employee record
            System.out.println("Enter id of employee to update");
            id= sc.nextInt();
            //display employee and then update
              e=empDao.getEmployeeById(id);
              if(e!=null){
                  em.displayEmployee(e);
                  //update this employee
                  e=em.updateEmployee(id);
                  empDao.updateEmployee(e);
              }else{
                  System.out.println("Record of employee not found!");
              }
              break;
        case 5:
            System.out.println("Delete record of employee with id: ");
            id=sc.nextInt();
            //display before deleting and confirming
            e=empDao.getEmployeeById(id);
            if(e!=null){
                //delete if exists
                //display before deleting
                em.displayEmployee(e);
                System.out.println("Do you really want to delete?(y/n) ");
                if(sc.next().charAt(0)=='y'){
                    empDao.deleteEmployee(id);
                    
                }
                else{
                    System.out.println("Employee record not found!");
                }
            }
            break;
        default:
            System.out.println("Wrong choice selected!");
            break;
    }
    }
    }
}
