package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
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

import modelo.Interprete;
import modelo.Pais;
import modelo.Sexo;

/**
 * Esta clase monta la pantalla de visualización para la modificación de interprete 
 * 
 * @author Sergio Fernández Rivera
 * @since 15/05/2020
 * @version 1.0
 *
 */
public class DialogoInterpreteModificacion extends JDialog{

private static final long serialVersionUID = 5913217229440232625L;
	
	/**
	 * Atributos de la ventana interpreteModificacion
	 */
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
	private JButton btnBuscar;
	private JTextField textFieldCache;
	
	public DialogoInterpreteModificacion() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		
		this.setTitle("Modificacion de intérpretes");
		setBounds(100, 100, 495, 353);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel labelCodigo = new JLabel("Código del intérprete:");
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
		
		JLabel labelCache = new JLabel("Caché:");
		labelCache.setBounds(244, 111, 55, 14);
		contentPanel.add(labelCache);
		
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
		
		textFieldCache = new JTextField();
		textFieldCache.setBounds(295, 108, 124, 32);
		contentPanel.add(textFieldCache);
		textFieldCache.setColumns(10);
		
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
			      if(((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
			         e.consume();  // No escribe el caracter
			      }
			   }
		});
	}
	
	/**
	 * Recorre el array de paises para sacar todos e introducirlos en comboBox
	 * @param paises Colección de países
	 */
	public void mostrarPaises(ArrayList<Pais>paises) {
		for (int i = 0; i < paises.size(); i++) {
			comboBoxPais.addItem(paises.get(i).getDescripcion());
		}
	}
	public void mostrarInterprete(Interprete interprete) {
				
		DecimalFormat formateador = new DecimalFormat("############");
		
		getPanelBtnsAceptarCancelar().getLabelTextoError().setText("");
		textFieldNombre.setText(interprete.getNombre());
		comboBoxPais.setSelectedItem(interprete.getNacionalidad().getDescripcion());
		textFieldFecha.setText(interprete.getFechaNacimiento().toString());
		if(interprete.getSexo()== Sexo.MASCULINO) {
			rdbtnNewRadioM.setSelected(true);
		}else
			rdbtnNewRadioF.setSelected(true);
		
		textFieldCache.setText(formateador.format(interprete.getCache()));

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

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public JTextField getTextFieldCache() {
		return textFieldCache;
	}

	public void setTextFieldCache(JTextField textFieldCache) {
		this.textFieldCache = textFieldCache;
	}

	public JTextField getTextField() {
		return textFieldCache;
	}

	public void setTextField(JTextField textField) {
		this.textFieldCache = textField;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
}
