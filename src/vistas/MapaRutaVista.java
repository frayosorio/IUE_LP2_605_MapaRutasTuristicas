package vistas;

import modelos.Nodo;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Set;

public class MapaRutaVista extends JFrame {

    private JButton btnAgregar, btnModificar, btnEliminar, btnGuardar, btnCargar;

    private JTree arbolRutas;
    private DefaultMutableTreeNode nodoRaiz;
    private JXMapViewer visorMapa;

    public MapaRutaVista() {
        setSize(1000, 650);
        setTitle("Gestión de Rutas Urbanas");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Barra de herramientas superior
        JToolBar barraHerramientas = new JToolBar();
        btnAgregar = new JButton("Agregar Parada");
        btnModificar = new JButton("Modificar Parada");
        btnEliminar = new JButton("Eliminar Parada");
        btnGuardar = new JButton("Guardar Ruta JSON");
        btnCargar = new JButton("Cargar Ruta JSON");

        barraHerramientas.add(btnAgregar);
        barraHerramientas.add(btnModificar);
        barraHerramientas.add(btnEliminar);
        barraHerramientas.add(btnGuardar);
        barraHerramientas.add(btnCargar);

        visorMapa = new JXMapViewer();

        TileFactoryInfo info = new TileFactoryInfo(1, 15, 17, 256, true, true,
                "https://tile.openstreetmap.org", "x", "y", "z") {
            @Override
            public String getTileUrl(int x, int y, int zoom) {
                return "https://tile.openstreetmap.org/" + (17 - zoom) + "/" + x + "/" + y + ".png";
            }
        };

        org.jxmapviewer.viewer.DefaultTileFactory tileFactory = new org.jxmapviewer.viewer.DefaultTileFactory(info);
        visorMapa.setTileFactory(tileFactory);

        nodoRaiz = new DefaultMutableTreeNode("Ruta Turística");
        arbolRutas = new JTree(nodoRaiz);

        JScrollPane scrollArbol = new JScrollPane(arbolRutas);
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollArbol, visorMapa);
        splitPane.setDividerLocation(260);

        add(barraHerramientas, BorderLayout.NORTH);
        add(splitPane, BorderLayout.CENTER);

        visorMapa.setZoom(17);
        visorMapa.setAddressLocation(new GeoPosition(6.24440601432547, -75.5734461516244));

        //evento de Movimiento del Ratón
        javax.swing.event.MouseInputListener mm = new org.jxmapviewer.input.PanMouseInputListener(visorMapa);
        visorMapa.addMouseListener(mm);
        visorMapa.addMouseMotionListener(mm);

    }

    //getters
    public Nodo getNodoSeleccionado() {
        DefaultMutableTreeNode nodoSeleccionado =
                (DefaultMutableTreeNode) arbolRutas.getLastSelectedPathComponent();

        if (nodoSeleccionado != null) {
            Object userObject = nodoSeleccionado.getUserObject();
            // Verificamos si el objeto almacenado en el nodo es una instancia de Nodo
            if (userObject instanceof Nodo) {
                return (Nodo) userObject;
            }
        }
        return null; // Retorna null si no hay nada seleccionado o no es un Nodo
    }


    //setters
    public void setNodosArbol(List<Nodo> nodos) {
        nodoRaiz.removeAllChildren();
        for (var nodo : nodos) {
            var nodoArbol = new DefaultMutableTreeNode(nodo);
            nodoRaiz.add(nodoArbol);
        }
        arbolRutas.updateUI();
    }

    public void setUbicacionMapa(double latitud, double longitud) {
        GeoPosition nuevaPosicion = new GeoPosition(latitud, longitud);
        visorMapa.setAddressLocation(nuevaPosicion);

        // Forzar al mapa a repintarse para reflejar el cambio de inmediato
        visorMapa.repaint();
    }

    public void setAgregarParada(ActionListener evento) {
        btnAgregar.addActionListener(evento);
    }

    public void setModificarParada(ActionListener evento) {
        btnModificar.addActionListener(evento);
    }

    public void setEliminarParada(ActionListener evento) {
        btnEliminar.addActionListener(evento);
    }

    public void setCargarRuta(ActionListener evento) {
        btnCargar.addActionListener(evento);
    }

    public void setGuardarRuta(ActionListener evento) {
        btnGuardar.addActionListener(evento);
    }

    public void setAlSeleccionarNodo(Runnable evento) {
        arbolRutas.addTreeSelectionListener(e -> {
            // Validamos que sea un evento de selección real y que haya un nodo
            if (e.isAddedPath()) {
                evento.run(); // Ejecuta la orden que le dio el controlador
            }
        });
    }

    // metodos publicos
    public void mostrarMarcadores(Set<Marcador> marcadores) {
        WaypointPainter<Marcador> trazadorMarcadores = new WaypointPainter<Marcador>();
        trazadorMarcadores.setWaypoints(marcadores);

        visorMapa.setOverlayPainter(trazadorMarcadores);
        visorMapa.repaint();
    }


}
