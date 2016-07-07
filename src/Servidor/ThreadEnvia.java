package Servidor;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.SocketException;
import vista.VentanaServidor;

/**
 * 
 * @author Adrian
 *
 */
        
public class ThreadEnvia  implements Runnable, ActionListener  {
    private VentanaServidor vs=VentanaServidor.getInstancia();	
    private ObjectOutputStream salida;
    private String mensaje;
    private Socket conexion; 
   
    public ThreadEnvia(Socket conexion, final VentanaServidor vs){
        this.conexion = conexion;
        this.vs = vs;
        vs.txtMsgs.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                mensaje = event.getActionCommand();
                enviarDatos(mensaje); 
                vs.txtMsgs.setText(""); 
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
         salida.writeObject("Servidor: " + mensaje);
         salida.flush(); //flush salida a cliente
         vs.mostrarMensaje("Servidor: " + mensaje);
      } 
      catch (IOException ioException){ 
         vs.mostrarMensaje("Error escribiendo Mensaje");
      }
      
   } 

    public void mostrarMensaje(String mensaje) {
        vs.areaTexto.append(mensaje);
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



	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}


 
   
   
} 
