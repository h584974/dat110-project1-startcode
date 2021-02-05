package no.hvl.dat110.messaging;

import java.util.Arrays;
import no.hvl.dat110.TODO;

public class Message {

	private byte[] payload;

	public Message(byte[] payload) {
		this.payload = payload.length <= 128 ? payload : null;
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	public byte[] encapsulate() {
		
		byte[] encoded = new byte[128];
		
		for(int i = 0; i < 128; i++) {
			
			if(i == 0) {
				encoded[i] = (byte)payload.length;
			}
			else if(i < payload.length + 1) {
				encoded[i] = payload[i - 1];
			}
			else {
				encoded[i] = (byte)0;
			}
			
		}

		return encoded;	
	}

	public void decapsulate(byte[] received) {
		
		int numberOfBytes = received[0];
		byte[] newPayload = new byte[numberOfBytes];
		
		for(int i = 1; i <= numberOfBytes; i++) {
			
			if(i < payload.length + 1) {
				newPayload[i - 1] = received[i];
			}
			else {
				break;
			}
			
		}
		
		payload = newPayload;
	}
}
