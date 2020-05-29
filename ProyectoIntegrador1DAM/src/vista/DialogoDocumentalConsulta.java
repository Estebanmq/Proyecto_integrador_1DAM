package vista;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import modelo.Documental;
import javax.swing.JTextArea;

/**
 * Esta clase genera la pantalla de visualización de datos para la transacción de documentales
 *
 * @author Esteban Martínez
 * @since 24/05/2020
 * @version 1.0
 */
public class DialogoDocumentalConsulta extends JDialog {
	private static final long serialVersionUID = 1L;

	private Documental documental;
	
	private PanelBtnOk panelBtnOk;

	private JTextField textFieldBuscarCodigo;
	private JPanel panelResultado;
	private JTextArea textAreaSinopsisResul;
	private JTextField textFieldTitResul;
	private JTextField textFieldAnyoResul;
	private JTextField textFieldGeneroResul;
	private JTextField textFieldPaisResul;
	private JTextField textFieldDirectorResul;
	
	public DialogoDocumentalConsulta() {
		//Panel para buscar por codigo
		JPanel panelBuscar = new JPanel();
		JLabel labelCodigo = new JLabel("Código del documental: ");
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
		textFieldDirectorResul = new JTextField();
		JLabel labelGenero = new JLabel("Género: ");
		textFieldGeneroResul = new JTextField();
		JLabel labelSinopsis = new JLabel("Sinopsis: ");
		textAreaSinopsisResul = new JTextArea();
		
		panelBtnOk = new PanelBtnOk();
		
		setTitle("Consulta de documentales");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModal(true);
		setBounds(100, 100, 559, 384);
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
		
		
		labelTitulo.setBounds(22, 11, 61, 16);
		panelResultado.add(labelTitulo);
		
		textFieldTitResul.setBounds(95, 6, 179, 26);
		panelResultado.add(textFieldTitResul);
		textFieldTitResul.setColumns(10);
		textFieldTitResul.setEditable(false);
		
		labelPais.setBounds(286, 11, 61, 16);
		panelResultado.add(labelPais);
		
		textFieldPaisResul.setBounds(354, 6, 169, 26);
		panelResultado.add(textFieldPaisResul);
		textFieldPaisResul.setColumns(10);
		textFieldPaisResul.setEditable(false);
		
		labelAnyo.setBounds(22, 39, 61, 16);
		panelResultado.add(labelAnyo);
		
		textFieldAnyoResul.setBounds(95, 34, 179, 26);
		panelResultado.add(textFieldAnyoResul);
		textFieldAnyoResul.setColumns(10);
		textFieldAnyoResul.setEditable(false);
		
		labelDirector.setBounds(286, 39, 61, 16);
		panelResultado.add(labelDirector);
		
		textFieldDirectorResul.setBounds(354, 34, 169, 26);
		panelResultado.add(textFieldDirectorResul);
		textFieldDirectorResul.setColumns(10);
		textFieldDirectorResul.setEditable(false);
		
		labelGenero.setBounds(22, 67, 61, 16);
		panelResultado.add(labelGenero);
		
		textFieldGeneroResul.setBounds(95, 62, 179, 26);
		panelResultado.add(textFieldGeneroResul);
		textFieldGeneroResul.setColumns(10);
		textFieldGeneroResul.setEditable(false);
		
		labelSinopsis.setBounds(22, 95, 61, 16);
		panelResultado.add(labelSinopsis);
		
		textAreaSinopsisResul.setEditable(false);
		textAreaSinopsisResul.setLineWrap(true);
		textAreaSinopsisResul.setBounds(95, 95, 428, 164);
		panelResultado.add(textAreaSinopsisResul);
		
		
	
		
		
		
		panelBtnOk.setBounds(0, 320, 541, 39);
		getContentPane().add(panelBtnOk);
		
		getPanelResultado().setVisible(false);
			
	}

	public void mostrardocumental(Documental d) {
		textFieldTitResul.setText(d.getTitulo());
		textFieldPaisResul.setText(d.getNacionalidad().getDescripcion());
		textFieldAnyoResul.setText(Integer.toString(d.getAnyo()));
		textFieldDirectorResul.setText(d.getDirector().getNombre());
		textFieldGeneroResul.setText(d.getGenero().getDescripcion());
		textAreaSinopsisResul.setText(d.getSinopsis());
	}
	
	
	
	public JTextField getTextFieldTitResul() {
		return textFieldTitResul;
	}

	public void setTextFieldTitResul(JTextField textFieldTitResul) {
		this.textFieldTitResul = textFieldTitResul;
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
