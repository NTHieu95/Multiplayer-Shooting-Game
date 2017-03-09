package server.sender;

import server.ServerIO;

public abstract class Sender implements Runnable {

	protected ServerIO io;
	protected boolean running = true;

	public Sender(ServerIO io){
		this.io = io;
	}
	
	@Override
	public void run() {
		while(running){
			running();
		}
	}
	
	public abstract void running();
}
