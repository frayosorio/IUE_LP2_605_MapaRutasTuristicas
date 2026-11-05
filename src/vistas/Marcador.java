package vistas;

import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;

public class Marcador extends DefaultWaypoint {

    private String nombre;

    public Marcador(GeoPosition coord, String nombre) {
        super(coord);
        this.nombre = nombre;
    }
}
