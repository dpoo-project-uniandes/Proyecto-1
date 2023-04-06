package hotel_system.models;

import java.util.List;

public class Alojamiento extends Servicio {
    private List<Habitacion> habitaciones;

    public Alojamiento(Long id, List<Habitacion> habitaciones) {
        super(id);
        this.habitaciones = habitaciones;
    }

    public void alojamiento(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }

    @Override
    public Factura facturar(Huesped titular) {
        // No se como se factura un alojamiento pero si es como servicio 
    	// Esto no va
        return super.facturar(titular);
    }

    @Override
    public Double valor() {
        // Sacar el valor de las habitaciones para el valor total no se como 
    	// Pero supongo que lo saco del csv 
        return super.valor();
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }
}
