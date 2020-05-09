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

public class DialogoPaisMantenimiento extends JDialog {

	private static final long serialVersionUID = -6456737675544774116L;

	private final String NOMCOLUMNAS[] = new String[] {"Código", "Descripción"};
	
	private DefaultTableModel dtmModelo;
	private final JPanel contentPanel = new JPanel();
	private JTable tablaPaises;
	private JScrollPane scrollPane;
	
	private PanelBtnsAceptarCancelar panelBtnsAceptarCancelar;

	public DialogoPaisMantenimiento() {	
		
		JLabel labelTexto = new JLabel("Relación de países existentes");
		
		this.setModal(true); 
		
		this.setTitle("Mantenimiento de países");
		this.setBounds(100, 100, 386, 300);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		this.getContentPane().add(getContentPanel(), BorderLayout.CENTER);
		this.getContentPanel().setLayout(null);
		
		labelTexto.setBounds(22, 54, 155, 14);
		this.getContentPanel().add(labelTexto);

		
		this.setScrollPane(new JScrollPane());
		this.getScrollPane().setBounds(22, 79, 327, 124);
		
		this.getContentPanel().add(getScrollPane());

		this.setDtmModelo(new DefaultTableModel(this.getNOMCOLUMNAS(), 0));

		this.setTablaPaises(new JTable(this.getDtmModelo()));
		this.getTablaPaises().setToolTipText("Relación de países");
		this.getTablaPaises().setCellSelectionEnabled(true);
		this.getTablaPaises().setColumnSelectionAllowed(true);
		
		this.getScrollPane().add(this.getTablaPaises());
		this.getScrollPane().setViewportView(this.getTablaPaises());
		
		panelBtnsAceptarCancelar = new PanelBtnsAceptarCancelar();
		getContentPane().add(panelBtnsAceptarCancelar, BorderLayout.SOUTH);
		this.getRootPane().setDefaultButton(panelBtnsAceptarCancelar.getBtnAceptar());

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

	public DefaultTableModel getDtmModelo() {
		return dtmModelo;
	}

	public void setDtmModelo(DefaultTableModel dtmModelo) {
		this.dtmModelo = dtmModelo;
	}

	public PanelBtnsAceptarCancelar getPanelBtnsAceptarCancelar() {
		return panelBtnsAceptarCancelar;
	}

	public void setPanelBtnsAceptarCancelar(PanelBtnsAceptarCancelar panelBtnsAceptarCancelar) {
		this.panelBtnsAceptarCancelar = panelBtnsAceptarCancelar;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public void setScrollPane(JScrollPane scrollPane) {
		this.scrollPane = scrollPane;
	}
}
