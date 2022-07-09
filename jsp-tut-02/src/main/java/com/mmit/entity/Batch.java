package com.mmit.entity;

import java.time.LocalDate;

public class Batch
{
	public Batch(String name, LocalDate date)
	{
		super();
		this.name = name;
		this.date = date;
	}
	private String name;
	private LocalDate date;
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public LocalDate getDate()
	{
		return date;
	}
	public void setDate(LocalDate date)
	{
		this.date = date;
	}
}
