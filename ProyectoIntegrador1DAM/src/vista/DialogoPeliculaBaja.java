package vista;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Pelicula;
import javax.swing.JTextArea;

/**
 * Esta clase genera la pantalla de visualización de datos para la transacción de baja de películas
 *
 * @author Esteban Martínez
 * @since 16/05/2020
 * @version 1.0
 */
public class DialogoPeliculaBaja extends JDialog {
	private static final long serialVersionUID = 1L;

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
	
	public DialogoPeliculaBaja() {
		//Panel para buscar por codigo
		JPanel panelBuscar = new JPanel();
		JLabel labelCodigo = new JLabel("Código de la película: ");
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
		
		setTitle("Baja de películas");
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
		
		
		labelTitulo.setBounds(37, 11, 61, 16);
		panelResultado.add(labelTitulo);
		
		textFieldTitResul.setBounds(110, 6, 296, 26);
		panelResultado.add(textFieldTitResul);
		textFieldTitResul.setColumns(10);
		textFieldTitResul.setEditable(false);
		
		labelPais.setBounds(37, 39, 61, 16);
		panelResultado.add(labelPais);
				
		textFieldPaisResul.setBounds(110, 34, 296, 26);
		panelResultado.add(textFieldPaisResul);
		textFieldPaisResul.setColumns(10);
		textFieldPaisResul.setEditable(false);
		
		labelAnyo.setBounds(37, 67, 61, 16);
		panelResultado.add(labelAnyo);
		
		textFieldAnyoResul.setBounds(110, 62, 296, 26);
		panelResultado.add(textFieldAnyoResul);
		textFieldAnyoResul.setColumns(10);
		textFieldAnyoResul.setEditable(false);
		
		labelDirector.setBounds(37, 95, 61, 16);
		panelResultado.add(labelDirector);
		
		textFieldDirectorResul.setBounds(110, 90, 296, 26);
		panelResultado.add(textFieldDirectorResul);
		textFieldDirectorResul.setColumns(10);
		textFieldDirectorResul.setEditable(false);
		
		labelGenero.setBounds(37, 123, 61, 16);
		panelResultado.add(labelGenero);
		textFieldGeneroResul.setBounds(110, 118, 296, 26);
		panelResultado.add(textFieldGeneroResul);
		textFieldGeneroResul.setColumns(10);
		textFieldGeneroResul.setEditable(false);
		
		labelSinopsis.setBounds(37, 151, 61, 16);
		panelResultado.add(labelSinopsis);
		
		textAreaSinopsisResul.setEditable(false);
		textAreaSinopsisResul.setLineWrap(true);
		textAreaSinopsisResul.setBounds(110, 151, 400, 113);
		panelResultado.add(textAreaSinopsisResul);
		
		panelBtnsAceptarCancelar.setBounds(0, 320, 541, 39);
		getContentPane().add(panelBtnsAceptarCancelar);
		
		getPanelResultado().setVisible(false);
	}

	public void mostrarPelicula(Pelicula p) {
		if (p.getTitulo().equals("Pelicula no existe")) {
			getPanelBtnsAceptarCancelar().getLabelTextoError().setText("La película introducida no existe");
			getPanelResultado().setVisible(false);
		} else {
			getPanelBtnsAceptarCancelar().getLabelTextoError().setText("");
			getPanelResultado().setVisible(true);
			textFieldTitResul.setText(p.getTitulo());
			textFieldPaisResul.setText(p.getNacionalidad().getDescripcion());
			textFieldAnyoResul.setText(Integer.toString(p.getAnyo()));
			textFieldDirectorResul.setText(p.getDirector().getNombre());
			textFieldGeneroResul.setText(p.getGenero().getDescripcion());
			textAreaSinopsisResul.setText(p.getSinopsis());
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
