package vista;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import modelo.GeneroDocumental;
import modelo.Pais;
import modelo.Documental;
import javax.swing.JButton;
import javax.swing.JComboBox;
/**
 * Esta clase genera la pantalla de visualización de datos para la transacción de modificacion de documentales
 *
 * @author Esteban Martínez
 * @since 23/05/2020
 * @version 1.0
 */
public class DialogoDocumentalModificacion extends JDialog {

	 
	private static final long serialVersionUID = -1501089186399986888L;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private PanelBtnsAceptarCancelar panelBtnsAceptarCancelar;
	
	private JButton btnBuscar;
	private JTextField textFieldBuscarCodigo;
	private JPanel panelResultado;
	private JTextField textFieldTitResul;
	private JTextField textFieldAnyoResul;
	private JComboBox<String> comboBoxPais;
	private JComboBox<String> comboBoxGenero;
	private JComboBox<String> comboBoxDirector;
	private JTextArea textAreaSinopsisResul;
	
	public DialogoDocumentalModificacion() {
		//Panel para buscar por codigo
		JPanel panelBuscar = new JPanel();
		JLabel labelCodigo = new JLabel("Código del documental: ");
		textFieldBuscarCodigo = new JTextField();
		btnBuscar = new JButton("Buscar");
		
		//Panel resultado
		panelResultado = new JPanel();
		JLabel labelTitulo = new JLabel("Título*: ");
		textFieldTitResul = new JTextField();
		JLabel labelPais = new JLabel("País*: ");
		comboBoxPais = new JComboBox<String>();
		JLabel labelAnyo = new JLabel("Año*: ");
		textFieldAnyoResul = new JTextField();
		JLabel labelDirector = new JLabel("Director*: ");
		comboBoxDirector = new JComboBox<String>();
		JLabel labelGenero = new JLabel("Género*: ");
		comboBoxGenero = new JComboBox<String>();
		JLabel labelSinopsis = new JLabel("Sinopsis: ");
		textAreaSinopsisResul = new JTextArea();
		
		panelBtnsAceptarCancelar = new PanelBtnsAceptarCancelar();
		
		
		setTitle("Modificacion de documentales");
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
			      if((caracter < '0') || (caracter > '9')) {
			         e.consume();  // No escribe el caracter
			      }
			   }
		});
		
		panelResultado.setBounds(0, 53, 541, 265);
		getContentPane().add(panelResultado);
		panelResultado.setLayout(null);
		
		
		labelTitulo.setBounds(6, 6, 61, 16);
		panelResultado.add(labelTitulo);
	
		textFieldTitResul.setBounds(71, 1, 181, 26);
		panelResultado.add(textFieldTitResul);
		textFieldTitResul.setColumns(10);
		textFieldTitResul.setEditable(false);
		
		labelPais.setBounds(264, 6, 61, 16);
		panelResultado.add(labelPais);
		
		comboBoxPais.setBounds(338, 2, 190, 27);
		panelResultado.add(comboBoxPais);
		
		labelAnyo.setBounds(6, 34, 61, 16);
		panelResultado.add(labelAnyo);
		
		textFieldAnyoResul.setBounds(71, 29, 181, 26);
		panelResultado.add(textFieldAnyoResul);
		textFieldAnyoResul.setColumns(10);
		textFieldAnyoResul.addKeyListener(new KeyAdapter() {
			 public void keyTyped(KeyEvent e) {
			      char caracter = e.getKeyChar();
			      // Verifico si la tecla pulsada no es un digito
			      if(((caracter < '0') || (caracter > '9')) || textFieldAnyoResul.getText().length()>3 && (caracter != '\b')) {
			         e.consume();  // No escribe el caracter
			      }
			   }
		});
		
		labelDirector.setBounds(264, 34, 76, 16);
		panelResultado.add(labelDirector);
		
		comboBoxDirector.setBounds(338, 29, 190, 26);
		panelResultado.add(comboBoxDirector);
		
		labelGenero.setBounds(6, 62, 61, 16);
		panelResultado.add(labelGenero);
		
		comboBoxGenero.setBounds(71, 57, 181, 26);
		panelResultado.add(comboBoxGenero);
		
		labelSinopsis.setBounds(6, 90, 61, 16);
		panelResultado.add(labelSinopsis);
		
		textAreaSinopsisResul.setLineWrap(true);
		textAreaSinopsisResul.setRows(1);
		textAreaSinopsisResul.setBounds(71, 90, 454, 169);
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
		GeneroDocumental[]gp=GeneroDocumental.values();
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
	

	public void mostrardocumental(Documental d) {
		textFieldTitResul.setText(d.getTitulo());
		textFieldTitResul.setEditable(true);
		comboBoxPais.setSelectedItem(d.getNacionalidad().getDescripcion());
		textFieldAnyoResul.setText(Integer.toString(d.getAnyo()));
		comboBoxDirector.setSelectedItem(d.getDirector().getNombre());
		comboBoxGenero.setSelectedItem(d.getGenero().getDescripcion());
		textAreaSinopsisResul.setText(d.getSinopsis());
		textAreaSinopsisResul.setEditable(true);
	}
	
	//GETTER Y SETTER
	
	
	
	public JButton getBtnBuscar() {
		return btnBuscar;
	}

	public JTextField getTextFieldTitResul() {
		return textFieldTitResul;
	}

	public void setTextFieldTitResul(JTextField textFieldTitResul) {
		this.textFieldTitResul = textFieldTitResul;
	}

	public JTextField getTextFieldAnyoResul() {
		return textFieldAnyoResul;
	}

	public void setTextFieldAnyoResul(JTextField textFieldAnyoResul) {
		this.textFieldAnyoResul = textFieldAnyoResul;
	}

	public JComboBox<String> getComboBoxPais() {
		return comboBoxPais;
	}

	public void setComboBoxPais(JComboBox<String> comboBoxPais) {
		this.comboBoxPais = comboBoxPais;
	}

	public JComboBox<String> getComboBoxGenero() {
		return comboBoxGenero;
	}

	public void setComboBoxGenero(JComboBox<String> comboBoxGenero) {
		this.comboBoxGenero = comboBoxGenero;
	}

	public JComboBox<String> getComboBoxDirector() {
		return comboBoxDirector;
	}

	public void setComboBoxDirector(JComboBox<String> comboBoxDirector) {
		this.comboBoxDirector = comboBoxDirector;
	}

	public JTextArea getTextAreaSinopsisResul() {
		return textAreaSinopsisResul;
	}

	public void setTextAreaSinopsisResul(JTextArea textAreaSinopsisResul) {
		this.textAreaSinopsisResul = textAreaSinopsisResul;
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
