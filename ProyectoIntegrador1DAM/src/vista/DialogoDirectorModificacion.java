package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

import modelo.Director;
import modelo.GeneroPelicula;
import modelo.Pais;
import modelo.Sexo;

/**
 * Esta clase monta la pantalla de visualización para la modificación de director  
 * 
 * @author Sergio Fernández Rivera
 * @since 15/05/2020
 * @version 1.0
 *
 */
public class DialogoDirectorModificacion extends JDialog {

	private static final long serialVersionUID = 5913217229440232625L;
	
	private JButton btnAceptar;
	private JButton btnCancelar;
	private PanelBtnsAceptarCancelar panelBtnsAceptarCancelar;
	private final JPanel contentPanel = new JPanel();
	private JTextField textFieldCodigo;
	private JTextField textFieldNombre;
	private JTextField textFieldFecha;
	private ButtonGroup bg;
	private JRadioButton rdbtnNewRadioM;
	private JRadioButton rdbtnNewRadioF;
	private JComboBox<String> comboBoxPais;
	private JComboBox<String> comboBoxGenero;
	private JButton btnBuscar;

	/**
	 * Crea la ventana con todos sus campos.
	 */
	public DialogoDirectorModificacion() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		this.setTitle("Modificacion de directores");
		setBounds(100, 100, 495, 353);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel labelCodigo = new JLabel("Código del director:");
		labelCodigo.setBounds(70, 28, 124, 14);
		contentPanel.add(labelCodigo);
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setBounds(204, 25, 86, 34);
		contentPanel.add(textFieldCodigo);
		textFieldCodigo.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(300, 24, 89, 23);
		contentPanel.add(btnBuscar);
		
		btnBuscar.setActionCommand("btnBuscar");
		
		JLabel labelNombre = new JLabel("Nombre:");
		labelNombre.setBounds(24, 70, 55, 14);
		contentPanel.add(labelNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(89, 67, 86, 32);
		contentPanel.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		JLabel labelFecha = new JLabel("Fecha Nacimiento:");
		labelFecha.setBounds(24, 111, 99, 14);
		contentPanel.add(labelFecha);
		
		textFieldFecha = new JTextField();
		textFieldFecha.setBounds(134, 108, 86, 32);
		contentPanel.add(textFieldFecha);
		textFieldFecha.setColumns(10);
		
		JLabel labelPais = new JLabel("Pais:");
		labelPais.setBounds(229, 70, 46, 14);
		contentPanel.add(labelPais);
		
		comboBoxPais = new JComboBox<String>();
		comboBoxPais.setEditable(true);
		comboBoxPais.setBounds(285, 66, 160, 22);
		contentPanel.add(comboBoxPais);
		comboBoxPais.addItem("--Seleccionar País--");
		comboBoxPais.setSelectedItem("--Seleccionar País--");
		
		JLabel labelGenero = new JLabel("Género:");
		labelGenero.setBounds(244, 111, 55, 14);
		contentPanel.add(labelGenero);
		
		JLabel labelSexo = new JLabel("Sexo:");
		labelSexo.setBounds(24, 154, 46, 14);
		contentPanel.add(labelSexo);
		
		rdbtnNewRadioM = new JRadioButton("Masculino");
		rdbtnNewRadioM.setActionCommand("MASCULINO");
		rdbtnNewRadioM.setBounds(85, 150, 109, 23);
		contentPanel.add(rdbtnNewRadioM);
		
		rdbtnNewRadioF = new JRadioButton("Femenino");
		rdbtnNewRadioF.setActionCommand("FEMENINO");
		rdbtnNewRadioF.setBounds(204, 150, 109, 23);
		contentPanel.add(rdbtnNewRadioF);
		
		bg = new ButtonGroup();
		bg.add(rdbtnNewRadioM);
		bg.add(rdbtnNewRadioF);
		
		comboBoxGenero = new JComboBox<String>();
		comboBoxGenero.setEditable(true);
		comboBoxGenero.setBounds(321, 107, 148, 22);
		contentPanel.add(comboBoxGenero);
		comboBoxGenero.addItem("--Seleccionar Género--");
		comboBoxGenero.setSelectedItem("--Seleccionar Género--");
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		setPanelBtnsAceptarCancelar(new PanelBtnsAceptarCancelar());
		getContentPane().add(getPanelBtnsAceptarCancelar(), BorderLayout.SOUTH);
		this.getRootPane().setDefaultButton(getPanelBtnsAceptarCancelar().getBtnAceptar());

		textFieldCodigo.addKeyListener(new KeyAdapter() {
			 public void keyTyped(KeyEvent e) {
			      char caracter = e.getKeyChar();
			      // Verifico si la tecla pulsada no es un digito
			      if((caracter < '0') || (caracter > '9')) {
			         e.consume();  // No escribe el caracter
			      }
			   }
		});
	}
	
	/**
	 * Recorre el array de paises para sacar todos e introducirlos en comboBox
	 * @param paises Relación de países
	 */
	public void mostrarPaises(ArrayList<Pais>paises) {
		for (int i = 0; i < paises.size(); i++) {
			comboBoxPais.addItem(paises.get(i).getDescripcion());
		}
	}
	/**
	 * Recorre el array de los tipos de género
	 */
	public void mostrarGeneros() {
		GeneroPelicula[]gp=GeneroPelicula.values();
		for (int i=0;i<gp.length;i++) {
			comboBoxGenero.addItem(gp[i].getDescripcion());
		}
	}
	
	public void mostrarDirector(Director d) {
		getPanelBtnsAceptarCancelar().getLabelTextoError().setText("");
		textFieldNombre.setText(d.getNombre());
		comboBoxPais.setSelectedItem(d.getNacionalidad().getDescripcion());
		textFieldFecha.setText(d.getFechaNacimiento().toString());
		if(d.getSexo()== Sexo.MASCULINO) {
			rdbtnNewRadioM.setSelected(true);
		}else
			rdbtnNewRadioF.setSelected(true);
		comboBoxGenero.setSelectedItem(d.getGeneroPreferido().getDescripcion());
	}

	// GETTERS & SETTERS
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

	public PanelBtnsAceptarCancelar getPanelBtnsAceptarCancelar() {
		return panelBtnsAceptarCancelar;
	}

	public void setPanelBtnsAceptarCancelar(PanelBtnsAceptarCancelar panelBtnsAceptarCancelar) {
		this.panelBtnsAceptarCancelar = panelBtnsAceptarCancelar;
	}

	public JTextField getTextFieldCodigo() {
		return textFieldCodigo;
	}

	public void setTextFieldCodigo(JTextField textFieldCodigo) {
		this.textFieldCodigo = textFieldCodigo;
	}

	public JTextField getTextFieldNombre() {
		return textFieldNombre;
	}

	public void setTextFieldNombre(JTextField textFieldNombre) {
		this.textFieldNombre = textFieldNombre;
	}

	public JTextField getTextFieldFecha() {
		return textFieldFecha;
	}

	public void setTextFieldFecha(JTextField textFieldFecha) {
		this.textFieldFecha = textFieldFecha;
	}

	public ButtonGroup getBg() {
		return bg;
	}

	public void setBg(ButtonGroup bg) {
		this.bg = bg;
	}

	public JRadioButton getRdbtnNewRadioM() {
		return rdbtnNewRadioM;
	}

	public void setRdbtnNewRadioM(JRadioButton rdbtnNewRadioM) {
		this.rdbtnNewRadioM = rdbtnNewRadioM;
	}

	public JRadioButton getRdbtnNewRadioF() {
		return rdbtnNewRadioF;
	}

	public void setRdbtnNewRadioF(JRadioButton rdbtnNewRadioF) {
		this.rdbtnNewRadioF = rdbtnNewRadioF;
	}

	public JComboBox<String> getComboBoxPais() {
		return comboBoxPais;
	}

	public void setComboBoxPais(JComboBox<String> comboBoxPais) {
		this.comboBoxPais = comboBoxPais;
	}

	public JComboBox<String> getComboBoxGenero() {
		return comboBoxGenero;
	}

	public void setComboBoxGenero(JComboBox<String> comboBoxGenero) {
		this.comboBoxGenero = comboBoxGenero;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}	
}
