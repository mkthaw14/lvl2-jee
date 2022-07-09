package com.mmit.entity;

import java.time.LocalDate;

public class Student
{
	private String name;
	private String phone;
	private String email;
	private String batchName;
	private LocalDate registeredDate;
	private boolean old_student;
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPhone()
	{
		return phone;
	}
	public void setPhone(String phone)
	{
		this.phone = phone;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public LocalDate getRegisteredDate()
	{
		return registeredDate;
	}
	public void setRegisteredDate(LocalDate registeredDate)
	{
		this.registeredDate = registeredDate;
	}
	public boolean isOld_student()
	{
		return old_student;
	}
	public void setOld_student(boolean old_student)
	{
		this.old_student = old_student;
	}
	public String getBatchName()
	{
		return batchName;
	}
	public void setBatchName(String batchName)
	{
		this.batchName = batchName;
	}
}
