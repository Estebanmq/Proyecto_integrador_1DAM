package vista;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class PanelBtnsAceptarCancelar extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JLabel labelTextoError;
	
	public PanelBtnsAceptarCancelar() {

		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		this.setLabelTextoError(new JLabel(""));
		this.getLabelTextoError().setFont(new Font("SansSerif", Font.BOLD, 12));
		this.getLabelTextoError().setForeground(Color.RED);
		this.getLabelTextoError().setHorizontalAlignment(SwingConstants.RIGHT);
		this.getLabelTextoError().setPreferredSize(new Dimension(270, 16));
		this.add(getLabelTextoError());
	
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

	public JLabel getLabelTextoError() {
		return labelTextoError;
	}

	public void setLabelTextoError(JLabel labelTextoError) {
		this.labelTextoError = labelTextoError;
	}
	
}
