package frame;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import CS.Client;
import common.*;

@SuppressWarnings("serial")
public class SelfInfoFrame extends JFrame {

	private JPanel contentPane;
	private JLabel Username_Label;
	private JLabel OldPassword_Label;
	private JLabel NewPassword_Label;
	private JLabel ConfirmPassword_Label;
	private JLabel Role_Label;

	private JTextField Username_Txt;
	private JPasswordField OldPassword_Txt;
	private JPasswordField NewPassword_Txt;
	private JPasswordField ConfirmPassword_Txt;
	private JTextField Role_Txt;

	private JButton Confirm_Button;
	private JButton Return_Button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// ʵ��6�޷�ʵ�ֵ������ý���, ����ʵ���û������������Ϣ���ݣ�
					// ��ӵ�¼���濪ʼ��ת

					// ���������������ݿ�
					try {
						SQLconnection.Connect();
					} catch (ClassNotFoundException | SQLException e) {
						JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
					}

					SelfInfoFrame frame = new SelfInfoFrame(new Administrator("kate", "123", "Administrator"));
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
	public SelfInfoFrame(User user) {
		// �����û�����
		// ���
		setResizable(false);
		setTitle("\u4E2A\u4EBA\u4FE1\u606F\u7BA1\u7406");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 502, 384);

		// �м�����
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// �û�����ǩ
		Username_Label = new JLabel("\u7528\u6237\u540D:");
		Username_Label.setFont(new Font("����", Font.PLAIN, 18));
		Username_Label.setBounds(100, 51, 72, 30);
		contentPane.add(Username_Label);

		// �������ǩ
		OldPassword_Label = new JLabel("\u539F\u5BC6\u7801:");
		OldPassword_Label.setFont(new Font("����", Font.PLAIN, 18));
		OldPassword_Label.setBounds(100, 93, 72, 30);
		contentPane.add(OldPassword_Label);

		// �������ǩ
		NewPassword_Label = new JLabel("\u65B0\u5BC6\u7801:");
		NewPassword_Label.setFont(new Font("����", Font.PLAIN, 18));
		NewPassword_Label.setBounds(100, 135, 72, 30);
		contentPane.add(NewPassword_Label);

		// ȷ�������ǩ
		ConfirmPassword_Label = new JLabel("\u786E\u8BA4\u65B0\u5BC6\u7801:");
		ConfirmPassword_Label.setFont(new Font("����", Font.PLAIN, 18));
		ConfirmPassword_Label.setBounds(63, 178, 109, 30);
		contentPane.add(ConfirmPassword_Label);

		// ��ɫ��ǩ
		Role_Label = new JLabel("\u89D2\u8272:");
		Role_Label.setFont(new Font("����", Font.PLAIN, 18));
		Role_Label.setBounds(118, 221, 57, 30);
		contentPane.add(Role_Label);

		// �û����ı���
		Username_Txt = new JTextField();
		// �Զ������ı�Ϊ�û���
		Username_Txt.setText(user.getName());
		Username_Txt.setEditable(false);
		Username_Txt.setBounds(186, 56, 154, 24);
		contentPane.add(Username_Txt);
		Username_Txt.setColumns(10);

		// �������ı���
		OldPassword_Txt = new JPasswordField();
		OldPassword_Txt.setBounds(186, 98, 154, 24);
		contentPane.add(OldPassword_Txt);

		// �������ı���
		NewPassword_Txt = new JPasswordField();
		NewPassword_Txt.setBounds(186, 140, 154, 24);
		contentPane.add(NewPassword_Txt);

		// ȷ�������ı���
		ConfirmPassword_Txt = new JPasswordField();
		ConfirmPassword_Txt.setBounds(186, 183, 154, 24);
		contentPane.add(ConfirmPassword_Txt);

		// ��ɫ�ı���
		Role_Txt = new JTextField();
		// �Զ������û����
		Role_Txt.setText(user.getRole());
		Role_Txt.setEditable(false);
		Role_Txt.setColumns(10);
		Role_Txt.setBounds(186, 226, 154, 24);
		contentPane.add(Role_Txt);

		// ȷ�ϰ�ť
		Confirm_Button = new JButton("\u786E\u8BA4");
		Confirm_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �޸������¼�
				ChangeSelfInfoActionPerformed(user, e);
			}
		});
		Confirm_Button.setFont(new Font("����", Font.PLAIN, 18));
		Confirm_Button.setBounds(118, 288, 113, 27);
		contentPane.add(Confirm_Button);

		// ���ذ�ť
		Return_Button = new JButton("\u8FD4\u56DE");
		Return_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �����¼�
				ReturnActionPerformed(e);
			}
		});
		Return_Button.setFont(new Font("����", Font.PLAIN, 18));
		Return_Button.setBounds(278, 288, 113, 27);
		contentPane.add(Return_Button);
	}

	// �޸�����
	private void ChangeSelfInfoActionPerformed(User user, ActionEvent evt) {

		String oldpassword = new String(OldPassword_Txt.getPassword());
		String newpassword = new String(NewPassword_Txt.getPassword());
		String confirmpassword = new String(ConfirmPassword_Txt.getPassword());

		// ����Ƿ�Ϊ��
		if (StringUtil.isEmpty(oldpassword)) {
			JOptionPane.showMessageDialog(null, "δ��������룡");
			return;
		}
		if (StringUtil.isEmpty(newpassword)) {
			JOptionPane.showMessageDialog(null, "δ���������룡");
			return;
		}
		if (StringUtil.isEmpty(confirmpassword)) {
			JOptionPane.showMessageDialog(null, "������ȷ�����룡");
			return;
		}

		// ����ƥ��
		try {
			if (DataProcessing.searchUser(user.getName(), oldpassword) == null) {
				JOptionPane.showMessageDialog(null, "�û�����ԭ���벻ƥ�䣡");
				return;
			}
			if (!newpassword.equals(confirmpassword)) {
				JOptionPane.showMessageDialog(null, "��������������벻��ͬ��");
				return;
			}

			// �޸�����
			if (user.changeSelfInfo(newpassword)) {

				// �����޸�������Ϣ
				try {
					Client.SendMessage("�޸�����");
					Client.ReceiveMessage();
				} catch (IOException | ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
					return;
				}

				// ���
				this.OldPassword_Txt.setText("");
				this.NewPassword_Txt.setText("");
				this.ConfirmPassword_Txt.setText("");

				JOptionPane.showMessageDialog(null, "�޸ĳɹ�!");
				return;

			} else {
				JOptionPane.showMessageDialog(null, "�޸�ʧ��!");
				return;
			}

		} catch (HeadlessException | SQLException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		}

	}

	// ����
	private void ReturnActionPerformed(ActionEvent evt) {
		this.dispose();
	}
}
