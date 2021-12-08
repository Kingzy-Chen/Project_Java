package frame;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.Enumeration;
import common.*;

@SuppressWarnings("serial")
public class UserFrame extends JFrame {

	// �м�����
	private JPanel contentPane;
	// ��ҳ������
	private JTabbedPane tabbedPane;

	// �����û�ҳ�漰���
	private JPanel AddUser_Panel;
	private JLabel Username_Label1;
	private JLabel Password_Label1;
	private JLabel Role_Label1;
	private JTextField Username_Txt1;
	private JPasswordField Password_Txt1;
	@SuppressWarnings("rawtypes")
	private JComboBox Role_ComboBox1;
	private JButton Confirm_Button1;
	private JButton Return_Button1;

	// ɾ���û�ҳ�漰���
	private JPanel DelUser_Panel;
	private JScrollPane scrollPane;
	private JTable Users_table;
	private JButton Confirm_Button2;
	private JButton Return_Button2;

	// �޸��û�ҳ�漰���
	private JPanel UpdateUser_Panel;
	private JLabel Username_Label2;
	private JLabel Password_Label2;
	private JLabel Role_Label2;
	private JTextField Username_Txt2;
	private JPasswordField Password_Txt2;
	@SuppressWarnings("rawtypes")
	private JComboBox Role_ComboBox2;
	private JButton Confirm_Button3;
	private JButton Return_Button3;

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

					UserFrame frame = new UserFrame(new Administrator("kate", "123", "Administrator"), 0);
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UserFrame(User user, int choice) {
		// �����û���ҳ��ѡ��: 0 �����û� 1 ɾ���û� 2 �޸��û�
		// ���
		setResizable(false);
		setTitle("\u7528\u6237\u7BA1\u7406\u754C\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 587, 441);

		// �м�����
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ��ҳ������
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(14, 49, 553, 309);
		contentPane.add(tabbedPane);

		// ����û�ҳ��
		AddUser_Panel = new JPanel();
		// ����ѡ�
		tabbedPane.addTab("\u65B0\u589E\u7528\u6237", null, AddUser_Panel, null);
		AddUser_Panel.setLayout(null);

		// �û�����ǩ
		Username_Label1 = new JLabel("\u7528\u6237\u540D");
		Username_Label1.setFont(new Font("����", Font.PLAIN, 18));
		Username_Label1.setBounds(114, 38, 61, 32);
		AddUser_Panel.add(Username_Label1);

		// �����ǩ
		Password_Label1 = new JLabel("\u5BC6\u7801");
		Password_Label1.setFont(new Font("����", Font.PLAIN, 18));
		Password_Label1.setBounds(132, 93, 43, 32);
		AddUser_Panel.add(Password_Label1);

		// ��ɫ��ǩ
		Role_Label1 = new JLabel("\u89D2\u8272");
		Role_Label1.setFont(new Font("����", Font.PLAIN, 18));
		Role_Label1.setBounds(132, 150, 43, 32);
		AddUser_Panel.add(Role_Label1);

		// �û����ı���
		Username_Txt1 = new JTextField();
		Username_Txt1.setBounds(197, 44, 181, 24);
		Username_Txt1.setColumns(10);
		AddUser_Panel.add(Username_Txt1);

		// �����ı���
		Password_Txt1 = new JPasswordField();
		Password_Txt1.setBounds(197, 99, 181, 24);
		AddUser_Panel.add(Password_Txt1);

		// ��ɫѡ����
		Role_ComboBox1 = new JComboBox();
		Role_ComboBox1.setEditable(true);
		Role_ComboBox1.setModel(new DefaultComboBoxModel(new String[] { "", "Administrator", "Browser", "Operator" }));
		Role_ComboBox1.setBounds(197, 156, 181, 24);
		AddUser_Panel.add(Role_ComboBox1);

		// ����ť
		Confirm_Button1 = new JButton("\u589E\u6DFB");
		Confirm_Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �����û��¼�
				AddUserActionPerformed(user, e);
			}
		});
		Confirm_Button1.setFont(new Font("����", Font.PLAIN, 18));
		Confirm_Button1.setBounds(132, 222, 113, 27);
		AddUser_Panel.add(Confirm_Button1);

		// ���ذ�ť
		Return_Button1 = new JButton("\u8FD4\u56DE");
		Return_Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �����¼�
				ReturnActionPerformed(e);
			}
		});
		Return_Button1.setFont(new Font("����", Font.PLAIN, 18));
		Return_Button1.setBounds(329, 222, 113, 27);
		AddUser_Panel.add(Return_Button1);

		// ɾ���û�ҳ��
		DelUser_Panel = new JPanel();
		tabbedPane.addTab("\u5220\u9664\u7528\u6237", null, DelUser_Panel, null);
		DelUser_Panel.setLayout(null);

		// ɾ����ť
		Confirm_Button2 = new JButton("\u5220\u9664");
		Confirm_Button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ɾ���¼�
				DelUserActionPerformed(user, e);
			}
		});
		Confirm_Button2.setBounds(132, 222, 113, 27);
		Confirm_Button2.setFont(new Font("����", Font.PLAIN, 18));
		DelUser_Panel.add(Confirm_Button2);

		// ���ذ�ť
		Return_Button2 = new JButton("\u8FD4\u56DE");
		Return_Button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �����¼�
				ReturnActionPerformed(e);
			}
		});
		Return_Button2.setBounds(329, 222, 113, 27);
		Return_Button2.setFont(new Font("����", Font.PLAIN, 18));
		DelUser_Panel.add(Return_Button2);

		// ������������
		scrollPane = new JScrollPane();
		scrollPane.setBounds(62, 37, 432, 159);
		DelUser_Panel.add(scrollPane);

		// �û��б�
		Users_table = new JTable();
		// ������
		ConstructUserTable();
		// �������������
		scrollPane.setViewportView(Users_table);

		// �޸��û�ҳ��
		UpdateUser_Panel = new JPanel();
		UpdateUser_Panel.setLayout(null);
		tabbedPane.addTab("\u4FEE\u6539\u7528\u6237", null, UpdateUser_Panel, null);

		// �û�����ǩ
		Username_Label2 = new JLabel("\u7528\u6237\u540D");
		Username_Label2.setFont(new Font("����", Font.PLAIN, 18));
		Username_Label2.setBounds(114, 38, 61, 32);
		UpdateUser_Panel.add(Username_Label2);

		// �����ǩ
		Password_Label2 = new JLabel("\u5BC6\u7801");
		Password_Label2.setFont(new Font("����", Font.PLAIN, 18));
		Password_Label2.setBounds(132, 93, 43, 32);
		UpdateUser_Panel.add(Password_Label2);

		// ��ɫ��ǩ
		Role_Label2 = new JLabel("\u89D2\u8272");
		Role_Label2.setFont(new Font("����", Font.PLAIN, 18));
		Role_Label2.setBounds(132, 150, 43, 32);
		UpdateUser_Panel.add(Role_Label2);

		// �û����ı���
		Username_Txt2 = new JTextField();
		Username_Txt2.setColumns(10);
		Username_Txt2.setBounds(197, 44, 181, 24);
		UpdateUser_Panel.add(Username_Txt2);

		// �����ı���
		Password_Txt2 = new JPasswordField();
		Password_Txt2.setBounds(197, 99, 181, 24);
		UpdateUser_Panel.add(Password_Txt2);

		// ��ɫѡ����
		Role_ComboBox2 = new JComboBox();
		Role_ComboBox2.setModel(new DefaultComboBoxModel(new String[] { "", "Administrator", "Browser", "Operator" }));
		Role_ComboBox2.setEditable(true);
		Role_ComboBox2.setBounds(197, 156, 181, 24);
		UpdateUser_Panel.add(Role_ComboBox2);

		// �޸İ�ť
		Confirm_Button3 = new JButton("\u4FEE\u6539");
		Confirm_Button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �޸��û��¼�
				UpdateUserActionPerformed(user, e);
			}
		});
		Confirm_Button3.setFont(new Font("����", Font.PLAIN, 18));
		Confirm_Button3.setBounds(132, 222, 113, 27);
		UpdateUser_Panel.add(Confirm_Button3);

		// ���ذ�ť
		Return_Button3 = new JButton("\u8FD4\u56DE");
		Return_Button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �����¼�
				ReturnActionPerformed(e);
			}
		});
		Return_Button3.setFont(new Font("����", Font.PLAIN, 18));
		Return_Button3.setBounds(329, 222, 113, 27);
		UpdateUser_Panel.add(Return_Button3);

		// ����ҳ��
		SetPane(choice);
	}

	// �û������
	private void ConstructUserTable() {

		// ��ͷ����
		String[] columnNames = { "\u7528\u6237\u540D", "\u5BC6\u7801", "\u89D2\u8272" };
		// �������
		String[][] rowData = new String[20][3];

		Enumeration<User> u;
		try {
			// ��ȡ��ϣ����Ϣ
			u = DataProcessing.getAllUser();
			// ����
			int row = 0;
			// ����ϣ����Ϣ�������������
			while (u.hasMoreElements()) {
				User user = u.nextElement();
				rowData[row][0] = user.getName();
				rowData[row][1] = user.getPassword();
				rowData[row][2] = user.getRole();
				row++;
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		}

		// ������
		Users_table.setModel(new DefaultTableModel(rowData, columnNames) {

			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		});

	}

	// ����
	private void AddUserActionPerformed(User user, ActionEvent evt) {

		// ��ȡ��������
		String username = this.Username_Txt1.getText();
		String password = new String(this.Password_Txt1.getPassword());
		String role = (String) this.Role_ComboBox1.getSelectedItem();

		if (StringUtil.isEmpty(username)) {
			JOptionPane.showMessageDialog(null, "δ�����û�����");
			return;
		}
		if (StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "δ�������룡");
			return;
		}
		if (StringUtil.isEmpty(role)) {
			JOptionPane.showMessageDialog(null, "δѡ����ݣ�");
			return;
		}

		try {

			if (DataProcessing.insertUser(username, password, role)) {
				ConstructUserTable(); // ���±������
				JOptionPane.showMessageDialog(null, "��ӳɹ���");
				return;
			} else {
				JOptionPane.showMessageDialog(null, "���ʧ�ܣ��û����Ѵ��ڣ�");
				return;
			}

		} catch (HeadlessException | SQLException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		}

	}

	// ɾ��
	private void DelUserActionPerformed(User user, ActionEvent evt) {

		// ��ȡ��ѡ�����,��δѡ����ֵΪ-1
		int selectedrow = Users_table.getSelectedRow();

		// δѡ���û������
		if (selectedrow == -1) {
			JOptionPane.showMessageDialog(null, "δѡ���û���");
			return;
		} else {

			// ��ȡ��ѡ�е��û���
			String username = (String) Users_table.getValueAt(selectedrow, 0);
			// ��ѡ�����
			if (StringUtil.isEmpty(username)) {
				return;
			}
			// ѡ�������û������
			if (username.equals(user.getName())) {
				JOptionPane.showMessageDialog(null, "����ɾ�������û���");
				return;
			}

			// ��ʾȷ�Ͻ���: ��Ϣ, ����, ѡ�����
			int value = JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ���û���", "�û�ɾ��ȷ�Ͻ���", 2);

			// Yes=0 No=1
			if (value == 0) {

				try {
					if (DataProcessing.deleteUser(username)) {
						ConstructUserTable(); // ���±������
						JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
						return;
					} else {
						JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
						return;
					}

				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
				}

			} else if (value == 1) {
				return;
			}
		}
	}

	// �޸�
	private void UpdateUserActionPerformed(User user, ActionEvent evt) {

		String username = this.Username_Txt2.getText();
		String password = new String(this.Password_Txt2.getPassword());
		String role = (String) this.Role_ComboBox2.getSelectedItem();

		if (StringUtil.isEmpty(username)) {
			JOptionPane.showMessageDialog(null, "δ�����û�����");
			return;
		}
		if (StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "δ�������룡");
			return;
		}
		if (StringUtil.isEmpty(role)) {
			JOptionPane.showMessageDialog(null, "δѡ����ݣ�");
			return;
		}

		try {

			if (DataProcessing.searchUser(username, password) == null) {
				JOptionPane.showMessageDialog(null, "�û��������벻ƥ�䣡");
				return;
			} else {

				// ��ʾȷ�Ͻ��棺��Ϣ�����⣬ѡ�����
				int value = JOptionPane.showConfirmDialog(null, "ȷ��Ҫ�޸���Ϣ��", "��Ϣ�޸�ȷ�Ͻ���", 2);

				// Yes=0 No=1
				if (value == 0) {

					if (DataProcessing.updateUser(username, password, role)) {
						ConstructUserTable(); // ���±������
						JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
						return;
					} else {
						JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
						return;
					}

				} else if (value == 1) {
					return;
				}
			}

		} catch (HeadlessException | SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		}
	}

	// ����ҳ��
	private void SetPane(int value) {
		if (value == 0) {
			tabbedPane.setSelectedComponent(AddUser_Panel);
		} else if (value == 1) {
			tabbedPane.setSelectedComponent(DelUser_Panel);
		} else if (value == 2) {
			tabbedPane.setSelectedComponent(UpdateUser_Panel);
		}
	}

	// ����
	private void ReturnActionPerformed(ActionEvent evt) {
		this.dispose();
	}

}