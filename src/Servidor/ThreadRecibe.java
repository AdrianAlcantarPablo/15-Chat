package Servidor;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import vista.VentanaServidor;

/**
 * 
 * @author Adrian
 *
 */

public class ThreadRecibe implements Runnable {
	private VentanaServidor vs=VentanaServidor.getInstancia();	
    private String mensaje; 
    private ObjectInputStream entrada;
    private Socket cliente;
   
    
   /**
    * Inicializar chatServer y configurar GUI
    * @param cliente
    * @param vs
    */
   public ThreadRecibe(Socket cliente,  VentanaServidor vs){
       this.cliente = cliente;
       this.vs = vs;
   }  

    public void mostrarMensaje(String mensaje) {
        vs.areaTexto.append(mensaje);
    } 
   
    public void run() {
        try {
            entrada = new ObjectInputStream(cliente.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ThreadRecibe.class.getName()).log(Level.SEVERE, null, ex);
        }
        do { 
        	/**
        	 * procesa los mensajes enviados dsd el servidor
        	 */
            try {
                mensaje = (String) entrada.readObject(); 
                vs.mostrarMensaje(mensaje);
            } 
            catch (SocketException ex) {
            }
            catch (EOFException eofException) {
                vs.mostrarMensaje("Fin de la conexion");
                break;
            }
            catch (IOException ex) {
                Logger.getLogger(ThreadRecibe.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException classNotFoundException) {
                vs.mostrarMensaje("Objeto desconocido");
            }             

        } while (!mensaje.equals("Servidor>>> TERMINATE")); 

        try {
            entrada.close(); 
            cliente.close(); 
        } 
        catch (IOException ioException) {
            ioException.printStackTrace();
        } 

        vs.mostrarMensaje("Fin de la conexion");
        System.exit(0);
    } 
} 
