/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package me.mycompany.sticky_rice_restaurant;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class DatabaseUtil {
    private static Connection connection;
    private static Connection createConnection(){
        
        try {
            Class.forName(SQLServerDriver.class.getName());
            String url = "jdbc:sqlserver://localhost;database=QLNH;integratedSecurity=false;user=sa;password=290420;encrypt=true;trustServerCertificate=true;";
            return DriverManager.getConnection(url);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException(ex);
        }
        
    }
    
    public  static  Connection getConnection(){
        try{
            if(connection == null || connection.isClosed()){
                connection = createConnection();
                
            }
            return connection;
            
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }
    
    
}
