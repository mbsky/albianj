package org.albianj.protocol;

public class ManagementProtocol
{
	public static final byte REGISTER = 1;
	public static final byte REPORT = 2;
	public static final byte RESP = 127;
	
	public static final int HEADER_LEN = 10;
	public static final int HEADER_CMD_INDEX = 0;
	public static final int HEADER_BODYLEN_INDEX = 1;
	public static final int HEADER_STATE_INDEX = 9;
	
	public static final byte SUCCESS = 0;
	public static final byte ERROR_UNKOWN = 1;
	public static final byte ERROR_READ_HEADER = 2;
	public static final byte ERROR_READ_BODY = 3;
	public static final byte ERROR_UNREGISTER = 4;
	public static final byte ERROR_UNKOWN_COMMAND = 5;
	public static final byte EXCEPTION_REPEAT = 127;
	
}
