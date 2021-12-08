package frame;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.*;
import CS.Client;
import common.*;

@SuppressWarnings("serial")
public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JLabel Username_Label;
	private JLabel Password_Label;
	private JTextField Username_Txt;
	private JPasswordField Password_Txt;
	private JButton Confirm_Button;
	private JButton Cancel_Button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {

		// ���
		setTitle("\u7CFB\u7EDF\u767B\u5F55");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 565, 372);

		// �м�����
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// �û�����ǩ
		Username_Label = new JLabel("\u7528\u6237\u540D\uFF1A");
		Username_Label.setBounds(128, 89, 80, 40);
		Username_Label.setFont(new Font("����", Font.PLAIN, 20));

		// �����ǩ
		Password_Label = new JLabel("\u5BC6\u7801\uFF1A");
		Password_Label.setBounds(148, 142, 60, 37);
		Password_Label.setFont(new Font("����", Font.PLAIN, 20));

		// �û����ı���
		Username_Txt = new JTextField();
		Username_Txt.setBounds(222, 99, 176, 24);
		Username_Txt.setColumns(10);

		// �����ı���
		Password_Txt = new JPasswordField();
		Password_Txt.setBounds(222, 150, 176, 24);

		// ȷ�ϰ�ť
		Confirm_Button = new JButton("\u786E\u5B9A");
		Confirm_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ��¼�¼�
				LoginInActionPerformed(e);
			}
		});
		Confirm_Button.setFont(new Font("����", Font.PLAIN, 20));
		Confirm_Button.setBounds(173, 216, 85, 27);

		// ȡ����ť
		Cancel_Button = new JButton("\u53D6\u6D88");
		Cancel_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ȡ���¼�
				ResetValueActionPerformed(e);
			}
		});
		Cancel_Button.setFont(new Font("����", Font.PLAIN, 20));
		Cancel_Button.setBounds(313, 216, 85, 27);

		// ���ò�������Ӳ���
		// ���Բ���
		contentPane.setLayout(null);
		contentPane.add(Username_Label);
		contentPane.add(Password_Label);
		contentPane.add(Username_Txt);
		contentPane.add(Password_Txt);
		contentPane.add(Confirm_Button);
		contentPane.add(Cancel_Button);

		// �������ݿ�
		// Ӧ�ڹ��캯�������ӣ���Ӧ�����������ӣ�����ǳ����޷�����
		try {
			SQLconnection.Connect();
		} catch (ClassNotFoundException | SQLException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		}

	}

	// ��¼
	private void LoginInActionPerformed(ActionEvent evt) {

		String username = this.Username_Txt.getText();
		String password = new String(this.Password_Txt.getPassword()); // ��ȡ��������

		if (StringUtil.isEmpty(username)) {
			JOptionPane.showMessageDialog(null, "δ�����û�����"); // ��ʾ�Ի���
			return;
		}
		if (StringUtil.isEmpty(password)) {
			JOptionPane.showMessageDialog(null, "δ�������룡"); // ��ʾ�Ի���
			return;
		}

		try {
			if (DataProcessing.searchUser(username, password) == null) {
				JOptionPane.showMessageDialog(null, "�û��������벻ƥ�䣡"); // ��ʾ�Ի���
				return;
			} else {

				// ���ӷ����������͵�¼��Ϣ
				Client.ConnectToServer();
				Client.GetStreams();
				Client.SendMessage(username + "��¼");
				Client.ReceiveMessage();

				// �����û�
				User user = DataProcessing.searchUser(username, password);
				// �ǰ������ʧ
				this.dispose();
				// ��ת��������,�½����󲢴����û�����
				MainFrame mainframe = new MainFrame(user);
				mainframe.setVisible(true);
			}

		} catch (HeadlessException | SQLException | IOException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
			return;
		}

	}

	// �����ı���
	private void ResetValueActionPerformed(ActionEvent evt) {
		// ����Ϊ��
		this.Username_Txt.setText("");
		this.Password_Txt.setText("");
	}
}
