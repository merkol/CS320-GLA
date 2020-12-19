import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class gui extends JFrame {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gui frame = new gui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public gui() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(gui.class.getResource("/com/sun/java/swing/plaf/windows/icons/Computer.gif")));
		setTitle("Game Library Application");
		setSize(1200,700);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(479, 280, 175, 46);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sign Up");
		btnNewButton_1.setBounds(479, 339, 175, 56);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Background");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\atakrk\\Desktop\\2.jpg"));
		lblNewLabel.setBounds(-77, -47, 1298, 774);
		getContentPane().add(lblNewLabel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		
		
	}
}

