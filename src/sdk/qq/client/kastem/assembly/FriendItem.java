package sdk.qq.client.kastem.assembly;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

import sdk.qq.general.FriendItemModel;
import sdk.qq.tools.ChangeImage;
import sdk.qq.tools.Colors;
import sdk.qq.tools.Fonts;

//��������
//��JToggle��д�����ܽ���¼����ݵ�����
public class FriendItem extends JPanel{
    /**
	 * 
	 */
	private static final long serialVersionUID = 5772102618380608802L;
	
	private static final Color friendContainerBackColor = new Color(252,240,193);
	
	private boolean hover = false;
	private boolean selected = false;
	private GradientPaint gradientPaint;
	private static final Color startColor = new Color(252,236,175);
	private static final Color endColor = new Color(252,233,158);
	{
    	setLayout(new FlowLayout(FlowLayout.LEFT, 7, 7));
    	setOpaque(false);
		setBackground(friendContainerBackColor);
    	setPreferredSize(new Dimension(0, 53));
    	addMouseListener(new MouseAdapter(){
    		public void mouseEntered(MouseEvent e){
    			hover = true;
    			repaint();   			
    		}
    		public void mouseExited(MouseEvent e){
    			hover = false;
    			repaint();
    		}
    	});
    	addMouseListener(mutualExclusion);
	}
	//Ϊ�˴�ɻ���
	private static final MouseAdapter mutualExclusion = new MouseAdapter(){
		
		byte[] img;
		{
			  ImageIcon ima = new ImageIcon("Image\\Head\\1.png");
			  BufferedImage bu = new BufferedImage(ima.getIconWidth(), ima
			    .getIconHeight(), BufferedImage.TYPE_INT_ARGB);
		        Graphics2D graphics = bu.createGraphics();
		        graphics.drawImage(ima.getImage(), 0, 0, null);
			  ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
			  try {
			   ImageIO.write(bu, "png", imageStream);
			  } catch (IOException e) {
			   e.printStackTrace();
			  }
			  img = imageStream.toByteArray();
		}
		private FriendItem lastItem = new FriendItem(new FriendItemModel(img,
				null,
				null,
				false,
				null,
				null));
		public void mousePressed(MouseEvent e){
			FriendItem pressItem = (FriendItem) e.getSource();
			if(pressItem == lastItem){
				return;
			}
			pressItem.selected = true;
			pressItem.hover = false;
			pressItem.repaint();
			lastItem.selected = false;
			lastItem.repaint();
			lastItem = pressItem;
		} 		 
	};
	
	protected void paintComponent(Graphics g){
		if(selected){
			int width = getWidth();
			int height = getHeight();
			Graphics2D g2d = (Graphics2D)g;
			gradientPaint = new GradientPaint(0, 0, startColor, 0, height, endColor);
			g2d.setPaint(gradientPaint);
			g2d.fillRect(0, 0, width, height);
		}else if(hover){
			g.setColor(friendContainerBackColor);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
		super.paintComponent(g);
	}
	
	//ģ��
	private FriendItemModel model;
	//ͷ��
	private JLabel headPanel;
	//�ֲ�
	private JPanel createCenterLayout;
	//��һ��
	private JPanel firstLayer;
	//�ڶ���
	private JPanel SecondLayer;	
	//��ע
	private JLabel remarks;
	//�ǳ�
	private JLabel nickName;
	//�鿴�ռ䰴ť
	private SenioButton seeZone;
	//����ǩ��
	private JLabel signature;
	//����
	public FriendItem(FriendItemModel model){
		
		this.model = model;
		//ͷ��
		JPanel pane11 = new JPanel(new BorderLayout());
		pane11.setOpaque(false);
		add(pane11);
		headPanel = new JLabel();
		headPanel.setIcon(ChangeImage.roundedCornerIcon(new ImageIcon(model.getHead()),
				40,
				40,
	    		5));
		pane11.add(headPanel, "West");
		
		//����ͷ��֮������Ĳ���
		createCenterLayout = new JPanel(new GridLayout(2, 1));
		createCenterLayout.setOpaque(false);
		createCenterLayout.addMouseListener(util);
		add(createCenterLayout, "Center");
		//��һ��
		firstLayer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		firstLayer.setOpaque(false);
		firstLayer.addMouseListener(util);
		createCenterLayout.add(firstLayer);	
		//��ע
		if(model.getRemarks()!=null){
			remarks = new JLabel(model.getRemarks());
			remarks.setFont(Fonts.MicrosoftAccor15);
			firstLayer.add(remarks);
			//�ǳ�	
			nickName = new JLabel(model.getNickName());
			nickName.setFont(Fonts.MicrosoftAccor11);
			nickName.setForeground(Colors.greyColor);
			firstLayer.add(nickName);
		}else{
			nickName = new JLabel();
			nickName.setFont(Fonts.MicrosoftAccor15);
			firstLayer.add(nickName);
		}
		//�ڶ���
		SecondLayer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		SecondLayer.setOpaque(false);
		SecondLayer.addMouseListener(util);
		createCenterLayout.add(SecondLayer);
		//�ռ��и���
		if(model.isNewMessage()){
			seeZone = new SenioButton(new ImageIcon("Image\\AppPluginIcon\\qzoneicon.png"));
			seeZone.setSendEvent(true);			
			SecondLayer.add(seeZone);
		}
		signature = new JLabel(model.getSignature());
		signature.setFont(Fonts.MicrosoftAccor11);
		signature.setForeground(Colors.greyColor);
		SecondLayer.add(signature);
	}
	//�����¼�����
	private MouseAdapter util = new MouseAdapter(){
		public void mouseEntered(MouseEvent e){
			sendEvent(e);
		}
		public void mouseExited(MouseEvent e){
			sendEvent(e);
		}
		public void mouseClicked(MouseEvent e){
			sendEvent(e);
		}
		public void mousePressed(MouseEvent e){
			sendEvent(e);
		}
		private void sendEvent(MouseEvent e){
			Component source = (Component) e.getSource();
			Container father = source.getParent();
			MouseEvent e1 = SwingUtilities.convertMouseEvent(source, e, father);
			father.dispatchEvent(e1);
		}
	};
	public void addToFirstLayer(Component com){
		firstLayer.add(com);
	}	
	public void addToSecondLayer(Component com){
		SecondLayer.add(com);
	}	
	//�ṩͷ�����÷���
	public void setHead(Icon head){
		headPanel.setIcon(head);
	}
	public Icon getHead(){
		return headPanel.getIcon();
	}
	//���ظú��Ѻ���
	public String getNo(){
		return model.getNO();
	}
	//��������
	public String getName(){
		return nickName.getText();
	}
	//model
	public FriendItemModel getModel() {
		return model;
	}
	public void setModel(FriendItemModel model) {
		this.model = model;
	}
}
