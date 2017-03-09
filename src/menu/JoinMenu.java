package menu;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import client.ClientIO;
import common.Constant;
import common.Serializer;
import domain.Room;
import game.JoinGame;

public class JoinMenu extends JFrame implements Runnable {
	private String nickname;
	private JTable table;
	private ArrayList<Room> rooms;
	private ClientIO io;

	public JoinMenu(String nickname) {
		this.nickname = nickname;
		initComponent();
		init(Constant.PORT);
	}

	private void init(int port) {
		io = new ClientIO(port);
		rooms = new ArrayList<Room>();
		Thread thread = new Thread(this);
		thread.start();
		io.sendToServer(Constant.JOIN_MESS);
	}

	private void initComponent() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(41, 26, 700, 500);
		getContentPane().add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (table.getSelectedRow() >= 0) {
					int check = JOptionPane.showConfirmDialog(null, "Are you sure", "Are you sure",
							JOptionPane.YES_NO_OPTION);
					if (check == JOptionPane.YES_OPTION) {
						dispose();
						JoinGame game = new JoinGame(nickname, rooms.get(table.getSelectedRow()).getId());
					}
				}
			}
		});
	}

	private void genTableContent() {
		try {
			Vector<String> row, colunm;
			int count = 1;
			DefaultTableModel model = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			};
			String[] colunmNames = { "#", "Title", "Level", "Available" };
			colunm = new Vector<String>();
			int numberColumn;
			numberColumn = colunmNames.length;
			for (int i = 0; i < numberColumn; i++) {
				colunm.add(colunmNames[i]);
			}
			model.setColumnIdentifiers(colunm);
			for (Room r : rooms) {
				row = new Vector<String>();
				row.add(r.getId() + "");
				row.add(r.getTitle());
				row.add(r.getLevel() + "");
				row.add("");
				count++;
				model.addRow(row);
			}
			table.setModel(model);
			table.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void run() {
		byte[] buf = new byte[1024];
		DatagramPacket packet;
		try {
			packet = new DatagramPacket(buf, buf.length, InetAddress.getByName(Constant.CLIENT_HOST), Constant.PORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		while (true) {
			packet = io.getPacket();
			byte[] data = packet.getData();
			try {
				Object obj = Serializer.deserialize(data);
				if (obj != null) {
					if (obj instanceof Room) {
						Room r = (Room) obj;
						rooms.add(r);
						genTableContent();
					}
				}
			} catch (Exception e) {
				String mess = new String(data);
				System.out.println(mess);
			}
		}
	}
}
