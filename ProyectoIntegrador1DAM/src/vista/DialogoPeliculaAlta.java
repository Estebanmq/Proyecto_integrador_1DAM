package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import modelo.Pelicula;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JSlider;
import javax.swing.JTextPane;

public class DialogoPeliculaAlta extends JDialog {
	
	private static final long serialVersionUID = 3894472721898604079L;

	private Pelicula pelicula;
	
	private JButton btnAceptar;
	private JButton btnCancelar;
	private PanelBtnsAceptarCancelar panelBtnsAceptarCancelar;

	private final JPanel contentPanel = new JPanel();
	private JTextField fieldTitulo;

	public DialogoPeliculaAlta() {

		setTitle("Alta de películas");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 450, 300);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel labelTitulo = new JLabel("Título:");
		labelTitulo.setBounds(6, 22, 55, 16);
		contentPanel.add(labelTitulo);
		
		setFieldTitulo(new JTextField());
		getFieldTitulo().setBounds(68, 16, 188, 28);
		contentPanel.add(getFieldTitulo());
		getFieldTitulo().setColumns(33);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(68, 56, 156, 26);
		contentPanel.add(comboBox);
		
		JLabel labelDirector = new JLabel("Director:");
		labelDirector.setBounds(6, 61, 55, 16);
		contentPanel.add(labelDirector);
		
		JLabel labelAnyo = new JLabel("Año:");
		labelAnyo.setBounds(12, 107, 44, 16);
		contentPanel.add(labelAnyo);
		
		JSlider sliderAnyo = new JSlider();
		sliderAnyo.setPaintTicks(true);
		sliderAnyo.setMajorTickSpacing(20);
		sliderAnyo.setPaintLabels(true);
		sliderAnyo.setValue(2020);
		sliderAnyo.setMinimum(1900);
		sliderAnyo.setMaximum(2020);
		sliderAnyo.setBounds(68, 94, 304, 45);
		contentPanel.add(sliderAnyo);
		
		JLabel labelValorAnyo = new JLabel();
		labelValorAnyo.setBounds(171, 140, 82, 16);
		contentPanel.add(labelValorAnyo);
		sliderAnyo.addChangeListener(new ChangeListener() {
		      public void stateChanged(ChangeEvent e) {
		    	  labelValorAnyo.setText("Año: "+sliderAnyo.getValue());
		      }
		});
		
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		setPanelBtnsAceptarCancelar(new PanelBtnsAceptarCancelar());
		getContentPane().add(getPanelBtnsAceptarCancelar(), BorderLayout.SOUTH);
		this.getRootPane().setDefaultButton(getPanelBtnsAceptarCancelar().getBtnAceptar());
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
}
