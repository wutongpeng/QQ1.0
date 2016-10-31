package sdk.qq.client.kastem.assembly;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.*;


//�������
public class MainPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7888285776199480915L;

	private ImageIcon bg;
	private int arcWidth = 11;
	private int arcHeight = 11;
	private Color borderColor = new Color(255,255,255, 110);
	
	public MainPanel(int arcWidth, int arcHeight){
		super();
		this.arcWidth = arcWidth;
		this.arcHeight = arcHeight;
	}
	
	public MainPanel(ImageIcon bg){
		super();
		this.bg = bg;
	}
	
	public MainPanel(ImageIcon bg, 	int arcWidth, int arcHeight){
		super();
		this.bg = bg;
		this.arcWidth = arcWidth;
		this.arcHeight = arcHeight;
	}
	
	//�߿�������������С���Ϊ���� paintComponent �� paintBorder �϶��������
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d = (Graphics2D)g;	
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        //�����ⲿ�߿�
		g2d.drawRoundRect(0, 0, this.getWidth()-1, this.getHeight()-1, arcWidth+2, arcHeight+2);
		//�����ڳı߿�
		g2d.setColor(borderColor);
		g2d.setStroke(new BasicStroke(1.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER));				
		g2d.drawRoundRect(1, 1, this.getWidth()-3, this.getHeight()-3, arcWidth, arcHeight);
	}
	
	//���Ʊ���
	public void paintComponent(Graphics g){
		super.paintComponent(g);		
		if(bg != null){
			g.drawImage(bg.getImage(),
					0,
					0,
					bg.getIconWidth(),
					bg.getIconHeight(),
					null);
		}			        
		//�ֶ�����frame�ı߿�©��
//		Path2D.Float p = new Path2D.Float();
//		int x = getWidth();
//		p.moveTo(x-160, 0);
//		p.lineTo(x-3, 0);
//		p.curveTo(x-1, 0, x, 20, x, 60);
//		g2d.draw(p);       
//      g2d.drawRoundRect(0, 0, this.getWidth()-1, this.getHeight()-1, arcWidth+2, arcHeight+2);
	}
	
	//�ṩ���ñ���
	public void setBg(ImageIcon bg){
		this.bg = bg;
	}
	//�ṩ���ñ߿���ɫ
	public void setBorderColor(Color borderColor){
		this.borderColor = borderColor;
	}
}
