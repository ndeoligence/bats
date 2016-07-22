import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.SpringLayout;

import java.awt.SystemColor;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Template extends JFrame
{

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Template frame = new Template();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Template()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.inactiveCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblSubheading = new JLabel("Subheading");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblSubheading, 23, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblSubheading, -196, SpringLayout.EAST, contentPane);
		lblSubheading.setFont(new Font("Cambria", Font.BOLD, 28));
		contentPane.add(lblSubheading);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblSubheading, -32, SpringLayout.NORTH, lblNewLabel_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel_1, 45, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel_1, -185, SpringLayout.EAST, contentPane);
		lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD, 22));
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField, 35, SpringLayout.SOUTH, lblSubheading);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textField, -78, SpringLayout.SOUTH, contentPane);
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		sl_contentPane.putConstraint(SpringLayout.WEST, textField, 209, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField, -94, SpringLayout.EAST, contentPane);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("New button");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblNewLabel_1, -41, SpringLayout.NORTH, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton, 59, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton, -10, SpringLayout.SOUTH, contentPane);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setFont(new Font("Cambria", Font.BOLD, 16));
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		sl_contentPane.putConstraint(SpringLayout.NORTH, panel, 0, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, panel, 0, SpringLayout.WEST, lblSubheading);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, panel, -185, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, panel, -10, SpringLayout.EAST, contentPane);
		panel.setBorder(BorderFactory.createEtchedBorder(0));
		contentPane.add(panel);
		
		JLabel label = new JLabel("New City Bank");
		label.setFont(new Font("Cambria", Font.BOLD, 36));
		panel.add(label);
		//google toolkit, java full screen - resolution
	}
}
