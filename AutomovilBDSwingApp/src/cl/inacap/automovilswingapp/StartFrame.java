package cl.inacap.automovilswingapp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cl.inacap.automovilswingapp.frame.EliminarAutomovilFrame;
import cl.inacap.automovilswingapp.frame.IngresarAutomovilFrame;
import cl.inacap.automovilswingapp.frame.ModificarAutomovilFrame;
import cl.inacap.automovilswingapp.frame.MostrarAutomovilFrame;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;

import javax.swing.JDesktopPane;

public class StartFrame extends JFrame {
	private IngresarAutomovilFrame ingresarAutomovilFrame; //Relaci�n de tipo HAS-A (Porque el JFrame tiene JInternalFrame).
	private MostrarAutomovilFrame mostrarAutomovilFrame; //Relaci�n de tipo HAS-A (Porque el JFrame tiene JInternalFrame).
	private EliminarAutomovilFrame eliminarAutomovilFrame; //Relaci�n de tipo HAS-A (Porque el JFrame tiene JInternalFrame).
	private ModificarAutomovilFrame modificarAutomovilFrame; //Relaci�n de tipo HAS-A (Porque el JFrame tiene JInternalFrame).
	private JPanel contentPane;
	private JDesktopPane desktopPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Proceso de creaci�n del frame.
	public StartFrame() {
		setTitle("AutomovilSwingApp"); //Es es el titulo del frame, se muestra al lado del icono cuando se ejecuta el programa. 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 652);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOpciones = new JMenu("Opciones");
		menuBar.add(mnOpciones);
		
		//Creaci�n del JMenuItem (Ingresar Automovil).
		JMenuItem mntmIngresarAutomovil = new JMenuItem("Ingresar Automovil"); //Aca se crea el JMenuItem llamado (Ingresar Automovil).
		mntmIngresarAutomovil.addActionListener(e -> showFrameIngresarAutomovil(e)); //Aca se a�ade el Listener del JmenuItem llamado (Ingresar Automovil) el cual llama al m�todo llamado showFrameIngresarAutomovil().
		mnOpciones.add(mntmIngresarAutomovil);
		
		//Creaci�n del JMenuItem (Mostrar Automoviles).
		JMenuItem mntmMostrarAutomoviles = new JMenuItem("Mostrar Automoviles"); //Aca se crea el JMenuItem llamado (Mostrar Automovil).
		mntmMostrarAutomoviles.addActionListener(e -> showFrameMostrarAutomovil(e)); //Aca se a�ade el Listener del JmenuItem llamado (Mostrar Automovil) el cual llama al m�todo llamado showFrameMostrarAutomovil().
		mnOpciones.add(mntmMostrarAutomoviles);
		
		//Creaci�n del JMenuItem (Modificar Automovil).
		JMenuItem mntmModificarAutomoviles = new JMenuItem("Modificar Automoviles"); //Aca se crea el JMenuItem llamado (Modificar Automoviles).
		mntmModificarAutomoviles.addActionListener(e -> showFrameModificarAutomoviles(e)); //Aca se a�ade el Listener del JmenuItem llamado (Modificar Automoviles) el cual llama al m�todo llamado showFrameModificarAutomoviles().
		mnOpciones.add(mntmModificarAutomoviles);
		
		//Creaci�n del JMenuItem (Eliminar Automovil).
		JMenuItem mntmEliminarAutomovil = new JMenuItem("Eliminar Automovil"); //Aca se crea el JMenuItem llamado (Eliminar Automovil).
		mntmEliminarAutomovil.addActionListener(e -> showFrameEliminarAutomovil(e)); //Aca se a�ade el Listener del JmenuItem llamado (Eliminar Automovil) el cual llama al m�todo llamado showFrameMostrarAutomovil().
		mnOpciones.add(mntmEliminarAutomovil);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, BorderLayout.CENTER);
	}

	//Codigo a ejecutar del Listener del componente JMenuItem llamado (Ingresar Automovil).
	private void showFrameIngresarAutomovil(ActionEvent e) {
		if(this.ingresarAutomovilFrame != null) { //Verifica si el usuario ya habia abierto el frame antes (Esta es la variable que est� arriba como atributo).
			desktopPane.remove(this.ingresarAutomovilFrame); //Esta instrucci�n elimina el frame existente si el usuario ya lo tenia abierto (Se debe utilizar el objeto desktopPane porque es all� donde st� agregado el frame).
		}
		this.ingresarAutomovilFrame = new IngresarAutomovilFrame(); //Proceso de creaci�n del nuevo frame (Ya que el anterior se borr�).
		desktopPane.add(this.ingresarAutomovilFrame); //Agregar el frame creado al desktopPane (Se debe utilizar el objeto desktopPane porque es all� donde st� agregado el frame).
		this.ingresarAutomovilFrame.setVisible(true); //Hacer visible el nuevo frame.
	}

	//Codigo a ejecutar del Listener del componente JMenuItem llamado (Mostrar Automovil).
	private void showFrameMostrarAutomovil(ActionEvent e) {
		if(this.mostrarAutomovilFrame != null) { //Verifica si el usuario ya habia abierto el frame antes (Esta es la variable que est� arriba como atributo).
			desktopPane.remove(this.mostrarAutomovilFrame); //Esta instrucci�n elimina el frame existente si el usuario ya lo tenia abierto (Se debe utilizar el objeto desktopPane porque es all� donde st� agregado el frame).
		}
		this.mostrarAutomovilFrame = new MostrarAutomovilFrame(); //Proceso de creaci�n del nuevo frame (Ya que el anterior se borr�).
		desktopPane.add(this.mostrarAutomovilFrame); //Agregar el frame creado al desktopPane (Se debe utilizar el objeto desktopPane porque es all� donde st� agregado el frame).
		this.mostrarAutomovilFrame.setVisible(true); //Hacer visible el nuevo frame.
		
	}
	
	//Codigo a ejecutar del Listener del componente JMenuItem llamado (Eliminar Automovil).
	private void showFrameEliminarAutomovil(ActionEvent e) {
		if(this.eliminarAutomovilFrame != null) { //Verifica si el usuario ya habia abierto el frame antes (Esta es la variable que est� arriba como atributo).
			desktopPane.remove(this.eliminarAutomovilFrame); //Esta instrucci�n elimina el frame existente si el usuario ya lo tenia abierto (Se debe utilizar el objeto desktopPane porque es all� donde st� agregado el frame).
		}
		this.eliminarAutomovilFrame = new EliminarAutomovilFrame(); //Proceso de creaci�n del nuevo frame (Ya que el anterior se borr�).
		desktopPane.add(this.eliminarAutomovilFrame); //Agregar el frame creado al desktopPane (Se debe utilizar el objeto desktopPane porque es all� donde st� agregado el frame).
		this.eliminarAutomovilFrame.setVisible(true); //Hacer visible el nuevo frame.
		
	}
	
	//Codigo a ejecutar del Listener del componente JMenuItem llamado (Modificar Automoviles).
	private void showFrameModificarAutomoviles(ActionEvent e) {
		if(this.modificarAutomovilFrame != null) { //Verifica si el usuario ya habia abierto el frame antes (Esta es la variable que est� arriba como atributo).
			desktopPane.remove(this.modificarAutomovilFrame); //Esta instrucci�n elimina el frame existente si el usuario ya lo tenia abierto (Se debe utilizar el objeto desktopPane porque es all� donde st� agregado el frame).
		}
		this.modificarAutomovilFrame = new ModificarAutomovilFrame(); //Proceso de creaci�n del nuevo frame (Ya que el anterior se borr�).
		desktopPane.add(this.modificarAutomovilFrame); //Agregar el frame creado al desktopPane (Se debe utilizar el objeto desktopPane porque es all� donde st� agregado el frame).
		this.modificarAutomovilFrame.setVisible(true); //Hacer visible el nuevo frame.
	}

}
