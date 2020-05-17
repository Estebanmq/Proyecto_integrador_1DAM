package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.derby.impl.sql.catalog.SYSFOREIGNKEYSRowFactory;

import modelo.GeneroPelicula;
import modelo.Pais;
import modelo.Pelicula;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class DialogoPeliculaAlta extends JDialog {
	
	private static final long serialVersionUID = 3894472721898604079L;

	private Pelicula pelicula;
	
	private JButton btnAceptar;
	private JButton btnCancelar;
	private PanelBtnsAceptarCancelar panelBtnsAceptarCancelar;

	private final JPanel contentPanel = new JPanel();
	private JTextField fieldTitulo;
	private JLabel labelMensaje;
	private JComboBox comboBoxGenero;
	private JComboBox comboBoxPais;
	private JComboBox comboBoxDirector; 
	

	public DialogoPeliculaAlta() {
		Date sysDate = new Date();
		int max = Calendar.getInstance().get(Calendar.YEAR);
		SpinnerModel model = new SpinnerNumberModel(1900,1900,max,1);
		
		setTitle("Alta de películas");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 495, 381);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel labelTitulo = new JLabel("Título:");
		labelTitulo.setBounds(16, 24, 55, 16);
		contentPanel.add(labelTitulo);
		
		setFieldTitulo(new JTextField());
		getFieldTitulo().setBounds(80, 18, 188, 28);
		contentPanel.add(getFieldTitulo());
		getFieldTitulo().setColumns(33);
		
		comboBoxDirector = new JComboBox();
		comboBoxDirector.setBounds(78, 123, 190, 26);
		contentPanel.add(comboBoxDirector);
		comboBoxDirector.addItem("--Seleccionar Director--");
		comboBoxDirector.setSelectedItem("--Seleccionar Director--");
		
		JLabel labelDirector = new JLabel("Director:");
		labelDirector.setBounds(16, 127, 55, 16);
		contentPanel.add(labelDirector);
		
		JLabel labelAnyo = new JLabel("Año:");
		labelAnyo.setBounds(16, 62, 44, 16);
		contentPanel.add(labelAnyo);
		
		JSpinner spinnerAnyo = new JSpinner(model);
		spinnerAnyo.setBounds(78, 57, 72, 26);
		spinnerAnyo.setValue(2020);
		contentPanel.add(spinnerAnyo);
		
		JLabel labelPais = new JLabel("País:");
		labelPais.setBounds(16, 95, 61, 16);
		contentPanel.add(labelPais);
		
		comboBoxPais = new JComboBox();
		comboBoxPais.setBounds(78, 88, 190, 27);
		contentPanel.add(comboBoxPais);
		comboBoxPais.addItem("--Seleccionar País--");
		comboBoxPais.setSelectedItem("--Seleccionar País--");
		
		JLabel labelSinopsis = new JLabel("Sinopsis: ");
		labelSinopsis.setBounds(16, 192, 61, 16);
		contentPanel.add(labelSinopsis);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setRows(1);
		textArea.setBounds(80, 192, 218, 109);
		contentPanel.add(textArea);
		
		setPanelBtnsAceptarCancelar(new PanelBtnsAceptarCancelar());
		getContentPane().add(getPanelBtnsAceptarCancelar(), BorderLayout.SOUTH);
		this.getRootPane().setDefaultButton(getPanelBtnsAceptarCancelar().getBtnAceptar());

		
		JLabel labelGenero = new JLabel("Género:");
		labelGenero.setBounds(16, 157, 61, 16);
		contentPanel.add(labelGenero);
		
		comboBoxGenero = new JComboBox();
		comboBoxGenero.setBounds(78, 153, 190, 27);
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

	public JComboBox getComboBoxPais() {
		return comboBoxPais;
	}

	public void setComboBoxPais(JComboBox comboBoxPais) {
		this.comboBoxPais = comboBoxPais;
	}

	public JComboBox getComboBoxDirector() {
		return comboBoxDirector;
	}

	public void setComboBoxDirector(JComboBox comboBoxDirector) {
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
	
	public JComboBox getComboBoxGenero() {
		return comboBoxGenero;
	}

	public void setComboBoxGenero(JComboBox comboBoxGenero) {
		this.comboBoxGenero = comboBoxGenero;
	}
}
