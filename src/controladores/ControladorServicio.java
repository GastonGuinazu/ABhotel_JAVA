package controladores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.DTO.consumoDTO;
import modelo.Habitacion;
import modelo.Servicio;

public class ControladorServicio {

    private String conexion;

    //Aca iria la cadena de conexion
    public ControladorServicio() {
        conexion = "jdbc:mysql://localhost:3306/hotel?user=root&password=gasti123";

        //SQL SERVER "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=HOTEL;TrustServerCertificate=true;", "sa", "1234"
    }

    public List<Habitacion> obtenerHabitaciones() {
        List<Habitacion> lst = new ArrayList<>();

        try (Connection cnn = DriverManager.getConnection(conexion);
                Statement st = cnn.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM habitaciones")) {

            while (rs.next()) {

                int id = rs.getInt("idHabitacion");
                String nombre = rs.getString("Denominacion");

                lst.add(new Habitacion(id, nombre));
            }

        } catch (Exception e) {

        }
        return lst;
    }

    public boolean registrarServicio(Servicio servicio) {
        boolean ok = true;

        try (Connection cnn = DriverManager.getConnection(conexion);
                PreparedStatement ps = cnn.prepareStatement("INSERT INTO servicios (concepto, idHabitacion, importe) VALUES(?, ?, ?)");) {

            ps.setString(1, servicio.getConcepto());
            ps.setInt(2, servicio.getHabitacion().getIdHabitacion());
            ps.setFloat(3, servicio.getImporte());

            ok = ps.execute();

        } catch (Exception e) {
            ok = false;
        }

        return ok;
    }
    
    public int obtenerReporte1(){
         int consulta = 0;    
        String query = "SELECT SUM(Importe) 'Importe' FROM servicios";
        
         try (Connection cnn = DriverManager.getConnection(conexion);
                Statement st = cnn.createStatement();
                ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {
                consulta += rs.getInt("Importe");                
  
            }
        } catch (Exception e) {

        }
        return consulta;
    }

    public List<consumoDTO> ObtenerReporte2() {

        List<consumoDTO> lst = new ArrayList<>();
        
        String query = "SELECT t.idHabitacion, t.denominacion, SUM(t2.Importe) 'Total', COUNT(t2.idHabitacion)'Cantidad'\n" +
"FROM habitaciones t, servicios t2 " +
"WHERE t.idHabitacion = t2.idHabitacion " +
"GROUP BY t.idHabitacion,t.denominacion " +
"HAVING COUNT(t2.idHabitacion) > 3;";

        try (Connection cnn = DriverManager.getConnection(conexion);
                Statement st = cnn.createStatement();
                ResultSet rs = st.executeQuery(query)) {

            while (rs.next()) {

                int id = rs.getInt("idHabitacion");
                String nombre = rs.getString("Denominacion");
                float total = rs.getFloat("total");
                int cantidad = rs.getInt ("cantidad");

                lst.add(new consumoDTO(id, nombre,total,cantidad));
            }

        } catch (Exception e) {

        }
        return lst;
    }

    public List<Servicio> obtenerReporte3(int idHabitacion) {
        List<Servicio> lst = new ArrayList<>();
        String query = "SELECT * FROM Servicios t WHERE idHabitacion = " + idHabitacion;
         try ( Connection cnn = DriverManager.getConnection(conexion);  Statement stmt = cnn.createStatement();  ResultSet rs = stmt.executeQuery(query)) {
            while(rs.next()) {
                int id = rs.getInt("idServicio");
                String concepto = rs.getString("concepto");
                float importe = rs.getFloat("importe");
                lst.add(new Servicio(id, concepto,null,importe)); //No necesitamos recuperar los datos de la habitaci??n
            }
        } catch (Exception e) {
        }
        return lst;
    }   

}
