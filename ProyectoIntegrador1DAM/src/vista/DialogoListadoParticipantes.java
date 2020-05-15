package vista;

import java.awt.BorderLayout;

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

public class DialogoListadoParticipantes extends JDialog {

	private static final long serialVersionUID = -222187089871399229L;
	
	private final String NOMCOLUMNAS[] = new String[] {"Código", "Nombre", "Nº apariciones"};
	
	private JTextField fieldNombre;
	private JTextField fieldCodigo;
	
	private DefaultTableModel dtmModelo;
	private JTable tableParticipantes;
	
	private PanelBtnOk panelBtnOk;
	
	public DialogoListadoParticipantes() {
		
		JPanel contentPanel = new JPanel();
		JPanel panelFiltro = new JPanel();
		JCheckBox chkDirectores = new JCheckBox("Directores.");
		JCheckBox chkParticipantes = new JCheckBox("Participantes.");
		JLabel labelNombre = new JLabel("Nombre:");
		JLabel labelEjemplar = new JLabel("Ejemplar audiovisual:");
		JComboBox comboEjemplar = new JComboBox();
		JLabel labelNacionalidad = new JLabel("Nacionalidad:");
		JToggleButton tglbtnFemenino = new JToggleButton("Femenino");
		JToggleButton tglbtnMasculino = new JToggleButton("Masculino");
		JComboBox comboNacionalidad = new JComboBox();
		JLabel labelCodigo = new JLabel("Cód. participante:");
		JButton btnAplicar = new JButton("Aplicar filtros");

		JScrollPane scrollPane = new JScrollPane();
		JButton btnExportar = new JButton("Exportar");

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
		panelFiltro.setBounds(10, 11, 599, 197);
		contentPanel.add(panelFiltro);
		panelFiltro.setLayout(null);
		
		chkDirectores.setSelected(true);
		chkDirectores.setBounds(27, 17, 97, 23);
		panelFiltro.add(chkDirectores);
		
		chkParticipantes.setSelected(true);
		chkParticipantes.setBounds(154, 17, 97, 23);
		panelFiltro.add(chkParticipantes);
		
		labelNombre.setBounds(27, 80, 97, 14);
		panelFiltro.add(labelNombre);
		
		labelEjemplar.setBounds(27, 47, 117, 14);
		panelFiltro.add(labelEjemplar);
		
		comboEjemplar.setBounds(154, 40, 187, 28);
		panelFiltro.add(comboEjemplar);
		
		this.setFieldNombre(new JTextField());
		this.getFieldNombre().setBounds(154, 73, 187, 28);
		panelFiltro.add(getFieldNombre());
		this.getFieldNombre().setColumns(10);
		
		labelNacionalidad.setBounds(27, 113, 117, 14);
		panelFiltro.add(labelNacionalidad);
		
		comboNacionalidad.setBounds(154, 106, 187, 28);
		panelFiltro.add(comboNacionalidad);
		
		JPanel panelSexo = new JPanel();
		panelSexo.setBorder(new TitledBorder(null, "Sexo", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelSexo.setBounds(366, 79, 211, 61);
		panelFiltro.add(panelSexo);
		
		tglbtnFemenino.setSelected(true);
		panelSexo.add(tglbtnFemenino);
		
		tglbtnMasculino.setSelected(true);
		panelSexo.add(tglbtnMasculino);
		
		labelCodigo.setBounds(27, 147, 97, 14);
		panelFiltro.add(labelCodigo);
		
		this.setFieldCodigo(new JTextField());
		this.getFieldCodigo().setColumns(10);
		this.getFieldCodigo().setBounds(154, 140, 61, 28);
		panelFiltro.add(getFieldCodigo());
		
		btnAplicar.setBounds(473, 156, 104, 23);
		btnAplicar.setActionCommand("btnAplicarFiltros");
		panelFiltro.add(btnAplicar);
		
		scrollPane.setBounds(10, 208, 552, 189);
		contentPanel.add(scrollPane);
		
		this.setDtmModelo(new DefaultTableModel(this.getNOMCOLUMNAS(), 0));
		
		this.setTableParticipantes(new JTable(this.getDtmModelo()));
		this.getTableParticipantes().getColumnModel().getColumn(1).setPreferredWidth(275);
		this.getTableParticipantes().getColumnModel().getColumn(2).setResizable(false);
		this.getTableParticipantes().getColumnModel().getColumn(2).setPreferredWidth(85);
		this.getTableParticipantes().setToolTipText("Relación de participantes");
		this.getTableParticipantes().setShowHorizontalLines(false);
		scrollPane.setViewportView(getTableParticipantes());
		
		btnExportar.setEnabled(false);
		btnExportar.setBounds(574, 360, 55, 23);
		btnExportar.setActionCommand("btnExportar");
		contentPanel.add(btnExportar);

		this.setPanelBtnOk(new PanelBtnOk());
		getContentPane().add(this.getPanelBtnOk(), BorderLayout.SOUTH);
		this.getRootPane().setDefaultButton(this.getPanelBtnOk().getBtnOk());
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

	public JTextField getFieldCodigo() {
		return fieldCodigo;
	}

	public void setFieldCodigo(JTextField fieldCodigo) {
		this.fieldCodigo = fieldCodigo;
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
}
