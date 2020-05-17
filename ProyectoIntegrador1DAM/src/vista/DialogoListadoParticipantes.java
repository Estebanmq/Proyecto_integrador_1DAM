package vista;

import java.awt.BorderLayout;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Actuacion;
import modelo.ListaParticipante;
import modelo.Pais;
import modelo.PaisComboBox;

public class DialogoListadoParticipantes extends JDialog {

	private static final long serialVersionUID = -222187089871399229L;
	
	private final String NOMCOLUMNAS[] = new String[] {"Código", "Nombre", "Nacionalidad"};
	
	private DefaultTableModel dtmModelo;
	private JTable tableParticipantes;
	
	private PanelBtnOk panelBtnOk;

	private JButton btnAplicar;
	private JButton btnExportar;

	private JCheckBox chkDirectores;
	private JCheckBox chkParticipantes;
	private JComboBox<Actuacion> comboPelicula;
	private JTextField fieldNombre;
	private JComboBox<PaisComboBox> comboNacionalidad;
	private JToggleButton tglbtnFemenino;
	private JToggleButton tglbtnMasculino;
	
	public DialogoListadoParticipantes() {
		
		JPanel contentPanel = new JPanel();
		JPanel panelFiltro = new JPanel();
		JLabel labelNombre = new JLabel("Nombre:");
		JLabel labelEjemplar = new JLabel("Ejemplar audiovisual:");
		JLabel labelNacionalidad = new JLabel("Nacionalidad:");

		JScrollPane scrollPane = new JScrollPane();
		

		this.setModal(true); 		
		this.setTitle("Listado de participantes");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 664, 480);
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
		getChkDirectores().setBounds(27, 17, 97, 23);
		panelFiltro.add(getChkDirectores());
		
		setChkParticipantes(new JCheckBox("Participantes."));
		getChkParticipantes().setSelected(true);
		getChkParticipantes().setBounds(154, 17, 97, 23);
		panelFiltro.add(getChkParticipantes());
		
		labelNombre.setBounds(27, 80, 97, 14);
		panelFiltro.add(labelNombre);
		
		labelEjemplar.setBounds(27, 47, 120, 14);
		panelFiltro.add(labelEjemplar);
		
		setComboPelicula(new JComboBox<Actuacion>());
		getComboPelicula().setEnabled(false);
		getComboPelicula().setBounds(154, 40, 187, 28);
		panelFiltro.add(getComboPelicula());
		
		this.setFieldNombre(new JTextField());
		this.getFieldNombre().setBounds(154, 73, 187, 28);
		panelFiltro.add(getFieldNombre());
		this.getFieldNombre().setColumns(10);
		
		labelNacionalidad.setBounds(27, 113, 117, 14);
		panelFiltro.add(labelNacionalidad);
		
		setComboNacionalidad(new JComboBox<PaisComboBox>());
		getComboNacionalidad().setBounds(154, 106, 187, 28);
		panelFiltro.add(getComboNacionalidad());
		
		JPanel panelSexo = new JPanel();
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
		
		scrollPane.setBounds(10, 162, 552, 235);
		contentPanel.add(scrollPane);
		
		this.setDtmModelo(new DefaultTableModel(this.getNOMCOLUMNAS(), 0));
		
		this.setTableParticipantes(new JTable(this.getDtmModelo()));
		this.getTableParticipantes().getColumnModel().getColumn(1).setResizable(false);
		this.getTableParticipantes().getColumnModel().getColumn(1).setPreferredWidth(80);
		this.getTableParticipantes().getColumnModel().getColumn(2).setResizable(false);
		this.getTableParticipantes().getColumnModel().getColumn(2).setPreferredWidth(170);
		this.getTableParticipantes().getColumnModel().getColumn(2).setResizable(false);
		this.getTableParticipantes().getColumnModel().getColumn(2).setPreferredWidth(120);
		this.getTableParticipantes().setToolTipText("Relación de participantes");
		this.getTableParticipantes().setShowHorizontalLines(false);
		scrollPane.setViewportView(getTableParticipantes());
		
		btnExportar = new JButton("Exportar");
		btnExportar.setEnabled(false);
		btnExportar.setBounds(574, 360, 55, 23);
		btnExportar.setActionCommand("btnExportar");
		contentPanel.add(btnExportar);

		this.setPanelBtnOk(new PanelBtnOk());
		getContentPane().add(this.getPanelBtnOk(), BorderLayout.SOUTH);
		this.getRootPane().setDefaultButton(this.getPanelBtnOk().getBtnOk());
	}
	
	public void crearFilas(ArrayList<ListaParticipante> array) {
		
		Object[] fila = new Object[this.getNOMCOLUMNAS().length];
		this.getDtmModelo().setRowCount(0);
		
		for (ListaParticipante l : array) {
			fila[0] = l.getCodigo();
			fila[1] = l.getNombre();
			fila[2] = l.getNacionalidad();
			this.getDtmModelo().addRow(fila);
		}
		
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

	public JTable getTableParticipantes() {
		return tableParticipantes;
	}

	public void setTableParticipantes(JTable tableParticipantes) {
		this.tableParticipantes = tableParticipantes;
	}

	public String[] getNOMCOLUMNAS() {
		return NOMCOLUMNAS;
	}

	/**
	 * @return the btnAplicar
	 */
	public JButton getBtnAplicar() {
		return btnAplicar;
	}

	/**
	 * @param btnAplicar the btnAplicar to set
	 */
	public void setBtnAplicar(JButton btnAplicar) {
		this.btnAplicar = btnAplicar;
	}

	/**
	 * @return the btnExportar
	 */
	public JButton getBtnExportar() {
		return btnExportar;
	}

	/**
	 * @param btnExportar the btnExportar to set
	 */
	public void setBtnExportar(JButton btnExportar) {
		this.btnExportar = btnExportar;
	}

	public JCheckBox getChkDirectores() {
		return chkDirectores;
	}

	public void setChkDirectores(JCheckBox chkDirectores) {
		this.chkDirectores = chkDirectores;
	}

	public JCheckBox getChkParticipantes() {
		return chkParticipantes;
	}

	public void setChkParticipantes(JCheckBox chkParticipantes) {
		this.chkParticipantes = chkParticipantes;
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
}
