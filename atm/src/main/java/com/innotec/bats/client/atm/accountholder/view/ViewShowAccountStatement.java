package com.innotec.bats.client.atm.accountholder.view;

import javax.swing.*;

import java.awt.*;

import javax.swing.border.*;

public class ViewShowAccountStatement extends JPanel {
	private JPanel framePanel;

	public ViewShowAccountStatement(JPanel framePanel) {
		this.framePanel = framePanel;
		framePanel.removeAll();

		setBackground(SystemColor.inactiveCaption);
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);

		JPanel panel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, panel, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -628, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, panel, -10, SpringLayout.EAST, this);
		panel.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, SystemColor.windowBorder));
		panel.setBackground(SystemColor.inactiveCaption);
		add(panel);
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);

		JLabel label = new JLabel("");
		sl_panel.putConstraint(SpringLayout.EAST, label, 1326, SpringLayout.WEST, panel);
		label.setBorder(BorderFactory.createEtchedBorder());
		sl_panel.putConstraint(SpringLayout.NORTH, label, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, label, 10, SpringLayout.WEST, panel);
		label.setIcon(new ImageIcon("resources/NewCityBankLogoSmall.jpg"));
		panel.add(label);

		JPanel panel_1 = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, panel_1, 6, SpringLayout.SOUTH, panel);
		panel_1.setBackground(SystemColor.inactiveCaption);
		springLayout.putConstraint(SpringLayout.SOUTH, panel_1, -10, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, panel_1, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, panel_1, 1352, SpringLayout.WEST, this);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, SystemColor.windowBorder));
		add(panel_1);
		SpringLayout sl_panel_1 = new SpringLayout();
		panel_1.setLayout(sl_panel_1);

		JPanel panel_2 = new JPanel();
		sl_panel_1.putConstraint(SpringLayout.WEST, panel_2, 232, SpringLayout.WEST, panel_1);
		sl_panel_1.putConstraint(SpringLayout.SOUTH, panel_2, -30, SpringLayout.SOUTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.EAST, panel_2, -233, SpringLayout.EAST, panel_1);
		panel_2.setBackground(SystemColor.inactiveCaption);
		panel_2.setBorder(BorderFactory.createEtchedBorder());
		panel_1.add(panel_2);
		SpringLayout sl_panel_2 = new SpringLayout();
		panel_2.setLayout(sl_panel_2);

		JButton btnCancel = new JButton("OK");
		sl_panel_2.putConstraint(SpringLayout.NORTH, btnCancel, 404, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, btnCancel, 238, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, btnCancel, -8, SpringLayout.SOUTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.EAST, btnCancel, -234, SpringLayout.EAST, panel_2);
		btnCancel.setIcon(new ImageIcon("resources/YesIcon.jpg"));
		btnCancel.setFont(new Font("Cambria", Font.PLAIN, 38));
		panel_2.add(btnCancel);

		JLabel lblWhatWouldYou = new JLabel("Statement for x account as at xx-xx-xxxx");
		sl_panel_1.putConstraint(SpringLayout.NORTH, panel_2, 14, SpringLayout.SOUTH, lblWhatWouldYou);
		sl_panel_1.putConstraint(SpringLayout.NORTH, lblWhatWouldYou, 10, SpringLayout.NORTH, panel_1);
		sl_panel_1.putConstraint(SpringLayout.WEST, lblWhatWouldYou, 193, SpringLayout.WEST, panel_1);

		JTextArea textArea = new JTextArea();
		sl_panel_2.putConstraint(SpringLayout.NORTH, textArea, 26, SpringLayout.NORTH, panel_2);
		sl_panel_2.putConstraint(SpringLayout.WEST, textArea, 26, SpringLayout.WEST, panel_2);
		sl_panel_2.putConstraint(SpringLayout.SOUTH, textArea, -23, SpringLayout.NORTH, btnCancel);
		sl_panel_2.putConstraint(SpringLayout.EAST, textArea, -27, SpringLayout.EAST, panel_2);
		panel_2.add(textArea);
		lblWhatWouldYou.setFont(new Font("Cambria", Font.PLAIN, 56));
		panel_1.add(lblWhatWouldYou);

		framePanel.add(this);
		framePanel.revalidate();
	}

}
