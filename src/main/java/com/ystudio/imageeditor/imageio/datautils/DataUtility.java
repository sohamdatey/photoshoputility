package com.ystudio.imageeditor.imageio.datautils;

public class DataUtility {

	public static <T> boolean  isArrayHavingNullElements(T array[]) {
		for (T t : array) {
			if(t==null){
				return true;
			}
		} 
       return false;
	}
	
}
