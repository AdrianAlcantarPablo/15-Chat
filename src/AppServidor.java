import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import Servidor.ThreadEnvia;
import Servidor.ThreadRecibe;
import vista.VentanaServidor;

/**
 * 
 * @author Adrian
 *
 */

public class AppServidor {

	public static void main(String[] args) throws IOException {
		VentanaServidor vs=VentanaServidor.getInstancia();
		  ExecutorService executor = Executors.newCachedThreadPool(); 
		  /**
		   * Para correr los threads
		   */
		  vs.setVisible(true);
          vs.mostrarMensaje("Esperando Cliente ...");
          
        /**
         * Espera de conexiones de los clientes
         */
          while (true){
                  vs.conexion = vs.servidor.accept(); 
                  /**
                   * Permite al servidor aceptar conexiones        
                   */

                  vs.mostrarMensaje("Conectado a : " +vs. conexion.getInetAddress().getHostName());

                  vs.habilitarTexto(true); 
                  /**
                   * permite escribir texto para enviar
                   */

                  /**
                   * Ejecucion de los threads
                   */
                  executor.execute(new ThreadRecibe(vs.conexion, vs)); //client
                  executor.execute(new ThreadEnvia(vs.conexion, vs));
              
          }
          }
}
