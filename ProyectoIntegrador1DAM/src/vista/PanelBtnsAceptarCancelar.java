package vista;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

/** 
 * 
 * Esta clase monta los botones de Aceptar y Cancelar en un JPanel que se reutilizará en las distintas ventanas de la aplicación. 
 * Además contiene un JLabel donde se mostrarán los errores de aplicación ocurridos 
 * 
 * @see JPanel
 * @see JLabel
 * @see JButton
 * 
 * @author Jose Manuel de Dios
 * @version 1.0
 * @since 03/05/2020
 */
public class PanelBtnsAceptarCancelar extends JPanel {
	
	private static final long serialVersionUID = -2916018382013617038L;

	private JButton btnAceptar;
	private JButton btnCancelar;
	private JLabel labelTextoError;
	
	public PanelBtnsAceptarCancelar() {

		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		setLabelTextoError(new JLabel(""));
		getLabelTextoError().setFont(new Font("SansSerif", Font.BOLD, 12));
		getLabelTextoError().setForeground(Color.RED);
		getLabelTextoError().setHorizontalAlignment(SwingConstants.RIGHT);
		getLabelTextoError().setPreferredSize(new Dimension(270, 16));
		add(getLabelTextoError());
	
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
