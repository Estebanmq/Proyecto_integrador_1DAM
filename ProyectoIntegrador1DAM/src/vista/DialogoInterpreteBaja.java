package vista;

import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import modelo.Interprete;

/**
 * Esta clase está dedicada al control de la interfaz gráfica de alta de películas y del acceso a la base de datos para añadir películas
 * @author Sergio Fernández Rivera
 * @since 15/05/2020
 * @version 1.0
 *
 */
public class DialogoInterpreteBaja extends JDialog{

	private static final long serialVersionUID = 2089480937214925088L;
	
	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private PanelBtnsAceptarCancelar panelBtnsAceptarCancelar;
	private JTextField textFieldCodigo;
	private JLabel LabelCampoNombre;
	private JLabel LabelCampoFecha;
	private JLabel LabelCampoSexo;
	private JLabel LabelCampoPais;
	private JButton btnBuscar;
	private JLabel LabelCampoCache;
	
	/**
	 * Creacion del dialogo
	 */
	public DialogoInterpreteBaja() {
		
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		this.setTitle("Baja de Interpretes");
		this.setBounds(100, 100, 537, 400);
		this.setLocationRelativeTo(null);
		getContentPanel().setLayout(new FlowLayout());
		getContentPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(null);

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 0, 0, 0);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane);

		panelBtnsAceptarCancelar = (new PanelBtnsAceptarCancelar());
		panelBtnsAceptarCancelar.setBounds(0, 328, 521, 33);
		getContentPane().add(panelBtnsAceptarCancelar);
		this.getRootPane().setDefaultButton(panelBtnsAceptarCancelar.getBtnAceptar());
		
		textFieldCodigo = new JTextField();
		textFieldCodigo.setBounds(228, 34, 86, 33);
		getContentPane().add(textFieldCodigo);
		textFieldCodigo.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(324, 33, 89, 23);
		getContentPane().add(btnBuscar);
		
		btnBuscar.setActionCommand("btnBuscar");
		
		JLabel LabelNombre = new JLabel("Nombre:");
		LabelNombre.setBounds(45, 100, 86, 14);
		getContentPane().add(LabelNombre);
		
		JLabel LabelFecha = new JLabel("Fecha Nacimiento:");
		LabelFecha.setBounds(45, 135, 142, 14);
		getContentPane().add(LabelFecha);
		
		JLabel LabelSexo = new JLabel("Sexo:");
		LabelSexo.setBounds(45, 166, 86, 14);
		getContentPane().add(LabelSexo);
		
		JLabel LabelPais = new JLabel("País Nacimiento:");
		LabelPais.setBounds(45, 201, 142, 14);
		getContentPane().add(LabelPais);
		
		JLabel LabelCache = new JLabel("Caché:");
		LabelCache.setBounds(45, 242, 156, 14);
		getContentPane().add(LabelCache);
		
		LabelCampoNombre = new JLabel("");
		LabelCampoNombre.setBounds(138, 100, 149, 14);
		getContentPane().add(LabelCampoNombre);
		
		JLabel LabelCodigo = new JLabel("Código del Interprete:");
		LabelCodigo.setBounds(80, 37, 138, 14);
		getContentPane().add(LabelCodigo);
		
		LabelCampoFecha = new JLabel("");
		LabelCampoFecha.setBounds(255, 135, 199, 14);
		getContentPane().add(LabelCampoFecha);
		
		LabelCampoSexo = new JLabel("");
		LabelCampoSexo.setBounds(151, 166, 122, 14);
		getContentPane().add(LabelCampoSexo);
		
		LabelCampoPais = new JLabel("");
		LabelCampoPais.setBounds(201, 201, 149, 14);
		getContentPane().add(LabelCampoPais);
		
		LabelCampoCache = new JLabel("");
		LabelCampoCache.setBounds(241, 242, 171, 14);
		getContentPane().add(LabelCampoCache);
		
		textFieldCodigo.addKeyListener(new KeyAdapter() {
			 public void keyTyped(KeyEvent e) {
			      char caracter = e.getKeyChar();
			      // Verifico si la tecla pulsada no es un digito
			      if(((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
			         e.consume();  // No escribe el caracter
			      }
			   }
		});
	}
	
	/**
	 * Método que devuelve los atributos de la base de datos y la introduce en pantalla
	 * @param interprete Intérprete
	 */
	public void mostrarInterprete(Interprete interprete) {
		
		DecimalFormat formateador = new DecimalFormat("###,###,###,###");

		getPanelBtnsAceptarCancelar().getLabelTextoError().setText("");
		LabelCampoNombre.setText(interprete.getNombre());
		LabelCampoPais.setText(interprete.getNacionalidad().getDescripcion());
		LabelCampoFecha.setText(interprete.getFechaNacimiento().toString());
		LabelCampoSexo.setText(interprete.getSexo().toString());
		LabelCampoCache.setText(formateador.format(interprete.getCache()));

	}
	
	//Getters and setters

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
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

	public JTextField getTextFieldCodigo() {
		return textFieldCodigo;
	}

	public void setTextFieldCodigo(JTextField textFieldCodigo) {
		this.textFieldCodigo = textFieldCodigo;
	}

	public JLabel getLabelCampoNombre() {
		return LabelCampoNombre;
	}

	public void setLabelCampoNombre(JLabel labelCampoNombre) {
		LabelCampoNombre = labelCampoNombre;
	}

	public JLabel getLabelCampoFecha() {
		return LabelCampoFecha;
	}

	public void setLabelCampoFecha(JLabel labelCampoFecha) {
		LabelCampoFecha = labelCampoFecha;
	}

	public JLabel getLabelCampoSexo() {
		return LabelCampoSexo;
	}

	public void setLabelCampoSexo(JLabel labelCampoSexo) {
		LabelCampoSexo = labelCampoSexo;
	}

	public JLabel getLabelCampoPais() {
		return LabelCampoPais;
	}

	public void setLabelCampoPais(JLabel labelCampoPais) {
		LabelCampoPais = labelCampoPais;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public JLabel getLabelCampoCache() {
		return LabelCampoCache;
	}

	public void setLabelCampoCache(JLabel labelCampoCache) {
		LabelCampoCache = labelCampoCache;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}
	
	
}
