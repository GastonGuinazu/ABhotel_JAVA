
package modelo;


public class Habitacion {
    
    private int idHabitacion;
    private String denominacion;

    public Habitacion(int idHabitacion, String denominacion) {
        this.idHabitacion = idHabitacion;
        this.denominacion = denominacion;
    }

    public int getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(int idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    @Override
    public String toString() {
        return String.valueOf(idHabitacion) + ", " + denominacion;
    }
    
    
}
