package game;

import javax.swing.JFrame;

import client.ClientIO;
import common.Constant;
import screen.GameScreen;

public abstract class ShootingPlane {

	protected ClientIO io;
	
	public ShootingPlane() {
		io = new ClientIO(Constant.PORT);
		JFrame frame = new JFrame("ShootingPlane");
		frame.setSize(480, 640);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(new GameScreen(io));
		frame.setVisible(true);
	}

}
