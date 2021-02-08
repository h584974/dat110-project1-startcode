package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.util.Arrays;
import no.hvl.dat110.TODO;

public class RPCUtils {

	// Utility methods for marshalling and marshalling of parameters and return values
	// in RPC request and RPC responses
	// data bytearrays and return byte arrays is according to the 
	// RPC message syntax [rpcid,parameter/return value]
	
	public static byte[] marshallString(byte rpcid, String str) {

		byte[] stringBytes = str.getBytes();
		byte[] encoded = new byte[stringBytes.length + 1];
		encoded[0] = rpcid;
		
		for(int i = 1; i < encoded.length; i++) {
			encoded[i] = stringBytes[i - 1];
		}

		return encoded;
	}

	public static String unmarshallString(byte[] data) {

		byte[] stringBytes = Arrays.copyOfRange(data, 1, data.length);
		String decoded = new String(stringBytes);

		return decoded;
		
	}

	public static byte[] marshallVoid(byte rpcid) {

		byte[] encoded = new byte[1];
		encoded[0] = rpcid;

		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {

		return;
		
	}

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) {

		ByteBuffer b = ByteBuffer.allocate(4);
		b.putInt(x);
		byte[] intAsBytes = b.array();
		
		byte[] encoded = new byte[5];
		encoded[0] = rpcid;
		
		for(int i = 1; i < encoded.length; i++) {
			encoded[i] = intAsBytes[i - 1];
		}

		return encoded;
		
	}

	public static int unmarshallInteger(byte[] data) {

		int decoded = ByteBuffer.wrap(data).getInt();

		return decoded;

	}
}
