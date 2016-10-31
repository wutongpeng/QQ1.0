package test;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import com.sun.awt.AWTUtilities;

import sdk.qq.client.kastem.assembly.SenioButton;

public class Test implements ActionListener{

	JFrame frame;
	ImageIcon bg;
	ImageIcon texture;
	
	JPanel panel;
	JButton button;
	private boolean startDrag = true;
	private Point oldposition = null;
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new Test();
	}
	
	public Test(){
		
		frame = new JFrame();
		frame.setLayout(new FlowLayout());
		
		  ImageIcon ima = new ImageIcon("Image\\Head\\2.png");
		  BufferedImage bu = new BufferedImage(ima.getIconWidth(), ima
		    .getIconHeight(), BufferedImage.TYPE_INT_ARGB);
	        Graphics2D graphics = bu.createGraphics();
	        graphics.drawImage(ima.getImage(), 0, 0, null);
		  ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
		  try {
		   //把这个jpg图像写到这个流中去,这里可以转变图片的编码格式
		   boolean resultWrite = ImageIO.write(bu, "png", imageStream);
		  } catch (IOException e) {
		   // TODO Auto-generated catch block
		   e.printStackTrace();
		  }
		  byte[] tagInfo = imageStream.toByteArray();
		
		  
		  frame.add(new JLabel(new ImageIcon(tagInfo)));
		frame.setSize(380, 292);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
		
		canDrag();
	}
	
	/*
	 * 实现窗口拖动
	 * 实例化鼠标监听器为顶级窗口添加鼠标监听器
	 */
	protected void canDrag() {
		MouseAdapter mouseAdapter = new MouseAdapter(){
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO 自动生成的方法存根
				if(startDrag){
					oldposition = e.getPoint(); 
					startDrag = false;
				}
		        Point newposition = e.getPoint();
		        frame.setLocation(frame.getX()+(newposition.x-oldposition.x), frame.getY()+(newposition.y-oldposition.y));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				startDrag = true;
			}
		};
		frame.addMouseListener(mouseAdapter);
		frame.addMouseMotionListener(mouseAdapter);
	}	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
	}
}
