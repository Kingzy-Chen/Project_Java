package frame;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.*;
import common.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;

	private JMenu UserManager_Menu;
	private JMenu FileManager_Menu;
	private JMenu SelfInfo_Menu;
	private JMenu Others_Menu;

	private JButton AddUser_Button;
	private JButton DelUser_Button;
	private JButton UpdateUser_Button;
	private JButton UploadFile_Button;
	private JButton DownloadFile_Button;
	private JButton ChangeSelfInfo_Button;
	private JButton Exit_Button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					// ���������������ݿ�
					try {
						SQLconnection.Connect();
					} catch (ClassNotFoundException | SQLException e) {
						JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
					}
					
					// ��������Ĭ�Ϲ�����
					MainFrame frame = new MainFrame(new Administrator("kate", "123", "Administrator"));
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame(User user) {
		// �����ɫ����
		// ���
		setResizable(false);
		// ���ݽ�ɫ���ñ���
		SetTitle(user.getRole());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1171, 699);

		// �м�����
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// �˵���
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1165, 33);
		contentPane.add(menuBar);

		// �û����������˵�
		UserManager_Menu = new JMenu("\u7528\u6237\u7BA1\u7406");
		UserManager_Menu.setFont(new Font("����", Font.PLAIN, 18));
		menuBar.add(UserManager_Menu);

		// �����û���ť
		AddUser_Button = new JButton("\u589E\u6DFB\u7528\u6237");
		AddUser_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �����û��¼�
				AddUserActionPerformed(user, e);
			}
		});
		AddUser_Button.setFont(new Font("����", Font.PLAIN, 16));
		UserManager_Menu.add(AddUser_Button);

		// ɾ���û���ť
		DelUser_Button = new JButton("\u5220\u9664\u7528\u6237");
		DelUser_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ɾ���û��¼�
				DelUserActionPerformed(user, e);
			}
		});
		DelUser_Button.setFont(new Font("����", Font.PLAIN, 16));
		UserManager_Menu.add(DelUser_Button);

		// �޸��û���ť
		UpdateUser_Button = new JButton("\u4FEE\u6539\u7528\u6237");
		UpdateUser_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �޸��û��¼�
				UpdateUserActionPerformed(user, e);
			}
		});
		UpdateUser_Button.setFont(new Font("����", Font.PLAIN, 16));
		UserManager_Menu.add(UpdateUser_Button);

		// �������������˵�
		FileManager_Menu = new JMenu("\u6863\u6848\u7BA1\u7406");
		FileManager_Menu.setFont(new Font("����", Font.PLAIN, 18));
		menuBar.add(FileManager_Menu);

		// �ϴ��ļ���ť
		UploadFile_Button = new JButton("\u4E0A\u4F20\u6587\u4EF6");
		UploadFile_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �ϴ��ļ��¼�
				UploadFileActionPerformed(user, e);
			}
		});
		UploadFile_Button.setFont(new Font("����", Font.PLAIN, 16));
		FileManager_Menu.add(UploadFile_Button);

		// �����ļ���ť
		DownloadFile_Button = new JButton("\u4E0B\u8F7D\u6587\u4EF6");
		DownloadFile_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �����ļ��¼�
				DownloadFileActionPerformed(user, e);
			}
		});
		DownloadFile_Button.setFont(new Font("����", Font.PLAIN, 16));
		FileManager_Menu.add(DownloadFile_Button);

		// ������Ϣ���������˵�
		SelfInfo_Menu = new JMenu("\u4E2A\u4EBA\u4FE1\u606F\u7BA1\u7406");
		SelfInfo_Menu.setFont(new Font("����", Font.PLAIN, 18));
		menuBar.add(SelfInfo_Menu);

		// �޸����밴ť
		ChangeSelfInfo_Button = new JButton("\u5BC6\u7801\u4FEE\u6539");
		ChangeSelfInfo_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �޸������¼�
				ChangeSelfActionPerformed(user, e);
			}
		});
		ChangeSelfInfo_Button.setFont(new Font("����", Font.PLAIN, 16));
		SelfInfo_Menu.add(ChangeSelfInfo_Button);

		// �����������˵�
		Others_Menu = new JMenu("\u5176\u4ED6");
		Others_Menu.setFont(new Font("����", Font.PLAIN, 18));
		menuBar.add(Others_Menu);

		// �˳���ť
		Exit_Button = new JButton("\u9000\u51FA");
		Exit_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �˳��¼�
				ExitActionPerformed(e);
			}
		});
		Exit_Button.setFont(new Font("����", Font.PLAIN, 16));
		Others_Menu.add(Exit_Button);

		// ���ø���ťȨ��
		SetRights(user.getRole());
	}

	// �����û�
	private void AddUserActionPerformed(User user, ActionEvent evt) {
		// ѡ���� 0
		UserFrame userframe = new UserFrame(user, 0);
		userframe.setVisible(true);
	}

	// ɾ���û�
	private void DelUserActionPerformed(User user, ActionEvent evt) {
		// ѡ���� 1
		UserFrame userframe = new UserFrame(user, 1);
		userframe.setVisible(true);
	}

	// �޸��û�
	private void UpdateUserActionPerformed(User user, ActionEvent evt) {
		// ѡ���� 2
		UserFrame userframe = new UserFrame(user, 2);
		userframe.setVisible(true);
	}

	// �ϴ��ļ�
	private void UploadFileActionPerformed(User user, ActionEvent evt) {
		// ѡ����0
		FileFrame fileframe = new FileFrame(user, 0);
		fileframe.setVisible(true);
	}

	// �����ļ�
	private void DownloadFileActionPerformed(User user, ActionEvent evt) {
		// ѡ����1
		FileFrame fileframe = new FileFrame(user, 1);
		fileframe.setVisible(true);
	}

	// �޸�����
	private void ChangeSelfActionPerformed(User user, ActionEvent evt) {
		SelfInfoFrame selfframe = new SelfInfoFrame(user);
		selfframe.setVisible(true);
	}

	// ���ñ���
	private void SetTitle(String role) {
		if (role.equalsIgnoreCase("administrator")) {
			setTitle("��������Ա����");
		} else if (role.equalsIgnoreCase("browser")) {
			setTitle("�������Ա����");
		} else if (role.equalsIgnoreCase("operator")) {
			setTitle("����¼��Ա����");
		}
	}

	// �����û�Ȩ��
	private void SetRights(String role) {

		if (role.equalsIgnoreCase("administrator")) {

			AddUser_Button.setEnabled(true);
			DelUser_Button.setEnabled(true);
			UpdateUser_Button.setEnabled(true);
			DownloadFile_Button.setEnabled(true);
			UploadFile_Button.setEnabled(false);
			ChangeSelfInfo_Button.setEnabled(true);
			Exit_Button.setEnabled(true);

		} else if (role.equalsIgnoreCase("browser")) {

			AddUser_Button.setEnabled(false);
			DelUser_Button.setEnabled(false);
			UpdateUser_Button.setEnabled(false);
			DownloadFile_Button.setEnabled(true);
			UploadFile_Button.setEnabled(false);
			ChangeSelfInfo_Button.setEnabled(true);
			Exit_Button.setEnabled(true);

		} else if (role.equalsIgnoreCase("operator")) {

			AddUser_Button.setEnabled(false);
			DelUser_Button.setEnabled(false);
			UpdateUser_Button.setEnabled(false);
			DownloadFile_Button.setEnabled(true);
			UploadFile_Button.setEnabled(true);
			ChangeSelfInfo_Button.setEnabled(true);
			Exit_Button.setEnabled(true);
		}
	}

	// �˳�
	private void ExitActionPerformed(ActionEvent evt) {

		// �Ͽ����ݿ�����
		try {
			SQLconnection.Disconnect();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		}

		this.dispose();
		LoginFrame loginframe = new LoginFrame();
		loginframe.setVisible(true);
	}

}