package vista;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
import javax.swing.table.DefaultTableModel;

import modelo.GeneroPelicula;
import modelo.Pais;
import modelo.Pelicula;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class DialogoPeliculaBaja extends JDialog {
	private static final long serialVersionUID = 1L;
	


	private Pelicula pelicula;
	
	private JButton btnAceptar;
	private JButton btnCancelar;
	private PanelBtnsAceptarCancelar panelBtnsAceptarCancelar;

	private final JPanel contentPanel = new JPanel();
	private JTextField fieldTitulo;
	private JLabel labelMensaje;
	private JTextField fieldCodPeli;
	private JButton btnEliminar;
	private JButton btnBuscar;
	private JLabel labelTitulo;
	private JLabel labelTituloResul;
	private JLabel labelPais;
	private JLabel labelPaisResul;
	private JLabel labelAnyo;
	private JLabel labelAnyoResul;
	private JLabel labelDirector;
	private JLabel labelGenero;
	private JLabel labelGeneroResul;
	private JLabel labelSinopsis;

	public DialogoPeliculaBaja() {
		
		JLabel labelTextoCodigo = new JLabel("Código de la película:");
		fieldCodPeli = new JTextField();
		btnEliminar = new JButton("Eliminar pelicula");
		btnBuscar = new JButton("Buscar");
		labelTitulo = new JLabel("Titulo: ");
		labelTituloResul = new JLabel("");
		labelPais = new JLabel("País:");
		labelPaisResul = new JLabel("");
		labelAnyo = new JLabel("Año:");
		labelAnyoResul = new JLabel("");
		labelDirector = new JLabel("Director:");
		labelAnyoResul = new JLabel("");
		labelGenero = new JLabel("Genero:");
		labelGeneroResul = new JLabel("");
		labelSinopsis = new JLabel("Sinopsis:");
		JScrollPane scrollPane = new JScrollPane();
		JLabel labelSinopsisResul = new JLabel("");
		
		setTitle("Baja de películas");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 541, 381);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		labelTextoCodigo.setBounds(6, 24, 145, 16);
		contentPanel.add(labelTextoCodigo);
		
		fieldCodPeli.setBounds(163, 19, 130, 26);
		contentPanel.add(fieldCodPeli);
		fieldCodPeli.setColumns(10);
		
		
		btnEliminar.setBounds(376, 19, 137, 29);
		btnEliminar.setActionCommand("btnEliminar");
		contentPanel.add(btnEliminar);
		
		btnBuscar.setBounds(303, 19, 75, 29);
		btnBuscar.setActionCommand("btnBuscar");
		contentPanel.add(btnBuscar);
		
		
		labelTitulo.setBounds(6, 52, 61, 16);
		contentPanel.add(labelTitulo);
		
		labelTituloResul.setBounds(81, 52, 212, 16);
		contentPanel.add(labelTituloResul);
		
		labelPais.setBounds(6, 80, 61, 16);
		contentPanel.add(labelPais);
		
		labelPaisResul.setBounds(81, 80, 212, 16);
		contentPanel.add(labelPaisResul);
		
		labelAnyo.setBounds(6, 108, 61, 16);
		contentPanel.add(labelAnyo);
		
		labelAnyoResul.setBounds(81, 108, 212, 16);
		contentPanel.add(labelAnyoResul);
		
		labelDirector.setBounds(6, 136, 61, 16);
		contentPanel.add(labelDirector);
		
		labelAnyoResul.setBounds(81, 136, 212, 16);
		contentPanel.add(labelAnyoResul);
		
		labelGenero.setBounds(6, 164, 61, 16);
		contentPanel.add(labelGenero);
		
		labelGeneroResul.setBounds(81, 164, 212, 16);
		contentPanel.add(labelGeneroResul);
		
		labelSinopsis.setBounds(6, 192, 61, 16);
		contentPanel.add(labelSinopsis);
		
		scrollPane.setBounds(81, 192, 432, 122);
		contentPanel.add(scrollPane);
		
		scrollPane.setViewportView(labelSinopsisResul);
		
		setPanelBtnsAceptarCancelar(new PanelBtnsAceptarCancelar());
		getContentPane().add(getPanelBtnsAceptarCancelar(), BorderLayout.SOUTH);
		this.getRootPane().setDefaultButton(getPanelBtnsAceptarCancelar().getBtnAceptar());		
	}
	
	
	
	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public void setBtnEliminar(JButton btnEliminar) {
		this.btnEliminar = btnEliminar;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
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

	public JLabel getLabelTituloResul() {
		return labelTituloResul;
	}

	public void setLabelTituloResul(JLabel labelTituloResul) {
		this.labelTituloResul = labelTituloResul;
	}

	public JLabel getLabelPaisResul() {
		return labelPaisResul;
	}

	public void setLabelPaisResul(JLabel labelPaisResul) {
		this.labelPaisResul = labelPaisResul;
	}

	public JLabel getLabelAnyoResul() {
		return labelAnyoResul;
	}

	public void setLabelAnyoResul(JLabel labelAnyoResul) {
		this.labelAnyoResul = labelAnyoResul;
	}

	public JLabel getLabelGeneroResul() {
		return labelGeneroResul;
	}

	public void setLabelGeneroResul(JLabel labelGeneroResul) {
		this.labelGeneroResul = labelGeneroResul;
	}
	
	
}
