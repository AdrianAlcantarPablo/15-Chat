package Cliente;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import vista.VentanaCliente;

/**
 * 
 * @author Adrian
 *
 */
public class ThreadRecibe implements Runnable {
	private VentanaCliente vc=VentanaCliente.getInstancia();	
    private String mensaje; 
    private ObjectInputStream entrada;
    private Socket cliente;
   
    
   /**
    * Inicializar chatServer y configurar GUI
    * @param cliente
    * @param vc
    */
   public ThreadRecibe(Socket cliente, VentanaCliente vc){
       this.cliente = cliente;
       this.vc = vc;
   }  

    public void mostrarMensaje(String mensaje) {
        vc.areaTexto.append(mensaje);
    } 
   
    public void run() {
        try {
            entrada = new ObjectInputStream(cliente.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ThreadRecibe.class.getName()).log(Level.SEVERE, null, ex);
        }
        do { 
        	/**
        	 * procesa los mensajes enviados desde el servidor
        	 */
            try { 
                mensaje = (String) entrada.readObject(); 
                vc.mostrarMensaje(mensaje);
            } 
            catch (SocketException ex) {
            }
            catch (EOFException eofException) {
                vc.mostrarMensaje("Fin de la conexion");
                
            } 
            catch (IOException ex) {
                Logger.getLogger(ThreadRecibe.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException classNotFoundException) {
                vc.mostrarMensaje("Objeto desconocido");
            }               

        } while (!mensaje.equals("Cliente>>> TERMINATE")); 
        /**
         * Ejecuta hasta que el server escriba TERMINATE
         */

        try {
            entrada.close(); 
            cliente.close(); 
        } 
        catch (IOException ioException) {
            ioException.printStackTrace();
        } 

        vc.mostrarMensaje("Fin de la conexion");
        System.exit(0);
    }
        
    
    
      
} 
