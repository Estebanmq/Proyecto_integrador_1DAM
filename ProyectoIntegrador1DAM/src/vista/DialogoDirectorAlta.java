package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.Pais;

public class DialogoDirectorAlta extends JDialog {

	private static final long serialVersionUID = 5428825630214882590L;

	private final JPanel contentPanel = new JPanel();

	private JPanel panel;
	
	private JButton btnAceptar;
	private JButton btnCancelar;
	private PanelBtnsAceptarCancelar panelBtnsAceptarCancelar;
	private JLabel labelNombre;
	private JTextField textNombre;
	private JTextField textFecha;
	private JTextField textGenero;
	private ButtonGroup bg;
	private JComboBox comboBoxPais;


	/**
	 * Create the dialog.
	 */
	public DialogoDirectorAlta() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		this.setTitle("Alta de directores");
		this.setBounds(100, 100, 537, 400);
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
			labelNombre = new JLabel("Nombre*:");
			labelNombre.setBounds(6, 27, 91, 16);
			panel.add(labelNombre);
		}
		{
			textNombre = new JTextField();
			textNombre.setToolTipText("");
			textNombre.setBounds(62, 21, 184, 28);
			panel.add(textNombre);
			textNombre.setColumns(33);
		}
		
		JRadioButton rdbtnSexoM = new JRadioButton("Masculino");
		rdbtnSexoM.setSelected(true);
		rdbtnSexoM.setActionCommand("Masculino");
		rdbtnSexoM.setBounds(34, 135, 91, 18);
		panel.add(rdbtnSexoM);
		
		JRadioButton rdbtnSexoF = new JRadioButton("Femenino");
		rdbtnSexoF.setActionCommand("Femenino");
		rdbtnSexoF.setBounds(131, 135, 115, 18);
		panel.add(rdbtnSexoF);
		
		bg = new ButtonGroup();
		bg.add(rdbtnSexoM);
		bg.add(rdbtnSexoF);

		comboBoxPais = new JComboBox();
		comboBoxPais.setEditable(true);
		comboBoxPais.setBounds(88, 169, 158, 26);
		panel.add(comboBoxPais);
		comboBoxPais.addItem("--Seleccionar País--");
		comboBoxPais.setSelectedItem("--Seleccionar País--");
		
		JLabel labelFecha = new JLabel("Fecha*:");
		labelFecha.setBounds(6, 70, 46, 14);
		panel.add(labelFecha);
		
		JLabel labelSexo = new JLabel("Sexo*:");
		labelSexo.setBounds(6, 114, 46, 14);
		panel.add(labelSexo);
		
		JLabel labelNacionalidad = new JLabel("Nacionalidad:");
		labelNacionalidad.setBounds(6, 175, 71, 14);
		panel.add(labelNacionalidad);
		
		JLabel labelGenero = new JLabel("Genero preferido:");
		labelGenero.setBounds(6, 241, 106, 14);
		panel.add(labelGenero);
		
		textFecha = new JTextField();
		textFecha.setBounds(62, 63, 185, 28);
		panel.add(textFecha);
		textFecha.setColumns(10);
		
		textGenero = new JTextField();
		textGenero.setBounds(119, 234, 164, 28);
		panel.add(textGenero);
		textGenero.setColumns(15);
		
		JLabel labelObligatorio = new JLabel("* Campos obligatorios.");
		labelObligatorio.setBounds(10, 289, 135, 16);
		panel.add(labelObligatorio);

	}
	
	public void mostrarPaises(ArrayList<Pais>paises) {
		for (int i = 0; i < paises.size(); i++) {
			comboBoxPais.addItem(paises.get(i).getDescripcion());
		}
	}
	
//	getters y setters

	public JTextField getTextNombre() {
		return textNombre;
	}

	public void setTextNombre(JTextField textNombre) {
		this.textNombre = textNombre;
	}

	public JTextField getTextFecha() {
		return textFecha;
	}

	public void setTextFecha(JTextField textFecha) {
		this.textFecha = textFecha;
	}

	public JTextField getTextGenero() {
		return textGenero;
	}

	public void setTextGenero(JTextField textGenero) {
		this.textGenero = textGenero;
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
	public JComboBox getComboBoxPais() {
		return comboBoxPais;
	}

	public void setComboBoxPais(JComboBox comboBoxPais) {
		this.comboBoxPais = comboBoxPais;
	}

	public ButtonGroup getBg() {
		return bg;
	}

	public void setBg(ButtonGroup bg) {
		this.bg = bg;
	}
}
