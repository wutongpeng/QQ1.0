package sdk.qq.client.view;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import sdk.qq.client.kastem.assembly.*;
import sdk.qq.general.FriendItemModel;
import sdk.qq.general.GroupModel;
import sdk.qq.general.UserInformation;
import sdk.qq.general.parcel.NewsParcel;
import sdk.qq.general.parcel.ParcelModel;
import sdk.qq.tools.ChangeImage;
import sdk.qq.tools.Colors;
import sdk.qq.tools.Fonts;
import sdk.qq.tools.VerlicelColumn;

public class UseView {
	
	public static UserInformation user;
	public UseView(UserInformation user, Socket socket){
		UseView.user = user;
		this.socket = socket;
		createFrame();
		new Receive().start();
	}
	
	//����,
	private FillitFrame frame;
	//�������汳���������������
	private ImageIcon bg;
	private MainPanel mainPanel;
	//����
	public void createFrame(){
		//����
		frame = new FillitFrame(280, 678, 7, 7);
		
		//�в����
		bg = new ImageIcon("Image\\use\\δ����-2.jpg");
		mainPanel = new MainPanel(bg, 7, 7);
		mainPanel.setLayout(new BorderLayout());
		frame.add(mainPanel);
		
		//����ͷ��
		createTop();
		//�����в�
		createCenter();
		//�����ײ�
		createSouth();
		
		frame.setVisible(true);
	}
	
	//ͷ��
	private JPanel forTop;

	//����ͷ��
	private void createTop(){
		forTop = new JPanel(new BorderLayout());
		forTop.setOpaque(false);
		mainPanel.add(forTop, "North");
		
		//������:�������ϵͳ��ť
		createTitle();
		//ͷ������
		createHeadDistrict();
		//������
		createSearchBar();
	}
	
	//�ر�����С����ť
	private JPanel forSystemButton;
	private JButton closeButton;
	private JButton minimizationButton;
	//���������
	private JLabel title;
	//����
	private void createTitle(){		
		JPanel pane2 = new JPanel(new BorderLayout());
		pane2.setPreferredSize(new Dimension(pane2.getPreferredSize().width, 30));
		pane2.setOpaque(false);
		forTop.add(pane2, "North");
		//�����
		JPanel pane3 = new JPanel(new FlowLayout(FlowLayout.LEFT, 7, 0));
		pane3.setOpaque(false);
		pane2.add(pane3);
		title = new JLabel("warau");
		title.setFont(new Font("΢���ź�", 0, 18));
		pane3.add(title);
		
		//ϵͳ��ť
		forSystemButton = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
		forSystemButton.setOpaque(false);
		assembly();
		pane2.add(forSystemButton, "East");
	}
	/*
	 * ���ɽ����ϰ���
	 */
	private void assembly(){
		//˳�򣺹��������þ���/���ͼ��������ʾ����Ӳ�����������������
		
		minimizationButton = new JButton(new ImageIcon("Image\\SystemButton\\minimize.png"));
		minimizationButton.setRolloverIcon(new ImageIcon("Image\\SystemButton\\minimize_hover.png"));
		minimizationButton.setPressedIcon(new ImageIcon("Image\\SystemButton\\minimize_press.png"));
		minimizationButton.setToolTipText("��С��");
		minimizationButton.setContentAreaFilled(false);
		minimizationButton.setFocusPainted(false);
		minimizationButton.setFocusable(false);
		minimizationButton.setPreferredSize(new Dimension(30, 20));
		minimizationButton.addActionListener(actionAdapter);
		forSystemButton.add(minimizationButton);
		
		closeButton = new JButton(new ImageIcon("Image\\SystemButton\\clouse.png"));
		closeButton.setRolloverIcon(new ImageIcon("Image\\SystemButton\\clouse_hover.png"));
		closeButton.setPressedIcon(new ImageIcon("Image\\SystemButton\\clouse_press.png"));
		closeButton.setToolTipText("�ر�");
		closeButton.setContentAreaFilled(false);
		closeButton.setFocusPainted(false);
		closeButton.setFocusable(false);
		closeButton.setPreferredSize(new Dimension(40, 20));
		closeButton.addActionListener(actionAdapter);
		forSystemButton.add(closeButton);
	}	
	/*
	 * ��ťר�õļ�����
	 */
	private ActionListener actionAdapter = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == closeButton){				
				System.exit(0);				
			}else if(e.getSource() == minimizationButton){				
				System.out.println("��С��");
				frame.setExtendedState(JFrame.ICONIFIED);			
			}
		}
	};
	
	//ͷ��
	private HeadPanel headPanel;
	//״̬ѡ��ť
	private SenioButton stateButton;
	//��ʾ״̬��ť�ϵ�ͼ��
	private JLabel forStateIcon;
	//�ǳ�
	private JLabel nickName;
	//�ȼ�
	private SenioButton lv;	
	//����ǩ�������
	private JTextField signatureField;
	private boolean defaultSignature = true;
	//�鿴�ռ䰴ť
	private SenioButton seeZone;
	//���䰴ť
	private SenioButton mailBox;
	//��Ϣ��
	private SenioButton messageBox;
	//����
	private void createHeadDistrict(){
		//ͷ������
		JPanel pane4 = new JPanel(new BorderLayout());
		pane4.setOpaque(false);
		pane4.setPreferredSize(new Dimension(pane4.getPreferredSize().width, 66));
		forTop.add(pane4);
		//ͷ��
		JPanel pane11 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
		pane11.setOpaque(false);
		pane4.add(pane11, "West");
		headPanel = new HeadPanel();
		headPanel.setHeadBorder(new ImageIcon("Image\\use\\Padding4Normal.png"));
		headPanel.setPreferredSize(new Dimension(66, 66));
		headPanel.setHorizontalAlignment(SwingConstants.CENTER);
		headPanel.setIcon(ChangeImage.roundedCornerIcon(new ImageIcon(user.getUser().getHeadURL()),
				62,
				62,
	    		7));
		pane11.add(headPanel, "West");
		
		//����ͷ��֮������Ĳ���
		JPanel pane5 = new JPanel(new BorderLayout());
		pane5.setOpaque(false);
		pane4.add(pane5, "Center");
		//״̬��ť
		JPanel pane6 = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		pane6.setOpaque(false);
		pane5.add(pane6, "North");		

		stateButton = new SenioButton();
		stateButton.setPreferredSize(new Dimension(36, 18));
		stateButton.setRoundSize(4);
		stateButton.setLayout(new GridLayout(1, 2));
		forStateIcon = new JLabel(new ImageIcon("Image\\Status\\FLAG\\Big\\invisible.png"));
		stateButton.add(forStateIcon);
		JLabel arrow = new JLabel(new ImageIcon("Image\\use\\arrow_down.png"));
		stateButton.add(arrow);
		pane6.add(stateButton);
		//�ǳ�
		nickName = new JLabel(user.getNickName());
		nickName.setFont(new Font("΢���ź�", 0, 15));
		nickName.setBounds(20, 34, 100, 15);
		pane6.add(nickName);
		//�ȼ�
		lv = new SenioButton(new ImageIcon("Image\\use\\LV\\9.png"));
		pane6.add(lv);
		
		//����ǩ�������
		JPanel pane12 = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 1));
		pane12.setOpaque(false);
		pane5.add(pane12, "Center");
		String content = user.getSignature();
		if(content == null){
			signatureField = new JTextField("�༭����ǩ��");
		}else{
			signatureField = new JTextField(content);
		}
		signatureField.setOpaque(false);
		signatureField.setBorder(null);
		signatureField.setPreferredSize(new Dimension(130, 22));
		pane12.add(signatureField, "Center");
		//�����߿��ʵ��
		signatureField.addMouseListener(new MouseAdapter(){
			private final LineBorder border = new LineBorder(Colors.greyColor);
			public void mouseEntered(MouseEvent e){
				signatureField.setBorder(border);
			}
			public void mouseExited(MouseEvent e){
				signatureField.setBorder(null);
			}
		});
		//Ĭ����ʾ��ʵ��
		signatureField.addFocusListener(new FocusListener(){			
			public void focusGained(FocusEvent e) {
				if(defaultSignature){
					signatureField.setText("");
				}
			}
			public void focusLost(FocusEvent e) {
				if(signatureField.getText().isEmpty()){
					signatureField.setText("�༭����ǩ��");
				}
			}
		});
		//
		JPanel pane7 = new JPanel(new BorderLayout());
		pane7.setOpaque(false);
		pane7.setPreferredSize(new Dimension(pane7.getPreferredSize().width, 22));
		pane5.add(pane7, "South");
		JPanel pane9 = new JPanel(new FlowLayout(FlowLayout.LEFT, 1, 1));
		pane9.setOpaque(false);
		pane7.add(pane9, "Center");
		//�ռ䰴ť
		seeZone = new SenioButton(new ImageIcon("Image\\AppPluginIcon\\qzoneicon.png"));
		seeZone.setPreferredSize(new Dimension(seeZone.getPreferredSize().width+4, 20));
		pane9.add(seeZone);
		//����
		mailBox = new SenioButton("27", 
				new ImageIcon("Image\\AppPluginIcon\\ContactTipsVASFlagExt_Mail.png"),
				SwingConstants.CENTER);
		mailBox.setIconTextGap(0);
		mailBox.setFont(Fonts.MicrosoftAccor12);
		mailBox.setToolTipText("������"+" �ռ���"+" Ư��ƿ");
		mailBox.setPreferredSize(new Dimension(mailBox.getPreferredSize().width+4, 20));
		pane9.add(mailBox);
		
		//��Ϣ��
		JPanel pane10 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 3, 1));
		pane10.setOpaque(false);
		pane7.add(pane10, "East");
		messageBox = new SenioButton(new ImageIcon("Image\\AppPluginIcon\\tips_16.png"));
		messageBox.setPreferredSize(new Dimension(messageBox.getPreferredSize().width+4, 20));
		messageBox.setToolTipText("����Ϣ��");
		pane10.add(messageBox);
	}
	
	//������
	private JLabel searchBar;
	private JTextField searchField;
	private JButton auxiliaryButton;
	private boolean swiching = true;
	//����
	public void createSearchBar(){
		JPanel pane13 = new JPanel(new BorderLayout());
		pane13.setOpaque(false);
		pane13.setBorder(BorderFactory.createMatteBorder(0,
				1,
				0,
				1,
				Color.black));
		forTop.add(pane13, "South");
		
		JPanel pane14 = new JPanel();
		pane14.setOpaque(false);
		pane13.add(pane14);
		searchBar = new JLabel(" ��������ϵ�ˡ������顢Ⱥ����ҵ"){
			/**
			 * 
			 */
			private static final long serialVersionUID = -9012114410749053341L;
			public void paintComponent(Graphics g){
				g.setColor(new Color(255, 255, 255, 110));
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		searchBar.setBorder(BorderFactory.createMatteBorder(1,
				0,
				1,
				0,
				new Color(204, 217, 203)));
		searchBar.setOpaque(false);
		searchBar.setLayout(new BorderLayout());
		searchBar.setPreferredSize(new Dimension(searchBar.getPreferredSize().width, 30));
		searchBar.setBackground(Color.white);
		searchBar.setForeground(Colors.greyColor);
		pane13.add(searchBar, "South");
		//�����
		searchField = new JTextField();
		searchField.setOpaque(false);
		searchField.setBorder(BorderFactory.createEmptyBorder(0,
				4,
				0,
				0));
		searchBar.add(searchField);
		searchField.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(swiching){
					swiching = false;
					searchBar.setText("");
					auxiliaryButton.setEnabled(true);
					searchBar.setOpaque(true);
				}
			}
		});
		searchField.getDocument().addDocumentListener(new DocumentListener(){
			public void insertUpdate(DocumentEvent e) {
				if(swiching){
					swiching = false;
					searchBar.setText("");
					auxiliaryButton.setEnabled(true);
					searchBar.setOpaque(true);
				}
			}
			public void removeUpdate(DocumentEvent e) {
			}
			public void changedUpdate(DocumentEvent e) {
			}
		});
		//�رհ�ť
		auxiliaryButton = new JButton(new ImageIcon("Image\\use\\search.png"));
		auxiliaryButton.setDisabledIcon(new ImageIcon("Image\\use\\search_Disable.png"));
		auxiliaryButton.setRolloverIcon(new ImageIcon("Image\\use\\search_Hover.png"));
		auxiliaryButton.setEnabled(false);
		auxiliaryButton.setContentAreaFilled(false);
		auxiliaryButton.setFocusPainted(false);
		searchBar.add(auxiliaryButton, "East");
		//��Ӽ���
		auxiliaryButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				auxiliaryButton.setEnabled(false);
				swiching = true;
				searchField.setText("");
				searchBar.setText(" ��������ϵ�ˡ������顢Ⱥ����ҵ");
				searchBar.setOpaque(false);
			}			
		});
	}
	
	
	//�в�
	private JPanel forCenter;
	//����
	private void createCenter(){
		forCenter = new JPanel(new BorderLayout());
		forCenter.setOpaque(false);
		mainPanel.add(forCenter);
		//ѡ���
		crateTabs();
		//�������
		createFriendPanel();
	}
	//ѡ���
	private JPanel forTab;
	private Tab friendsTab;
	private Tab groupTab;
	private Tab conversationTab;
	//����
	private void crateTabs(){
		forTab = new JPanel(new GridLayout(1, 3, 1, 0));
		forTab.setOpaque(false);
		forTab.setPreferredSize(new Dimension(forTab.getPreferredSize().width, 34));
		forCenter.add(forTab, "North");
		
		ButtonGroup buttonGroup = new ButtonGroup();
		//��ϵ��
		friendsTab = new Tab(new ImageIcon("Image\\use\\icon_contacts_normal.png"), true);
		friendsTab.setSelectedIcon(new ImageIcon("Image\\use\\icon_contacts_selected.png"));
		friendsTab.setToolTipText("��ϵ��");
		forTab.add(friendsTab);
		buttonGroup.add(friendsTab);
		//Ⱥ/������
		groupTab = new Tab(new ImageIcon("Image\\use\\icon_group_normal.png"));
		groupTab.setSelectedIcon(new ImageIcon("Image\\use\\icon_group_selected.png"));
		groupTab.setToolTipText("Ⱥ/������");
		forTab.add(groupTab);
		buttonGroup.add(groupTab);
		//�Ự
		conversationTab = new Tab(new ImageIcon("Image\\use\\icon_last_normal.png"));
		conversationTab.setSelectedIcon(new ImageIcon("Image\\use\\icon_last_selected.png"));
		conversationTab.setToolTipText("�Ự");
		forTab.add(conversationTab);
		buttonGroup.add(conversationTab);
	}	
	//�������
	private CardLayout cardLayout;
	private JPanel forCard;
	private JScrollPane scrollPane;
	//�������
	private JPanel friendPanel;
	//����
	private void createFriendPanel(){
		cardLayout = new CardLayout();
		forCard = new JPanel(cardLayout);
		forCard.setOpaque(false);
		scrollPane = new JScrollPane(forCard,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.setBorder(BorderFactory.createMatteBorder(0,
				1,
				0,
				1,
				Color.black));
		forCenter.add(scrollPane, "Center");
		
		friendPanel = new JPanel(new VerlicelColumn(5)){
			/**
			 * 
			 */
			private static final long serialVersionUID = 2549744010310864813L;
			public void paintComponent(Graphics g){
				g.setColor(new Color(255,255,255,235));
				g.fillRect(0, 0, getWidth(), getHeight());
				super.paintComponent(g);
			}
		};
		friendPanel.setOpaque(false);
		forCard.add(friendPanel);
		
		ArrayList<GroupModel> groups = user.getFriendList();
		for(GroupModel group : groups){
			GroupContainer gropContainer = createGroupNode(group.getGroupName());
			int length = group.size();
			for(int i=0; i<length; i++){
				FriendItemModel friendModel = group.get(i);	
				FriendItem friend = new FriendItem(friendModel);
				gropContainer.addMember(friend);
				friend.addMouseListener(friendItemMouseAdapter);
			}
			friendPanel.add(gropContainer);
		}
	}
	
	//Ū�ýڵ㣬֮����GroupLayout
	private final static ImageIcon arrow = new ImageIcon("Image\\use\\MainPanel_FolderNode_collapseTexture.png");
	private final static ImageIcon arrowHover = new ImageIcon("Image\\use\\MainPanel_FolderNode_collapseTextureHighlight.png");
	private final static ImageIcon arrowSelected = new ImageIcon("Image\\use\\MainPanel_FolderNode_expandTexture.png");
	private final static ImageIcon arrowSelectedHover = new ImageIcon("Image\\use\\MainPanel_FolderNode_expandTextureHighlight.png");
	private final static ImageIcon[] arrows = new ImageIcon[]{
		    arrowHover,
			new ImageIcon("Image\\use\\15.png"),
			new ImageIcon("Image\\use\\30.png"),
			 new ImageIcon("Image\\use\\45.png"),
			 new ImageIcon("Image\\use\\60.png"),
			 new ImageIcon("Image\\use\\75.png"),
			 arrowSelectedHover
	};	
	//��ӽڵ�
	public GroupContainer createGroupNode(String name){
		final JToggleButton node = new JToggleButton(name,
				arrow){
			/**
			 * 
			 */
			private static final long serialVersionUID = 392923895832778450L;
			private ButtonModel buttonModel = getModel();
			private final Color startColor = new Color(255,255,255,10);
			private final Color endColor = new Color(49,143,197,35);
			public void paintComponent(Graphics g){
				if(buttonModel.isRollover() && !isFocusOwner()){
					  int width = getWidth();
					  int height = getHeight();
					Graphics2D g2d = (Graphics2D)g;
				    g2d.setPaint(new GradientPaint(0, 0, startColor, 0, height, endColor));
					g2d.fillRect(0, 0, width, height);
				}
				super.paintComponent(g);
			}
		};
		node.setRolloverIcon(arrowHover);
		
		node.setContentAreaFilled(false);
		node.setOpaque(false);
		node.setFocusPainted(false);
		node.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));	
		node.setPreferredSize(new Dimension(0, 25));
		node.setHorizontalAlignment(SwingConstants.LEFT);
		node.addActionListener(nodeActionListener);
		node.addKeyListener(nodeKeyAdapter);
		node.addFocusListener(nodeFocusListener);
		
		return new GroupContainer(node);
	}		
	//�ڵ�ĵ��������
	private ActionListener nodeActionListener = new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			JToggleButton pressButton = (JToggleButton) e.getSource();
			pressButton.setRolloverIcon(null);
			if(pressButton.getModel().isSelected()){
				openEffect(pressButton);
			}else{
				closeEffect(pressButton);
			}
		}
	};
	//�ڵ�ENTER���ļ�����
	private KeyAdapter nodeKeyAdapter = new KeyAdapter(){
		public void keyPressed(KeyEvent e){
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				JToggleButton pressButton = (JToggleButton) e.getSource();
				pressButton.setRolloverIcon(null);
				if(pressButton.getModel().isSelected()){
					closeEffect(pressButton);
					pressButton.setSelected(false);
				}else{
					openEffect(pressButton);
					pressButton.setSelected(true);
				}
			}
		}
	};
	//����չ��Ч��
	private void openEffect(final JToggleButton pressButton){
		GroupContainer gropContainer = (GroupContainer) pressButton.getParent();
		gropContainer.expand();
		Thread t = new Thread(){
			public void run(){
				for(ImageIcon img: arrows){
					pressButton.setIcon(img);
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		t.start();	
	}
	//�ر�Ч��
	private void closeEffect(final JToggleButton pressButton){
		GroupContainer gropContainer = (GroupContainer) pressButton.getParent();
		gropContainer.collapse();
		Thread t = new Thread(){
			public void run(){
				ImageIcon img;
				int length = arrows.length;
				for(int i=length-1; i>=0;i--){
					img = arrows[i];
					pressButton.setIcon(img);
					try {
						Thread.sleep(15);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		t.start();	
	}
	//�ڵ�Ľ��������
	private FocusListener nodeFocusListener = new FocusListener(){
		public void focusGained(FocusEvent e) {}
		public void focusLost(FocusEvent e){
			final JToggleButton pressButton = (JToggleButton) e.getSource();
			if(pressButton.getModel().isSelected()){
				Thread t = new Thread(){
					public void run(){
						pressButton.setSelectedIcon(arrowSelected);
						pressButton.setRolloverSelectedIcon(arrowSelectedHover);
					}
				};
				t.start();
			}else{
				Thread t = new Thread(){
					public void run(){
						pressButton.setIcon(arrow);
						pressButton.setRolloverIcon(arrowHover);
					}
				};
				t.start();
			}				
		}
	};
	
	//�����������
	private static HashMap<String, ChatPanel> chatPanels = new HashMap<String, ChatPanel>();
	//˫�������������
	private MouseAdapter friendItemMouseAdapter = new MouseAdapter(){
		public void mouseClicked(MouseEvent e){
			if(e.getClickCount() == 2){
				final FriendItem selectedItem = (FriendItem) e.getSource();				
				Thread t = new Thread(){
					public void run(){
						ChatPanel chetPanel = chatPanels.get(selectedItem.getNo());
						//�ж��Ƿ��Ѿ��и����
						if(chetPanel==null){
							chetPanel = new ChatPanel(selectedItem.getModel(), socket);
							chatPanels.put(selectedItem.getNo(), chetPanel);
						}else{
							chetPanel.aa();
						}
					}
				};
				t.start();
			}
		}		 
	};
	//�ṩɾ�����ڵľ�̬����
	public static void remove(String key){
		chatPanels.remove(key);
	}
	
	//�ײ�
	private JPanel forSouth;
	//soso
	private SenioButton soso;
	//���Թܼ�
	private SenioButton housekeeper;
	//Ӧ�ù���
	private SenioButton appManagement;
	
	//���˵�
	private SenioButton mainMenu;
	//ϵͳ����
	private SenioButton systemSettings;
	//��Ϣ������
	private SenioButton messageManager;
	//Ӧ�ñ�
	private SenioButton application;
	//����
	private void createSouth(){
		forSouth = new JPanel(new GridLayout(2, 1));
		forSouth.setBackground(new Color(255,255,255,240));
		forSouth.setPreferredSize(new Dimension(forSouth.getPreferredSize().width, 60));
		forSouth.setBorder(BorderFactory.createMatteBorder(1,
				0,
				0,
				0,
				new Color(0,0,0,50)));
		mainPanel.add(forSouth, "South");
		
		//��һ��
		JPanel pane15 = new JPanel(new BorderLayout());
		pane15.setOpaque(false);
		forSouth.add(pane15);
		JPanel pane16 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pane16.setOpaque(false);
		pane15.add(pane16);
		
		soso = new SenioButton(new ImageIcon("Image\\use\\SoSo.png"));
		soso.setPreferredSize(new Dimension(22, 22));
		pane16.add(soso);
		
		housekeeper = new SenioButton(new ImageIcon("Image\\use\\safe.png"));
		housekeeper.setPreferredSize(new Dimension(22, 22));
		pane16.add(housekeeper);
		//���ֵ�һ�����ұ�
		JPanel pane17 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pane17.setOpaque(false);
		pane15.add(pane17, "East");
		
		appManagement = new SenioButton(new ImageIcon("Image\\use\\error.png"));
		appManagement.setPreferredSize(new Dimension(22, 22));
		pane17.add(appManagement);
		
		//�ڶ���
		JPanel pane18 = new JPanel(new BorderLayout());
		pane18.setOpaque(false);
		forSouth.add(pane18);
		JPanel pane19 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pane19.setOpaque(false);
		pane18.add(pane19);
		
		mainMenu = new SenioButton(new ImageIcon("Image\\use\\menu_btn_highlight.png"));
		mainMenu.setPreferredSize(new Dimension(22, 22));
		mainMenu.addMouseListener(new MouseAdapter(){
			private final ImageIcon hoverIcon = new ImageIcon("Image\\use\\menu_btn_normal.png");
			private final ImageIcon icon = (ImageIcon) mainMenu.getIcon();
			public void mouseEntered(MouseEvent e){
				mainMenu.setIcon(hoverIcon);
			}
			public void mouseExited(MouseEvent e){
				mainMenu.setIcon(icon);
			}
		});
		pane19.add(mainMenu);
		
		systemSettings = new SenioButton(new ImageIcon("Image\\use\\Tools.png"));
		systemSettings.setPreferredSize(new Dimension(22, 22));
		pane19.add(systemSettings);
		
		messageManager = new SenioButton(new ImageIcon("Image\\use\\message.png"));
		messageManager.setPreferredSize(new Dimension(22, 22));
		pane19.add(messageManager); 
		//���ֵڶ������ұ�
		JPanel pane20 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		pane20.setOpaque(false);
		pane18.add(pane20, "East");
		
		application = new SenioButton("Ӧ�ñ�", 
				new ImageIcon("Image\\use\\TencentNet.png"),
				SwingConstants.LEFT);
		application.setPreferredSize(new Dimension(58, 22));
		application.setIconTextGap(0);
		pane20.add(application);  
	}

	//�������ñ�����
	public void setBg(){
		//**** ֻ��Ҫ���� Bg��ֵ�Ϳ��Դ�ɸ��ı�����Ч��
	}

	//��������������Ӷ�ȡ��������������Ϣ
	private Socket socket;
	private ObjectInputStream objectInputStream;
	
	class Receive extends Thread{
		public void run(){
			ParcelModel parcel;
			while(true){
				try {
					objectInputStream = new ObjectInputStream(socket.getInputStream());
					try {
						parcel = (ParcelModel)objectInputStream.readObject();
						switch(parcel.getMessage()){
						case NEWS:
							NewsParcel newsParcel = (NewsParcel) parcel;
							String sender = newsParcel.getSender();
							ChatPanel chatPanel = chatPanels.get(sender);
							//���û�и�����򴴽�һ��
							if(chatPanel == null){
								ArrayList<GroupModel> groups = user.getFriendList();
								for(GroupModel group : groups){
									int length = group.size();
									for(int i=0; i<length; i++){
										FriendItemModel friendModel = group.get(i);	
										if(friendModel.getNO().equals(sender)){
											chatPanel = new ChatPanel(friendModel, socket);
											chatPanels.put(friendModel.getNO(), chatPanel);										
										}
									}
								}
							}
							//�����в�������
							chatPanel.insertString(newsParcel.getSenderInformation(), null);
							chatPanel.insertString((String)newsParcel.getContent(), null);
							break;
						default:
							break;
						}
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//�ر���Դ
	public void close(){
		try {
			if(objectInputStream!=null){
				objectInputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
