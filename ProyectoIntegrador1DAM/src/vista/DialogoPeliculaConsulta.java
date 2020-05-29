package vista;

import java.awt.BorderLayout;
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
 * Esta clase genera la pantalla de visualización de datos para la transacción de s de películas
 *
 * @author Esteban Martínez
 * @since 16/05/2020
 * @version 1.0
 */
public class DialogoPeliculaConsulta extends JDialog {
	private static final long serialVersionUID = 1L;

	private Pelicula pelicula;
	
	private PanelBtnOk panelBtnOk;

	private JTextField textFieldBuscarCodigo;
	private JPanel panelResultado;
	private JTextArea textAreaSinopsisResul;
	private JTextField textFieldTitResul;
	private JTextField textFieldAnyoResul;
	private JTextField textFieldGenResul;
	private JTextField textFieldPaisResul;
	private JTextField textFieldDirecResul;
	
	public DialogoPeliculaConsulta() {
		//Panel para buscar por codigo
		JPanel panelBuscar = new JPanel();
		JLabel labelCodigo = new JLabel("Código de la película: ");
		textFieldBuscarCodigo = new JTextField();

		//Panel resultado
		panelResultado = new JPanel();
		JLabel labelTitulo = new JLabel("Título: ");
		textFieldTitResul = new JTextField();
		JLabel labelPais = new JLabel("País: ");
		textFieldPaisResul = new JTextField();
		JLabel labelAnyo = new JLabel("Año: ");
		textFieldAnyoResul = new JTextField();
		JLabel labelDirector = new JLabel("Director: ");
		textFieldDirecResul = new JTextField();
		JLabel labelGenero = new JLabel("Género: ");
		textFieldGenResul = new JTextField();
		JLabel labelSinopsis = new JLabel("Sinopsis: ");
		textAreaSinopsisResul = new JTextArea();
		
		panelBtnOk = new PanelBtnOk();
		
		setTitle("Consulta de películas");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 541, 381);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		panelBuscar.setBounds(0, 6, 541, 50);
		getContentPane().add(panelBuscar);
		panelBuscar.setLayout(null);
		
		
		labelCodigo.setBounds(126, 10, 146, 16);
		panelBuscar.add(labelCodigo);
		
		
		textFieldBuscarCodigo.setBounds(269, 6, 108, 24);
		panelBuscar.add(textFieldBuscarCodigo);
		textFieldBuscarCodigo.setColumns(10);
		
		textFieldBuscarCodigo.addKeyListener(new KeyAdapter() {
			 public void keyTyped(KeyEvent e) {
			      char caracter = e.getKeyChar();
			      // Verifico si la tecla pulsada no es un digito
			      if(((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
			         e.consume();  // No escribe el caracter
			      }
			   }
		});
		
		panelResultado.setBounds(0, 53, 541, 265);
		getContentPane().add(panelResultado);
		panelResultado.setLayout(null);
		
		
		labelTitulo.setBounds(6, 6, 61, 16);
		panelResultado.add(labelTitulo);
		
		textFieldTitResul.setBounds(79, 1, 179, 26);
		panelResultado.add(textFieldTitResul);
		textFieldTitResul.setColumns(10);
		textFieldTitResul.setEditable(false);
		
		labelAnyo.setBounds(6, 34, 61, 16);
		panelResultado.add(labelAnyo);
		
		textFieldAnyoResul.setBounds(79, 29, 179, 26);
		panelResultado.add(textFieldAnyoResul);
		textFieldAnyoResul.setColumns(10);
		textFieldAnyoResul.setEditable(false);
		
		labelPais.setBounds(270, 6, 61, 16);
		panelResultado.add(labelPais);

		textFieldPaisResul.setBounds(338, 1, 179, 26);
		panelResultado.add(textFieldPaisResul);
		textFieldPaisResul.setColumns(10);
		textFieldPaisResul.setEditable(false);

		labelGenero.setBounds(6, 62, 61, 16);
		panelResultado.add(labelGenero);
		
		textFieldGenResul.setBounds(79, 57, 179, 26);
		panelResultado.add(textFieldGenResul);
		textFieldGenResul.setColumns(10);
		textFieldGenResul.setEditable(false);
		
		labelDirector.setBounds(270, 34, 61, 16);
		panelResultado.add(labelDirector);
		
		textFieldDirecResul.setBounds(338, 29, 179, 26);
		panelResultado.add(textFieldDirecResul);
		textFieldDirecResul.setColumns(10);
		textFieldDirecResul.setEditable(false);
		
		labelSinopsis.setBounds(6, 90, 61, 16);
		panelResultado.add(labelSinopsis);
		
		textAreaSinopsisResul.setEditable(false);
		textAreaSinopsisResul.setLineWrap(true);
		textAreaSinopsisResul.setBounds(79, 90, 438, 148);
		panelResultado.add(textAreaSinopsisResul);
		
		panelBtnOk.setBounds(0, 320, 541, 39);
		getContentPane().add(panelBtnOk);
		
		getPanelResultado().setVisible(false);
			
	}

	public void mostrarPelicula(Pelicula p) {
		textFieldTitResul.setText(p.getTitulo());
		textFieldPaisResul.setText(p.getNacionalidad().getDescripcion());
		textFieldAnyoResul.setText(Integer.toString(p.getAnyo()));
		textFieldDirecResul.setText(p.getDirector().getNombre());
		textFieldGenResul.setText(p.getGenero().getDescripcion());
		textAreaSinopsisResul.setText(p.getSinopsis());
	}
	
	
	
	public PanelBtnOk getPanelBtnOk() {
		return panelBtnOk;
	}

	public void setPanelBtnOk(PanelBtnOk panelBtnOk) {
		this.panelBtnOk = panelBtnOk;
	}

	public JTextField getTextFieldBuscarCodigo() {
		return textFieldBuscarCodigo;
	}

	public void setTextFieldBuscarCodigo(JTextField textFieldBuscarCodigo) {
		this.textFieldBuscarCodigo = textFieldBuscarCodigo;
	}

	public JPanel getPanelResultado() {
		return panelResultado;
	}

	public void setPanelResultado(JPanel panelResultado) {
		this.panelResultado = panelResultado;
	}
}
