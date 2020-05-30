package vista;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.Documental;


/**
 * Esta clase genera la pantalla de visualización de datos para la transacción de baja de Documentales
 *
 * @author Esteban Martínez
 * @since 24/05/2020
 * @version 1.0
 */
public class DialogoDocumentalBaja extends JDialog {
	
	private static final long serialVersionUID = -8011581073722444154L;
	
	private JButton btnCancelar;
	private PanelBtnsAceptarCancelar panelBtnsAceptarCancelar;

	private JTextField textFieldBuscarCodigo;
	private JButton btnBuscar;
	private JPanel panelResultado;
	private JTextArea textAreaSinopsisResul;
	private JTextField textFieldTitResul;
	private JTextField textFieldPaisResul;
	private JTextField textFieldAnyoResul;
	private JTextField textFieldDirectorResul;
	private JTextField textFieldGeneroResul;
	
	public DialogoDocumentalBaja() {
		//Panel para buscar por codigo
		JPanel panelBuscar = new JPanel();
		JLabel labelCodigo = new JLabel("Código del documental: ");
		textFieldBuscarCodigo = new JTextField();
		btnBuscar = new JButton("Buscar");

		//Panel resultado
		panelResultado = new JPanel();
		JLabel labelTitulo = new JLabel("Título: ");
		textFieldTitResul = new JTextField();
		JLabel labelPais = new JLabel("País: ");
		textFieldPaisResul = new JTextField();
		JLabel labelAnyo = new JLabel("Año: ");
		textFieldAnyoResul = new JTextField();
		JLabel labelDirector = new JLabel("Director: ");
		textFieldDirectorResul = new JTextField();
		JLabel labelGenero = new JLabel("Género: ");
		textFieldGeneroResul = new JTextField();
		JLabel labelSinopsis = new JLabel("Sinopsis: ");
		textAreaSinopsisResul = new JTextArea();
		
		panelBtnsAceptarCancelar = new PanelBtnsAceptarCancelar();
		
		setTitle("Baja de Documentales");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 541, 381);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		btnBuscar.setActionCommand("btnBuscar");
		
		panelBuscar.setBounds(0, 6, 541, 50);
		getContentPane().add(panelBuscar);
		panelBuscar.setLayout(null);
		
		
		labelCodigo.setBounds(68, 20, 146, 16);
		panelBuscar.add(labelCodigo);
		
		
		textFieldBuscarCodigo.setBounds(214, 16, 108, 24);
		panelBuscar.add(textFieldBuscarCodigo);
		textFieldBuscarCodigo.setColumns(10);
		
		textFieldBuscarCodigo.addKeyListener(new KeyAdapter() {
			 public void keyTyped(KeyEvent e) {
			      char caracter = e.getKeyChar();
			      // Verifico si la tecla pulsada no es un digito
			      if((caracter < '0') || (caracter > '9')) {
			         e.consume();  // No escribe el caracter
			      }
			   }
		});
		
		btnBuscar.setBounds(320, 15, 85, 29);
		panelBuscar.add(btnBuscar);
		
		panelResultado.setBounds(0, 53, 541, 265);
		getContentPane().add(panelResultado);
		panelResultado.setLayout(null);
		
		
		labelTitulo.setBounds(35, 11, 61, 16);
		panelResultado.add(labelTitulo);
		
		textFieldTitResul.setBounds(108, 6, 355, 26);
		panelResultado.add(textFieldTitResul);
		textFieldTitResul.setColumns(10);
		textFieldTitResul.setEditable(false);

		labelPais.setBounds(35, 39, 61, 16);
		panelResultado.add(labelPais);
		
		textFieldPaisResul.setBounds(108, 34, 355, 26);
		panelResultado.add(textFieldPaisResul);
		textFieldPaisResul.setColumns(10);
		textFieldPaisResul.setEditable(false);
		
		labelAnyo.setBounds(35, 67, 61, 16);
		panelResultado.add(labelAnyo);
		
		textFieldAnyoResul.setBounds(108, 62, 355, 26);
		panelResultado.add(textFieldAnyoResul);
		textFieldAnyoResul.setColumns(10);
		textFieldAnyoResul.setEditable(false);
		
		labelDirector.setBounds(35, 95, 61, 16);
		panelResultado.add(labelDirector);
		
		textFieldDirectorResul.setBounds(108, 90, 355, 26);
		panelResultado.add(textFieldDirectorResul);
		textFieldDirectorResul.setColumns(10);
		textFieldDirectorResul.setEditable(false);
		
		labelGenero.setBounds(35, 123, 61, 16);
		panelResultado.add(labelGenero);
		
		textFieldGeneroResul.setBounds(108, 118, 355, 26);
		panelResultado.add(textFieldGeneroResul);
		textFieldGeneroResul.setColumns(10);
		textFieldGeneroResul.setEditable(false);
	
		labelSinopsis.setBounds(35, 151, 61, 16);
		panelResultado.add(labelSinopsis);
		
		textAreaSinopsisResul.setEditable(false);
		textAreaSinopsisResul.setLineWrap(true);
		textAreaSinopsisResul.setBounds(108, 151, 351, 108);
		panelResultado.add(textAreaSinopsisResul);
		
		panelBtnsAceptarCancelar.setBounds(0, 320, 541, 39);
		getContentPane().add(panelBtnsAceptarCancelar);
		
		getPanelResultado().setVisible(false);
	}

	public void mostrarDocumental(Documental d) {
		if (d.getTitulo().equals("Documental no existe")) {
			getPanelBtnsAceptarCancelar().getLabelTextoError().setText("El documental introducido no existe");
			getPanelResultado().setVisible(false);
		} else {
			getPanelBtnsAceptarCancelar().getLabelTextoError().setText("");
			getPanelResultado().setVisible(true);
			textFieldTitResul.setText(d.getTitulo());
			textFieldPaisResul.setText(d.getNacionalidad().getDescripcion());
			textFieldAnyoResul.setText(Integer.toString(d.getAnyo()));
			textFieldDirectorResul.setText(d.getDirector().getNombre());
			textFieldGeneroResul.setText(d.getGenero().getDescripcion());
			textAreaSinopsisResul.setText(d.getSinopsis());
		}
	}
	
	public JTextField getTextFieldBuscarCodigo() {
		return textFieldBuscarCodigo;
	}

	public void setTextFieldBuscarCodigo(JTextField textFieldBuscarCodigo) {
		this.textFieldBuscarCodigo = textFieldBuscarCodigo;
	}

	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}
	public JPanel getPanelResultado() {
		return panelResultado;
	}

	public void setPanelResultado(JPanel panelResultado) {
		this.panelResultado = panelResultado;
	}


	public void setBtnAceptar(JButton btnAceptar) {
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
}
