package com.innotec.bats.client.atm.accountholder.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;

public class MainGUI extends JFrame {

	private JPanel contentPane;
	private JPanel insertPanel;
	private JPanel framePanel;

	/**
	 * Create the frame.
	 */
	// public MainGUI (JPanel insertPanel)
	public MainGUI() //
	{
		// this.insertPanel = insertPanel;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 1));

		framePanel = new JPanel();
		contentPane.add(framePanel);
		framePanel.setLayout(new GridLayout(1, 1));

		// panel.add(insertPanel);
		this.getContentPane().setVisible(true);
	}

	public JPanel getFramePanel() {
		return framePanel;
	}

}
