package sdk.qq.client.kastem.assembly;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Icon;
import javax.swing.JLabel;

/*
 * �������  ����� ��ť��ȡ����ʱ����ENTER���Զ���Ӧ������
 */
public class MyButton extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2635812551775218654L;
	
	private Icon img = null;
	private Icon hoverimg = null;
	private Icon pressimg = null;
	
	public MyButton(){
		super();
	}
	public MyButton(Icon icon){
		super(icon);
		addMouseAdapter();
		img = icon;
	}
	public MyButton(String s){
		super(s);
	}
	
	public void setPressedIcon(Icon icon){
		pressimg = icon;
	}
	public void setRolloverIcon(Icon icon){
		hoverimg = icon;
	}
	
	private void addMouseAdapter(){
		MouseAdapter mouseAdapter = new MouseAdapter(){
			public void mouseEntered(MouseEvent e) {
				setIcon(hoverimg);
			}
			public void mouseExited(MouseEvent e) {
				setIcon(img);
			}
			public void mousePressed(MouseEvent e) {
				setIcon(pressimg);
			}
			public void mouseReleased(MouseEvent e) {
				setIcon(img);
			}
		};
		addMouseListener(mouseAdapter);
	}
	//���ڵ����С�����ڵ�ʱ������ɿ��¼���׽����������Ҫ�ֶ�����ť��Ϊ��ʼ״̬
	public void initialState(){
		setIcon(img);
	}
}
