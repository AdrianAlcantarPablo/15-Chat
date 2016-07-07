import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import Cliente.ThreadEnvia;
import Cliente.ThreadRecibe;
import vista.VentanaCliente;

/**
 * 
 * @author Adrian
 *
 */

public class AppCliente {

	public static void main(String[] args) throws UnknownHostException, IOException {
		VentanaCliente vc=VentanaCliente.getInstancia();	
		vc.setVisible(true);
        vc.setLocationRelativeTo(null);   
        
        /**
         * Centrar el JFrame
         */
       
        ExecutorService executor = Executors.newCachedThreadPool(); 
        
        /**
         * Para correr los threads
         */
 
       
            vc.mostrarMensaje("Buscando Servidor ...");
            vc.cliente = new Socket(InetAddress.getByName(vc.ip), 11111); 
            /**
             * comunicacion con el servidor
             */
            vc.mostrarMensaje("Conectado a :" + vc.cliente.getInetAddress().getHostName());
    
            vc.habilitarTexto(true); 
            /**
             * habilita el texto
             */
            
            /**
             * Ejecucion de los Threads
             */
            executor.execute(new ThreadRecibe(vc.cliente, vc));
            executor.execute(new ThreadEnvia(vc.cliente, vc)); 
            
        
    }

}
