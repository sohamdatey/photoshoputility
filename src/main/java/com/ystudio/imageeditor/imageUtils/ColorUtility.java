package com.ystudio.imageeditor.imageUtils;

import java.awt.Rectangle;

import ij.process.ImageProcessor;

public class ColorUtility {

	public static boolean isTendentTowardsWhite(ImageProcessor imgProcessor, Rectangle workingRectangle) {
		int count = 0;
		int threshHold = 128;
		for (int i = 0; i < workingRectangle.width; i++) {
			for (int j = 0; j < workingRectangle.height; j++) {
				int[] rgb = new int[3];
				imgProcessor.getPixel(workingRectangle.x + i, workingRectangle.y + j, rgb);
				/*
				 * First convert the RGB color value to compute luminance by the
				 * following formula
				 */
				double luminance = 0.2126 * rgb[0] + 0.7152 * rgb[1] + 0.0722 * rgb[2];
				if (luminance > threshHold) {
					//is more near white
					count++;
				}
			}
		}
		return (count > (workingRectangle.height * workingRectangle.width) / 2) ? true : false;
	}

}
