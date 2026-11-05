package controladores;

import modelos.Nodo;
import org.jxmapviewer.viewer.GeoPosition;
import vistas.MapaRutaVista;
import vistas.Marcador;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MapaRutaControlador {

    private final MapaRutaVista vista;

    public MapaRutaControlador(MapaRutaVista vista) {
        this.vista = vista;
        this.vista.setAlSeleccionarNodo(() -> {
            alSeleccionarNodo();
        });

/*
        List<Nodo> lista = new ArrayList();
        lista.add(new Nodo());
        lista.add(new Nodo());
        this.vista.setNodosArbol(lista);

        this.vista.setUbicacionMapa(48.85870456606569, 2.2952820548205253);

        Set<Marcador> marcadores = new HashSet<>();
        marcadores.add(new Marcador(new GeoPosition(48.85870456606569, 2.2952820548205253), "Torre Eiffel"));
        marcadores.add(new Marcador(new GeoPosition(48.874248466188135, 2.2952329840775194), "Arco del Triunfo"));
        marcadores.add(new Marcador(new GeoPosition(48.86097052218563, 2.3361854832153397), "Museo de Louvre"));


        this.vista.mostrarMarcadores(marcadores);
*/

    }

    private void alSeleccionarNodo() {
        //vista.setUbicacionMapa(48.874248466188135, 2.2952329840775194);
    }

}
