package no.hvl.dat110.system.controller;

import no.hvl.dat110.TODO;
import no.hvl.dat110.rpc.RPCClient;
import no.hvl.dat110.rpc.RPCServerStopStub;

public class Controller  {
	
	private static int N = 5;
	
	public static void main (String[] args) {
		
		Display display = new Display();
		Sensor sensor = new Sensor();
		
		RPCClient displayclient,sensorclient;
		
		System.out.println("Controller starting ...");
				
		RPCServerStopStub stopdisplay = new RPCServerStopStub();
		RPCServerStopStub stopsensor = new RPCServerStopStub();
		
		displayclient = new RPCClient(Common.DISPLAYHOST,Common.DISPLAYPORT);
		sensorclient = new RPCClient(Common.SENSORHOST,Common.SENSORPORT);
		
		displayclient.connect();
		display.register(displayclient);
		sensorclient.connect();
		sensor.register(sensorclient);
		
		// register stop methods in the RPC layer
		displayclient.register(stopdisplay);
		sensorclient.register(stopsensor);
		
		int temp;
		
		while(true) {
			try {
				temp = sensor.read();
			}
			catch(Throwable e) {
				break;
			}
			
			display.write(temp + "C");
		}
		// TODO:
		// loop while reading from sensor and write to display via RPC
		
		if (true) {
			throw new UnsupportedOperationException(TODO.method());
			}
		
		stopdisplay.stop();
		stopsensor.stop();
	
		displayclient.disconnect();
		sensorclient.disconnect();
		
		System.out.println("Controller stopping ...");
		
	}
}
