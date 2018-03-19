package com.travelex.utils;
import java.util.Arrays;
import java.util.List;


public enum TestEnum {
	PROP_NAME("abc",Arrays.asList(new String[] {"abc","jjj","daata"})),
	PROP_NAME1("abc",Arrays.asList(new String[] {"abc","jjj","daata"})),
	;
	
	final String a;
	final List<String> b;
	private TestEnum(final String a, final List<String> b) {
		// TODO Auto-generated constructor stub
		this.a  = a;
		this.b = b;
	}
	public String getA() {
		return a;
	}
	public List<String> getB() {
		return b;
	}

	
}
