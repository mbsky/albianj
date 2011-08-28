package org.albianj.text;

import java.util.Arrays;

public class StringHelper
{
	public static String padLeft(String s, int length) {
		   byte[] bs = new byte[length];
		   byte[] ss = s.getBytes();
		   Arrays.fill(bs, (byte) (48 & 0xff));
		   System.arraycopy(ss, 0, bs, length - ss.length, ss.length);
		   return new String(bs);
		}
}
