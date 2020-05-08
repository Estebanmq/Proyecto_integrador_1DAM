package vista;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBtnsAceptarCancelar extends JPanel {
	
	private static final long serialVersionUID = -2916018382013617038L;

	private JButton btnAceptar;
	private JButton btnCancelar;
	
	public PanelBtnsAceptarCancelar() {

		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
	
		this.setBtnAceptar(new JButton("Aceptar"));
		this.getBtnAceptar().setActionCommand("btnAceptar");
		this.add(getBtnAceptar());
	
		this.setBtnCancelar(new JButton("Cancelar"));
		this.getBtnCancelar().setActionCommand("btnCancelar");
		this.add(getBtnCancelar());		
	
	}

	public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public void setBtnAceptar(JButton btnAceptar) {
		this.btnAceptar = btnAceptar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public void setBtnCancelar(JButton btnCancelar) {
		this.btnCancelar = btnCancelar;
	}
	
}
