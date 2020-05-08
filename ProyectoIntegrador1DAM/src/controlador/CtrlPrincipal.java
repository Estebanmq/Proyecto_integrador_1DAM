package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.PantallaPrincipal;

public class CtrlPrincipal implements ActionListener {
	
	private PantallaPrincipal pantalla;
	
	public CtrlPrincipal() {
		
		this.setPantalla(new PantallaPrincipal());
		this.getPantalla().getMntmSalir().addActionListener(this);
		this.getPantalla().getMntmDirectorAlta().addActionListener(this);
		this.getPantalla().getMntmDirectorBaja().addActionListener(this);
		this.getPantalla().getMntmDirectorModificacion().addActionListener(this);
		this.getPantalla().getMntmDirectorConsulta().addActionListener(this);
		this.getPantalla().getMntmInterpreteAlta().addActionListener(this);
		this.getPantalla().getMntmInterpreteBaja().addActionListener(this);
		this.getPantalla().getMntmInterpreteModificacion().addActionListener(this);
		this.getPantalla().getMntmInterpreteConsulta().addActionListener(this);
		this.getPantalla().getMntmPeliculaAlta().addActionListener(this);
		this.getPantalla().getMntmPeliculaBaja().addActionListener(this);
		this.getPantalla().getMntmPeliculaModificacion().addActionListener(this);
		this.getPantalla().getMntmPeliculaConsulta().addActionListener(this);
		this.getPantalla().getMntmDocumentalAlta().addActionListener(this);
		this.getPantalla().getMntmDocumentalBaja().addActionListener(this);
		this.getPantalla().getMntmDocumentalModificacion().addActionListener(this);
		this.getPantalla().getMntmDocumentalConsulta().addActionListener(this);
		this.getPantalla().getMntmPaisMantenimiento().addActionListener(this);
		this.getPantalla().getMntmListadoParticipantes().addActionListener(this);
		this.getPantalla().getMntmListadoEjemplaresAudiovisuales().addActionListener(this);

	
		this.getPantalla().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
			case "menuSalir" :
				System.exit(0);
			case "menuDirectorAlta" :
				new CtrlDirectorAlta();
				break;
			case "menuDirectorBaja" :
				System.out.format("%s\n", e.getActionCommand());
				break;
			case "menuDirectorModificacion" :
				System.out.format("%s\n", e.getActionCommand());
				break;
			case "menuDirectorConsulta" :
				System.out.format("%s\n", e.getActionCommand());
				break;
			case "menuInterpreteAlta" :
				System.out.format("%s\n", e.getActionCommand());
				break;
			case "menuInterpreteBaja" :
				System.out.format("%s\n", e.getActionCommand());
				break;
			case "menuInterpreteModificacion" :
				System.out.format("%s\n", e.getActionCommand());
				break;
			case "menuInterpreteConsulta" :
				System.out.format("%s\n", e.getActionCommand());
				break;
			case "menuPeliculaAlta" :
				System.out.format("%s\n", e.getActionCommand());
				break;
			case "menuPeliculaBaja" :
				System.out.format("%s\n", e.getActionCommand());
				break;
			case "menuPeliculaModificación" :
				System.out.format("%s\n", e.getActionCommand());
				break;
			case "menuPeliculaConsulta" :
				System.out.format("%s\n", e.getActionCommand());
				break;
			case "menuDocumentalAlta" :
				System.out.format("%s\n", e.getActionCommand());
				break;
			case "menuDocumentalBaja" :
				System.out.format("%s\n", e.getActionCommand());
				break;
			case "menuDocumentalModificación" :
				System.out.format("%s\n", e.getActionCommand());
				break;
			case "menuDocumentalConsulta" :
				System.out.format("%s\n", e.getActionCommand());
				break;
			case "menuEstructurasPaisesMantenimiento" :
				System.out.format("%s\n", e.getActionCommand());
				new CtrlPaisMantenimiento();
				break;
			case "menuListadosParticipantes" :
				System.out.format("%s\n", e.getActionCommand());
				break;
			case "menuListadosEjemplares" :
				System.out.format("%s\n", e.getActionCommand());
				break;
			default :
				System.out.format("%s\n", e.getActionCommand());
		}

		
	}

	// GETTERS & SETTERS
	public PantallaPrincipal getPantalla() {
		return pantalla;
	}

	public void setPantalla(PantallaPrincipal pantalla) {
		this.pantalla = pantalla;
	}

}
