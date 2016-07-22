import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class MainGUI extends JFrame
{

private JPanel contentPane;
private JPanel insertPanel;
private ImageIcon ncbIcon;

/**
 * Launch the application.
 */
public static void main (String[] args)
{

			
				ATMWelcomeScreen atmWelcomeScreen = new ATMWelcomeScreen();
				MainGUI frame = new MainGUI(atmWelcomeScreen);
				frame.setVisible(true);
			
		
}

/**
 * Create the frame.
 */
public MainGUI (JPanel insertPanel)
{
	setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ilana\\workspace\\BatsGUIs\\resources\\NCBIcon.jpg"));

	this.insertPanel = insertPanel;
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	contentPane.setLayout(new BorderLayout(0, 0));
	this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
	this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	contentPane.add(insertPanel);
	this.setContentPane(contentPane);
	ncbIcon = new ImageIcon("resources/NCBIcon.jpg");
	//this.setIconImage(ncbIcon);
	this.getContentPane().setVisible(true);
	
}



}
