package com.alphaserve;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TESTG {

	public static void main(String[] args) {
		String[] arr = {"pasSW63or#" ,"PASSW63ord", "PASSW63ord"};
		System.out.println(passString(arr, "2#ro9"));
	}

	static String passString(String[] arr, String val) {
		String pass = "";
		int sum = 0;

		for (int i = 0; i < arr.length; i++) {
			sum = 0;

			String p = "(?<up>[A-Z]*)(?<di>\\d*)(?<end>%s)";
			String end = val.substring(1, 4);			
			
	        StringBuilder sb = new StringBuilder();
	        sb.append(end);
	        sb.reverse();
	        end = sb.toString();
			
			Pattern pattern = Pattern.compile(String.format(p, end));
			Matcher matcher = pattern.matcher(arr[i]);				
			
			if (matcher.find()) {	
				if(matcher.group("up").length() != Character.getNumericValue(val.charAt(0))) continue;
				if (!matcher.group("end").equals(end)) continue;
				
				char[] nums = matcher.group("di").toCharArray();
				for (int j = 0; j < nums.length; j++) {
					sum = sum + Character.getNumericValue(nums[j]);
				}
				
				if (sum != Character.getNumericValue(val.charAt(4))) continue;
				
				pass = arr[i];
			}			
		}

		return pass;
	}
}
