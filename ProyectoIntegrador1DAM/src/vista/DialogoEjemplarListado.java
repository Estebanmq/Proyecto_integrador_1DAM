package vista;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Documental;
import modelo.ListaEjemplar;
import modelo.Pais;
import modelo.PaisComboBox;
import modelo.Pelicula;
/**
 * 
 * Esta clase genera la pantalla de visualización del listado de ejemplares audiovisuales
 * 
 * @author Jose Manuel de Dios
 * @version 1.0
 * @since 24/05/2020
 */
public class DialogoEjemplarListado extends JDialog {
	
	private static final long serialVersionUID = 5838718128147058007L;

	private final String NOMCOLUMNAS[] = new String[] {"Código", "Título", "Nacionalidad"};
	
	private DefaultTableModel dtmModelo;
	private JTable tablaEjemplares;
	
	private PanelBtnOk panelBtnOk;

	private JButton btnAplicar;
	private JButton btnExportar;

	private JCheckBox chkPeliculas;
	private JCheckBox chkDocumentales;
	private JTextField fieldTitulo;
	private JComboBox<PaisComboBox> comboNacionalidad;
	private JSpinner spinnerAnyo;
	
	private JTextField fieldCodigoSelected;
	private JTextField fieldTituloSelected;
	private JTextField fieldNacionalidadSelected;
	private JTextField fieldAnyoSelected;
	private JTextField fieldGeneroSelected;
	private JTextField fieldDirectorSelected;
	
	public DialogoEjemplarListado() {
		
		JPanel contentPanel = new JPanel();
		JPanel panelFiltro = new JPanel();
		JLabel labelTitulo = new JLabel("Título:");
		JLabel labelNacionalidad = new JLabel("Nacionalidad:");
		JLabel labelAnyo = new JLabel("Año:");
		SpinnerModel spinnerModel;
		JPanel panelDatos = new JPanel();
		JLabel labelCodigoSelected = new JLabel("Código:");
		JLabel labelTituloSelected = new JLabel("Título:");
		JLabel labelanyoSelected = new JLabel("Año:");
		JLabel labelNacionalidadSelected = new JLabel("Nacionalidad:");
		JLabel labelGenero = new JLabel("Género:");
		JLabel labelDirectorSelected = new JLabel("Director:");


		JScrollPane scrollPane = new JScrollPane();

		int anyoActual = Calendar.getInstance().get(Calendar.YEAR);
		
		spinnerModel = new SpinnerNumberModel(0, 0, anyoActual, 1);
		this.setSpinnerAnyo(new JSpinner(spinnerModel));

		this.setModal(true); 		
		this.setTitle("Listado de ejemplares audiovisuales");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 670, 500);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		panelFiltro.setBorder(new TitledBorder(null, "Filtrar por...", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelFiltro.setBounds(10, 11, 599, 148);
		contentPanel.add(panelFiltro);
		panelFiltro.setLayout(null);
		
		setChkPeliculas(new JCheckBox("Películas."));
		getChkPeliculas().setSelected(true);
		getChkPeliculas().setBounds(27, 17, 104, 23);
		panelFiltro.add(getChkPeliculas());
		
		setChkDocumentales(new JCheckBox("Documentales."));
		getChkDocumentales().setSelected(true);
		getChkDocumentales().setBounds(154, 17, 117, 23);
		panelFiltro.add(getChkDocumentales());
		
		labelTitulo.setBounds(27, 51, 127, 14);
		panelFiltro.add(labelTitulo);
		
		this.setFieldTitulo(new JTextField());
		this.getFieldTitulo().setBounds(154, 44, 187, 28);
		panelFiltro.add(getFieldTitulo());
		this.getFieldTitulo().setColumns(33);
		
		labelNacionalidad.setBounds(27, 84, 127, 14);
		panelFiltro.add(labelNacionalidad);
		
		setComboNacionalidad(new JComboBox<PaisComboBox>());
		getComboNacionalidad().setBounds(154, 77, 187, 28);
		panelFiltro.add(getComboNacionalidad());
		
		this.btnAplicar = new JButton("Aplicar filtros");
		this.btnAplicar.setBounds(471, 109, 104, 23);
		this.btnAplicar.setActionCommand("btnAplicarFiltros");
		panelFiltro.add(btnAplicar);
		
		labelAnyo.setBounds(27, 118, 127, 14);
		panelFiltro.add(labelAnyo);
		
		getSpinnerAnyo().setBounds(154, 110, 72, 26);
		panelFiltro.add(getSpinnerAnyo());
		
		scrollPane.setBounds(10, 162, 552, 123);
		contentPanel.add(scrollPane);
		
		this.setDtmModelo(new DefaultTableModel(this.getNOMCOLUMNAS(), 0));
		
		this.setTablaEjemplares(new JTable(this.getDtmModelo()));
		this.getTablaEjemplares().getColumnModel().getColumn(0).setResizable(false);
		this.getTablaEjemplares().getColumnModel().getColumn(0).setPreferredWidth(60);
		this.getTablaEjemplares().getColumnModel().getColumn(1).setResizable(false);
		this.getTablaEjemplares().getColumnModel().getColumn(1).setPreferredWidth(270);
		this.getTablaEjemplares().getColumnModel().getColumn(2).setResizable(false);
		this.getTablaEjemplares().getColumnModel().getColumn(2).setPreferredWidth(220);
		this.getTablaEjemplares().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.getTablaEjemplares().setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(getTablaEjemplares());
		
		btnExportar = new JButton("Exportar");
		btnExportar.setEnabled(false);
		btnExportar.setBounds(573, 250, 55, 23);
		btnExportar.setActionCommand("btnExportar");
		contentPanel.add(btnExportar);
		
		panelDatos.setBorder(new TitledBorder(null, "Datos del ejemplar audiovisual seleccionado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDatos.setBounds(10, 297, 632, 120);
		contentPanel.add(panelDatos);
		panelDatos.setLayout(null);
		
		labelCodigoSelected.setBounds(27, 29, 73, 16);
		panelDatos.add(labelCodigoSelected);
		
		labelTituloSelected.setBounds(269, 30, 53, 14);
		panelDatos.add(labelTituloSelected);
		
		this.setFieldTituloSelected(new JTextField());
		this.getFieldTituloSelected().setEnabled(false);
		this.getFieldTituloSelected().setColumns(33);
		this.getFieldTituloSelected().setBounds(328, 23, 262, 28);
		panelDatos.add(this.getFieldTituloSelected());
		
		labelNacionalidadSelected.setBounds(27, 57, 83, 16);
		panelDatos.add(labelNacionalidadSelected);
		
		this.setFieldNacionalidadSelected(new JTextField());
		this.getFieldNacionalidadSelected().setEnabled(false);
		this.getFieldNacionalidadSelected().setColumns(25);
		this.getFieldNacionalidadSelected().setBounds(112, 50, 207, 28);
		panelDatos.add(this.getFieldNacionalidadSelected());
		
		this.setFieldCodigoSelected(new JTextField());
		this.getFieldCodigoSelected().setEnabled(false);
		this.getFieldCodigoSelected().setDragEnabled(true);
		this.getFieldCodigoSelected().setColumns(25);
		this.getFieldCodigoSelected().setBounds(112, 23, 83, 28);
		panelDatos.add(this.getFieldCodigoSelected());
		
		labelanyoSelected.setBounds(472, 56, 48, 16);
		panelDatos.add(labelanyoSelected);
		
		this.setFieldAnyoSelected(new JTextField());
		this.getFieldAnyoSelected().setEnabled(false);
		this.getFieldAnyoSelected().setBounds(507, 50, 83, 28);
		panelDatos.add(this.getFieldAnyoSelected());
		
		labelGenero.setBounds(27, 85, 73, 16);
		panelDatos.add(labelGenero);
		
		this.setFieldGeneroSelected(new JTextField());
		this.getFieldGeneroSelected().setEnabled(false);
		this.getFieldGeneroSelected().setColumns(25);
		this.getFieldGeneroSelected().setBounds(112, 79, 109, 28);
		panelDatos.add(getFieldGeneroSelected());
		
		labelDirectorSelected.setBounds(269, 85, 58, 16);
		panelDatos.add(labelDirectorSelected);
		
		this.setFieldDirectorSelected(new JTextField());
		this.getFieldDirectorSelected().setEnabled(false);
		this.getFieldDirectorSelected().setColumns(33);
		this.getFieldDirectorSelected().setBounds(328, 79, 262, 28);
		panelDatos.add(getFieldDirectorSelected());

		this.setPanelBtnOk(new PanelBtnOk());
		getContentPane().add(this.getPanelBtnOk(), BorderLayout.SOUTH);
		this.getRootPane().setDefaultButton(this.getPanelBtnOk().getBtnOk());
	}
	
	public void crearFilas(ArrayList<ListaEjemplar> array) {
		
		Object[] fila = new Object[this.getNOMCOLUMNAS().length];
		this.getDtmModelo().setRowCount(0);
		
		for (ListaEjemplar l : array) {
			fila[0] = l.getCodigo();
			fila[1] = l.getTitulo();
			fila[2] = l.getNacionalidad();
			this.getDtmModelo().addRow(fila);
		}
		
	}
	
	public void mostrarPelicula(Pelicula pelicula) {

		this.getFieldCodigoSelected().setText(pelicula.getCodigo().toString());
		this.getFieldTituloSelected().setText(pelicula.getTitulo());
		this.getFieldAnyoSelected().setText(Integer.toString(pelicula.getAnyo()));
		this.getFieldNacionalidadSelected().setText(pelicula.getNacionalidad().getDescripcion());
		this.getFieldGeneroSelected().setText(pelicula.getGenero().getDescripcion());
		this.getFieldDirectorSelected().setText(pelicula.getDirector().getNombre());
		
	}
	
	public void mostrarDocumental(Documental documental) {

		this.getFieldCodigoSelected().setText(documental.getCodigo().toString());
		this.getFieldTituloSelected().setText(documental.getTitulo());
		this.getFieldAnyoSelected().setText(Integer.toString(documental.getAnyo()));
		this.getFieldNacionalidadSelected().setText(documental.getNacionalidad().getDescripcion());
		this.getFieldGeneroSelected().setText(documental.getGenero().getDescripcion());
		this.getFieldDirectorSelected().setText(documental.getDirector().getNombre());
		
	}
	
	public void cargarPaises(ArrayList<Pais> paises) {
		
		PaisComboBox paisComboBox;
		
		this.comboNacionalidad.addItem(new PaisComboBox(0, "--Seleccione País--"));

		for (Pais p : paises) {
			paisComboBox = new PaisComboBox(p);
			this.comboNacionalidad.addItem(paisComboBox);	
		}		
		
	}

	// GETTERS & SETTERS
	public PanelBtnOk getPanelBtnOk() {
		return panelBtnOk;
	}

	public void setPanelBtnOk(PanelBtnOk panelBtnOk) {
		this.panelBtnOk = panelBtnOk;
	}

	public JTextField getFieldTitulo() {
		return fieldTitulo;
	}

	public void setFieldTitulo(JTextField fieldTitulo) {
		this.fieldTitulo = fieldTitulo;
	}

	public DefaultTableModel getDtmModelo() {
		return dtmModelo;
	}

	public void setDtmModelo(DefaultTableModel dtmModelo) {
		this.dtmModelo = dtmModelo;
	}

	public JTable getTablaEjemplares() {
		return tablaEjemplares;
	}

	public void setTablaEjemplares(JTable tableEjemplares) {
		this.tablaEjemplares = tableEjemplares;
	}

	public String[] getNOMCOLUMNAS() {
		return NOMCOLUMNAS;
	}

	public JButton getBtnAplicar() {
		return btnAplicar;
	}

	public void setBtnAplicar(JButton btnAplicar) {
		this.btnAplicar = btnAplicar;
	}

	public JButton getBtnExportar() {
		return btnExportar;
	}

	public void setBtnExportar(JButton btnExportar) {
		this.btnExportar = btnExportar;
	}

	public JCheckBox getChkPeliculas() {
		return chkPeliculas;
	}

	public void setChkPeliculas(JCheckBox chkPeliculas) {
		this.chkPeliculas = chkPeliculas;
	}

	public JCheckBox getChkDocumentales() {
		return chkDocumentales;
	}

	public void setChkDocumentales(JCheckBox chkDocumentales) {
		this.chkDocumentales = chkDocumentales;
	}

	public JComboBox<PaisComboBox> getComboNacionalidad() {
		return comboNacionalidad;
	}

	public void setComboNacionalidad(JComboBox<PaisComboBox> comboNacionalidad) {
		this.comboNacionalidad = comboNacionalidad;
	}

	public JTextField getFieldNacionalidadSelected() {
		return fieldNacionalidadSelected;
	}

	public void setFieldNacionalidadSelected(JTextField fieldNacionalidadSelected) {
		this.fieldNacionalidadSelected = fieldNacionalidadSelected;
	}

	public JTextField getFieldCodigoSelected() {
		return fieldCodigoSelected;
	}

	public void setFieldCodigoSelected(JTextField fieldCodigoSelected) {
		this.fieldCodigoSelected = fieldCodigoSelected;
	}

	public JTextField getFieldTituloSelected() {
		return fieldTituloSelected;
	}

	public void setFieldTituloSelected(JTextField fieldTituloSelected) {
		this.fieldTituloSelected = fieldTituloSelected;
	}

	public JTextField getFieldAnyoSelected() {
		return fieldAnyoSelected;
	}

	public void setFieldAnyoSelected(JTextField fieldAnyoSelected) {
		this.fieldAnyoSelected = fieldAnyoSelected;
	}

	public JSpinner getSpinnerAnyo() {
		return spinnerAnyo;
	}

	public void setSpinnerAnyo(JSpinner spinnerAnyo) {
		this.spinnerAnyo = spinnerAnyo;
	}

	public JTextField getFieldGeneroSelected() {
		return fieldGeneroSelected;
	}

	public void setFieldGeneroSelected(JTextField fieldGeneroSelected) {
		this.fieldGeneroSelected = fieldGeneroSelected;
	}

	public JTextField getFieldDirectorSelected() {
		return fieldDirectorSelected;
	}

	public void setFieldDirectorSelected(JTextField fieldDirectorSelected) {
		this.fieldDirectorSelected = fieldDirectorSelected;
	}
}
