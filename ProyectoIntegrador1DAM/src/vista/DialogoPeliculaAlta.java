package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
		
		JComboBox comboBoxDirector = new JComboBox();
		comboBoxDirector.setBounds(78, 123, 156, 26);
		contentPanel.add(comboBoxDirector);
		
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
		
		JComboBox comboBoxPais = new JComboBox();
		comboBoxPais.setBounds(78, 88, 156, 27);
		contentPanel.add(comboBoxPais);
		
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

		
		JLabel labelGenero = new JLabel("Genero:");
		labelGenero.setBounds(16, 157, 61, 16);
		contentPanel.add(labelGenero);
		
		JComboBox comboBoxGenero = new JComboBox();
		comboBoxGenero.setBounds(78, 153, 156, 27);
		contentPanel.add(comboBoxGenero);
		
		
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
		panelBtnsAceptarCancelar.setBounds(0, 304, 486, 55);
		contentPanel.add(panelBtnsAceptarCancelar);
		panelBtnsAceptarCancelar.getBtnCancelar().setBounds(382, 5, 98, 29);
		panelBtnsAceptarCancelar.getBtnAceptar().setBounds(282, 5, 92, 29);
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
}
