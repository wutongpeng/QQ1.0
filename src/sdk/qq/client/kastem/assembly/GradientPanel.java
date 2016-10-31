package sdk.qq.client.kastem.assembly;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class GradientPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4152468688678649960L;

	private Color startColor = Color.white;
	private Color endColor = Color.white;
	private boolean isGradient = true;
	private boolean Longitudinal = true;
	
	public GradientPanel(){
		super();
	}
	
	public GradientPanel(LayoutManager arg0){
		super(arg0);
	}
	
	public GradientPanel(Color startColor, Color endColor) {
		this.startColor = startColor;
		this.endColor = endColor;
	}
	
	public GradientPanel(LayoutManager arg0, Color startColor, Color endColor) {
		super(arg0);
		this.startColor = startColor;
		this.endColor = endColor;
	}

	public void paintComponent(Graphics g){
		
		  super.paintComponent(g);		  
		  //������ж��Ի�ȡ����ת�͵�����
		  if(isGradient){
			  int width = getWidth();
			  int height = getHeight();
			  Graphics2D g2d = (Graphics2D) g;
			  if(Longitudinal){
				  g2d.setPaint(new GradientPaint(0, 0, startColor, width, 0, endColor));
			  }else{
				  g2d.setPaint(new GradientPaint(0, 0, startColor, 0, height, endColor));
			  }
			  g2d.fillRect(0, 0, width, height);
		  }
	}

	public Color getStartColor() {
		return startColor;
	}

	public Color getEndColor() {
		return endColor;
	}

	public boolean isGradient() {
		return isGradient;
	}

	public void setStartColor(Color startColor) {
		this.startColor = startColor;
	}

	public void setEndColor(Color endColor) {
		this.endColor = endColor;
	}

	//�ṩ֧�ֵ�ɫ�ģ���ʹ���������������ĸ��ġ�ӭ�ϸ������
	public void setGradientColor(Color color){
		this.startColor = color;
		this.endColor = color;
	}
	//���ý���ɫ
	public void setGradientColor(Color startColor, Color endColor){
		this.startColor = startColor;
		this.endColor = endColor;
	}
	//�����Ƿ����ý���ɫ
	public void setGradient(boolean isGradient) {
		this.isGradient = isGradient;
	}

	public boolean isLongitudinal() {
		return Longitudinal;
	}
	//���÷���
	public void setLongitudinal(boolean longitudinal) {
		Longitudinal = longitudinal;
	}
}
