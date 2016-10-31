package sdk.qq.client.kastem.assembly;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;

import sdk.qq.general.StateEnum;

public class StateItem extends JMenuItem{
	/**
	 * 
	 */
	private static final long serialVersionUID = 50758049687381612L;

	private StateEnum state;
	
	public StateItem(StateEnum state){
		
		this.state = state;
		confirmation();
	}
	
	//���ظ�ѡ��������ĵ�״̬
	public StateEnum getState(){		
		return state;
	}
	
	//���ǵ���������ֶ����õ�������Ծ�������������
	public void setState(StateEnum state){
		this.state = state;
		confirmation();
	}

	//ȷ�� ѡ�ť�����ֵ
	public void confirmation(){

		switch(state){ 
		//��������
		case ONLINE:
			setText("��������");
			setIcon(new ImageIcon("Image\\Status\\FLAG\\Big\\imonline.png"));
			break;
		//����Q�Ұ�
		case QME:
			setText("Q�Ұ�");
			setIcon(new ImageIcon("Image\\Status\\FLAG\\Big\\Qme.png"));
			break;
		//�����뿪
		case LEAVE:
			setText("�뿪");
			setIcon(new ImageIcon("Image\\Status\\FLAG\\Big\\leave.png"));
			break;
		//����æµ
		case BEBUSY:
			setText("æµ");
			setIcon(new ImageIcon("Image\\Status\\FLAG\\Big\\busy.png"));
			break;
		//�����������
		case DONTDISTURB:
			setText("�������");
			setIcon(new ImageIcon("Image\\Status\\FLAG\\Big\\dontDisturb.png"));
			break;
		//��������
		case INVISIBLE:
			setText("����");
			setIcon(new ImageIcon("Image\\Status\\FLAG\\Big\\invisible.png"));
			break;
		}
	}


	public StateItem(String string, ImageIcon imageIcon) {
		super(string, imageIcon);
	}
	
	public StateItem(){
		super();
	}
}
