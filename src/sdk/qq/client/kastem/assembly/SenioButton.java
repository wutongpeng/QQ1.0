package sdk.qq.client.kastem.assembly;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class SenioButton extends JLabel{	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1518775487740923190L;
	
	//�����Ƿ���ʾ���б߿�
	private boolean showBorder = true;
	//�����Ƿ���ʾ��ͣ״̬
	private boolean hover;
	//�����Ƿ���ʾ���״̬
	private boolean press;
	//����״̬�µ���ɫ��Ĭ������� pressû����ɫ,�������������
	private LinearGradientPaint hoverPaint;
	private LinearGradientPaint pressPaint;
	private int roundSize = 4;
	//�Ƿ񴫵��¼�
	private boolean sendEvent = true;
	
	//Ĭ��״̬�Ĺ�����
	public SenioButton(){
		super();
		addDefaultStateChanger();
	}	
	public SenioButton(Icon icon){
		super(icon);
		addDefaultStateChanger();
	}	
	public SenioButton(Icon icon, int horizontalAlignment, boolean showBorder){
		super(icon, horizontalAlignment);
		addDefaultStateChanger();
		this.showBorder = showBorder;
	}
	public SenioButton(String text, Icon icon, int horizontalAlignment){
		super(text, icon, horizontalAlignment);
		addDefaultStateChanger();
	}
	public SenioButton(String text, Icon icon, int horizontalAlignment, boolean showBorder){
		super(text, icon, horizontalAlignment);
		addDefaultStateChanger();
		this.showBorder = showBorder;
	}
	
	//ӵ��չ����ť�Ĺ�����
	public SenioButton(Icon icon, boolean hasOpenButton){
		super(icon);
		if(hasOpenButton){
			createOpenButton(new ImageIcon("Image\\ChatFrame\\aio_toolbar_arrow.png"));
		}
	}	
	public SenioButton(Icon icon, Icon icon2){
		super(icon);
		createOpenButton(icon2);
	}	
	
	protected void paintComponent(Graphics g) {
		  //��Ϊ��֪�� �����Ƿ��ǻ�ı�ġ����Ծ�ֻ��ÿ�ζ�����һ����
		// ����Ļ�����Ϊ��֪��ʲôʱ���ȷ���� �����С ���ԾͲ����ȥ�ˣ� ��Ȼ�Ļ� ��������Ч�ʰ�
		  if(press){
	    	  
	      }else if(hover){
	    	  Graphics2D g2d = (Graphics2D) g;
	          g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                  RenderingHints.VALUE_ANTIALIAS_ON);
	    	  int width  = getWidth();
	    	  int height  = getHeight();
	    	  
	    	  if(hoverPaint==null){
	    			hoverPaint = new LinearGradientPaint(0.0f, 0.0f, 0.0f, this.getWidth(),
	    					new float[] { 0.0f, 0.35f, 0.70f, 1.0f }, new Color[] {
	    							new Color(255,255,255,150), new Color(255,255,255,100),
	    							new Color(255,255,255,70), new Color(255,255,255,30)});
	    	  }
			  g2d.setPaint(hoverPaint);
			  g2d.fillRoundRect(1, 1, width-1, height-1, roundSize, roundSize); 	  
	      }
		  super.paintComponent(g);
	  }
	
	public void paintBorder(Graphics g){
		super.paintBorder(g);
		if(showBorder){
			if(hover || press){
		    	  Graphics2D g2d = (Graphics2D) g;
		          g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		                  RenderingHints.VALUE_ANTIALIAS_ON);
		          g2d.setColor(new Color(0, 0, 0, 100));
		          g2d.drawRoundRect(0, 0, getSize().width-1, getSize().height-1, roundSize+1,
		        		  roundSize+1);
		          g2d.setColor(new Color(255, 255, 255, 150));
		          g2d.drawRoundRect(1, 1, getSize().width-3, getSize().height-3, roundSize,
		        		  roundSize);
			}	
		}			
	}
	
	//������ʾ��ͣ״̬��״̬����ʾ��ͨ�����õģ�����ζ��������ֶ��Ľ�������ԭ�ȵļ�������������Լ��ļ�����
	//����ʾ��ͬ��Ч��
	public void showHover(){
		hover = true;
		repaint();
	}
	public void dontShowHover(){
		hover = false;
		repaint();
	}
	//������ʾ���״̬
	public void showPress(){
		press = true;
		repaint();
	}	
	public void dontShowPress(){
		press = false;
		repaint();
	}
	
	//�����Ƿ���ʾ��������еı߿�����Զ�����ӱ߿�
	public void setShowBorder(boolean show){
		showBorder = show;
	}
	
	public LinearGradientPaint getHoverPaint() {
		return hoverPaint;
	}
	public LinearGradientPaint getPressPaint() {
		return pressPaint;
	}
	public void setHoverPaint(LinearGradientPaint hoverPaint) {
		this.hoverPaint = hoverPaint;
	}
	public void setPressPaint(LinearGradientPaint pressPaint) {
		this.pressPaint = pressPaint;
	}

	//֧������Բ�Ƕ���
	public void setRoundSize(int roundSize) {
		this.roundSize = roundSize;
	}
	public int getRoundSize() {
		return roundSize;
	}
	//�����Ƿ����ϴ����¼�
	public void setSendEvent(boolean b){
		sendEvent = b;
	}
	
	//���Ĭ�ϼ�����
	public void removeDefaultStateChanger(){
		removeMouseListener(defaultStateChanger);
		defaultStateChanger = null;
	}
	
	//չ����ť
	protected SenioButton OpenButton;
	//�ṩ���ⲿ�����
	public void addOpenButton(){
		createOpenButton(new ImageIcon("Image\\ChatFrame\\aio_toolbar_arrow.png"));
	}
	public void addOpenButton(Icon icon){
		createOpenButton(icon);
	}
	//���չ����ť
	protected void createOpenButton(Icon icon){
		
		OpenButton = new SenioButton(icon);
		OpenButton.setPreferredSize(new Dimension(16, getPreferredSize().height));
		OpenButton.setShowBorder(false);
		
		setLayout(new BorderLayout());
		add(OpenButton, "East");			
	}
	//Ϊչ����ť�����չ����
	public void openButtonAddMouseListener(MouseListener l){
		OpenButton.addMouseListener(l);
	}
	
	//Ĭ��״̬�µ�״̬������
	protected MouseAdapter defaultStateChanger;
	//���״̬������
	protected void addDefaultStateChanger(){
		defaultStateChanger = new MouseAdapter(){
			
			public void mouseEntered(MouseEvent e) {				
				showHover();
				if(sendEvent){
					sendEvent(e);
				}
			}
			public void mouseExited(MouseEvent e) {
				dontShowHover();
				if(sendEvent){
					sendEvent(e);
				}
			}
			public void mousePressed(MouseEvent e) {
				showPress();
			}
			public void mouseReleased(MouseEvent e) {
				dontShowPress();
			}
			public void sendEvent(MouseEvent e){
				Component source = (Component) e.getSource();
				Container father = source.getParent();
				MouseEvent e1 = SwingUtilities.convertMouseEvent(source, e, father);
				father.dispatchEvent(e1);
			}
		};
		addMouseListener(defaultStateChanger);
	}
}