package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import common.Constant;
import game.ShootingPlane;
import game.NewGame;

public class MainMenu extends JFrame {
	private JPanel contentPane;
	private JButton btn_start;
	private JButton btn_join;
	private JButton btn_exit;
	
	public static String name;

	public static void main(String[] agrs) {
		MainMenu main = new MainMenu();
		main.setVisible(true);
	}

	public MainMenu() {
		initComponent();
		btnControll();
		init(Constant.PORT);
	}

	private void init(int port) {
		// io = new ClientIO(port);
	}

	private void btnControll() {
		btn_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				name = JOptionPane.showInputDialog("Input your name: ");
				String title = JOptionPane.showInputDialog("Input room's title: ");
				dispose();
				ShootingPlane game = new NewGame(name, title);
			}
		});

		btn_join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				name = JOptionPane.showInputDialog("Input your name: ");
				JoinMenu join = new JoinMenu(name);
				join.setVisible(true);
				dispose();
			}
		});

		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
	}

	private void initComponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 300, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btn_start = new JButton("START");
		btn_start.setBounds(70, 50, 150, 40);
		contentPane.add(btn_start);

		btn_join = new JButton("JOIN GAME");
		btn_join.setBounds(70, 120, 150, 40);
		contentPane.add(btn_join);

		btn_exit = new JButton("EXIT");
		btn_exit.setBounds(70, 200, 150, 40);
		contentPane.add(btn_exit);
	}
}
