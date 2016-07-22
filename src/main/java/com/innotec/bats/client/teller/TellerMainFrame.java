package TellerBank;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import java.awt.FlowLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.BoxLayout;


public class TellerMainFrame extends JFrame
{

	private JPanel contentPane;
	private TellerHomePage tellerDefaultPage;

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
					TellerMainFrame frame = new TellerMainFrame();
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
	public TellerMainFrame()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 535, 439);
		contentPane = new JPanel();
		this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBorder(null);
		contentPane.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new GridLayout(1, 1, 0, 0));
		
		tellerDefaultPage = new TellerHomePage();
		mainPanel.add(tellerDefaultPage);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	}

}
