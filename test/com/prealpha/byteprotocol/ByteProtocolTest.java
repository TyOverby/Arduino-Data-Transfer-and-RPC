package com.prealpha.byteprotocol;

import org.junit.Test;
import static org.junit.Assert.*;

public class ByteProtocolTest {

	@Test
	public void testMessageTypes(){
		final byte MESSAGE_TYPE_LOCATION = 0;

		// READ
		Message readMessage = ByteProtocolHelper.makeReadMessage((byte) 5);
		assertEquals(1, readMessage.toBytes()[MESSAGE_TYPE_LOCATION]);

		// CLEAR
		Message clearMessage = ByteProtocolHelper.makeClearMessage();
		assertEquals(2,clearMessage.toBytes()[MESSAGE_TYPE_LOCATION]);

		// SET
		Message setMessage = ByteProtocolHelper.makeSetMessage((byte)10, (byte)10);
		assertEquals(3, setMessage.toBytes()[MESSAGE_TYPE_LOCATION]);

		// FILL
		Message fillMessage = ByteProtocolHelper.makeFillMessage((byte)10,(byte)20);
		assertEquals(4,fillMessage.toBytes()[MESSAGE_TYPE_LOCATION]);

		// GRAB
		Message grabMessage = ByteProtocolHelper.makeGrabMessage();
		assertEquals(5,grabMessage.toBytes()[MESSAGE_TYPE_LOCATION]);
	}

	@Test 
	public void testLength(){
		final byte LENGTH_LOCATION = 2;

		// READ
		Message readMessage = ByteProtocolHelper.makeReadMessage((byte) 5);
		assertEquals(1, readMessage.toBytes()[LENGTH_LOCATION]);

		// CLEAR
		Message clearMessage = ByteProtocolHelper.makeClearMessage();
		assertEquals(0, clearMessage.toBytes()[LENGTH_LOCATION]);

		// SET
		Message setMessage = ByteProtocolHelper.makeSetMessage((byte)10, (byte)10);
		assertEquals(2, setMessage.toBytes()[LENGTH_LOCATION]);

		// FILL
		Message fillMessage = ByteProtocolHelper.makeFillMessage(new byte[5]);
		assertEquals(5, fillMessage.toBytes()[LENGTH_LOCATION]);

		// GRAB
		Message grabMessage = ByteProtocolHelper.makeGrabMessage();
		assertEquals(0,grabMessage.toBytes()[LENGTH_LOCATION]);
	}

	@Test 
	public void testData(){
		final byte DATA_LOCATION = 3;
		final byte LENGTH_LOCATION = 2;

		// READ
		{
			byte data = 5;
			Message readMessage = ByteProtocolHelper.makeReadMessage(data);
			assertEquals(data, readMessage.toBytes()[DATA_LOCATION]);
		}

		// SET
		{
			byte pos = 5;
			byte data = 10;
			Message setMessage = ByteProtocolHelper.makeSetMessage(pos,data);
			assertEquals(pos, setMessage.toBytes()[DATA_LOCATION]);
			assertEquals(data, setMessage.toBytes()[DATA_LOCATION+1]);
		}

		// FILL
		{
			byte[] data = {5,3,60,28};
			Message fillMessage = ByteProtocolHelper.makeFillMessage(data);
			byte[] bytes = fillMessage.toBytes();

			for(int i=DATA_LOCATION,k=0;i<bytes[LENGTH_LOCATION];i++,k++){
				assertEquals(data[k], bytes[i]);
			}
		}
	}
}
