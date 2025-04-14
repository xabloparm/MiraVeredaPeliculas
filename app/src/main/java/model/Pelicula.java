package model;

import java.io.Serializable;

public class Pelicula implements Serializable, Comparable<Pelicula>{
    private String nombre;
    private String cartel;
    private String detalle;

    public Pelicula(String nombre, String cartel, String detalle) {
        this.nombre = nombre;
        this.cartel = cartel;
        this.detalle = detalle;
    }

    public String getNombre() {
        return nombre;
    }
    public String getCartel() {
        return cartel;
    }

    public String getDetalle() {
        return detalle;
    }

    @Override
    public int compareTo(Pelicula pelicula) {
        return this.nombre.compareToIgnoreCase(pelicula.getNombre());
    }
}
