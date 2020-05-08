package vista;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Pais;

public class DialogoPaisMantenimiento extends JDialog {

	private static final long serialVersionUID = -6456737675544774116L;

	private final String NOMCOLUMNAS[] = new String[] {"Código", "Descripción"};
	
	private DefaultTableModel dtmModelo;
	private final JPanel contentPanel = new JPanel();
	private JTable tablaPaises;
	
	private ArrayList<Pais> listaPaises;
	
	private PanelBtnsAceptarCancelar panelBtnsAceptarCancelar;

	public DialogoPaisMantenimiento() {
		setModal(true); 
		
		setTitle("Mantenimiento de países");
		setBounds(100, 100, 450, 300);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(getContentPanel(), BorderLayout.CENTER);
		this.getContentPanel().setLayout(null);
		this.setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 75, 327, 124);
		getContentPanel().add(scrollPane);

		this.setDtmModelo(new DefaultTableModel(this.getNOMCOLUMNAS(), 0));

		this.setTablaPaises(new JTable(this.getDtmModelo()));
		this.getTablaPaises().setToolTipText("Relación de países");
		this.getTablaPaises().setCellSelectionEnabled(true);
		this.getTablaPaises().setColumnSelectionAllowed(true);
		
		scrollPane.setViewportView(this.getTablaPaises());
		
		panelBtnsAceptarCancelar = new PanelBtnsAceptarCancelar();
		getContentPane().add(panelBtnsAceptarCancelar, BorderLayout.SOUTH);
		this.getRootPane().setDefaultButton(panelBtnsAceptarCancelar.getBtnAceptar());

		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

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
	public JPanel getContentPanel() {
		return contentPanel;
	}

	public JTable getTablaPaises() {
		return tablaPaises;
	}

	public void setTablaPaises(JTable tablaPaises) {
		this.tablaPaises = tablaPaises;
	}

	private String[] getNOMCOLUMNAS() {
		return NOMCOLUMNAS;
	}

	private DefaultTableModel getDtmModelo() {
		return dtmModelo;
	}

	private void setDtmModelo(DefaultTableModel dtmModelo) {
		this.dtmModelo = dtmModelo;
	}

	public ArrayList<Pais> getListaPaises() {
		return listaPaises;
	}

	public void setListaPaises(ArrayList<Pais> listaPaises) {
		this.listaPaises = listaPaises;
	}

	public PanelBtnsAceptarCancelar getPanelBtnsAceptarCancelar() {
		return panelBtnsAceptarCancelar;
	}

	public void setPanelBtnsAceptarCancelar(PanelBtnsAceptarCancelar panelBtnsAceptarCancelar) {
		this.panelBtnsAceptarCancelar = panelBtnsAceptarCancelar;
	}
	
}
