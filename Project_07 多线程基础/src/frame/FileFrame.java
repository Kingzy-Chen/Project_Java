package frame;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import CS.Client;

import java.text.SimpleDateFormat;
import java.util.Enumeration;
import common.*;

@SuppressWarnings("serial")
public class FileFrame extends JFrame {

	// �м�����
	private JPanel contentPane;
	// ��ҳ������
	private JTabbedPane tabbedPane;

	// �ϴ��ļ�ҳ�漰���
	private JPanel Upload_Panel;
	private JLabel FileID_Label;
	private JLabel Filedescription_Label;
	private JLabel Filename_Label;
	private JTextField FileID_Txt;
	private JTextArea Filedescription_Txt;
	private JTextField Filepath_Txt;
	private JButton Upload_Button;
	private JButton OpenFile_Button;
	private JButton Return_Button1;

	// �����ļ�ҳ�漰���
	private JPanel Download_Panel;
	private JButton Download_Button;
	private JButton Return_Button2;
	private JScrollPane scrollPane;
	private JTable Files_table;

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

					FileFrame frame = new FileFrame(new Administrator("jack", "123", "operator"), 0);
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
	public FileFrame(User user, int choice) {
		// �����û���ҳ��ѡ��: 0�ϴ��ļ� 1�����ļ�
		// ���
		setTitle("\u6587\u4EF6\u7BA1\u7406\u754C\u9762");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 802, 581);

		// �м�����
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ��ҳ������
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(38, 35, 713, 469);
		contentPane.add(tabbedPane);

		// �ϴ�ҳ��
		Upload_Panel = new JPanel();
		tabbedPane.addTab("\u6587\u4EF6\u4E0A\u4F20", null, Upload_Panel, null);
		Upload_Panel.setLayout(null);

		// �����ű�ǩ
		FileID_Label = new JLabel("\u6863\u6848\u53F7");
		FileID_Label.setFont(new Font("����", Font.PLAIN, 20));
		FileID_Label.setBounds(125, 33, 60, 36);
		Upload_Panel.add(FileID_Label);

		// �ļ�������ǩ
		Filedescription_Label = new JLabel("\u6863\u6848\u63CF\u8FF0");
		Filedescription_Label.setFont(new Font("����", Font.PLAIN, 20));
		Filedescription_Label.setBounds(105, 90, 80, 36);
		Upload_Panel.add(Filedescription_Label);

		// �ļ�����ǩ
		Filename_Label = new JLabel("\u6863\u6848\u6587\u4EF6\u540D");
		Filename_Label.setFont(new Font("����", Font.PLAIN, 20));
		Filename_Label.setBounds(85, 314, 100, 36);
		Upload_Panel.add(Filename_Label);

		// �������ı���
		FileID_Txt = new JTextField();
		FileID_Txt.setBounds(215, 40, 272, 27);
		Upload_Panel.add(FileID_Txt);
		FileID_Txt.setColumns(10);

		// �ļ������ı���
		Filedescription_Txt = new JTextArea();
		Filedescription_Txt.setBounds(215, 96, 272, 199);
		Upload_Panel.add(Filedescription_Txt);
		Filedescription_Txt.setColumns(10);

		// �ļ����ı���
		Filepath_Txt = new JTextField();
		Filepath_Txt.setColumns(10);
		Filepath_Txt.setBounds(215, 321, 272, 27);
		Upload_Panel.add(Filepath_Txt);

		// �ϴ���ť
		Upload_Button = new JButton("\u4E0A\u4F20");
		Upload_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �ϴ��ļ��¼�
				UploadActionPerformed(user, e);
			}
		});
		Upload_Button.setBounds(215, 380, 95, 27);
		Upload_Button.setFont(new Font("����", Font.PLAIN, 20));
		Upload_Panel.add(Upload_Button);

		// ���ذ�ť
		Return_Button1 = new JButton("\u8FD4\u56DE");
		Return_Button1.setBounds(395, 380, 95, 27);
		Return_Button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �����¼�
				ReturnActionPerformed(e);
			}
		});

		// �򿪰�ť
		OpenFile_Button = new JButton("\u6253\u5F00");
		OpenFile_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ���ļ��¼�
				OpenFileActionPerformed(e);
			}
		});
		OpenFile_Button.setFont(new Font("����", Font.PLAIN, 18));
		OpenFile_Button.setBounds(532, 319, 95, 27);
		Upload_Panel.add(OpenFile_Button);
		Return_Button1.setFont(new Font("����", Font.PLAIN, 20));
		Upload_Panel.add(Return_Button1);

		// ����ҳ��
		Download_Panel = new JPanel();
		tabbedPane.addTab("\u6587\u4EF6\u4E0B\u8F7D", null, Download_Panel, null);
		tabbedPane.setEnabledAt(1, true);
		Download_Panel.setLayout(null);

		// ���ذ�ť
		Download_Button = new JButton("\u4E0B\u8F7D");
		Download_Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �����ļ��¼�
				DownloadActionPerformed(user, e);
			}
		});
		Download_Button.setFont(new Font("����", Font.PLAIN, 20));
		Download_Button.setBounds(215, 380, 95, 27);
		Download_Panel.add(Download_Button);

		// ���ذ�ť
		Return_Button2 = new JButton("\u8FD4\u56DE");
		Return_Button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �����¼�
				ReturnActionPerformed(e);
			}
		});
		Return_Button2.setFont(new Font("����", Font.PLAIN, 20));
		Return_Button2.setBounds(395, 380, 95, 27);
		Download_Panel.add(Return_Button2);

		// ����������
		scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 32, 637, 322);
		Download_Panel.add(scrollPane);

		// �����ļ��б�
		Files_table = new JTable();
		// ������
		ConstructFileTable();
		// �������������
		scrollPane.setViewportView(Files_table);

		// ����Ȩ�޼�ҳ��
		setPane(user, choice);
	}

	// �����
	private void ConstructFileTable() {

		// ��ͷ����
		String[] columnNames = { "\u6863\u6848\u53F7", "\u521B\u5EFA\u8005", "\u65F6\u95F4", "\u6587\u4EF6\u540D",
				"\u6587\u4EF6\u63CF\u8FF0" };
		// �������
		String[][] rowData = new String[20][5];

		Enumeration<Doc> f;
		try {
			// ��ȡ��ϣ����Ϣ
			f = DataProcessing.getAllDocs();

			// ����
			int row = 0;
			// ����ϣ����Ϣ���������
			while (f.hasMoreElements()) {
				Doc doc = f.nextElement();
				rowData[row][0] = doc.getID();
				rowData[row][1] = doc.getCreator();
				rowData[row][2] = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(doc.getTimestamp()); // TimeתString
				rowData[row][3] = doc.getFilename();
				rowData[row][4] = doc.getDescription();
				row++;
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
		}

		// ������
		Files_table.setModel(new DefaultTableModel(rowData, columnNames) {

			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}

		});

	}

	// ���ļ�
	private void OpenFileActionPerformed(ActionEvent evt) {

		// �����ļ�ѡ���
		FileDialog OpenFileDialog = new FileDialog(this, "ѡ���ϴ��ļ�");
		OpenFileDialog.setVisible(true);

		// ��ȡ�ļ�·��
		String filepath = OpenFileDialog.getDirectory() + OpenFileDialog.getFile();
		Filepath_Txt.setText(filepath);

	}

	// �ϴ��ļ�
	private synchronized void UploadActionPerformed(User user, ActionEvent evt) {

		String filepath = Filepath_Txt.getText();
		String fileID = FileID_Txt.getText();
		String filedescription = Filedescription_Txt.getText();

		if (StringUtil.isEmpty(filepath)) {
			JOptionPane.showMessageDialog(null, "δѡ���ļ���");
			return;
		}
		if (StringUtil.isEmpty(fileID)) {
			JOptionPane.showMessageDialog(null, "δ���뵵���ţ�");
			return;
		}
		if (StringUtil.isEmpty(filedescription)) {
			JOptionPane.showMessageDialog(null, "δ�����ļ�������");
			return;
		}

		if (user.uploadFile(fileID, filepath, filedescription)) {

			// �����ϴ��ļ���Ϣ
			try {
				Client.SendMessage("�ϴ��ļ�");
				Client.ReceiveMessage();
			} catch (IOException | ClassNotFoundException e) {
				JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
				return;
			}

			// ���±������
			ConstructFileTable();
			JOptionPane.showMessageDialog(null, "�ϴ��ɹ���");
			return;

		} else {
			JOptionPane.showMessageDialog(null, "�ϴ�ʧ�ܣ�");
			return;
		}

	}

	// �����ļ�
	private synchronized void DownloadActionPerformed(User user, ActionEvent evt) {

		// ��ȡ��ѡ�����, ��δѡ����ֵΪ-1
		int selectedrow = Files_table.getSelectedRow();

		// δѡ���ļ������
		if (selectedrow == -1) {
			JOptionPane.showMessageDialog(null, "δѡ���ļ���");
			return;
		} else {

			// ��ȡ������
			String fileID = (String) Files_table.getValueAt(selectedrow, 0);
			// ��ѡ�����
			if (StringUtil.isEmpty(fileID)) {
				return;
			}

			// ��ʾȷ�Ͻ���: ��Ϣ, ����, ѡ�����
			int value = JOptionPane.showConfirmDialog(null, "ȷ��Ҫ�����ļ���", "�ļ�����ȷ�Ͻ���", 2);
			// Yes=0 No=1
			if (value == 0) {
				if (user.downloadFile(fileID)) {

					// ���������ļ���Ϣ
					try {
						Client.SendMessage("�����ļ�");
						Client.ReceiveMessage();
					} catch (IOException | ClassNotFoundException e) {
						JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
						return;
					}

					JOptionPane.showMessageDialog(null, "���سɹ���");
					return;
				} else {
					JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
					return;
				}

			} else if (value == 1) {
				return;
			}
		}
	}

	// ����ҳ��
	private void setPane(User user, int choice) {

		if (!user.getRole().equalsIgnoreCase("operator")) {
			FileID_Txt.setEditable(false);
			Filedescription_Txt.setEditable(false);
			Filepath_Txt.setEditable(false);
			Upload_Button.setEnabled(false);
			OpenFile_Button.setEnabled(false);
		}

		if (choice == 0) {
			tabbedPane.setSelectedComponent(Upload_Panel);
		} else if (choice == 1) {
			tabbedPane.setSelectedComponent(Download_Panel);
		}

	}

	// ����
	private void ReturnActionPerformed(ActionEvent evt) {
		this.dispose();
	}
}