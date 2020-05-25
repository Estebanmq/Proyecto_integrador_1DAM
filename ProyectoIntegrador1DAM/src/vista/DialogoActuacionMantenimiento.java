package vista;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import modelo.ListaInterprete;
import modelo.ListaPersonaje;
import java.awt.Color;

public class DialogoActuacionMantenimiento extends JDialog {

	private static final long serialVersionUID = 6524971885930777430L;

	static final String NOMCOLUMNASINTERPRETE[] = new String[] {"Cod.", "Nombre"};
	static final String NOMCOLUMNASPERSONAJE[] = new String[] {"Cod.", "Nombre", "Personaje"};
	
	private PanelBtnsAceptarCancelar panelBtnsAceptarCancelar;
	private JTextField fieldCodigoPelicula;

	private JButton btnBuscar;
	private JTextField fieldInterpreteSelected;
	private JTextField fieldNombrePersonaje;
	private JButton btnAdd;
	private JTextField fieldTitulo;
	
	private DefaultTableModel dtmModeloTablaInterprete;
	private DefaultTableModel dtmModeloTablaPersonaje;

	private JTable tablaInterprete;
	private JTable tablaPersonaje;

	private ListaInterprete listaInterprete;
	
													// Almacena la lista de interpretes a mostrar en la pantalla
	private HashSet<ListaInterprete> listaInterpretes;

													// Almacena la lista de personajes creada en pantalla
	private HashSet<ListaPersonaje> listaPersonajes;


	public DialogoActuacionMantenimiento() {
		
		this.setListaInterprete(new ListaInterprete());

		JPanel contentPanel = new JPanel();
		JLabel labelCodigoPelicula = new JLabel("Código de película:");
		JScrollPane scrollPaneInterprete = new JScrollPane();
		JScrollPane scrollPanePersonaje = new JScrollPane();
		JLabel labelNombrePersonaje = new JLabel("Nombre de personaje:");
		JLabel labelInterpretes = new JLabel("Intérpretes");
		JLabel labelInterpretesAdd = new JLabel("Intérpretes en la película");

		
		
		this.setTitle("Mantenimiento de actuaciones");
		this.setModal(true);
		this.setBounds(100, 100, 656, 366);
		this.setLocationRelativeTo(null);
		this.getContentPane().setLayout(new BorderLayout());
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);		
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));		
		
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		labelCodigoPelicula.setBounds(7, 12, 118, 16);
		contentPanel.add(labelCodigoPelicula);
		
		this.setFieldCodigoPelicula(new JTextField());
		this.getFieldCodigoPelicula().setBounds(126, 6, 94, 28);
		contentPanel.add(getFieldCodigoPelicula());
		this.getFieldCodigoPelicula().setColumns(10);
		
		this.setBtnBuscar(new JButton("Buscar"));
		this.getBtnBuscar().setBounds(230, 6, 77, 28);
		this.getBtnBuscar().setActionCommand("btnBuscar");
		contentPanel.add(getBtnBuscar());
		
		this.setFieldTitulo(new JTextField());
		this.getFieldTitulo().setEnabled(false);
		this.getFieldTitulo().setEditable(false);
		this.getFieldTitulo().setBounds(319, 6, 301, 28);
		contentPanel.add(getFieldTitulo());


		this.setDtmModeloTablaInterprete(new DefaultTableModel(NOMCOLUMNASINTERPRETE, 0));
		this.setDtmModeloTablaPersonaje(new DefaultTableModel(NOMCOLUMNASPERSONAJE, 0));

		
		scrollPaneInterprete.setBounds(6, 57, 164, 226);
		contentPanel.add(scrollPaneInterprete);

		this.setTablaInterprete(new JTable(this.getDtmModeloTablaInterprete()));
		this.getTablaInterprete().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.getTablaInterprete().getColumnModel().getColumn(0).setResizable(false);
		this.getTablaInterprete().getColumnModel().getColumn(0).setPreferredWidth(20);
		this.getTablaInterprete().getColumnModel().getColumn(1).setResizable(false);
		this.getTablaInterprete().getColumnModel().getColumn(1).setPreferredWidth(100);	
		scrollPaneInterprete.setViewportView(this.getTablaInterprete());
		
		
		this.getTablaInterprete().getSelectionModel().addListSelectionListener(new TablaInterpreteListener());

		
		scrollPanePersonaje.setBounds(346, 57, 274, 226);
		contentPanel.add(scrollPanePersonaje);
		
		this.setTablaPersonaje(new JTable(this.getDtmModeloTablaPersonaje()));
		this.getTablaPersonaje().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.getTablaPersonaje().getColumnModel().getColumn(0).setResizable(false);
		this.getTablaPersonaje().getColumnModel().getColumn(0).setPreferredWidth(20);
		this.getTablaPersonaje().getColumnModel().getColumn(1).setResizable(false);
		this.getTablaPersonaje().getColumnModel().getColumn(1).setPreferredWidth(100);	
		this.getTablaPersonaje().getColumnModel().getColumn(2).setResizable(false);
		this.getTablaPersonaje().getColumnModel().getColumn(2).setPreferredWidth(100);	
		scrollPanePersonaje.setViewportView(this.getTablaPersonaje());


		this.setFieldInterpreteSelected(new JTextField());
		this.getFieldInterpreteSelected().setEnabled(false);
		this.getFieldInterpreteSelected().setEditable(false);
		this.getFieldInterpreteSelected().setForeground(Color.GRAY);
		this.getFieldInterpreteSelected().setBounds(175, 57, 164, 28);
		contentPanel.add(this.getFieldInterpreteSelected());


		labelNombrePersonaje.setHorizontalAlignment(SwingConstants.CENTER);
		labelNombrePersonaje.setBounds(175, 107, 164, 16);
		contentPanel.add(labelNombrePersonaje);

		this.setFieldNombrePersonaje(new JTextField());
		this.getFieldNombrePersonaje().setEnabled(false);
		this.getFieldNombrePersonaje().setBounds(175, 124, 164, 28);
		contentPanel.add(getFieldNombrePersonaje());
		this.getFieldNombrePersonaje().setColumns(33);

		this.setBtnAdd(new JButton(">>>"));
		this.getBtnAdd().setEnabled(false);
		this.getBtnAdd().setBounds(222, 164, 77, 28);
		this.getBtnAdd().setActionCommand("btnAdd");
		contentPanel.add(getBtnAdd());

		labelInterpretes.setFont(new Font("SansSerif", Font.BOLD, 12));
		labelInterpretes.setHorizontalAlignment(SwingConstants.CENTER);
		labelInterpretes.setBounds(7, 41, 147, 16);
		contentPanel.add(labelInterpretes);

		labelInterpretesAdd.setHorizontalAlignment(SwingConstants.CENTER);
		labelInterpretesAdd.setFont(new Font("SansSerif", Font.BOLD, 12));
		labelInterpretesAdd.setBounds(346, 41, 274, 16);
		contentPanel.add(labelInterpretesAdd);

		this.setPanelBtnsAceptarCancelar(new PanelBtnsAceptarCancelar());
		this.getPanelBtnsAceptarCancelar().getBtnAceptar().setEnabled(false);
		this.getContentPane().add(getPanelBtnsAceptarCancelar(), BorderLayout.SOUTH);
		this.getRootPane().setDefaultButton(getPanelBtnsAceptarCancelar().getBtnAceptar());
		
		
	}

	/**
	 * Método que carga en el JTable de intérpretes la lista recibida en el hashset
	 */
	public void cargarFilasInterpretes() {
		
		Object[] fila = new Object[NOMCOLUMNASINTERPRETE.length];
		
		this.getDtmModeloTablaInterprete().setRowCount(0);
		
		for (ListaInterprete l : this.getListaInterpretes()) {
			fila[0] = l.getCodigo();
			fila[1] = l.getNombre();
			this.getDtmModeloTablaInterprete().addRow(fila);
		}
		
	}

	/**
	 * Método que carga en el JTable de personajes la lista recibida en el hashset
	 */
	public void cargarFilasPersonajes() {
		
		Object[] fila = new Object[NOMCOLUMNASPERSONAJE.length];
		
		this.getDtmModeloTablaPersonaje().setRowCount(0);
		
		for (ListaPersonaje l : this.getListaPersonajes()) {
			fila[0] = l.getCodigo();
			fila[1] = l.getNombre();
			fila[2] = l.getNombrePersonaje();
			this.getDtmModeloTablaPersonaje().addRow(fila);
		}
		
	}
	
	public class TablaInterpreteListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			
			if (getTablaInterprete().getSelectedRow() >= 0) {
				
				getListaInterprete().setCodigo((Integer)getTablaInterprete().getValueAt(getTablaInterprete().getSelectedRow(), 0));
				getListaInterprete().setNombre(getTablaInterprete().getValueAt(getTablaInterprete().getSelectedRow(), 1).toString());
				
				getFieldInterpreteSelected().setText(getListaInterprete().getNombre());
				
			}
		}
		
	}


	// GETTERS & SETTERS
	public PanelBtnsAceptarCancelar getPanelBtnsAceptarCancelar() {
		return panelBtnsAceptarCancelar;
	}

	public void setPanelBtnsAceptarCancelar(PanelBtnsAceptarCancelar panelBtnsAceptarCancelar) {
		this.panelBtnsAceptarCancelar = panelBtnsAceptarCancelar;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JTextField getFieldCodigoPelicula() {
		return fieldCodigoPelicula;
	}

	public void setFieldCodigoPelicula(JTextField fieldCodigoPelicula) {
		this.fieldCodigoPelicula = fieldCodigoPelicula;
	}

	public JButton getBtnAdd() {
		return btnAdd;
	}

	public void setBtnAdd(JButton btnAdd) {
		this.btnAdd = btnAdd;
	}

	public JTextField getFieldTitulo() {
		return fieldTitulo;
	}

	public void setFieldTitulo(JTextField fieldTitulo) {
		this.fieldTitulo = fieldTitulo;
	}

	public JTextField getFieldInterpreteSelected() {
		return fieldInterpreteSelected;
	}

	public void setFieldInterpreteSelected(JTextField fieldInterpreteSelected) {
		this.fieldInterpreteSelected = fieldInterpreteSelected;
	}

	public DefaultTableModel getDtmModeloTablaInterprete() {
		return dtmModeloTablaInterprete;
	}

	public void setDtmModeloTablaInterprete(DefaultTableModel dtmModeloTablaInterprete) {
		this.dtmModeloTablaInterprete = dtmModeloTablaInterprete;
	}

	public DefaultTableModel getDtmModeloTablaPersonaje() {
		return dtmModeloTablaPersonaje;
	}

	public void setDtmModeloTablaPersonaje(DefaultTableModel dtmModeloTablaPersonaje) {
		this.dtmModeloTablaPersonaje = dtmModeloTablaPersonaje;
	}

	public JTable getTablaInterprete() {
		return tablaInterprete;
	}

	public void setTablaInterprete(JTable tablaInterprete) {
		this.tablaInterprete = tablaInterprete;
	}

	public JTable getTablaPersonaje() {
		return tablaPersonaje;
	}

	public void setTablaPersonaje(JTable tablaPersonaje) {
		this.tablaPersonaje = tablaPersonaje;
	}

	public JTextField getFieldNombrePersonaje() {
		return fieldNombrePersonaje;
	}

	public void setFieldNombrePersonaje(JTextField fieldNombrePersonaje) {
		this.fieldNombrePersonaje = fieldNombrePersonaje;
	}

	public ListaInterprete getListaInterprete() {
		return listaInterprete;
	}

	public void setListaInterprete(ListaInterprete listaInterprete) {
		this.listaInterprete = listaInterprete;
	}

	public HashSet<ListaInterprete> getListaInterpretes() {
		return listaInterpretes;
	}

	public void setListaInterpretes(HashSet<ListaInterprete> listaInterpretes) {
		this.listaInterpretes = listaInterpretes;
	}

	public HashSet<ListaPersonaje> getListaPersonajes() {
		return listaPersonajes;
	}

	public void setListaPersonajes(HashSet<ListaPersonaje> listaPersonajes) {
		this.listaPersonajes = listaPersonajes;
	}
}
