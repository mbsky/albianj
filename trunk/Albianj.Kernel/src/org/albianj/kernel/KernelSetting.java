package org.albianj.kernel;

public class KernelSetting
{
	private static String kernelId = null;
	private static String kernelKey = null;
	
	public static String getKernelId()
	{
		return kernelId;
	}
	public static void setKernelId(String kernelId)
	{
		KernelSetting.kernelId = kernelId;
	}
	public static String getKernelKey()
	{
		return kernelKey;
	}
	public static void setKernelKey(String kernelKey)
	{
		KernelSetting.kernelKey = kernelKey;
	}
}
