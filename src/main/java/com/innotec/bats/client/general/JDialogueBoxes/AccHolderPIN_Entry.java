package JDialogueBoxes;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SpringLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;


public class AccHolderPIN_Entry extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JLabel lblEnterPin;
	private JLabel lblConfirmPin;
	private JButton btnSubmit;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			AccHolderPIN_Entry dialog = new AccHolderPIN_Entry();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AccHolderPIN_Entry()
	{
		setResizable(false);
		setTitle("Please Enter PIN");
		setBounds(100, 100, 470, 355);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaption);
		contentPanel.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new LineBorder(new Color(70, 130, 180), 2)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			lblEnterPin = new JLabel("Enter PIN:");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblEnterPin, 34, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblEnterPin, 10, SpringLayout.WEST, contentPanel);
			lblEnterPin.setHorizontalAlignment(SwingConstants.LEFT);
			lblEnterPin.setFont(new Font("Cambria", Font.BOLD, 25));
			contentPanel.add(lblEnterPin);
		}
		{
			lblConfirmPin = new JLabel("Confirm PIN:");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblConfirmPin, 43, SpringLayout.SOUTH, lblEnterPin);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblConfirmPin, 10, SpringLayout.WEST, contentPanel);
			lblConfirmPin.setHorizontalAlignment(SwingConstants.LEFT);
			lblConfirmPin.setFont(new Font("Cambria", Font.BOLD, 25));
			contentPanel.add(lblConfirmPin);
		}
		
		passwordField = new JPasswordField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, passwordField, 5, SpringLayout.NORTH, lblEnterPin);
		sl_contentPanel.putConstraint(SpringLayout.WEST, passwordField, 53, SpringLayout.EAST, lblEnterPin);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, passwordField, 25, SpringLayout.NORTH, lblEnterPin);
		sl_contentPanel.putConstraint(SpringLayout.EAST, passwordField, 183, SpringLayout.EAST, lblEnterPin);
		contentPanel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, passwordField_1, 5, SpringLayout.NORTH, lblConfirmPin);
		sl_contentPanel.putConstraint(SpringLayout.WEST, passwordField_1, -130, SpringLayout.EAST, passwordField);
		sl_contentPanel.putConstraint(SpringLayout.EAST, passwordField_1, 0, SpringLayout.EAST, passwordField);
		contentPanel.add(passwordField_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			buttonPane.setForeground(SystemColor.inactiveCaption);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			btnSubmit = new JButton("Submit");
			btnSubmit.setForeground(new Color(70, 130, 180));
			btnSubmit.setFont(new Font("Cambria", Font.BOLD, 16));
			btnSubmit.setBackground(new Color(70, 130, 180));
			buttonPane.add(btnSubmit);
			
			JButton btnCancel = new JButton("Cancel");
			btnCancel.setForeground(new Color(70, 130, 180));
			btnCancel.setFont(new Font("Cambria", Font.BOLD, 16));
			btnCancel.setBackground(new Color(70, 130, 180));
			buttonPane.add(btnCancel);
		}
	}
}
