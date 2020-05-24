package vista;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Actuacion;
import modelo.Director;
import modelo.Interprete;
import modelo.ListaParticipante;
import modelo.Pais;
import modelo.PaisComboBox;
/**
 * 
 * Esta clase genera la pantalla de visualización del listado de participantes
 * 
 * @author Jose Manuel de Dios
 * @version 1.0
 * @since 10/05/2020
 */
public class DialogoParticipanteListado extends JDialog {

	private static final long serialVersionUID = -222187089871399229L;
	
	private final String NOMCOLUMNAS[] = new String[] {"Código", "Nombre", "Nacionalidad"};
	
	private DefaultTableModel dtmModelo;
	private JTable tablaParticipantes;
	
	private PanelBtnOk panelBtnOk;

	private JButton btnAplicar;
	private JButton btnExportar;

	private JCheckBox chkDirectores;
	private JCheckBox chkInterpretes;
	private JComboBox<Actuacion> comboPelicula;
	private JTextField fieldNombre;
	private JComboBox<PaisComboBox> comboNacionalidad;
	private JToggleButton tglbtnFemenino;
	private JToggleButton tglbtnMasculino;
	
	private JTextField fieldNacionalidadSelected;
	private JTextField fieldCodigoSelected;
	private JTextField fieldNombreSelected;
	private JTextField fieldFNacimientoSelected;
	private JTextField fieldSexoSelected;
	
	public DialogoParticipanteListado() {
		
		JPanel contentPanel = new JPanel();
		JPanel panelSexo = new JPanel();
		JPanel panelFiltro = new JPanel();
		JLabel labelNombre = new JLabel("Nombre:");
		JLabel labelEjemplar = new JLabel("Ejemplar audiovisual:");
		JLabel labelNacionalidad = new JLabel("Nacionalidad:");
		JPanel panelDatos = new JPanel();
		JLabel labelCodigoSelected = new JLabel("Código:");
		JLabel labelNombreSelected = new JLabel("Nombre:");
		JLabel labelFnacSelected = new JLabel("F. nacimiento:");
		JLabel labelSexoSelected = new JLabel("Sexo:");
		JLabel labelNacionalidadSelected = new JLabel("Nacionalidad:");


		JScrollPane scrollPane = new JScrollPane();
		

		this.setModal(true); 		
		this.setTitle("Listado de participantes");
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
		
		setChkDirectores(new JCheckBox("Directores."));
		getChkDirectores().setSelected(true);
		getChkDirectores().setBounds(27, 17, 104, 23);
		panelFiltro.add(getChkDirectores());
		
		setChkInterpretes(new JCheckBox("Intérpretes."));
		getChkInterpretes().setSelected(true);
		getChkInterpretes().setBounds(154, 17, 117, 23);
		panelFiltro.add(getChkInterpretes());
		
		labelNombre.setBounds(27, 80, 127, 14);
		panelFiltro.add(labelNombre);
		
		labelEjemplar.setBounds(27, 47, 127, 14);
		panelFiltro.add(labelEjemplar);
		
		setComboPelicula(new JComboBox<Actuacion>());
		getComboPelicula().setEnabled(false);
		getComboPelicula().setBounds(154, 40, 187, 28);
		panelFiltro.add(getComboPelicula());
		
		this.setFieldNombre(new JTextField());
		this.getFieldNombre().setBounds(154, 73, 187, 28);
		panelFiltro.add(getFieldNombre());
		this.getFieldNombre().setColumns(10);
		
		labelNacionalidad.setBounds(27, 113, 127, 14);
		panelFiltro.add(labelNacionalidad);
		
		setComboNacionalidad(new JComboBox<PaisComboBox>());
		getComboNacionalidad().setBounds(154, 106, 187, 28);
		panelFiltro.add(getComboNacionalidad());
		
		panelSexo.setBorder(new TitledBorder(null, "Sexo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSexo.setBounds(365, 33, 211, 61);
		panelFiltro.add(panelSexo);
		
		setTglbtnFemenino(new JToggleButton("Femenino"));
		getTglbtnFemenino().setSelected(true);
		panelSexo.add(getTglbtnFemenino());
		
		setTglbtnMasculino(new JToggleButton("Masculino"));
		getTglbtnMasculino().setSelected(true);
		panelSexo.add(getTglbtnMasculino());		
		
		this.btnAplicar = new JButton("Aplicar filtros");
		this.btnAplicar.setBounds(471, 109, 104, 23);
		this.btnAplicar.setActionCommand("btnAplicarFiltros");
		panelFiltro.add(btnAplicar);
		
		scrollPane.setBounds(10, 162, 552, 133);
		contentPanel.add(scrollPane);
		
		this.setDtmModelo(new DefaultTableModel(this.NOMCOLUMNAS, 0));
		
		this.setTablaParticipantes(new JTable(this.getDtmModelo()));
		this.getTablaParticipantes().getColumnModel().getColumn(0).setResizable(false);
		this.getTablaParticipantes().getColumnModel().getColumn(0).setPreferredWidth(60);
		this.getTablaParticipantes().getColumnModel().getColumn(1).setResizable(false);
		this.getTablaParticipantes().getColumnModel().getColumn(1).setPreferredWidth(270);
		this.getTablaParticipantes().getColumnModel().getColumn(2).setResizable(false);
		this.getTablaParticipantes().getColumnModel().getColumn(2).setPreferredWidth(220);
		this.getTablaParticipantes().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.getTablaParticipantes().setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.getTablaParticipantes().setToolTipText("Relación de participantes");
		scrollPane.setViewportView(getTablaParticipantes());
		
		btnExportar = new JButton("Exportar");
		btnExportar.setEnabled(false);
		btnExportar.setBounds(573, 250, 55, 23);
		btnExportar.setActionCommand("btnExportar");
		contentPanel.add(btnExportar);
		
		panelDatos.setBorder(new TitledBorder(null, "Datos del participante seleccionado", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelDatos.setBounds(10, 307, 632, 110);
		contentPanel.add(panelDatos);
		panelDatos.setLayout(null);
		
		labelCodigoSelected.setBounds(27, 23, 73, 16);
		panelDatos.add(labelCodigoSelected);
		
		labelNombreSelected.setBounds(27, 51, 83, 14);
		panelDatos.add(labelNombreSelected);
		
		this.setFieldNombreSelected(new JTextField());
		this.getFieldNombreSelected().setEnabled(false);
		this.getFieldNombreSelected().setDragEnabled(true);
		this.getFieldNombreSelected().setColumns(33);
		this.getFieldNombreSelected().setBounds(112, 44, 262, 28);
		panelDatos.add(this.getFieldNombreSelected());
		
		labelNacionalidadSelected.setBounds(27, 77, 83, 16);
		panelDatos.add(labelNacionalidadSelected);
		
		this.setFieldNacionalidadSelected(new JTextField());
		this.getFieldNacionalidadSelected().setEnabled(false);
		this.getFieldNacionalidadSelected().setDragEnabled(true);
		this.getFieldNacionalidadSelected().setColumns(25);
		this.getFieldNacionalidadSelected().setBounds(112, 71, 207, 28);
		panelDatos.add(this.getFieldNacionalidadSelected());
		
		this.setFieldCodigoSelected(new JTextField());
		this.getFieldCodigoSelected().setEnabled(false);
		this.getFieldCodigoSelected().setDragEnabled(true);
		this.getFieldCodigoSelected().setColumns(25);
		this.getFieldCodigoSelected().setBounds(112, 17, 83, 28);
		panelDatos.add(this.getFieldCodigoSelected());
		
		labelFnacSelected.setBounds(439, 50, 96, 16);
		panelDatos.add(labelFnacSelected);
		
		this.setFieldFNacimientoSelected(new JTextField());
		this.getFieldFNacimientoSelected().setEnabled(false);
		this.getFieldFNacimientoSelected().setDragEnabled(true);
		this.getFieldFNacimientoSelected().setColumns(10);
		this.getFieldFNacimientoSelected().setBounds(532, 44, 83, 28);
		panelDatos.add(this.getFieldFNacimientoSelected());
		
		labelSexoSelected.setBounds(439, 77, 96, 16);
		panelDatos.add(labelSexoSelected);
		
		this.setFieldSexoSelected(new JTextField());
		this.getFieldSexoSelected().setEnabled(false);
		this.getFieldSexoSelected().setDragEnabled(true);
		this.getFieldSexoSelected().setColumns(10);
		this.getFieldSexoSelected().setBounds(532, 71, 83, 28);
		panelDatos.add(this.getFieldSexoSelected());

		this.setPanelBtnOk(new PanelBtnOk());
		getContentPane().add(this.getPanelBtnOk(), BorderLayout.SOUTH);
		this.getRootPane().setDefaultButton(this.getPanelBtnOk().getBtnOk());
	}
	
	public void crearFilas(ArrayList<ListaParticipante> array) {
		
		Object[] fila = new Object[this.NOMCOLUMNAS.length];
		this.getDtmModelo().setRowCount(0);
		
		for (ListaParticipante l : array) {
			fila[0] = l.getCodigo();
			fila[1] = l.getNombre();
			fila[2] = l.getNacionalidad();
			this.getDtmModelo().addRow(fila);
		}
		
	}
	
	public void mostrarDirector(Director director) {

		this.getFieldCodigoSelected().setText(director.getCodigo().toString());
		this.getFieldNombreSelected().setText(director.getNombre());
		this.getFieldFNacimientoSelected().setText(new SimpleDateFormat("dd-MM-yyyy").format(director.getFechaNacimiento()));
		this.getFieldNacionalidadSelected().setText(director.getNacionalidad().getDescripcion());
		this.getFieldSexoSelected().setText(director.getSexo().getDescripcion());
		
	}
	
	public void mostrarInterprete(Interprete interprete) {

		this.getFieldCodigoSelected().setText(interprete.getCodigo().toString());
		this.getFieldNombreSelected().setText(interprete.getNombre());
		this.getFieldFNacimientoSelected().setText(new SimpleDateFormat("dd-MM-yyyy").format(interprete.getFechaNacimiento()));
		this.getFieldNacionalidadSelected().setText(interprete.getNacionalidad().getDescripcion());
		this.getFieldSexoSelected().setText(interprete.getSexo().getDescripcion());
		
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

	public JTextField getFieldNombre() {
		return fieldNombre;
	}

	public void setFieldNombre(JTextField fieldNombre) {
		this.fieldNombre = fieldNombre;
	}

	public DefaultTableModel getDtmModelo() {
		return dtmModelo;
	}

	public void setDtmModelo(DefaultTableModel dtmModelo) {
		this.dtmModelo = dtmModelo;
	}

	public JTable getTablaParticipantes() {
		return tablaParticipantes;
	}

	public void setTablaParticipantes(JTable tableParticipantes) {
		this.tablaParticipantes = tableParticipantes;
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

	public JCheckBox getChkDirectores() {
		return chkDirectores;
	}

	public void setChkDirectores(JCheckBox chkDirectores) {
		this.chkDirectores = chkDirectores;
	}

	public JCheckBox getChkInterpretes() {
		return chkInterpretes;
	}

	public void setChkInterpretes(JCheckBox chkInterpretes) {
		this.chkInterpretes = chkInterpretes;
	}

	public JComboBox<Actuacion> getComboPelicula() {
		return comboPelicula;
	}

	public void setComboPelicula(JComboBox<Actuacion> comboPelicula) {
		this.comboPelicula = comboPelicula;
	}

	public JToggleButton getTglbtnFemenino() {
		return tglbtnFemenino;
	}

	public void setTglbtnFemenino(JToggleButton tglbtnFemenino) {
		this.tglbtnFemenino = tglbtnFemenino;
	}

	public JToggleButton getTglbtnMasculino() {
		return tglbtnMasculino;
	}

	public void setTglbtnMasculino(JToggleButton tglbtnMasculino) {
		this.tglbtnMasculino = tglbtnMasculino;
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

	public JTextField getFieldNombreSelected() {
		return fieldNombreSelected;
	}

	public void setFieldNombreSelected(JTextField fieldNombreSelected) {
		this.fieldNombreSelected = fieldNombreSelected;
	}

	public JTextField getFieldFNacimientoSelected() {
		return fieldFNacimientoSelected;
	}

	public void setFieldFNacimientoSelected(JTextField fieldFNacimientoSelected) {
		this.fieldFNacimientoSelected = fieldFNacimientoSelected;
	}

	public JTextField getFieldSexoSelected() {
		return fieldSexoSelected;
	}

	public void setFieldSexoSelected(JTextField fieldSexoSelected) {
		this.fieldSexoSelected = fieldSexoSelected;
	}
}
