package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class PantallaPrincipal extends JFrame {

	private static final long serialVersionUID = 4327675116380983494L;
	
	private JLabel status;

	private JMenuItem mntmSalir;
	private JMenuItem mntmDirectorAlta;
	private JMenuItem mntmDirectorBaja;
	private JMenuItem mntmDirectorModificacion;
	private JMenuItem mntmDirectorConsulta;
	private JMenuItem mntmInterpreteAlta;
	private JMenuItem mntmInterpreteBaja;
	private JMenuItem mntmInterpreteModificacion;
	private JMenuItem mntmInterpreteConsulta;
	private JMenuItem mntmPeliculaAlta;
	private JMenuItem mntmPeliculaBaja;
	private JMenuItem mntmPeliculaModificacion;
	private JMenuItem mntmPeliculaConsulta;
	private JMenuItem mntmDocumentalAlta;
	private JMenuItem mntmDocumentalBaja;
	private JMenuItem mntmDocumentalModificacion;
	private JMenuItem mntmDocumentalConsulta;
	private JMenuItem mntmPaisMantenimiento;
	private JMenuItem mntmListadoParticipantes;
	private JMenuItem mntmListadoEjemplaresAudiovisuales;

	public PantallaPrincipal() {
				
		this.setTitle("Gestión de videoteca");
		this.setResizable(false);
		this.setBounds(100, 100, 900, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
															// Menú Archivo
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
															// Opción Salir		
		setMntmSalir(new JMenuItem("Salir"));
		getMntmSalir().setActionCommand("menuSalir");
		mnArchivo.add(getMntmSalir());
															// Menú Mantenimiento
		JMenu mnMantenimiento = new JMenu("Mantenimiento");
		menuBar.add(mnMantenimiento);
		
		JMenu mnParticipantes = new JMenu("Participantes");
		mnMantenimiento.add(mnParticipantes);
		
		JMenu mnDirectores = new JMenu("Directores");
		mnDirectores.setActionCommand("Directores");
		mnParticipantes.add(mnDirectores);
		
		setMntmDirectorAlta(new JMenuItem("Alta"));
		getMntmDirectorAlta().setActionCommand("menuDirectorAlta");
		mnDirectores.add(getMntmDirectorAlta());
		
		setMntmDirectorBaja(new JMenuItem("Baja"));
		getMntmDirectorBaja().setActionCommand("menuDirectorBaja");
		mnDirectores.add(getMntmDirectorBaja());
		
		setMntmDirectorModificacion(new JMenuItem("Modificación"));
		getMntmDirectorModificacion().setActionCommand("menuDirectorModificacion");
		mnDirectores.add(getMntmDirectorModificacion());
		
		setMntmDirectorConsulta(new JMenuItem("Consulta"));
		getMntmDirectorConsulta().setActionCommand("menuDirectorConsulta");
		mnDirectores.add(getMntmDirectorConsulta());
		
		JMenu mnInterpretes = new JMenu("Interpretes");
		mnInterpretes.setActionCommand("Interpretes");
		mnParticipantes.add(mnInterpretes);
		
		setMntmInterpreteAlta(new JMenuItem("Alta"));
		getMntmInterpreteAlta().setActionCommand("menuInterpreteAlta");
		mnInterpretes.add(getMntmInterpreteAlta());
		
		setMntmInterpreteBaja(new JMenuItem("Baja"));
		getMntmInterpreteBaja().setActionCommand("menuInterpreteBaja");
		mnInterpretes.add(getMntmInterpreteBaja());
		
		setMntmInterpreteModificacion(new JMenuItem("Modificación"));
		getMntmInterpreteModificacion().setActionCommand("menuInterpreteModificacion");
		mnInterpretes.add(getMntmInterpreteModificacion());
		
		setMntmInterpreteConsulta(new JMenuItem("Consulta"));
		getMntmInterpreteConsulta().setActionCommand("menuInterpreteConsulta");
		mnInterpretes.add(getMntmInterpreteConsulta());		
		
		JMenu mnEjemplaresAudiovisuales = new JMenu("Ejemplares Audiovisuales");
		mnMantenimiento.add(mnEjemplaresAudiovisuales);
		
		JMenu mnPeliculas = new JMenu("Películas");
		mnEjemplaresAudiovisuales.add(mnPeliculas);
		
		setMntmPeliculaAlta(new JMenuItem("Alta"));
		getMntmPeliculaAlta().setActionCommand("menuPeliculaAlta");
		mnPeliculas.add(getMntmPeliculaAlta());
		
		setMntmPeliculaBaja(new JMenuItem("Baja"));
		getMntmPeliculaBaja().setActionCommand("menuPeliculaBaja");
		mnPeliculas.add(getMntmPeliculaBaja());
		
		setMntmPeliculaModificacion(new JMenuItem("Modificación"));
		getMntmPeliculaModificacion().setActionCommand("menuPeliculaModificación");
		mnPeliculas.add(getMntmPeliculaModificacion());
		
		setMntmPeliculaConsulta(new JMenuItem("Consulta"));
		getMntmPeliculaConsulta().setActionCommand("menuPeliculaConsulta");
		mnPeliculas.add(getMntmPeliculaConsulta());
		
		JMenu mnDocumentales = new JMenu("Documentales");
		mnEjemplaresAudiovisuales.add(mnDocumentales);
		
		setMntmDocumentalAlta(new JMenuItem("Alta"));
		getMntmDocumentalAlta().setActionCommand("menuDocumentalAlta");
		mnDocumentales.add(getMntmDocumentalAlta());
		
		setMntmDocumentalBaja(new JMenuItem("Baja"));
		getMntmDocumentalBaja().setActionCommand("menuDocumentalBaja");
		mnDocumentales.add(getMntmDocumentalBaja());
		
		setMntmDocumentalModificacion(new JMenuItem("Modificación"));
		getMntmDocumentalModificacion().setActionCommand("menuDocumentalModificación");
		mnDocumentales.add(getMntmDocumentalModificacion());
		
		setMntmDocumentalConsulta(new JMenuItem("Consulta"));
		getMntmDocumentalConsulta().setActionCommand("menuDocumentalConsulta");
		mnDocumentales.add(getMntmDocumentalConsulta());
		
		JMenu mnDatosAuxiliares = new JMenu("Estructuras");
		mnMantenimiento.add(mnDatosAuxiliares);
		
		JMenu mnPaises = new JMenu("Países");
		mnDatosAuxiliares.add(mnPaises);
		
		setMntmPaisMantenimiento(new JMenuItem("Mantenimiento"));
		getMntmPaisMantenimiento().setActionCommand("menuEstructurasPaisesMantenimiento");
		mnPaises.add(getMntmPaisMantenimiento());
		
		JMenu mnListados = new JMenu("Listados");
		menuBar.add(mnListados);
		
		setMntmListadoParticipantes(new JMenuItem("Participantes"));
		getMntmListadoParticipantes().setActionCommand("menuListadosParticipantes");
		mnListados.add(getMntmListadoParticipantes());
		
		setMntmListadoEjemplaresAudiovisuales(new JMenuItem("Ejemplares Audiovisuales"));
		getMntmListadoEjemplaresAudiovisuales().setActionCommand("menuListadosEjemplares");
		mnListados.add(getMntmListadoEjemplaresAudiovisuales());
		
		JPanel statusBar = new JPanel();
		statusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		this.setStatus(new JLabel("Bienvenido a la aplicación Gestión de videoteca"));
		this.getStatus().setForeground(Color.RED);
		
		statusBar.add(this.getStatus());
		statusBar.setPreferredSize(new Dimension(this.getWidth(), 22));
		statusBar.setLayout(new BoxLayout(statusBar, BoxLayout.X_AXIS));
		status.setHorizontalAlignment(JLabel.LEFT);
		status.setVerticalAlignment(JLabel.CENTER);
		getContentPane().add(statusBar, BorderLayout.SOUTH);
		
	}
	
	//GETTERS & SETTERS
	public JLabel getStatus() {
		return status;
	}
	
	public void setStatus(JLabel status) {
		this.status = status;
	}
	
	public JMenuItem getMntmSalir() {
		return mntmSalir;
	}

	public void setMntmSalir(JMenuItem mntmSalir) {
		this.mntmSalir = mntmSalir;
	}

	public JMenuItem getMntmPaisMantenimiento() {
		return mntmPaisMantenimiento;
	}

	public void setMntmPaisMantenimiento(JMenuItem mntmPaisMantenimiento) {
		this.mntmPaisMantenimiento = mntmPaisMantenimiento;
	}

	public JMenuItem getMntmListadoParticipantes() {
		return mntmListadoParticipantes;
	}

	public void setMntmListadoParticipantes(JMenuItem mntmListadoParticipantes) {
		this.mntmListadoParticipantes = mntmListadoParticipantes;
	}

	public JMenuItem getMntmListadoEjemplaresAudiovisuales() {
		return mntmListadoEjemplaresAudiovisuales;
	}

	public void setMntmListadoEjemplaresAudiovisuales(JMenuItem mntmListadoEjemplaresAudiovisuales) {
		this.mntmListadoEjemplaresAudiovisuales = mntmListadoEjemplaresAudiovisuales;
	}

	public JMenuItem getMntmDirectorAlta() {
		return mntmDirectorAlta;
	}

	public void setMntmDirectorAlta(JMenuItem mntmDirectorAlta) {
		this.mntmDirectorAlta = mntmDirectorAlta;
	}

	public JMenuItem getMntmDirectorBaja() {
		return mntmDirectorBaja;
	}

	public void setMntmDirectorBaja(JMenuItem mntmDirectorBaja) {
		this.mntmDirectorBaja = mntmDirectorBaja;
	}

	public JMenuItem getMntmDirectorModificacion() {
		return mntmDirectorModificacion;
	}

	public void setMntmDirectorModificacion(JMenuItem mntmDirectorModificacion) {
		this.mntmDirectorModificacion = mntmDirectorModificacion;
	}

	public JMenuItem getMntmDirectorConsulta() {
		return mntmDirectorConsulta;
	}

	public void setMntmDirectorConsulta(JMenuItem mntmDirectorConsulta) {
		this.mntmDirectorConsulta = mntmDirectorConsulta;
	}

	public JMenuItem getMntmInterpreteAlta() {
		return mntmInterpreteAlta;
	}

	public void setMntmInterpreteAlta(JMenuItem mntmInterpreteAlta) {
		this.mntmInterpreteAlta = mntmInterpreteAlta;
	}

	public JMenuItem getMntmInterpreteBaja() {
		return mntmInterpreteBaja;
	}

	public void setMntmInterpreteBaja(JMenuItem mntmInterpreteBaja) {
		this.mntmInterpreteBaja = mntmInterpreteBaja;
	}

	public JMenuItem getMntmInterpreteModificacion() {
		return mntmInterpreteModificacion;
	}

	public void setMntmInterpreteModificacion(JMenuItem mntmInterpreteModificacion) {
		this.mntmInterpreteModificacion = mntmInterpreteModificacion;
	}

	public JMenuItem getMntmInterpreteConsulta() {
		return mntmInterpreteConsulta;
	}

	public void setMntmInterpreteConsulta(JMenuItem mntmInterpreteConsulta) {
		this.mntmInterpreteConsulta = mntmInterpreteConsulta;
	}

	public JMenuItem getMntmPeliculaAlta() {
		return mntmPeliculaAlta;
	}

	public void setMntmPeliculaAlta(JMenuItem mntmPeliculaAlta) {
		this.mntmPeliculaAlta = mntmPeliculaAlta;
	}

	public JMenuItem getMntmPeliculaBaja() {
		return mntmPeliculaBaja;
	}

	public void setMntmPeliculaBaja(JMenuItem mntmPeliculaBaja) {
		this.mntmPeliculaBaja = mntmPeliculaBaja;
	}

	public JMenuItem getMntmPeliculaModificacion() {
		return mntmPeliculaModificacion;
	}

	public void setMntmPeliculaModificacion(JMenuItem mntmPeliculaModificacion) {
		this.mntmPeliculaModificacion = mntmPeliculaModificacion;
	}

	public JMenuItem getMntmPeliculaConsulta() {
		return mntmPeliculaConsulta;
	}

	public void setMntmPeliculaConsulta(JMenuItem mntmPeliculaConsulta) {
		this.mntmPeliculaConsulta = mntmPeliculaConsulta;
	}

	public JMenuItem getMntmDocumentalAlta() {
		return mntmDocumentalAlta;
	}

	public void setMntmDocumentalAlta(JMenuItem mntmDocumentalAlta) {
		this.mntmDocumentalAlta = mntmDocumentalAlta;
	}

	public JMenuItem getMntmDocumentalBaja() {
		return mntmDocumentalBaja;
	}

	public void setMntmDocumentalBaja(JMenuItem mntmDocumentalBaja) {
		this.mntmDocumentalBaja = mntmDocumentalBaja;
	}

	public JMenuItem getMntmDocumentalModificacion() {
		return mntmDocumentalModificacion;
	}

	public void setMntmDocumentalModificacion(JMenuItem mntmDocumentalModificacion) {
		this.mntmDocumentalModificacion = mntmDocumentalModificacion;
	}

	public JMenuItem getMntmDocumentalConsulta() {
		return mntmDocumentalConsulta;
	}

	public void setMntmDocumentalConsulta(JMenuItem mntmDocumentalConsulta) {
		this.mntmDocumentalConsulta = mntmDocumentalConsulta;
	}
	
}
