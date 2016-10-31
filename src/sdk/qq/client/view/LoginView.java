package sdk.qq.client.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicComboBoxUI;

import com.sun.awt.AWTUtilities;

import sdk.qq.client.kastem.assembly.FillitFrame;
import sdk.qq.client.kastem.assembly.HeadPanel;
import sdk.qq.client.kastem.assembly.MainPanel;
import sdk.qq.client.kastem.assembly.StateButton;
import sdk.qq.client.kastem.assembly.StateItem;
import sdk.qq.client.model.VerificationUser;
import sdk.qq.general.StateEnum;
import sdk.qq.general.User;
import sdk.qq.general.UserInformation;
import sdk.qq.general.parcel.ParcelModel;
import sdk.qq.general.parcel.UserThroughParcel;
import sdk.qq.tools.ChangeImage;
import sdk.qq.tools.Colors;
import sdk.qq.tools.Fonts;
import sdk.qq.tools.Images;

//��ȡ�˺ż�¼����ûд
//δ���Զ��尴ť�ĳ���ͨ��ť�İ汾

public class LoginView{
	
	//����
	private FillitFrame frame = null;
	//��������
	private ImageIcon bg;
	private MainPanel mainPanel;
	//���������ɫ�����������������
	private ImageIcon texture;
	private JLabel centerPanel = null;
	
	public static void main(String[] args){
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		LoginView login = new LoginView();
		login.createUI();
	}
	
	//���ɽ���
	public void createUI(){
		
		frame = new FillitFrame(380, 292);
		frame.setLocationRelativeTo(null);
		
		bg = new ImageIcon("Image\\client\\login's\\noon.jpg");
		mainPanel = new MainPanel(bg);
		mainPanel.setLayout(new BorderLayout());
		frame.add(mainPanel);
		//�в����
		texture = new ImageIcon("Image\\client\\login's\\texture.png");
		centerPanel = new JLabel(texture);
		centerPanel.setOpaque(false);
		centerPanel.setLayout(null);
		mainPanel.add(centerPanel);
		//ͷ������
		createHead();
		//ѡ��/��ť
		assembly();
		//�����
		createInputBox();

		frame.setVisible(true);
		//������ʾ��
		createTip();
	}

	
	// �༭��
	private UserFiledEdit edit; 
	//�û���
	private Vector<User> elements; 
	//ģ��
	private DefaultComboBoxModel comboBoxModel; 
	//������
	private JComboBox filluser = null; 
	//ѡ����ļ�����
	private UserChangeAdapter userChangeAdapter = null; 	
	//���������
	private JPasswordField fillcipher = null;
	private boolean deleteAll = false;
	private boolean isModify = true;
	//��������򽹵������
	private FillcipherFocusAdapter fillcipherFocusAdapter;
	
	/*
	 * ���������,ӵ����ʾĬ�ϵ�¼�˺Ź���
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void createInputBox() {		
		//���������
		fillcipher = new JPasswordField(16){
			/**
			 * 
			 */
			private static final long serialVersionUID = 6127751628415613865L;
			public void paintBorder(Graphics g){
				super.paintBorder(g);
				g.drawImage(new ImageIcon("Image\\client\\login's\\inputbox.png").getImage(),
						-2,
						-2,
						192,
						30,
						null
						);
			}
		};	
		fillcipher.setEchoChar('��');
		fillcipher.setFont(Fonts.MicrosoftAccor12);
		fillcipher.setBounds(115, 181, 188, 26);
		centerPanel.add(fillcipher);		
		//���������Ľ��������
		fillcipherFocusAdapter = new FillcipherFocusAdapter();
		fillcipher.addFocusListener(fillcipherFocusAdapter);
		//��������������ENTER��
		fillcipher.addKeyListener(new KeyAdapter(){
			//ֻ��ʹ�����������JTextField��ENTER���������Ķ����ڵ���������ʧ֮��Ż�ȡ�¼�
			//���ܹ���ɵ���������ʾ��ʱ�򲻵�¼��Ŀ��
			public void keyPressed(KeyEvent e) {				
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					query();
				}
			} 
		});
		//���ֵ�޸ļ���
		fillcipher.getDocument().addDocumentListener(new DocumentListener(){
			public void insertUpdate(DocumentEvent e) {
				if(deleteAll){
					String content = new String(fillcipher.getPassword()).replace("123456789", "") ;
					modifyCipherState("");
					modifyCipherState(content);
				}
			}
			public void removeUpdate(DocumentEvent e) {	
				if(deleteAll){
					modifyCipherState("");
				}
			}
			public void changedUpdate(DocumentEvent e) {
			}
			
			public void modifyCipherState(final String s){
           		EventQueue.invokeLater(new Runnable() {
                    public void run() {  
                    	fillcipher.setText(s);
                    	deleteAll = false;
                    	isModify = true;
                    }
                });
			}
		});
		
		//�˺������ļ�����
		userChangeAdapter = new UserChangeAdapter();
		//�˺������
		filluser = new JComboBox(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 6127751628415613865L;
			public void paintBorder(Graphics g){
				super.paintBorder(g);
				g.drawImage(new ImageIcon("Image\\client\\login's\\inputbox.png").getImage(),
						-2,
						-2,
						192,
						30,
						null
						);
			}
		};
		filluser.setUI(new BasicComboBoxUI() {
			protected JButton createArrowButton(){
				JButton arrowButton = new JButton(new ImageIcon("Image\\client\\login's\\arrowButton.png"));
				arrowButton.setFocusable(false);
				arrowButton.setFocusPainted(false);
				arrowButton.setRolloverIcon(new ImageIcon("Image\\client\\login's\\arrowButton_hover.png"));
				return arrowButton;
			}
		});

		filluser.setFont(Fonts.blackBody15);
		filluser.setEditable(true);
		filluser.setMaximumRowCount(2);
		filluser.setRenderer(new IconRenderer());
		filluser.setBounds(115, 147, 188, 26);
		centerPanel.add(filluser);
		
		//����ģ��
		elements = new Vector<User>();
		readUser();
		comboBoxModel = new DefaultComboBoxModel(elements);
		
		filluser.setModel(comboBoxModel);
		
		//JComboBox�ı༭��
		edit = new UserFiledEdit();
		edit.addMouseWheelListener(userChangeAdapter);
		edit.addKeyListener(new KeyAdapter(){
			//ֻ��ʹ�����������JTextField��ENTER���������Ķ����ڵ���������ʧ֮��Ż�ȡ�¼�
			//���ܹ���ɵ���������ʾ��ʱ�򲻵�¼��Ŀ��
			public void keyPressed(KeyEvent e) {				
				if(!filluser.isPopupVisible() && e.getKeyCode() == KeyEvent.VK_ENTER){
					query();
				}
			} 
		});
		edit.addDocumentListener(new DocumentListener(){
			//��ֵ���ڱ����ظ��ĸ���
			private boolean toUpDate = true;
			//ɾ���õ��ж�
			private boolean toUpDate_2 = true;
			
			//ɾ���ֶ�ʱ�����ж�
			public void removeUpdate(DocumentEvent e) {
				
				if(!toUpDate_2){
					return;
				}
                String input = edit.getText();

                if (!input.isEmpty()) {
                	
                    for (final User item : elements) {                    	
                    	//ȡ���˺�����ƥ��
                    	String value = item.toString();
                    	//�ж��Ƿ���ȫƥ��
                    	//�����е�ֵ�ྡ���� ���߳�֮ǰ�����������ֱ��ʹ�ã�����������ʱ�䣬��Ϊ��������̫���������
                    	if(value.equals(input)) {	
                			//����ֵʱ���ᴥ�� ֵ�����¼������ǲ�ϣ�������ģ�����״̬����Ϊ������
                			toUpDate_2 = false;
                			//�¿�һ���̵߳ȴ�ֵ���²�������ٽ����޸�ֵ
                     		EventQueue.invokeLater(new Runnable() {
                                public void run() {                                    	
                            		filluser.setSelectedItem(item);
                            		updateUser();
                            		//�������֮��״���Ϊϣ�����£���ִ���´εĲ���
                            		toUpDate_2 = true;
                                }
                            });
                    		//��ƥ�䵽һ���˻�ʱ�Ͳ���ƥ��
                    		return;
                    	}
                    }
                }
                //��û��ƥ����ʱ������ͼ��ΪĬ��
                reSet();
			}
			
			public void insertUpdate(DocumentEvent e) {
				
				if(!toUpDate || edit.isDefaultText() || edit.isAutomaticFilling()){
					return;
				}				
				filterUser();
			}
			
			public void changedUpdate(DocumentEvent e) {
				System.out.println("�κκκκκκκκκκκκκκκκκκκκκκκκκ�");
			}	
			
			//��������������������Ƿ��з��ϵ��˻����еĻ�����תΪ�Ǹ��˻�
			//������ΪĬ�ϵ�
			public void filterUser(){
				
                String input = edit.getText();
                
                if (!input.isEmpty()) {

                    for (final User item : elements) {                    	
                    	//ȡ���˺�����ƥ��
                    	String value = item.toString();                   	
                    	//�ж��Ƿ�ƥ��
                    	//�����е�ֵ�ྡ���� ���߳�֮ǰ�����������ֱ��ʹ�ã�����������ʱ�䣬��Ϊ��������̫���������
                    	if(value.startsWith(input)) {
                    		
                    		String fill = value.replaceAll(input,"");
                    		
                    		if(fill.length() == 0){
                    			//����ֵʱ���ᴥ�� ֵ�����¼������ǲ�ϣ�������ģ�����״̬����Ϊ������
                    			toUpDate = false;
                    			//�¿�һ���̵߳ȴ�ֵ���²�������ٽ����޸�ֵ
                         		EventQueue.invokeLater(new Runnable() {
                                    public void run() {                                    	
                                		filluser.setSelectedItem(item);
                                		updateUser();
                                		//�������֮��״���Ϊϣ�����£���ִ���´εĲ���
                                		toUpDate = true;
                                    }
                                });
                    		}else{
                    			toUpDate = false;
                        		final int start = input.length();
                        		final int end = value.length();
                        		EventQueue.invokeLater(new Runnable() {
                                    public void run() {  
                                    	//�����÷���edit.setItem(item);֮ǰ
                                    	//��Ϊ�����ᵼ��ˢ�����������ʹ�������������ѡ��Ч��
                                		filluser.setSelectedItem(item); 
                                		
                                    	edit.setItem(item);
                                    	edit.select(start, end);
                                		updateUser();  
                                		toUpDate = true;
                                    }
                                });
                    		}
                    		//��ƥ�䵽һ���˻�ʱ�Ͳ���ƥ��
                    		return;
                    	}
                    }
                }
                //��û��ƥ����ʱ������ͼ��ΪĬ��
                reSet();
			}			
		
		});
		filluser.setEditor(edit);
		createTextFieldHoverBorder();
	}
	
	//�����ͣ�����ʱ�ı߿�
	public void createTextFieldHoverBorder(){
		final JLabel boder = new JLabel();
		edit.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				boder.setIcon(Images.blueBorder);	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				boder.setIcon(null);
			}			
		});
		boder.setBounds(113, 145, 192, 30);
		centerPanel.add(boder);
		
		final JLabel boder2 = new JLabel();
		boder2.setOpaque(false);
		fillcipher.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseEntered(MouseEvent e) {
				boder2.setIcon(Images.blueBorder);	
			}
			@Override
			public void mouseExited(MouseEvent e) {
				boder2.setIcon(null);
			}			
		});
		boder2.setBounds(113, 179, 192, 30);
		centerPanel.add(boder2);
	}
	
	/*
	 * ���������Ľ��������
	 */
	class FillcipherFocusAdapter implements FocusListener{
		
		boolean isDefaultText = false;
		//��ý���ʱ
		public void focusGained(FocusEvent e) {			
			//����ý���ʱ�жϵ�ǰ�����Ƿ���Ĭ��ֵ������ȡ��Ĭ��ֵ��ǰ��ɫ
			String s = new String(fillcipher.getPassword());
	        if(s.equals("����")){
	        	fillcipher.setForeground(Color.black);
	        	fillcipher.setEchoChar('��');
	        	fillcipher.setText("");
	        	isDefaultText = false;
	        }
	    }
	    //ʧȥ����ʱ
	    public void focusLost(FocusEvent e) {
	    	//��ǰ��ʾ�����ǿհ�ʱ�����Ĭ��ֵ����״̬����
	    	if(fillcipher.getPassword().length == 0){
	    		fillcipher.setForeground(Colors.greyColor);
	    		fillcipher.setEchoChar('\u0000');
	    		fillcipher.setText("����");
	        	isDefaultText = true;
	    	}
	    }
	}
		
	//�����������ʾĬ��ֵ
	public synchronized void emptyFillcipher(){
		if(!fillcipherFocusAdapter.isDefaultText){
			fillcipher.setForeground(Colors.greyColor);
			fillcipher.setEchoChar('\u0000');
			fillcipher.setText("����");
			fillcipherFocusAdapter.isDefaultText = true;
		}
	}
		
	/*
	 * ���������� �� ���� �˺������� ���ֹ������س���ѡ��ֵ�¼�
	 * ���� �˺������ ���˳��� �˺� ������ ���������״̬
	 */
	class UserChangeAdapter implements MouseWheelListener{
		public void mouseWheelMoved(MouseWheelEvent e) {

			int position = filluser.getSelectedIndex();
		    int count = e.getWheelRotation();
			position += count;			    
		    //�ж��Ƿ�����ĳ����˱߽磬�����˾ͽ�λ������Ϊ�߽�
		    if (position<0) {	    	
		    	position = 0;
		    	return;
		    }else if (position > filluser.getItemCount()-1) {
		    	
		    	position = filluser.getItemCount()-1;
		    	return;
		    }
		    filluser.setSelectedIndex(position);
		}		
	}
		
	//��ȡ�˻�
	public void readUser(){
		elements.add(new User("1070550684", "Image\\Head\\1_100.gif", StateEnum.QME, false, false));
		elements.add(new User("1070550685", "Image\\Head\\10.png", StateEnum.BEBUSY, true, false));
		elements.add(new User("1070550686", "Image\\Head\\30.png", StateEnum.INVISIBLE, false, true));
	}
	
	
	//ͷ��
	private HeadPanel headPanel = null;
	//״̬ѡ��
	private JPopupMenu stateButton_popup = null;
	private StateItem onLine = null;
	private StateItem qMe = null;
	private StateItem leave = null;
	private StateItem beBusy = null;
	private StateItem dontDisturb = null;
	private StateItem invisible = null;
	private StateButton stateButton = null;
	//������
	private ItemActionAdapter itemActionAdapter = null;
	
	/*
	 * ����ͷ������
	 * 	 * ����Ϊ����ľ���ʵ�֣��������ⲿ������ ���Ծ�����Ϊprotected
	 */
	private void createHead(){
		//ͷ��
		headPanel = new HeadPanel();
		headPanel.setBounds(24, 146, 82, 82);
		centerPanel.add(headPanel);
		
		//״̬ѡ�񴰿�
		stateButton_popup = new JPopupMenu(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 9012594627907460339L;
			public void paintComponent(Graphics g){
				g.drawImage(new ImageIcon("Image\\client\\login's\\statePopup.png").getImage(), 
						0, 
						0, 
						108, 
						145, 
						null);
			}
		};

		//ʵ����������
		itemActionAdapter = new ItemActionAdapter();
		
		onLine = new StateItem(StateEnum.ONLINE);
		onLine.addActionListener(itemActionAdapter);
		stateButton_popup.add(onLine);
		
		qMe = new StateItem(StateEnum.QME);
		qMe.addActionListener(itemActionAdapter);
		stateButton_popup.add(qMe);
		
		stateButton_popup.addSeparator();
		
		leave = new StateItem(StateEnum.LEAVE);
		leave.addActionListener(itemActionAdapter);
		stateButton_popup.add(leave);
		
		beBusy = new StateItem(StateEnum.BEBUSY);
		beBusy.addActionListener(itemActionAdapter);
		stateButton_popup.add(beBusy);
		
		dontDisturb = new StateItem(StateEnum.DONTDISTURB);
		dontDisturb.addActionListener(itemActionAdapter);
		stateButton_popup.add(dontDisturb);
		
		stateButton_popup.addSeparator();
		
		invisible = new StateItem(StateEnum.INVISIBLE);
		invisible.addActionListener(itemActionAdapter);
		stateButton_popup.add(invisible);
		
		//״̬ѡ��ť
		stateButton = new StateButton();
		stateButton.setIcon(onLine.getIcon());
		stateButton.setState(onLine.getState());
		stateButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				stateButton_popup.show(centerPanel, 89, 232);
				stateButton_popup.updateUI();
			}
		});
		stateButton.setBounds(64, 64, 17, 17);
		headPanel.add(stateButton);
	}
		
	/*
	 * ״̬ѡ�� Item ����� ר�ü���������������ȥ��IF�ж�
	 */
	class ItemActionAdapter implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			StateItem selectedItem = (StateItem)e.getSource();
			stateButton.setIcon(selectedItem.getIcon());
			stateButton.setState(selectedItem.getState());
		}
	}	
	
	
	
	//�ر�����С����ť/����
	private JButton closeButton = null;
	private JButton minimizationButton = null;	
	private JButton settingsButton;
	//��ѡ��
	private JCheckBox rememberPassword = null;
	private JCheckBox automaticLogin = null;	
	//ע���˺š��һ����밴ť
	private JButton registration = null;
	private JButton find = null;	
	//��¼��ť
	private JButton loginButton = null;
	
	/*
	 * ���ɽ����ϰ���
	 */
	private void assembly(){
		
		//�ر�		//��С�� : ���������þ���ͼ�����ò���ȡ���㡢����͸������Ӳ�������������λ�ô�С����������
		closeButton = new JButton(new ImageIcon("Image\\SystemButton\\clouse.png"));
		closeButton.setRolloverIcon(new ImageIcon("Image\\SystemButton\\clouse_hover.png"));
		closeButton.setPressedIcon(new ImageIcon("Image\\SystemButton\\clouse_press.png"));
		closeButton.setToolTipText("�ر�");
		closeButton.setContentAreaFilled(false);
		closeButton.setFocusPainted(false);
		closeButton.setFocusable(false);
		closeButton.setBounds(340, 0, 40, 20);
		closeButton.addActionListener(actionAdapter);
		centerPanel.add(closeButton);

		minimizationButton = new JButton(new ImageIcon("Image\\SystemButton\\minimize.png"));
		minimizationButton.setRolloverIcon(new ImageIcon("Image\\SystemButton\\minimize_hover.png"));
		minimizationButton.setPressedIcon(new ImageIcon("Image\\SystemButton\\minimize_press.png"));
		minimizationButton.setToolTipText("��С��");
		minimizationButton.setContentAreaFilled(false);
		minimizationButton.setFocusPainted(false);
		minimizationButton.setFocusable(false);
		minimizationButton.setBounds(311, 0, 30, 20);
		minimizationButton.addActionListener(actionAdapter);
		centerPanel.add(minimizationButton);
		
		settingsButton = new JButton(new ImageIcon("Image\\SystemButton\\btn_set_normal.png"));
		settingsButton.setRolloverIcon(new ImageIcon("Image\\SystemButton\\btn_set_hover.png"));
		settingsButton.setPressedIcon(new ImageIcon("Image\\SystemButton\\btn_set_press.png"));
		settingsButton.setToolTipText("����");
		settingsButton.setContentAreaFilled(false);
		settingsButton.setFocusPainted(false);
		settingsButton.setFocusable(false);
		settingsButton.setBounds(284, 0, 28, 20);
		settingsButton.addActionListener(actionAdapter);
		centerPanel.add(settingsButton);
		
		//��ѡ��:��ʽΪ�����������ð��£���ͣ��ѡ�У�ѡ�к������ͣ ͼ�ꡢ
		//�������塢����������ɫ������͸�������ò����ƽ��㡢���ô�Сλ�á���������
		rememberPassword = new JCheckBox("��ס����",
				new ImageIcon("Image\\client\\login's\\checkbox_normal.png"));
		rememberPassword.setPressedIcon(new ImageIcon("Image\\client\\login's\\checkbox_selected_hover.png"));
		rememberPassword.setRolloverIcon(new ImageIcon("Image\\client\\login's\\checkbox_press.png"));
		rememberPassword.setSelectedIcon(new ImageIcon("Image\\client\\login's\\checkbox_selected_normal.png"));
		rememberPassword.setRolloverSelectedIcon(new ImageIcon("Image\\client\\login's\\checkbox_selected_press.png"));
		rememberPassword.setFont(Fonts.MicrosoftAccor12);
		rememberPassword.setForeground(new Color(0, 27, 47));
		rememberPassword.setOpaque(false);
		rememberPassword.setFocusPainted(false);
		rememberPassword.setBounds(113, 216, 75 , 15);
		centerPanel.add(rememberPassword);
		
		automaticLogin = new JCheckBox("�Զ���¼",
				new ImageIcon("Image\\client\\login's\\checkbox_normal.png"));
		automaticLogin.setPressedIcon(new ImageIcon("Image\\client\\login's\\checkbox_selected_hover.png"));
		automaticLogin.setRolloverIcon(new ImageIcon("Image\\client\\login's\\checkbox_press.png"));
		automaticLogin.setSelectedIcon(new ImageIcon("Image\\client\\login's\\checkbox_selected_normal.png"));
		automaticLogin.setRolloverSelectedIcon(new ImageIcon("Image\\client\\login's\\checkbox_selected_press.png"));
		automaticLogin.setFont(Fonts.MicrosoftAccor12);
		automaticLogin.setForeground(new Color(0, 27, 47));
		automaticLogin.setOpaque(false);
		automaticLogin.setFocusPainted(false);
		automaticLogin.setBounds(193, 216, 75, 15);
		centerPanel.add(automaticLogin);
		
		//ע���˺�		//�һ����� : ������������ͣͼ�ꡢ���ò���ȡ���㡢����͸������Ӳ������������ô�Сλ�á���������
		registration = new JButton(new ImageIcon("Image\\client\\login's\\registration.png"));
		registration.setPressedIcon(new ImageIcon("Image\\client\\login's\\registration_hover.png"));
		registration.setRolloverIcon(new ImageIcon("Image\\client\\login's\\registration_hover.png"));
		registration.setContentAreaFilled(false);
		registration.setFocusPainted(false);
		registration.setBounds(314, 154, 51, 16);
		registration.addActionListener(actionAdapter);
		centerPanel.add(registration);

		find = new JButton(new ImageIcon("Image\\client\\login's\\find.png"));
		find.setPressedIcon(new ImageIcon("Image\\client\\login's\\find_hover.png"));
		find.setRolloverIcon(new ImageIcon("Image\\client\\login's\\find_hover.png"));
		find.setContentAreaFilled(false);
		find.setFocusPainted(false);
		find.setBounds(314, 188, 51, 16);
		find.addActionListener(actionAdapter);
		centerPanel.add(find);
		//��¼��ť ������������ͼ�ꡢ��ͣͼ�ꡢ����ȡ���㡢͸������Ӳ���������λ�ô�С����������
		loginButton = new JButton(new ImageIcon("Image\\client\\login's\\loginButton_1.png"));
		loginButton.setPressedIcon(new ImageIcon("Image\\client\\login's\\loginButton_3.png"));
		loginButton.setRolloverIcon(new ImageIcon("Image\\client\\login's\\loginButton_2.png"));
		loginButton.setContentAreaFilled(false);
		loginButton.setFocusPainted(false);
		loginButton.setBounds(115, 250, 160, 36);
		loginButton.addActionListener(actionAdapter);
		centerPanel.add(loginButton);
	}

	/*
	 * ��ťר�õļ�����
	 */
	private ActionListener actionAdapter = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == loginButton){
				
				System.out.println("Login");
				query();
				
			}else if(e.getSource() == find){
				
				System.out.println("�һ�����");	
				
			}else if(e.getSource() == registration){
				
				System.out.println("ע���˺�");	
				
			}else if(e.getSource() == closeButton){
				
				System.exit(0);
				
			}else if(e.getSource() == minimizationButton){
				
				System.out.println("��С��");
				frame.setExtendedState(JFrame.ICONIFIED);
				
			}
		}
	};	

	
	//��ʾ��
	JLabel tipLabel;
	JButton stretchButton;
	
	//������ʾ��
	private void createTip(){
		tipLabel = new JLabel(new ImageIcon("Image\\client\\login's\\!.png"),
				SwingConstants.LEFT){
			/**
			 * 
			 */
			private static final long serialVersionUID = 286192857212324985L;

			public void paintComponent(Graphics g){
				g.setColor(new Color(249, 245, 215));
				g.fillRect(0, 0, this.getWidth(), this.getHeight());
				super.paintComponent(g);
			}
		};
		tipLabel.setVerticalAlignment(SwingConstants .CENTER);
		tipLabel.setPreferredSize(new Dimension(380, 26));
		tipLabel.setFont(Fonts.MicrosoftAccor12);
		tipLabel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		stretchButton = new JButton(new ImageIcon("Image\\client\\login's\\stretchButton.png"));
		stretchButton.setRolloverIcon(new ImageIcon("Image\\client\\login's\\stretchButton_hover.png"));
		stretchButton.setPressedIcon(new ImageIcon("Image\\client\\login's\\stretchButton_hover.png"));
		stretchButton.setFocusPainted(false);
		stretchButton.setContentAreaFilled(false);
		stretchButton.setPreferredSize(new Dimension(14, 14));
		tipLabel.add(stretchButton);
		
		stretchButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				contraction();
			}			
		});
	}
	
	//����
	public void eJect(String tipText){
		
		tipLabel.setText(tipText);
		if(mainPanel.getComponentCount() == 1){
			frame.setSize(380, 318);
			AWTUtilities.setWindowShape(frame, new RoundRectangle2D.Double(0.0D, 0.0D, frame.getWidth(),  
					frame.getHeight(), 11.0D, 11.0D));
			
			tipLabel.setBounds(0, 292, 380, 26);
			mainPanel.add(tipLabel, "South");
		}
	}
	
	//����
	public void contraction(){
		frame.setSize(380, 292);
		mainPanel.remove(tipLabel);
	}
	
	
	//���µ�ǰ�˻�
	public void updateUser(){
		
		if(!(filluser.getSelectedItem() instanceof User)){
			return;
		}
		User user = (User) filluser.getSelectedItem();
		//���µ�ǰ�û�ͷ��
		headPanel.setIcon(ChangeImage.roundedCornerIcon(new ImageIcon(user.getHeadURL()),
	    		82,
	    		82,
	    		10));
		//����״̬��ť
		switch(user.getState()){
		//��������
		case ONLINE:
			stateButton.setIcon(onLine.getIcon());
			stateButton.setState(onLine.getState());
			break;
		//����Q�Ұ�
		case QME:
			stateButton.setIcon(qMe.getIcon());
			stateButton.setState(qMe.getState());
			break;
		//�����뿪
		case LEAVE:
			stateButton.setIcon(leave.getIcon());
			stateButton.setState(leave.getState());
			break;
		//����æµ
		case BEBUSY:
			stateButton.setIcon(beBusy.getIcon());
			stateButton.setState(beBusy.getState());
			break;
		//�����������
		case DONTDISTURB:
			stateButton.setIcon(dontDisturb.getIcon());
			stateButton.setState(dontDisturb.getState());
			break;
		//��������
		case INVISIBLE:
			stateButton.setIcon(invisible.getIcon());
			stateButton.setState(invisible.getState());
			break;
		}
		//�ж��Ƿ��Զ���¼
		if(user.isAutomaticLogin()){
			automaticLogin.setSelected(true);
		}else{
			automaticLogin.setSelected(false);
		}
		//�ж��Ƿ��ס���룬���� ��ѡ��״̬�����������״̬
		if(user.isRememberPassWord()){			
			rememberPassword.setSelected(true);
			fillcipher.setForeground(Color.black);
			fillcipher.setEchoChar('��');
			fillcipher.setText("123456789");
			fillcipherFocusAdapter.isDefaultText = false;
			deleteAll = true;
			isModify = false;
		}else{
			rememberPassword.setSelected(false);
			emptyFillcipher();
		}
	}
		
	/*
	 * ��¼��֤
	 */
	public void query(){

		String userNumber = edit.getText();
		char[] password = fillcipher.getPassword();
		//�ж��˻�/�����Ƿ�Ϊ�գ�Ϊ��ֱ�ӵ�ת�������˷�
		if(userNumber.isEmpty() || edit.isDefaultText()){
			System.out.println("�������˺�");
			filluser.requestFocus();
			eJect("���������˺ź��ٵ�¼");
			return;
		}
		if(password.length == 0 || fillcipherFocusAdapter.isDefaultText){
			System.out.println("����������");
			fillcipher.requestFocus();			
			eJect("��������������ٵ�¼");
			return;
		}
		
		StateEnum state = stateButton.getState();
		
		//ȡ�� �˺������ ��ǰ��������ж�
		Object ob = filluser.getSelectedItem();
		if(ob instanceof String){
			//˵��������������˻���ô���б�Ҫ��������һ���˺ż�¼
			//ֻ���ڷ������жϵ�¼�ɹ�ʱ�Ž���¼����д�뱣���ļ���ȥ
			User user = new User(userNumber,
					null,
					state,
					false,
					false);
			user.setPassword(new String(password));
			reaction(user);
			
		}else if(ob instanceof User){
			//˵������ԭ��¼�ϵ���ĵ�¼��ô��û�б�Ҫ���´���һ���˺ż�¼��
			//ֻ���ڷ������жϵ�¼�ɹ�ʱ�Ž���¼����д�뱣���ļ���ȥ
			User user = (User)ob;
			if(user.isRememberPassWord() && !isModify){
				user.setState(state);
				reaction(user);
				return;
			}
			user.setPassword(new String(password));
			user.setState(state);
			reaction(user);
		}
	}
	
	//���ӷ��������в�ѯ
	@SuppressWarnings("incomplete-switch")
	public void reaction(User user){
		
		VerificationUser verfivation = new VerificationUser(); 
		ParcelModel message = verfivation.askServer(user);
		
		switch(message.getMessage()){
		case USER_THROUGH:
			System.out.println("��¼�ɹ�");
			//��ʽ��¼
			UserInformation userInformation = ((UserThroughParcel)message).getUserInformation();
			UseView useView = new UseView(userInformation, verfivation.getSocket());
			//�޸��˻�״̬
			user.setAutomaticLogin(automaticLogin.isSelected());
			user.setRememberPassWord(rememberPassword.isSelected());
			//�ͷŸ����
			frame.dispose();
			break;
		case NOT_THROUGH:
			System.out.println("���˺Ų����ڻ������벻��ȷ������ȷ�Ϻ�����������");
			eJect("���˺Ų����ڻ������벻��ȷ������ȷ�Ϻ�����������");
			break;
		case LOGGED_IN:
			System.out.println("���ѵ�¼"+user.toString()+"�����ظ���¼");
			eJect("���ѵ�¼"+user.toString()+"�����ظ���¼");
			break;
		case CONNECTION_FAILED:
			System.out.println("���ӷ�����ʧ��");
			eJect("���ӷ�����ʧ�ܣ��������������Ƿ�������");
			break;
		case ERROR:
			System.out.println("�������޻�Ӧ");
			eJect("����������Ӧ,���Ժ�����");
			break;
		}
		System.out.println(message.getMessage());
	}
	
	//�����˻���¼
	private void preservationUser(){
		
	}
	
	//��������ϵ�ֵ	
	public void reSet(){
		//����ͷ��
		headPanel.setIcon(ChangeImage.roundedCornerIcon(Images.defaultHead,
	    		82,
	    		82,
	    		10));
		//���������
		emptyFillcipher();
		//���ø�ѡ��
		automaticLogin.setSelected(false);
		rememberPassword.setSelected(false);
		//����״̬��ť
		stateButton.setIcon(onLine.getIcon());
		stateButton.setState(onLine.getState());
	}
	
	//���ñ���	
	public void setBg(ImageIcon bg){		
		this.bg = bg;
	}
}

/*
 * ��Ԫ�������
 */
class IconRenderer extends JLabel implements ListCellRenderer {	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 7575789313364976435L;
	
	private Color background = new Color(51,137,201);
	
	public IconRenderer() {		
		setFont(new Font("΢���ź�", 0, 11));
		setPreferredSize(new Dimension(186, 46));
	}
	
	/*
	 * @version JComboBox �Ὣ��Ԫ������Ϊ ͸�������ǽ�������д��ֻ��Ϊ ��͸��
	 */
	public void setOpaque(boolean b) {
		super.setOpaque(true);
	}
	
	public Component getListCellRendererComponent(JList list,
			 Object obj,
			 int row,
			 boolean sel,
			 boolean hasFocus) {
		
	    User user = (User) obj;
	    setIcon(ChangeImage.roundedCornerIcon(new ImageIcon(user.getHeadURL()),
	    		42,
	    		42,
	    		40,
	    		40,
	    		1,
	    		1,
	    		5));//����ͼƬ
	    setText(user.toString());//�����ı�
	    
	    setBorder(new LineBorder(Color.white));//���Ʊ߿�
	    
	    if(sel) {//���ѡ��
	    	  
	        setForeground(Color.white);//��ǰ��ɫΪ��ɫ
	        setBackground(background);
	    }	else { //ûѡ��
	    
	        setForeground(Color.black);//��ǰ��ɫΪ��ɫ
	        setBackground(Color.white);
	    }
	    return this; 
	 }
}

//�˺������༭��
class UserFiledEdit implements ComboBoxEditor,FocusListener {  
	  
	protected JTextField editor = new JTextField();
    private Object item = "";
    private boolean first = true;
    private boolean isDefaultText = false;
    private boolean isAutomaticFilling = false;
    
	public UserFiledEdit (){  	
    	editor.addFocusListener(this);
    	editor.setBorder(new LineBorder(Color.white, 2));
    }
	
	//��ý���ʱ
	public void focusGained(FocusEvent e) {
		setAutomaticFilling(false);
		//����ý���ʱ�жϵ�ǰ�����Ƿ���Ĭ��ֵ������ȡ��Ĭ��ֵ��ǰ��ɫ
        if(isDefaultText){
        	isDefaultText = false;
        	editor.setForeground(Color.black);
        	editor.setText("");
        	return;
        }
        //����ǰ���ݲ���Ĭ��ֵʱ��ȫѡ����
        if(first){
            first = false;
            selectAll(); 
    	}
    }
	
    //ʧȥ����ʱ
    public void focusLost(FocusEvent e) {   	
    	setAutomaticFilling(true);
    	//��ǰ��ʾ�����ǿհ�ʱ�����Ĭ��ֵ����״̬����
    	if(editor.getText().isEmpty()){
        	isDefaultText = true;
        	editor.setForeground(Colors.greyColor);
        	editor.setText("�˺�");
        	return;
    	}    	
    	first = true;
    }
      
    public void selectAll() {	
    	editor.selectAll();
    }
        
    public Object getItem() {
        String text = editor.getText();
        if (!text.equals(item.toString())) {
            item = text;
        }
        return item;
    }
    
    public String getText(){
    	return editor.getText();
    }
        
    public void setText(String s){
    	editor.setText(s);
    }
       
    public void select(int selectionStart, int selectionEnd){
    	editor.select(selectionStart, selectionEnd);
    }
       
    public void setItem(final Object item) { 	
        this.item = item;
        if(item == null){
        	this.item = "�˺�";
        	editor.setForeground(Colors.greyColor);
        	isDefaultText = true;
        }
        editor.setText(this.item.toString());
    }
      
    public Component getEditorComponent() {
	  	return editor;
    }
        
    public boolean isDefaultText(){
    	return isDefaultText;
    }
        
    public void setState(boolean isDefaultText){
    	this.isDefaultText = isDefaultText;
    }
       
	public boolean isAutomaticFilling() {
		return isAutomaticFilling;
	}

	public void setAutomaticFilling(boolean isAutomaticFilling) {
		this.isAutomaticFilling = isAutomaticFilling;
	}

	public void addFocusListener(FocusListener l) {
		editor.addFocusListener(l);
	}	
    
    public void removeFocusListener(FocusListener l) {
		editor.removeFocusListener(l);
	}
       
	public void addKeyListener(KeyListener l) {
		editor.addKeyListener(l);
	}
	
	public void removeKeyListener(KeyListener l) {
		editor.removeKeyListener(l);
	}
	
	public void addMouseListener(MouseListener mouseAdapter) {
		editor.addMouseListener(mouseAdapter);
	}
		
	public void removeMouseListener(MouseListener mouseAdapter) {
		editor.removeMouseListener(mouseAdapter);
	}
	
	public void addActionListener(final ActionListener l) {
        editor.addActionListener(l);
    }
	
    public void removeActionListener(final ActionListener l) {
        editor.removeActionListener(l);
    }
    
	public void addMouseWheelListener(MouseWheelListener l) {
		editor.addMouseWheelListener(l);		
	}
		
	public void removeMouseWheelListener(MouseWheelListener l) {
		editor.removeMouseWheelListener(l);		
	}
	    
    public void addDocumentListener(DocumentListener l){
    	editor.getDocument().addDocumentListener(l);
    }
       
    public void removeDocumentListener(DocumentListener l) {
        editor.getDocument().removeDocumentListener(l);
    }
}
