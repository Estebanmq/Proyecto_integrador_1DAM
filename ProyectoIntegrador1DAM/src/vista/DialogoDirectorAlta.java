package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class DialogoDirectorAlta extends JDialog {

	private static final long serialVersionUID = 5428825630214882590L;

	private final JPanel contentPanel = new JPanel();

	private JPanel panel;
	
	private JButton btnAceptar;
	private JButton btnCancelar;
	private PanelBtnsAceptarCancelar panelBtnsAceptarCancelar;
	private JLabel labelNombre;
	private JTextField textField;

	/**
	 * Create the dialog.
	 */
	public DialogoDirectorAlta() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		this.setTitle("Alta de directores");
		this.setBounds(100, 100, 537, 309);
		this.getContentPane().setLayout(new BorderLayout());
		this.setLocationRelativeTo(null);
		getContentPanel().setLayout(new FlowLayout());
		getContentPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		setPanelBtnsAceptarCancelar(new PanelBtnsAceptarCancelar());
		getContentPane().add(getPanelBtnsAceptarCancelar(), BorderLayout.SOUTH);
		this.getRootPane().setDefaultButton(getPanelBtnsAceptarCancelar().getBtnAceptar());

		this.setPanel(new JPanel());
		this.getContentPane().add(getPanel(), BorderLayout.CENTER);
		panel.setLayout(null);
		{
			labelNombre = new JLabel("Nombre:");
			labelNombre.setBounds(6, 27, 91, 16);
			panel.add(labelNombre);
		}
		{
			textField = new JTextField();
			textField.setBounds(109, 21, 184, 28);
			panel.add(textField);
			textField.setColumns(33);
		}
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Masculino");
		rdbtnNewRadioButton.setBounds(34, 81, 91, 18);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Femenino");
		rdbtnNewRadioButton_1.setBounds(138, 81, 115, 18);
		panel.add(rdbtnNewRadioButton_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(44, 111, 123, 26);
		panel.add(comboBox);

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

	private JPanel getContentPanel() {
		return contentPanel;
	}

	private JPanel getPanel() {
		return panel;
	}

	private void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public PanelBtnsAceptarCancelar getPanelBtnsAceptarCancelar() {
		return panelBtnsAceptarCancelar;
	}

	public void setPanelBtnsAceptarCancelar(PanelBtnsAceptarCancelar panelBtnsAceptarCancelar) {
		this.panelBtnsAceptarCancelar = panelBtnsAceptarCancelar;
	}
}
