package vista;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument.Content;
import javax.xml.transform.Source;

/**
 * 
 * @author Adrian
 *
 */

public final class VentanaCliente extends JFrame implements ActionListener {
	
	/**
	 * Se declaran objetos 
	 */
		private static VentanaCliente v=new VentanaCliente();
	 	public JTextField txtMsgs=new JTextField();
	    public JTextArea areaTexto=new JTextArea();
	    private JTextField txtIp = new JTextField();
	    private JScrollPane scroll = new JScrollPane ( areaTexto );
	    private static ServerSocket servidor; 
	    public  static Socket cliente; 
	    public static String ip = "127.0.0.1"; 
		private Container c = getContentPane();

	    private VentanaCliente(){
	    		super.setTitle("Cliente");
	    		super.setSize(300, 400);
	    		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    		super.setLocation(100, 100);
	    		Controladores();
	    		 
	    }

		public static VentanaCliente getInstancia() {
			return v;
		}
		
		/**
		 * Se determinan las posiciones de los objetos en el frame
		 */
		private void Controladores(){
			c.setLayout(null);
				txtIp.setBounds(5	, 5, 200, 20);
				txtMsgs.setBounds(5	, 50, 270, 20);
				scroll.setBounds(8	, 80, 270, 300);
				areaTexto.setEditable(false);
				areaTexto.setBackground(Color.yellow);
				txtMsgs.setEditable(false); 
					c.add(txtIp);
					c.add(txtMsgs, BorderLayout.NORTH); 
					c.add(scroll);	  
						txtIp.addActionListener(this);
		}
		
		  public void mostrarMensaje(String mensaje) {
		        areaTexto.append(mensaje + "\n");
		    } 
		  
		    public void habilitarTexto(boolean editable) {
		        txtMsgs.setEditable(editable);
		    }

		@Override
		public void actionPerformed(ActionEvent e) {
			Object btn=  e.getSource();
			if(btn.equals(txtIp)){
				JOptionPane.showMessageDialog(null, "Hola");
			}
			
		}

	

}


