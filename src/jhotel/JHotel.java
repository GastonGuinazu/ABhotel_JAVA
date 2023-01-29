
package jhotel;

import controladores.ControladorServicio;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import vistas.Menu;


public class JHotel {

    
    public static void main(String[] args) throws ClassNotFoundException {
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            new Menu(new ControladorServicio()).setVisible(true);          
               
        } catch (Exception ex) {
            Logger.getLogger(JHotel.class.getName()).log(Level.SEVERE, null, ex);
        }      
       
    }
    
}
