package server;

import java.net.InetAddress;

import domain.User;

public class ServerPlayer extends User {

	private int port;
	private InetAddress address;

	public ServerPlayer(String name) {
		super(name);
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}

	public InetAddress getAddress() {
		return address;
	}

	public void setAddress(InetAddress address) {
		this.address = address;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ServerPlayer) {
			if (((ServerPlayer) obj).getPort() == port) {
				return true;
			}
		}
		return false;
	}
}
