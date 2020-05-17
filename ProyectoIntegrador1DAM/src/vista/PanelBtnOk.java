package vista;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBtnOk extends JPanel {
	
	private static final long serialVersionUID = -2916018382013617038L;

	private JButton btnOk;
	
	public PanelBtnOk() {

		this.setLayout(new FlowLayout(FlowLayout.RIGHT));
	
		this.setBtnOk(new JButton("Ok"));
		this.getBtnOk().setActionCommand("btnOk");
		this.add(getBtnOk());
	
	}

	public JButton getBtnOk() {
		return btnOk;
	}

	public void setBtnOk(JButton btnOk) {
		this.btnOk = btnOk;
	}

}
