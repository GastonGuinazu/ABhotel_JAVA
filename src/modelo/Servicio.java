
package modelo;


public class Servicio {
    
    private int idServicio;
    private String concepto;
    private Habitacion habitacion;
    private float importe;

    public Servicio(int idServicio, String concepto, Habitacion habitacion, float importe) {
        this.idServicio = idServicio;
        this.concepto = concepto;
        this.habitacion = habitacion;
        this.importe = importe;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }
    
    
}
