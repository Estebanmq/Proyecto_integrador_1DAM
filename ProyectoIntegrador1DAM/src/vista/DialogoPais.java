package vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class DialogoPais extends JDialog {

	private static final long serialVersionUID = 183432188991880411L;

	private final JPanel contentPanel = new JPanel();
	private TableColumn columnaCodigo;
	private TableColumn columnaDescripcion;
	
	private JButton btnAceptar;
	private JButton btnCancelar;

	private JPanel buttonPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JTable tablaPais;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DialogoPais dialog = new DialogoPais();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogoPais() {
		
		this.setTitle("Mantenimiento de países");
		this.setBounds(100, 100, 655, 329);
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLocationRelativeTo(null);
		this.getContentPane().add(getContentPanel(), BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(82, 110, 461, 136);
		contentPanel.add(scrollPane_1);
		
		tablaPais = new JTable();
		tablaPais.setBorder(new BevelBorder(BevelBorder.RAISED, Color.RED, Color.BLUE, Color.ORANGE, Color.GREEN));
		tablaPais.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "New column"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane_1.setRowHeaderView(tablaPais);
		
		this.setColumnaCodigo(new TableColumn());
//		this.getColumnaCodigo().
		
//		this.setColumnaDescripcion(new TableColumn());
		
		
		
//		this.getTablaPaises().addC   addColumn("Código");
//		this.getTablaPaises().addColumn("Descripción");

		this.setButtonPane(new JPanel());
		this.getButtonPane().setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.getContentPane().add(getButtonPane(), BorderLayout.SOUTH);

		this.setBtnAceptar(new JButton("Aceptar"));
		this.getBtnAceptar().setActionCommand("btnAceptar");
		this.getButtonPane().add(this.getBtnAceptar());
		this.getRootPane().setDefaultButton(this.getBtnAceptar());
				
		this.setBtnCancelar(new JButton("Cancelar"));
		this.getBtnCancelar().setActionCommand("btnCancelar");
		this.getButtonPane().add(this.getBtnCancelar());

	}
	
	// GETTERS & SETTERS
	public JPanel getContentPanel() {
		return contentPanel;
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

	public JPanel getButtonPane() {
		return buttonPane;
	}

	public void setButtonPane(JPanel buttonPane) {
		this.buttonPane = buttonPane;
	}

	public TableColumn getColumnaCodigo() {
		return columnaCodigo;
	}

	public void setColumnaCodigo(TableColumn columnaCodigo) {
		this.columnaCodigo = columnaCodigo;
	}

	public TableColumn getColumnaDescripcion() {
		return columnaDescripcion;
	}

	public void setColumnaDescripcion(TableColumn columnaDescripcion) {
		this.columnaDescripcion = columnaDescripcion;
	}
}
