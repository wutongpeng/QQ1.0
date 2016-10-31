package sdk.qq.client.kastem.assembly;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JFrame;

import com.sun.awt.AWTUtilities;


//Բ��Frame
public class FillitFrame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4238819591067495300L;
	
	//�϶�����
	private boolean startDrag = true;
	private Point oldposition = null;
	private MouseAdapter mouseAdapter;
	
	public FillitFrame(int width, int height){
		super();
		setSize(width, height);
		setUndecorated(true);
		setLocationRelativeTo(null);
		AWTUtilities.setWindowShape(this, new RoundRectangle2D.Double(0.0D, 0.0D, getWidth(),  
				getHeight(), 11.0D, 11.0D));
		canDrag();
		setAlwaysOnTop(true);
	}
	
	//�ṩ�Զ���Բ�Ƕ���
	public FillitFrame(int width, int height, double arcWidth, double arcHeight){
		super();
		setSize(width, height);
		setUndecorated(true);
		setLocationRelativeTo(null);
		AWTUtilities.setWindowShape(this, new RoundRectangle2D.Double(0.0D, 0.0D, getWidth(),  
				getHeight(), arcWidth, arcHeight));
		canDrag();
		setAlwaysOnTop(true);
	}
	
	//�ṩ�Ƿ���϶�������
	public void isCandrag(boolean b){
		
		if(mouseAdapter != null){
			if(b){
				canDrag();
			}else{
				this.removeMouseListener(mouseAdapter);
				this.removeMouseMotionListener(mouseAdapter);
				mouseAdapter = null;
				oldposition = null;
			}
		}
	}
	
	/*
	 * ʵ�ִ����϶�
	 * ʵ������������Ϊ�������������������
	 */
	private void canDrag() {
		mouseAdapter = new MouseAdapter(){
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO �Զ����ɵķ������
				if(startDrag){
					oldposition = e.getPoint(); 
					startDrag = false;
				}
		        Point newposition = e.getPoint();
		        setLocation(getX()+(newposition.x-oldposition.x), getY()+(newposition.y-oldposition.y));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				startDrag = true;
			}
		};
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
	}	
}
