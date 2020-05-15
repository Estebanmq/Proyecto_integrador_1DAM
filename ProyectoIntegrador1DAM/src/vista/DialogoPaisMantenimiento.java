package vista;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Pais;
import javax.swing.ListSelectionModel;

public class DialogoPaisMantenimiento extends JDialog {

	private static final long serialVersionUID = -6456737675544774116L;

	private final String NOMCOLUMNAS[] = new String[] {"Código", "Descripción"};
	
	private DefaultTableModel dtmModelo;
	private JTable tablaPaises;
	
	private PanelBtnOk panelBtnOk;

	public DialogoPaisMantenimiento() {	

		JPanel contentPanel = new JPanel();
		JScrollPane scrollPane = new JScrollPane();

		JLabel labelTexto = new JLabel("Relación de países existentes");

		
		this.setModal(true); 		
		this.setTitle("Mantenimiento de países");
		this.setBounds(100, 100, 386, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		labelTexto.setBounds(22, 54, 190, 14);
		contentPanel.add(labelTexto);

				
		scrollPane.setBounds(22, 79, 327, 124);
	
		contentPanel.add(scrollPane);

		this.setDtmModelo(new DefaultTableModel(this.getNOMCOLUMNAS(), 0));

		this.setTablaPaises(new JTable(this.getDtmModelo()));
		this.getTablaPaises().setToolTipText("Relación de países");
		
		scrollPane.add(this.getTablaPaises());
		scrollPane.setViewportView(this.getTablaPaises());
		
		this.setPanelBtnOk(new PanelBtnOk());
		getContentPane().add(this.getPanelBtnOk(), BorderLayout.SOUTH);
		this.getRootPane().setDefaultButton(this.getPanelBtnOk().getBtnOk());

	}
	
	public void crearFilas(ArrayList<Pais> array) {
		
		Object[] fila = new Object[this.getNOMCOLUMNAS().length];
		
		for (Pais p : array) {
			fila[0] = p.getCodigo();
			fila[1] = p.getDescripcion();
			this.getDtmModelo().addRow(fila);
		}
		
	}
	
	// GETTERS & SETTERS

	public JTable getTablaPaises() {
		return tablaPaises;
	}

	public void setTablaPaises(JTable tablaPaises) {
		this.tablaPaises = tablaPaises;
		tablaPaises.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPaises.setRowSelectionAllowed(false);
		tablaPaises.setEnabled(false);
	}

	private String[] getNOMCOLUMNAS() {
		return NOMCOLUMNAS;
	}

	public DefaultTableModel getDtmModelo() {
		return dtmModelo;
	}

	public void setDtmModelo(DefaultTableModel dtmModelo) {
		this.dtmModelo = dtmModelo;
	}

	public PanelBtnOk getPanelBtnOk() {
		return panelBtnOk;
	}

	public void setPanelBtnOk(PanelBtnOk panelBtnOk) {
		this.panelBtnOk = panelBtnOk;
	}
}
