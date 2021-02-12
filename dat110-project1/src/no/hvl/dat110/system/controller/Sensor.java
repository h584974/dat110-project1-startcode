package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.*;

public class Sensor extends RPCStub {

	private byte RPCID = 1;
	
	public int read() {
		
		int temp = RPCUtils.unmarshallInteger(rpcclient.call(RPCUtils.marshallVoid(RPCID)));
		
		return temp;
	}
	
}
