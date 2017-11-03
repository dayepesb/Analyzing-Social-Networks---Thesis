package co.edu.poligran.FileChooser;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
 
public class EjemploJFileChooser extends JFrame {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField textField;
    private JTextArea textArea;
 
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    EjemploJFileChooser frame = new EjemploJFileChooser();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
 
    /**
     * Create the frame.
     */
    public EjemploJFileChooser() {
 
        //Parametros asociados a la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
 
        textField = new JTextField();
        textField.setToolTipText("Inserta la ruta del fichero de audio");
        textField.setBounds(52, 26, 209, 20);
        contentPane.add(textField);
        textField.setColumns(10);
 
        JButton btnSeleccionar = new JButton("Seleccionar...");
        btnSeleccionar.setBounds(288, 25, 109, 23);
        contentPane.add(btnSeleccionar);
 
        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBounds(52, 76, 360, 156);
 
        JScrollPane scroll=new JScrollPane(textArea);
        scroll.setBounds(52, 76, 360, 156);
        contentPane.add(scroll);
 
        btnSeleccionar.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent e){
            	
            	//Creamos el objeto JFileChooser
            	JFileChooser fc=new JFileChooser();
            	 
            	//Indicamos que podemos seleccionar varios ficheros
            	fc.setMultiSelectionEnabled(true);
            	 
            	//Indicamos lo que podemos seleccionar
            	fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            	 
            	//Creamos el filtro
            	FileNameExtensionFilter filtro = new FileNameExtensionFilter("*.TXT", "txt");
            	 
            	//Le indicamos el filtro
            	fc.setFileFilter(filtro);
            	 
            	//Abrimos la ventana, guardamos la opcion seleccionada por el usuario
            	int seleccion=fc.showOpenDialog(contentPane);
            	 
            	//Si el usuario, pincha en aceptar
            	if(seleccion==JFileChooser.APPROVE_OPTION){
            	 
            	    //Seleccionamos el fichero
            	    File[] ficheros=fc.getSelectedFiles();
            	 
            	    for(int i=0;i<ficheros.length;i++){
            	        try(FileReader fr=new FileReader(ficheros[i])){
            	            String cadena="";
            	            int valor=fr.read();
            	            while(valor!=-1){
            	                cadena=cadena+(char)valor;
            	                valor=fr.read();
            	            }
            	            textArea.append(cadena+"\n");
            	        } catch (IOException e1) {
            	            e1.printStackTrace();
            	        }
            	    }
            	}
            	
            }
        });
 
    }
}