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
import javax.swing.JTextField;


public class AccountNumber_Teller extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JLabel lblEnterClientsAccount;
	private JLabel lblAccountNo;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			AccountNumber_Teller dialog = new AccountNumber_Teller();
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
	public AccountNumber_Teller()
	{
		setResizable(false);
		setTitle("New City Bank");
		setBounds(100, 100, 552, 375);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.inactiveCaption);
		contentPanel.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new LineBorder(new Color(70, 130, 180), 2)));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			lblEnterClientsAccount = new JLabel("Account Holder's Information!");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblEnterClientsAccount, 10, SpringLayout.NORTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblEnterClientsAccount, 77, SpringLayout.WEST, contentPanel);
			lblEnterClientsAccount.setHorizontalAlignment(SwingConstants.LEFT);
			lblEnterClientsAccount.setFont(new Font("Cambria", Font.BOLD, 26));
			contentPanel.add(lblEnterClientsAccount);
		}
		{
			lblAccountNo = new JLabel("Account Number:");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, lblAccountNo, 89, SpringLayout.SOUTH, lblEnterClientsAccount);
			sl_contentPanel.putConstraint(SpringLayout.WEST, lblAccountNo, 10, SpringLayout.WEST, contentPanel);
			lblAccountNo.setHorizontalAlignment(SwingConstants.LEFT);
			lblAccountNo.setFont(new Font("Cambria", Font.BOLD, 20));
			contentPanel.add(lblAccountNo);
		}
		
		textField = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.NORTH, textField, 89, SpringLayout.SOUTH, lblEnterClientsAccount);
		sl_contentPanel.putConstraint(SpringLayout.WEST, textField, 200, SpringLayout.WEST, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, textField, -33, SpringLayout.EAST, contentPanel);
		textField.setFont(new Font("Cambria", Font.PLAIN, 14));
		textField.setColumns(10);
		contentPanel.add(textField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.inactiveCaption);
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			
			JButton btnOk = new JButton("  OK  ");
			btnOk.setForeground(new Color(70, 130, 180));
			btnOk.setFont(new Font("Cambria", Font.BOLD, 18));
			btnOk.setBackground(new Color(70, 130, 180));
			buttonPane.add(btnOk);
			
			JButton button_1 = new JButton("Cancel");
			button_1.setForeground(new Color(70, 130, 180));
			button_1.setFont(new Font("Cambria", Font.BOLD, 18));
			button_1.setBackground(new Color(70, 130, 180));
			buttonPane.add(button_1);
		}
	}
}
