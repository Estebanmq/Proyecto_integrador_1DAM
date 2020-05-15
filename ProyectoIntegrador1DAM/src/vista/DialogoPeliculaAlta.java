package vista;

import java.awt.BorderLayout;
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

	public DialogoPeliculaAlta() {
		Date sysDate = new Date();
		int max = Calendar.getInstance().get(Calendar.YEAR);
		SpinnerModel model = new SpinnerNumberModel(1900,1900,max,1);
		
		setTitle("Alta de películas");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 450, 300);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel labelTitulo = new JLabel("Título:");
		labelTitulo.setBounds(6, 22, 55, 16);
		contentPanel.add(labelTitulo);
		
		setFieldTitulo(new JTextField());
		getFieldTitulo().setBounds(68, 16, 188, 28);
		contentPanel.add(getFieldTitulo());
		getFieldTitulo().setColumns(33);
		
		JComboBox comboBoxDirector = new JComboBox();
		comboBoxDirector.setBounds(68, 102, 156, 26);
		contentPanel.add(comboBoxDirector);
		
		JLabel labelDirector = new JLabel("Director:");
		labelDirector.setBounds(6, 106, 55, 16);
		contentPanel.add(labelDirector);
		
		JLabel labelAnyo = new JLabel("Año:");
		labelAnyo.setBounds(6, 53, 44, 16);
		contentPanel.add(labelAnyo);
		
		JSpinner spinnerAnyo = new JSpinner(model);
		spinnerAnyo.setBounds(68, 48, 72, 26);
		spinnerAnyo.setValue(2020);
		contentPanel.add(spinnerAnyo);
		
		JLabel labelPais = new JLabel("País:");
		labelPais.setBounds(6, 81, 61, 16);
		contentPanel.add(labelPais);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(68, 75, 156, 27);
		contentPanel.add(comboBox);
		
		JLabel lblSinopsis = new JLabel("Sinopsis: ");
		lblSinopsis.setBounds(6, 140, 61, 16);
		contentPanel.add(lblSinopsis);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setRows(1);
		textArea.setBounds(68, 140, 218, 93);
		contentPanel.add(textArea);
		
		setPanelBtnsAceptarCancelar(new PanelBtnsAceptarCancelar());
		this.getRootPane().setDefaultButton(getPanelBtnsAceptarCancelar().getBtnAceptar());
		panelBtnsAceptarCancelar.setLayout(null);
		
		JLabel labelMensaje = new JLabel("");
		labelMensaje.setBounds(6, 6, 236, 21);
		panelBtnsAceptarCancelar.add(labelMensaje);
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
		panelBtnsAceptarCancelar.setBounds(0, 240, 450, 38);
		contentPanel.add(panelBtnsAceptarCancelar);
		panelBtnsAceptarCancelar.getBtnCancelar().setBounds(346, 5, 98, 29);
		panelBtnsAceptarCancelar.getBtnAceptar().setBounds(254, 5, 92, 29);
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
}
