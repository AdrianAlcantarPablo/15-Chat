package Cliente;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import vista.VentanaCliente;

/**
 * 
 * @author Adrian
 *
 */
        
public class ThreadEnvia implements Runnable {
	private VentanaCliente vc=VentanaCliente.getInstancia();
    private ObjectOutputStream salida;
    private String mensaje;
    private Socket conexion; 
   
    public ThreadEnvia(Socket conexion, final VentanaCliente main){
        this.conexion = conexion;
        this.vc = main;

        vc.txtMsgs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                mensaje = event.getActionCommand();
                enviarDatos(mensaje); 
                /**
                 * se envia el mensaje
                 */
                vc.txtMsgs.setText(""); 
                /**
                 * borra el texto del enterfield
                 */
            } 
        } 
        );
    } 
    
   /**
    * enviar objeto a cliente 
    * @param mensaje
    */
   private void enviarDatos(String mensaje){
      try {
         salida.writeObject("Cliente: " + mensaje);
         salida.flush(); 
         vc.mostrarMensaje("Cliente: " + mensaje);
      }
      catch (IOException ioException){ 
         vc.mostrarMensaje("Error escribiendo Mensaje");
      } 
      
   } 

   /**
    * manipula areaPantalla en el hilo despachador de eventos
    * @param mensaje
    */
    public void mostrarMensaje(String mensaje) {
        vc.areaTexto.append(mensaje);
    } 
   
    public void run() {
         try {
            salida = new ObjectOutputStream(conexion.getOutputStream());
            salida.flush(); 
        } catch (SocketException ex) {
        } catch (IOException ioException) {
          ioException.printStackTrace();
        } catch (NullPointerException ex) {
        }
    }   
   
} 
