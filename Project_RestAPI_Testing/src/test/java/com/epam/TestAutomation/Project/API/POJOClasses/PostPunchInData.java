package com.epam.TestAutomation.Project.API.POJOClasses;

public class PostPunchInData {
	String empNumber;
	String date;
	String time;
	String timezoneOffset;
	boolean forcePunchIn;
	
	public String getEmpNumber() {
		return empNumber;
	}
	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTimezoneOffset() {
		return timezoneOffset;
	}
	public void setTimezoneOffset(String timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}
	public boolean isforcePunchIn() {
		return forcePunchIn;
	}
	public void setforcePunchIn(boolean vforcePunchIn) {
		this.forcePunchIn = vforcePunchIn;
	}
}
