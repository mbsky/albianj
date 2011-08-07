package org.albianj.kernel;

@Kernel
public final class AlbianBootService
{
	private static AlbianState state = AlbianState.Normal;
	public static void start()
	{
		return;
	}
	
	public static String requestHandlerContext()
	{
		if(AlbianState.Running != state)
		{
			return "Albian is not ready,Please wait a minute or contact administrators!";
		}
		return "";
	}
}
