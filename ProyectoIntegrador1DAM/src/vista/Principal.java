package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class Principal extends JFrame {

	private static final long serialVersionUID = 4327675116380983494L;

	/**
	 * Create the application.
	 */
	public Principal() {
		this.setTitle("Gestión de videoteca");
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setBounds(100, 100, 811, 489);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new Escuchador());
		mnArchivo.add(mntmSalir);
		
		JMenu mnMantenimiento = new JMenu("Mantenimiento");
		menuBar.add(mnMantenimiento);
		
		JMenu mnParticipantes = new JMenu("Participantes");
		mnMantenimiento.add(mnParticipantes);
		
		JMenuItem mntmParticipanteAlta = new JMenuItem("Alta");
		mntmParticipanteAlta.addActionListener(new Escuchador());
		mnParticipantes.add(mntmParticipanteAlta);
		
		JMenuItem mntmParticipanteBaja = new JMenuItem("Baja");
		mntmParticipanteBaja.addActionListener(new Escuchador());
		mnParticipantes.add(mntmParticipanteBaja);
		
		JMenuItem mntmParticipanteModificacion = new JMenuItem("Modificación");
		mntmParticipanteModificacion.addActionListener(new Escuchador());
		mnParticipantes.add(mntmParticipanteModificacion);
		
		JMenuItem mntmParticipanteConsulta = new JMenuItem("Consulta");
		mntmParticipanteConsulta.addActionListener(new Escuchador());
		mnParticipantes.add(mntmParticipanteConsulta);
		
		JMenu mnEjemplaresAudiovisuales = new JMenu("Ejemplares Audiovisuales");
		mnMantenimiento.add(mnEjemplaresAudiovisuales);
		
		JMenuItem mntmEjemplarAlta = new JMenuItem("Alta");
		mntmEjemplarAlta.addActionListener(new Escuchador());
		mnEjemplaresAudiovisuales.add(mntmEjemplarAlta);
		
		JMenuItem mntmEjemplarBaja = new JMenuItem("Baja");
		mntmEjemplarBaja.addActionListener(new Escuchador());
		mnEjemplaresAudiovisuales.add(mntmEjemplarBaja);
		
		JMenuItem mntmEjemplarModificacion = new JMenuItem("Modificación");
		mntmEjemplarModificacion.addActionListener(new Escuchador());
		mnEjemplaresAudiovisuales.add(mntmEjemplarModificacion);
		
		JMenuItem mntmEjemplarConsulta = new JMenuItem("Consulta");
		mntmEjemplarConsulta.addActionListener(new Escuchador());
		mnEjemplaresAudiovisuales.add(mntmEjemplarConsulta);
		
		JMenu mnDatosAuxiliares = new JMenu("Datos auxiliares");
		mnMantenimiento.add(mnDatosAuxiliares);
		
		JMenu mnPaises = new JMenu("Países");
		mnDatosAuxiliares.add(mnPaises);
		
		JMenuItem mntmPaisMantenimiento = new JMenuItem("Mantenimiento");
		mntmPaisMantenimiento.addActionListener(new Escuchador());
		mnPaises.add(mntmPaisMantenimiento);
		
		JMenu mnListados = new JMenu("Listados");
		menuBar.add(mnListados);
		
		JMenuItem mntmListadoParticipantes = new JMenuItem("Participantes");
		mntmListadoParticipantes.addActionListener(new Escuchador());
		mnListados.add(mntmListadoParticipantes);
		
		JMenuItem mntmListadoEjemplaresAudiovisuales = new JMenuItem("Ejemplares Audiovisuales");
		mntmListadoEjemplaresAudiovisuales.addActionListener(new Escuchador());
		mnListados.add(mntmListadoEjemplaresAudiovisuales);
	}

	public class Escuchador implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.format("%s %d %s\n", e.getActionCommand(), e.getID(), e.getSource().toString());			
		}
	
		
	}
	
}
