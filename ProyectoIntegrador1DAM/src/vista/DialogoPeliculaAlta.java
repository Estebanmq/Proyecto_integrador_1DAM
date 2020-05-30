package vista;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Calendar;


import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import modelo.GeneroPelicula;
import modelo.Pais;
import modelo.Pelicula;

/**
 * Esta clase genera la pantalla de visualización de datos para la transacción de alta de películas
 * 
 * @author Esteban Martínez
 * @since 10/05/2020
 * @version 1.0
 *
 */
public class DialogoPeliculaAlta extends JDialog {
	
	private static final long serialVersionUID = 3894472721898604079L;

	private Pelicula pelicula;
	
	private JButton btnAceptar;
	private JButton btnCancelar;
	private PanelBtnsAceptarCancelar panelBtnsAceptarCancelar;

	private final JPanel contentPanel = new JPanel();
	private JTextField fieldTitulo;
	private JLabel labelMensaje;
	private JSpinner spinnerAnyo;
	private JComboBox<String> comboBoxGenero;
	private JComboBox<String> comboBoxPais;
	private JTextArea textAreaSinopsis;
	private JComboBox<String> comboBoxDirector; 
	

	public DialogoPeliculaAlta() {

		int max = Calendar.getInstance().get(Calendar.YEAR);
		SpinnerModel model = new SpinnerNumberModel(1900,1900,max,1);
		setFieldTitulo(new JTextField());
		JLabel labelTitulo = new JLabel("Título*:");
		JLabel labelDirector = new JLabel("Director*:");
		comboBoxDirector = new JComboBox<String>();
		JLabel labelAnyo = new JLabel("Año*:");
		spinnerAnyo = new JSpinner(model);
		JLabel labelPais = new JLabel("País*:");
		comboBoxPais = new JComboBox<String>();
		
		JLabel labelGenero = new JLabel("Género*:");
		comboBoxGenero = new JComboBox<String>();
		JLabel labelSinopsis = new JLabel("Sinopsis: ");
		textAreaSinopsis = new JTextArea();
		
		
		
		setTitle("Alta de películas");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 495, 381);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		labelTitulo.setBounds(16, 24, 55, 16);
		contentPanel.add(labelTitulo);
		
	
		getFieldTitulo().setBounds(96, 18, 188, 28);
		contentPanel.add(getFieldTitulo());
		getFieldTitulo().setColumns(33);
		
		labelDirector.setBounds(16, 127, 66, 16);
		contentPanel.add(labelDirector);
		
		
		comboBoxDirector.setBounds(94, 123, 190, 26);
		contentPanel.add(comboBoxDirector);
		comboBoxDirector.addItem("--Seleccionar Director--");
		comboBoxDirector.setSelectedItem("--Seleccionar Director--");
		
		labelAnyo.setBounds(16, 62, 44, 16);
		contentPanel.add(labelAnyo);
	
		spinnerAnyo.setBounds(94, 57, 72, 26);
		spinnerAnyo.setValue(2020);
		contentPanel.add(spinnerAnyo);
		
		labelPais.setBounds(16, 95, 61, 16);
		contentPanel.add(labelPais);
		
		comboBoxPais.setBounds(94, 88, 190, 27);
		contentPanel.add(comboBoxPais);
		comboBoxPais.addItem("--Seleccionar País--");
		comboBoxPais.setSelectedItem("--Seleccionar País--");
		
		labelSinopsis.setBounds(16, 192, 61, 16);
		contentPanel.add(labelSinopsis);
		
		
		textAreaSinopsis.setLineWrap(true);
		textAreaSinopsis.setRows(1);
		textAreaSinopsis.setBounds(80, 192, 349, 111);
		contentPanel.add(textAreaSinopsis);
		textAreaSinopsis.addKeyListener(new KeyAdapter() {
			 public void keyTyped(KeyEvent e) {
			      if(textAreaSinopsis.getText().length()==250) {
			         e.consume();  // No escribe el caracter
			         getPanelBtnsAceptarCancelar().getLabelTextoError().setText("Sinopsis no puede superar 250 caracteres");
			      } else {
			    	  getPanelBtnsAceptarCancelar().getLabelTextoError().setText("");
			      }
			   }
		});
		
		
		setPanelBtnsAceptarCancelar(new PanelBtnsAceptarCancelar());
		getContentPane().add(getPanelBtnsAceptarCancelar(), BorderLayout.SOUTH);
		this.getRootPane().setDefaultButton(getPanelBtnsAceptarCancelar().getBtnAceptar());

	
		labelGenero.setBounds(16, 157, 61, 16);
		contentPanel.add(labelGenero);
		
		comboBoxGenero.setBounds(94, 153, 190, 27);
		contentPanel.add(comboBoxGenero);
		comboBoxGenero.addItem("--Seleccionar Género--");
		comboBoxGenero.setSelectedItem("--Seleccionar Género--");
		
		mostrarGeneros();
		
		
	}

	public void mostrarGeneros() {
		GeneroPelicula[]gp=GeneroPelicula.values();
		for (int i=0;i<gp.length;i++) {
			comboBoxGenero.addItem(gp[i].getDescripcion());
		}
	}
	
	public void mostrarPaises(ArrayList<Pais>paises) {
		for (int i = 0; i < paises.size(); i++) {
			comboBoxPais.addItem(paises.get(i).getDescripcion());
		}
	}
	
	public void mostrarDirectores(ArrayList<String>directores) {
		for (int i = 0; i < directores.size(); i++) {
			comboBoxDirector.addItem(directores.get(i));
		}
	} 
	
	public JTextArea getTextAreaSinopsis() {
		return textAreaSinopsis;
	}

	public void setTextAreaSinopsis(JTextArea textAreaSinopsis) {
		this.textAreaSinopsis = textAreaSinopsis;
	}
	
	public JSpinner getSpinnerAnyo() {
		return spinnerAnyo;
	}

	public void setSpinnerAnyo(JSpinner spinnerAnyo) {
		this.spinnerAnyo = spinnerAnyo;
	}

	public JComboBox<String> getComboBoxPais() {
		return comboBoxPais;
	}

	public void setComboBoxPais(JComboBox<String> comboBoxPais) {
		this.comboBoxPais = comboBoxPais;
	}

	public JComboBox<String> getComboBoxDirector() {
		return comboBoxDirector;
	}

	public void setComboBoxDirector(JComboBox<String> comboBoxDirector) {
		this.comboBoxDirector = comboBoxDirector;
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

	public PanelBtnsAceptarCancelar getPanelBtnsAceptarCancelar() {
		return panelBtnsAceptarCancelar;
	}

	public void setPanelBtnsAceptarCancelar(PanelBtnsAceptarCancelar panelBtnsAceptarCancelar) {
		this.panelBtnsAceptarCancelar = panelBtnsAceptarCancelar;
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public JTextField getFieldTitulo() {
		return fieldTitulo;
	}

	public void setFieldTitulo(JTextField fieldTitulo) {
		this.fieldTitulo = fieldTitulo;
	}
	
	public JLabel getLabelMensaje() {
		return labelMensaje;
	}

	public void setLabelMensaje(JLabel labelMensaje) {
		this.labelMensaje = labelMensaje;
	}
	
	public JComboBox<String> getComboBoxGenero() {
		return comboBoxGenero;
	}

	public void setComboBoxGenero(JComboBox<String> comboBoxGenero) {
		this.comboBoxGenero = comboBoxGenero;
	}
}
