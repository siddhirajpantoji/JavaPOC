package com.travelex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Streaming {
	public static void main(String[] args) {
		
		Integer[] arr = {1,2,3,4,5,6,7,8,90};
		List<Integer> allInt = new ArrayList<>(Arrays.asList(arr));
		System.out.println(filter(allInt, 2, 2));
	}
	
	public static List<Integer> filter(List<Integer> source, int start , int end )
	{
		System.out.println(source);
		List<Integer> items = source.stream().skip(end).limit(start).collect(Collectors.toList());
		System.out.println(items);
		return items;
	}
}