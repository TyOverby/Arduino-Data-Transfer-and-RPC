package com.prealpha.byteprotocol;

public class Message {
	public static class MessageType{
		public static final byte UNKNOWN = 0;
		
		// 1 id 1 readPos
		public static final byte READ    = 1;
		// 1 id 0
		public static final byte CLEAR   = 2;
		// 3 id 2 pos value
		public static final byte SET     = 3;
		// 4 id n value1 value2 value3...
		public static final byte FILL    = 4;
		// 5 id 0
		public static final byte GRAB    = 5;
	}
	
	
	public final byte type;
	public final byte id;
	public final byte length;
	public final byte[] payload;
	
	public Message(byte type, byte id, byte... payload) throws IllegalArgumentException{
		this.type = type;
		this.id = id;
		this.payload = payload;
		
		if(payload.length>Byte.MAX_VALUE){
			throw new IllegalArgumentException("Payload is too high");
		}
		else{
			this.length = (byte) payload.length;
		}
	}
	
	public byte[] toBytes(){
		byte[] toReturn = new byte[length+3];
		toReturn[0] = type;
		toReturn[1] = id;
		toReturn[2] = length;
		
		for(int i=3,k=0; i<3+length;i++, k++){
			toReturn[i] = payload[k];
		}
		return toReturn;
	}
}
