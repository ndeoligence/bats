package TellerBank;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.BoxLayout;

import java.awt.BorderLayout;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Component;


public class TellerHomePage extends JPanel implements ActionListener
{
	private JButton btnNewAcc;
	/**
	 * Create the panel.
	 */
	public TellerHomePage()
	{
		setBackground(SystemColor.inactiveCaption);
		setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new EtchedBorder(EtchedBorder.LOWERED, new Color(244, 247, 252), new Color(153, 180, 209))));
		setLayout(null);
		
		JPanel panel = new JPanel();
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		panel.setBounds(7, 7, 1349, 138);
		panel.setBackground(SystemColor.inactiveCaption);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(70, 130, 180), 2), "Branch Teller", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(70, 130, 180)));
		add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("New City Bank");
		lblNewLabel.setForeground(SystemColor.textHighlight);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 85));
		lblNewLabel.setBackground(SystemColor.inactiveCaption);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(7, 145, 1349, 594);
		panel_1.setBackground(SystemColor.inactiveCaption);
		panel_1.setBorder(null);
		add(panel_1);
		
	    btnNewAcc = new JButton("Create New Acc");
		btnNewAcc.addActionListener(this);
		btnNewAcc.setBackground(new Color(70, 130, 180));
		btnNewAcc.setForeground(new Color(70, 130, 180));
		btnNewAcc.setFont(new Font("Cambria", Font.BOLD, 30));
		
		JButton btnAddNewAcc = new JButton("Add New Acc");
		btnAddNewAcc.setForeground(new Color(70, 130, 180));
		btnAddNewAcc.setFont(new Font("Cambria", Font.BOLD, 30));
		btnAddNewAcc.setBackground(new Color(70, 130, 180));
		
		JButton btnUnblockAcc = new JButton("Unblock Acc");
		btnUnblockAcc.setForeground(new Color(70, 130, 180));
		btnUnblockAcc.setFont(new Font("Cambria", Font.BOLD, 30));
		btnUnblockAcc.setBackground(new Color(70, 130, 180));
		
		JButton btnExit = new JButton("Exit");
		btnExit.setForeground(new Color(70, 130, 180));
		btnExit.setFont(new Font("Cambria", Font.BOLD, 30));
		btnExit.setBackground(new Color(70, 130, 180));
		
		JLabel lblNewLabel_1 = new JLabel("Establish New Account Holder:");
		lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD, 34));
		
		JLabel lblNewLabel_2 = new JLabel("Existing Account Holder:");
		lblNewLabel_2.setFont(new Font("Cambria", Font.BOLD, 34));
		
		JLabel lblServerStatus = new JLabel("Server Status:");
		lblServerStatus.setFont(new Font("Cambria", Font.BOLD, 34));
		
		JLabel lblConnected = new JLabel("Connected");
		lblConnected.setFont(new Font("Cambria", Font.ITALIC, 34));
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.setForeground(new Color(70, 130, 180));
		btnConnect.setFont(new Font("Cambria", Font.BOLD, 30));
		btnConnect.setBackground(new Color(70, 130, 180));
		
		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.setForeground(new Color(70, 130, 180));
		btnDisconnect.setFont(new Font("Cambria", Font.BOLD, 30));
		btnDisconnect.setBackground(new Color(70, 130, 180));
		
		JLabel label = new JLabel("Existing Account Holder:");
		label.setFont(new Font("Cambria", Font.BOLD, 34));
		
		JButton btnCloseAcc = new JButton("Close Acc");
		btnCloseAcc.setForeground(new Color(70, 130, 180));
		btnCloseAcc.setFont(new Font("Cambria", Font.BOLD, 30));
		btnCloseAcc.setBackground(new Color(70, 130, 180));
		SpringLayout sl_panel_1 = new SpringLayout();
		sl_panel_1.putConstraint(SpringLayout.EAST, btnUnblockAcc, 0, SpringLayout.EAST, btnNewAcc);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnCloseAcc, 719, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnCloseAcc, 0, SpringLayout.EAST, btnNewAcc);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnAddNewAcc, 719, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnAddNewAcc, 0, SpringLayout.EAST, btnNewAcc);
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnNewAcc, 0, SpringLayout.NORTH, lblNewLabel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnNewAcc, 233, SpringLayout.EAST, lblNewLabel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnDisconnect, 0, SpringLayout.NORTH, btnConnect);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnDisconnect, 56, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnDisconnect, 1132, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnExit, 577, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnExit, -70, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnExit, 771, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnCloseAcc, 345, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnAddNewAcc, 270, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnCloseAcc, 300, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, label, 304, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnAddNewAcc, 225, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblNewLabel_2, 229, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblNewLabel_2, 10, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 151, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblNewLabel_1, 10, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnDisconnect, 915, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnConnect, 11, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, btnConnect, 592, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, btnConnect, 809, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblConnected, 10, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblConnected, 296, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblServerStatus, 10, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblServerStatus, 10, SpringLayout.WEST, panel_1);
		panel_1.setLayout(sl_panel_1);
		panel_1.add(lblServerStatus);
		panel_1.add(lblConnected);
		panel_1.add(btnConnect);
		panel_1.add(btnDisconnect);
		panel_1.add(lblNewLabel_1);
		panel_1.add(btnNewAcc);
		panel_1.add(lblNewLabel_2);
		panel_1.add(btnAddNewAcc);
		panel_1.add(label);
		panel_1.add(btnCloseAcc);
		panel_1.add(btnUnblockAcc);
		panel_1.add(btnExit);
		
		JLabel label_1 = new JLabel("Existing Account Holder:");
		sl_panel_1.putConstraint(SpringLayout.WEST, btnUnblockAcc, 324, SpringLayout.EAST, label_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, btnUnblockAcc, 45, SpringLayout.NORTH, label_1);
		sl_panel_1.putConstraint(SpringLayout.NORTH, label_1, 31, SpringLayout.SOUTH, label);
		sl_panel_1.putConstraint(SpringLayout.NORTH, btnUnblockAcc, 0, SpringLayout.NORTH, label_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, label_1, 0, SpringLayout.WEST, lblServerStatus);
		label_1.setFont(new Font("Cambria", Font.BOLD, 34));
		panel_1.add(label_1);

		//this.setSize(32767,32767);
		//this.setPreferredSize(32767,32767);
	}

	@Override
	public void actionPerformed(ActionEvent acEvent)
	{
		Object source = acEvent.getSource();
		if(source == btnNewAcc)
		{
			
		}
	}
}
