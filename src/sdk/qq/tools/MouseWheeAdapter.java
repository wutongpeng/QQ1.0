package sdk.qq.tools;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JScrollBar;

/**
 * @author milai
 * @version ʵ�ֹ������ĳ��ֹ���
 */
public class MouseWheeAdapter implements MouseWheelListener {
	
	protected JScrollBar scrollBar = null;
	
	/**
	 * @param JScrollBar Ҫ������ JScrollBar description
	 */
	public MouseWheeAdapter(JScrollBar jScrollBar){
		this.scrollBar = jScrollBar;
	}
	
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		//Ϊ�˷�ֹ�����������������˹�������λ�ã�����ÿ�ζ����»�ȡ������λ��
	    int position = scrollBar.getValue();
	    //���Ϲ���Ϊ���� ����Ϊ���� ���ֱ�ӻ�ȡ����ֵ ��Ӽ���ȷ��������λ��
	    int count = e.getWheelRotation();
	    position += count;
	    
	    //�ж��Ƿ�����ĳ����˱߽磬�����˾ͽ�λ������Ϊ�߽�
	    if (position<0) {
	    	
	    	position = 0;
	    	return;
	    }else if (position > 130) {
	    	
	    	position = 130;
	    	return;
	    }
		scrollBar.setValue(position);
	}	
}
