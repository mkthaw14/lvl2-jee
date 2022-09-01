package com.mmit.model.service;

public class Service 
{
	private static boolean modelUpdated;
	public static boolean isModelUpdated()
	{
		return modelUpdated;
	}

	public static void setModelUpdated(boolean modelUpdated)
	{
		Service.modelUpdated = modelUpdated;
	}
}
