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
	
	private JLabel labelTitResul;
	private JLabel labelPaisResul;
	private JLabel labelAnyoResul;
	private JLabel labelDirectorResul;
	private JLabel labelGeneroResul;
	private JLabel labelSinopsisResul;
	
	public DialogoPeliculaConsulta() {
		//Panel para buscar por codigo
		JPanel panelBuscar = new JPanel();
		JLabel labelCodigo = new JLabel("Código de la película: ");
		textFieldBuscarCodigo = new JTextField();

		//Panel resultado
		panelResultado = new JPanel();
		JLabel labelTitulo = new JLabel("Título: ");
		labelTitResul = new JLabel("");
		JLabel labelPais = new JLabel("País: ");
		labelPaisResul = new JLabel("");
		JLabel labelAnyo = new JLabel("Año: ");
		labelAnyoResul = new JLabel("");
		JLabel labelDirector = new JLabel("Director: ");
		labelDirectorResul = new JLabel("");
		JLabel labelGenero = new JLabel("Género: ");
		labelGeneroResul = new JLabel("");
		JLabel labelSinopsis = new JLabel("Sinopsis: ");
		labelSinopsisResul = new JLabel("");
		
		panelBtnOk = new PanelBtnOk();
		
		setTitle("Baja de películas");
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
		
		labelPais.setBounds(270, 6, 61, 16);
		panelResultado.add(labelPais);
		
		labelAnyo.setBounds(6, 34, 61, 16);
		panelResultado.add(labelAnyo);
		
		labelDirector.setBounds(270, 34, 61, 16);
		panelResultado.add(labelDirector);
		
		labelGenero.setBounds(6, 62, 61, 16);
		panelResultado.add(labelGenero);
		
		labelSinopsis.setBounds(6, 90, 61, 16);
		panelResultado.add(labelSinopsis);
		
		labelTitResul.setBounds(79, 6, 179, 16);
		panelResultado.add(labelTitResul);
		
		labelPaisResul.setBounds(328, 6, 179, 16);
		panelResultado.add(labelPaisResul);
		
		labelAnyoResul.setBounds(79, 34, 179, 16);
		panelResultado.add(labelAnyoResul);
		
		labelDirectorResul.setBounds(328, 34, 179, 16);
		panelResultado.add(labelDirectorResul);
		
		
		labelGeneroResul.setBounds(79, 62, 179, 16);
		panelResultado.add(labelGeneroResul);
		
		
		labelSinopsisResul.setBounds(71, 90, 296, 113);
		panelResultado.add(labelSinopsisResul);
		
		panelBtnOk.setBounds(0, 320, 541, 39);
		getContentPane().add(panelBtnOk);
		
		getPanelResultado().setVisible(false);
			
	}

	public void mostrarPelicula(Pelicula p) {
		labelTitResul.setText(p.getTitulo());
		labelPaisResul.setText(p.getNacionalidad().getDescripcion());
		labelAnyoResul.setText(Integer.toString(p.getAnyo()));
		labelDirectorResul.setText(p.getDirector().getNombre());
		labelGeneroResul.setText(p.getGenero().getDescripcion());
		labelSinopsisResul.setText(p.getSinopsis());
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
