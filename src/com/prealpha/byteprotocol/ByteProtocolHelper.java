package com.prealpha.byteprotocol;

public class ByteProtocolHelper {
	public static byte getNewId(){
		return 0;
	}
	
	public static Message makeReadMessage(byte pos){
		byte type      = Message.MessageType.READ;
		byte id        = getNewId();
		byte[] payload = {pos};
		
		return new Message(type,id,payload);
	}
	public static Message makeClearMessage(){
		byte type      = Message.MessageType.CLEAR;
		byte id        = getNewId();
		byte[] payload = {};
		
		return new Message(type,id,payload);
	}
	public static Message makeSetMessage(byte pos, byte value){
		byte type      = Message.MessageType.SET;
		byte id        = getNewId();
		byte[] payload = {pos,value};
		
		return new Message(type,id,payload);
	}
	public static Message makeFillMessage(byte... bytes){
		byte type      = Message.MessageType.FILL;
		byte id        = getNewId();
		byte[] payload = bytes;
		
		return new Message(type,id,payload);
	}
	public static Message makeGrabMessage(){
		byte type      = Message.MessageType.GRAB;
		byte id        = getNewId();
		byte[] payload = {};
		
		return new Message(type,id,payload);
	}
}
