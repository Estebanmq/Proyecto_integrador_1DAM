package vista;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.GeneroPelicula;
import modelo.Pais;
import modelo.Pelicula;
import javax.swing.JButton;
import javax.swing.JComboBox;
/**
 * Esta clase genera la pantalla de visualización de datos para la transacción de modificacion de películas
 *
 * @author Esteban Martínez
 * @since 23/05/2020
 * @version 1.0
 */
public class DialogoPeliculaModificacion extends JDialog {
	private static final long serialVersionUID = 1L;

	private Pelicula pelicula;
	
	private JButton btnAceptar;
	private JButton btnCancelar;
	private PanelBtnsAceptarCancelar panelBtnsAceptarCancelar;
	
	private JButton btnBuscar;
	private JTextField textFieldBuscarCodigo;
	private JPanel panelResultado;
	private JTextField textFieldTitResul;
	private JTextField textFieldAnyoResul;
	private JComboBox<String> comboBoxPais;
	private JComboBox comboBoxGenero;
	private JComboBox comboBoxDirector;
	private JTextArea textAreaSinopsisResul;
	
	public DialogoPeliculaModificacion() {
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
		comboBoxPais = new JComboBox();
		JLabel labelAnyo = new JLabel("Año: ");
		textFieldAnyoResul = new JTextField();
		JLabel labelDirector = new JLabel("Director: ");
		comboBoxDirector = new JComboBox();
		JLabel labelGenero = new JLabel("Género: ");
		comboBoxGenero = new JComboBox();
		JLabel labelSinopsis = new JLabel("Sinopsis: ");
		textAreaSinopsisResul = new JTextArea();
		
		panelBtnsAceptarCancelar = new PanelBtnsAceptarCancelar();
		
		
		setTitle("Modificacion de peliculas");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 541, 381);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		panelBuscar.setBounds(0, 6, 541, 50);
		getContentPane().add(panelBuscar);
		panelBuscar.setLayout(null);
		
		labelCodigo.setBounds(79, 10, 146, 16);
		panelBuscar.add(labelCodigo);
		
		textFieldBuscarCodigo.setBounds(224, 6, 108, 24);
		panelBuscar.add(textFieldBuscarCodigo);
		textFieldBuscarCodigo.setColumns(10);
		
		btnBuscar.setBounds(344, 5, 117, 29);
		panelBuscar.add(btnBuscar);
		
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
	
		textFieldTitResul.setBounds(71, 1, 187, 26);
		panelResultado.add(textFieldTitResul);
		textFieldTitResul.setColumns(10);
		textFieldTitResul.setEditable(false);
		
		labelPais.setBounds(270, 6, 61, 16);
		panelResultado.add(labelPais);
		
		comboBoxPais.setBounds(338, 2, 190, 27);
		panelResultado.add(comboBoxPais);
		
		labelAnyo.setBounds(6, 34, 61, 16);
		panelResultado.add(labelAnyo);
		
		textFieldAnyoResul.setBounds(71, 29, 187, 26);
		panelResultado.add(textFieldAnyoResul);
		textFieldAnyoResul.setColumns(10);
		
		labelDirector.setBounds(270, 34, 61, 16);
		panelResultado.add(labelDirector);
		
		comboBoxDirector.setBounds(338, 29, 177, 26);
		panelResultado.add(comboBoxDirector);
		
		labelGenero.setBounds(6, 62, 61, 16);
		panelResultado.add(labelGenero);
		
		comboBoxGenero.setBounds(71, 57, 187, 26);
		panelResultado.add(comboBoxGenero);
		
		labelSinopsis.setBounds(6, 90, 61, 16);
		panelResultado.add(labelSinopsis);
		
		textAreaSinopsisResul.setLineWrap(true);
		textAreaSinopsisResul.setRows(1);
		textAreaSinopsisResul.setBounds(71, 90, 218, 109);
		panelResultado.add(textAreaSinopsisResul);
		textAreaSinopsisResul.addKeyListener(new KeyAdapter() {
			 public void keyTyped(KeyEvent e) {
			      if(textAreaSinopsisResul.getText().length()==150) {
			         e.consume();  // No escribe el caracter
			         
			      } 
			   }
		});
		
		panelBtnsAceptarCancelar.setBounds(0, 320, 541, 39);
		getContentPane().add(panelBtnsAceptarCancelar);
		
		getBtnBuscar().setActionCommand("btnBuscar");
	
		getPanelResultado().setVisible(false);
		
		mostrarGeneros();
		
	}

	public void mostrarGeneros() {
		GeneroPelicula[]gp=GeneroPelicula.values();
		for (int i=0;i<gp.length;i++) {
			comboBoxGenero.addItem(gp[i].getDescripcion());
		}
	}
	
	public void mostrarPaises(ArrayList<Pais>paises) {
		for (int i = 0; i < paises.size(); i++) {
			comboBoxPais.addItem(paises.get(i).getDescripcion());
		}
	}
	
	public void mostrarDirectores(ArrayList<String>directores) {
		for (int i = 0; i < directores.size(); i++) {
			comboBoxDirector.addItem(directores.get(i));
		}
	}
	

	public void mostrarPelicula(Pelicula p) {
		textFieldTitResul.setText(p.getTitulo());
		textFieldTitResul.setEditable(true);
		comboBoxPais.setSelectedItem(p.getNacionalidad().getDescripcion());
		textFieldAnyoResul.setText(Integer.toString(p.getAnyo()));
		comboBoxDirector.setSelectedItem(p.getDirector().getNombre());
		comboBoxGenero.setSelectedItem(p.getGenero().getDescripcion());
		textAreaSinopsisResul.setText(p.getSinopsis());
		textAreaSinopsisResul.setEditable(true);
	}
	
	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(JButton btnBuscar) {
		this.btnBuscar = btnBuscar;
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
