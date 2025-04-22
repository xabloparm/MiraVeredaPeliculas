package model;

import java.io.Serializable;

public class Pelicula implements Serializable {

    private String nombre;
    private String cartel;
    private String descripcion;
    private double filmaffinityNota;
    private double popcometerNota;
    private double rottentomatoesNota;

    public Pelicula(String nombre, String cartel, String descripcion,
                    double filmaffinityNota, double popcometerNota, double rottentomatoesNota) {
        this.nombre = nombre;
        this.cartel = cartel;
        this.descripcion = descripcion;
        this.filmaffinityNota = filmaffinityNota;
        this.popcometerNota = popcometerNota;
        this.rottentomatoesNota = rottentomatoesNota;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCartel() {
        return cartel;
    }

    public String getDetalle() {
        return descripcion;
    }

    public double getFilmaffinityNota() {
        return filmaffinityNota;
    }

    public double getPopcometerNota() {
        return popcometerNota;
    }

    public double getRottentomatoesNota() {
        return rottentomatoesNota;
    }
}
