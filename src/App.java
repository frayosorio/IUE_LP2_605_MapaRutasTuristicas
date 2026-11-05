import controladores.MapaRutaControlador;
import vistas.MapaRutaVista;

public class App {
    public static void main(String[] args) throws Exception {
        var vista=new MapaRutaVista();
        vista.setVisible(true);

        var controlador=new MapaRutaControlador((vista));
    }
}