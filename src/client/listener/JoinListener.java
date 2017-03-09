package client.listener;

import client.ClientIO;
import common.Constant;

public class JoinListener extends Listener {

	public JoinListener(ClientIO io) {
		super(io);
	}

	@Override
	public void controll(Object obj) {
		if(obj instanceof String){
			String mess = (String) obj;
			if(mess.equals(Constant.JOIN_MESS)){
				
			}
		}
	}

}
