package sdk.qq.client.kastem.assembly;

import javax.swing.*;

import sdk.qq.tools.VerlicelColumn;

import java.awt.*;

//����һ�鰴ť�����
public class GroupContainer extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4682584157233015966L;
	
	//�ڵ�/չ����ť
	private JToggleButton node;
	//�����б�
	private JPanel friendList;
	
	{
		setLayout(new BorderLayout());
		setOpaque(false);
	}
	
	public GroupContainer(JToggleButton node){
		this.node = node;
		add(this.node, "North");
		
		friendList = new JPanel(new VerlicelColumn());
		friendList.setOpaque(false);
		friendList.setVisible(false);
		add(friendList, "Center");
	}

    /**
     * ȡ����ı��ⰴť
     * 
     * @return JToggleButton
     */
    public JToggleButton getTitleButton() {
        return node;
    }

    /**
     * ������
     */
    public void collapse() {
    	friendList.setVisible(false);
        this.revalidate();
    }

    /**
     * չ����
     */
    public void expand() {
        friendList.setVisible(true);
        this.revalidate();
    }

    /**
     * ȡ������
     * 
     * @return String
     */
    public String getName() {
        return node.getText();
    }

    /**
     * ���һ����Ա���
     * 
     * @param index
     *            int ˳���
     * @param c
     *            Component ��Ա���
     */
    public void addMember(int index, Component c) {
//    	if(friendList == null){
//    		friendList = new JPanel(new VerlicelColumn(1));
//    		friendList.setOpaque(false);
//    		friendList.setVisible(false);
//    		add(friendList, "Center");
//    	}
        friendList.add(c, index);
        friendList.doLayout();
    }
    
    public void addMember(Component c) {
//    	if(friendList == null){
//    		friendList = new JPanel(new VerlicelColumn(1));
//    		friendList.setOpaque(false);
//    		friendList.setVisible(false);
//    		add(friendList, "Center");
//    	}
    	friendList.add(c);
    	friendList.doLayout();
    }
    
    /**
     * ����List�а��������г�Ա
     */
    public Component[] getMembers(){    	
    	return friendList.getComponents();
    }
    /**
     * ����List�а����ĳ�Ա
     */
    public Component getMembers(int index){
    	return friendList.getComponent(index);
    }
    /**
     * ����List�а����ĳ�Ա��
     */
    public int getMemberCount(){
    	return friendList.getComponentCount();
    }
}
