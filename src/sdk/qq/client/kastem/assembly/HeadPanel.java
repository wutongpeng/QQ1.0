package sdk.qq.client.kastem.assembly;

import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class HeadPanel extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6127751628415613865L;
	
	private ImageIcon border = new ImageIcon("Image\\client\\login's\\login_head_bkg.png");
	
	public void paintComponent(Graphics g){	
		
		super.paintComponent(g);
		
		if(border!=null){
			g.drawImage(border.getImage(), 
					0, 
					0, 
					getWidth(), 
					getHeight(), 
					null);	
		}			
	}
	
	public void setHeadBorder(ImageIcon border){
		this.border = border;
	}
}
