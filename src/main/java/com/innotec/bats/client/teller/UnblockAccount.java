package TellerBank;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.GridLayout;

import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;


public class UnblockAccount extends JPanel
{
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public UnblockAccount()
	{
		setBackground(SystemColor.inactiveCaption);
		setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new EtchedBorder(EtchedBorder.LOWERED, new Color(244, 247, 252), new Color(153, 180, 209))));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(70, 130, 180), 2), "Account Administrator", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(70, 130, 180)));
		add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
		JLabel label = new JLabel("New City Bank");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(SystemColor.textHighlight);
		label.setFont(new Font("Cambria", Font.BOLD, 46));
		label.setBackground(SystemColor.inactiveCaption);
		panel.add(label);
		
		JLabel lblUnblockAccount = new JLabel("Unblock Account:");
		lblUnblockAccount.setForeground(SystemColor.textHighlight);
		lblUnblockAccount.setFont(new Font("Cambria", Font.BOLD, 30));
		panel.add(lblUnblockAccount);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(70, 130, 180), 2), "Client Details", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(70, 130, 180)));
		panel_1.setBackground(SystemColor.inactiveCaption);
		add(panel_1, BorderLayout.CENTER);
		
		JLabel lblAccountHolder = new JLabel("Account Holder:");
		lblAccountHolder.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountHolder.setFont(new Font("Cambria", Font.BOLD, 38));
		
		JLabel lblAccountType = new JLabel("Account Type:");
		lblAccountType.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountType.setFont(new Font("Cambria", Font.BOLD, 38));
		
		textField = new JTextField();
		textField.setFont(new Font("Cambria", Font.PLAIN, 22));
		textField.setColumns(10);
		
		JCheckBox checkBox = new JCheckBox("Current Account");
		checkBox.setFont(new Font("Cambria", Font.PLAIN, 30));
		checkBox.setBackground(SystemColor.inactiveCaption);
		
		JCheckBox checkBox_1 = new JCheckBox("Savings Account");
		checkBox_1.setFont(new Font("Cambria", Font.PLAIN, 30));
		checkBox_1.setBackground(SystemColor.inactiveCaption);
		
		JLabel lblAccountNumber = new JLabel("Account Number:");
		lblAccountNumber.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountNumber.setFont(new Font("Cambria", Font.BOLD, 38));
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Cambria", Font.PLAIN, 22));
		textField_1.setColumns(10);
		
		JLabel lblAccountStatus = new JLabel("Account Status:");
		lblAccountStatus.setHorizontalAlignment(SwingConstants.LEFT);
		lblAccountStatus.setFont(new Font("Cambria", Font.BOLD, 38));
		
		JLabel lblBlocked = new JLabel("Blocked");
		lblBlocked.setHorizontalAlignment(SwingConstants.LEFT);
		lblBlocked.setFont(new Font("Cambria", Font.ITALIC, 30));
		
		JButton btnUnblock = new JButton("Unblock Acc");
		btnUnblock.setForeground(new Color(70, 130, 180));
		btnUnblock.setFont(new Font("Cambria", Font.BOLD, 24));
		btnUnblock.setBackground(new Color(70, 130, 180));
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setForeground(new Color(70, 130, 180));
		btnCancel.setFont(new Font("Cambria", Font.BOLD, 24));
		btnCancel.setBackground(new Color(70, 130, 180));
		SpringLayout sl_panel_1 = new SpringLayout();
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnCancel, -65, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnCancel, 296, SpringLayout.EAST, btnUnblock);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnCancel, 0, SpringLayout.SOUTH, btnUnblock);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnCancel, -358, SpringLayout.EAST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnUnblock, -819, SpringLayout.EAST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnUnblock, 356, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnUnblock, -28, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblBlocked, 60, SpringLayout.EAST, lblAccountStatus);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, lblBlocked, 0, SpringLayout.SOUTH, lblAccountStatus);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblAccountStatus, 79, SpringLayout.SOUTH, lblAccountNumber);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, textField_1, 0, SpringLayout.SOUTH, lblAccountNumber);
		sl_panel_1.putConstraint(SpringLayout.WEST, checkBox, 81, SpringLayout.EAST, lblAccountType);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, textField, 0, SpringLayout.SOUTH, lblAccountHolder);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblAccountNumber, 79, SpringLayout.SOUTH, lblAccountType);
		sl_panel_1.putConstraint(SpringLayout.NORTH, checkBox_1, 1, SpringLayout.NORTH, lblAccountType);
		sl_panel_1.putConstraint(SpringLayout.NORTH, checkBox, 1, SpringLayout.NORTH, lblAccountType);
		sl_panel_1.putConstraint(SpringLayout.WEST, checkBox_1, 17, SpringLayout.EAST, checkBox);
		sl_panel_1.putConstraint(SpringLayout.WEST, textField_1, 25, SpringLayout.EAST, lblAccountNumber);
		sl_panel_1.putConstraint(SpringLayout.EAST, textField_1, -629, SpringLayout.EAST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblAccountStatus, 16, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblAccountNumber, 16, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblAccountType, 16, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, textField, 46, SpringLayout.EAST, lblAccountHolder);
		sl_panel_1.putConstraint(SpringLayout.EAST, textField, -629, SpringLayout.EAST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblAccountType, 92, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblAccountHolder, 26, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblAccountHolder, 16, SpringLayout.WEST, panel_1);
		panel_1.setLayout(sl_panel_1);
		panel_1.add(lblAccountHolder);
		panel_1.add(textField);
		panel_1.add(lblAccountType);
		panel_1.add(checkBox);
		panel_1.add(checkBox_1);
		panel_1.add(lblAccountNumber);
		panel_1.add(textField_1);
		panel_1.add(lblAccountStatus);
		panel_1.add(lblBlocked);
		panel_1.add(btnUnblock);
		panel_1.add(btnCancel);
		
		JCheckBox chckbxCreditCard = new JCheckBox("Credit Card");
		sl_panel_1.putConstraint(SpringLayout.NORTH, chckbxCreditCard, 1, SpringLayout.NORTH, lblAccountType);
		sl_panel_1.putConstraint(SpringLayout.WEST, chckbxCreditCard, 16, SpringLayout.EAST, checkBox_1);
		chckbxCreditCard.setFont(new Font("Cambria", Font.PLAIN, 30));
		chckbxCreditCard.setBackground(SystemColor.inactiveCaption);
		panel_1.add(chckbxCreditCard);
	}

}
