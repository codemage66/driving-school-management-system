/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ijse.gdse41.drivingschool.dbconnection;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sandman
 */
public class DBConnection {
    private static DBConnection dbConnection;
    private Connection conn;
    
    private DBConnection() throws ClassNotFoundException, SQLException{
        try {
            Properties dbPro=new Properties();
            File dbfile=new File("dbSettings/settings.properties");
            FileReader dbFileReader=new FileReader(dbfile);
            dbPro.load(dbFileReader);
            String setDb=String.format("jdbc:mysql://%s/%s", dbPro.getProperty("ip"),dbPro.getProperty("database"));
            
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(setDb,dbPro.getProperty("user"),dbPro.getProperty("pass"));
        } catch (IOException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static DBConnection getDBConnection()throws ClassNotFoundException, SQLException{
        if (dbConnection==null){
            dbConnection= new DBConnection();
        }
        return dbConnection;
    }
    public Connection getConnection() {
        return conn;
    }
   
}
