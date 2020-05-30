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
public class DialogoInterpreteConsulta extends JDialog{

	private static final long serialVersionUID = -6010688314909964444L;
	/**
	 * Atributos de la interfaz
	 */
	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private PanelBtnOk panelBtnOk;
	private JTextField textFieldCodigo;
	private JLabel LabelCampoNombre;
	private JLabel LabelCampoFecha;
	private JLabel LabelCampoSexo;
	private JLabel LabelCampoPais;
	private JButton btnBuscar;
	private JLabel labelCampoCache;
	
	/**
	 * Creacion del dialogo
	 */
	public DialogoInterpreteConsulta() {
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

		this.setTitle("Consulta de intérpretes");
		this.setBounds(100, 100, 537, 400);
		this.setLocationRelativeTo(null);
		getContentPanel().setLayout(new FlowLayout());
		getContentPanel().setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(null);
		
		panelBtnOk = new PanelBtnOk();

		JPanel buttonPane = new JPanel();
		buttonPane.setBounds(0, 0, 0, 0);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane);

		this.setPanel(new JPanel());
		this.getContentPane().add(getPanel());
		panel.setLayout(null);
		
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
		
		LabelCampoNombre = new JLabel("");
		LabelCampoNombre.setBounds(138, 100, 149, 14);
		getContentPane().add(LabelCampoNombre);
		
		JLabel LabelCodigo = new JLabel("Código del Director:");
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
		
		JLabel labelCache = new JLabel("Caché:");
		labelCache.setBounds(45, 244, 65, 14);
		getContentPane().add(labelCache);
		
		labelCampoCache = new JLabel("");
		labelCampoCache.setBounds(138, 244, 106, 14);
		getContentPane().add(labelCampoCache);
		
		textFieldCodigo.addKeyListener(new KeyAdapter() {
			 public void keyTyped(KeyEvent e) {
			      char caracter = e.getKeyChar();
			      // Verifico si la tecla pulsada no es un digito
			      if((caracter < '0') || (caracter > '9')) {
			         e.consume();  // No escribe el caracter
			      }
			   }
		});
	}
	
	/**
	 * Método para devolver la busqueda de la BBDD a la ventana
	 * @param interprete Interprete 
	 */
	public void mostrarInterprete(Interprete interprete) {
		
		DecimalFormat formateador = new DecimalFormat("###,###,###,###");
		
		LabelCampoNombre.setText(interprete.getNombre());
		LabelCampoPais.setText(interprete.getNacionalidad().getDescripcion());
		LabelCampoFecha.setText(interprete.getFechaNacimiento().toString());
		LabelCampoSexo.setText(interprete.getSexo().toString());
		labelCampoCache.setText(formateador.format(interprete.getCache()));

	}

	public JPanel getPanel() {
		return panel;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public PanelBtnOk getPanelBtnOk() {
		return panelBtnOk;
	}

	public void setPanelBtnOk(PanelBtnOk panelBtnOk) {
		this.panelBtnOk = panelBtnOk;
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

	public JPanel getContentPanel() {
		return contentPanel;
	}
}
