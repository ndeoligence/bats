import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;


public class IntroPanel extends JPanel
{

	/**
	 * Create the panel.
	 */
	public IntroPanel()
	{
		setBorder(new LineBorder(new Color(0, 0, 0)));
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblNewLabel = new JLabel("New label");
		springLayout.putConstraint(SpringLayout.NORTH, lblNewLabel, 42, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblNewLabel, 160, SpringLayout.WEST, this);
		add(lblNewLabel);
		
		JButton btnNewButton = new JButton("New button");
		springLayout.putConstraint(SpringLayout.NORTH, btnNewButton, 163, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, btnNewButton, -128, SpringLayout.EAST, this);
		add(btnNewButton);

	}

}
