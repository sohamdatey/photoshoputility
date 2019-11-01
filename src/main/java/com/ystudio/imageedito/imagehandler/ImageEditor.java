package com.ystudio.imageedito.imagehandler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.File;

import com.ystudio.imageedito.imageUtils.ColorUtility;
import com.ystudio.imageeditor.imageio.ImageIO;

import ij.ImagePlus;
import ij.process.ImageProcessor;

public class ImageEditor {

	/**
	 * this would append the text at the bottom  of the image 
	 */
	private static void  appendText(String directoryLocation, Font font ) {
      int filesProcessed =0;
      int fileNameLocationAdjusterX=120;
      int fileNameLocationAdjusterY=10;
		for (final File fileEntry : ImageIO.listOfImagesInFolder(directoryLocation)) {
			
			ImagePlus imgPlus = new ImagePlus(directoryLocation +"/" + fileEntry.getName());
			ImageProcessor imgProcessor = imgPlus.getProcessor();

			int width = imgPlus.getImage().getWidth(imgPlus);
			int height = imgPlus.getImage().getHeight(imgPlus);
			int textStartingPointX = width - fileNameLocationAdjusterX;
			int textStartingPointY = height - fileNameLocationAdjusterY;

			Rectangle workingRectangle = new Rectangle(textStartingPointX, textStartingPointY, fileNameLocationAdjusterX, fileNameLocationAdjusterY);

			boolean isTendentTowardsWhite = ColorUtility.isTendentTowardsWhite(imgProcessor, workingRectangle);

			if (isTendentTowardsWhite) {
				Color color = new Color(0, 0, 0);
				imgProcessor.setColor(color);
			} else {
				Color color = new Color(255, 255, 255);
				imgProcessor.setColor(color);
			}
			
			imgProcessor.setFont(font);

			// width>height landscape
			// draw file name
			String[] extentions = fileEntry.getName().split("(\\.+)");
			imgProcessor.drawString(fileEntry.getName().replace("." + extentions[extentions.length - 1], ""),
					textStartingPointX, textStartingPointY);

			imgPlus.setProcessor(imgProcessor);
			ImageIO.saveFile(directoryLocation, fileEntry, imgPlus);
			filesProcessed++;
		}

	}

	public static void main(String[] args) {
		appendText("S:/javaProjects/photoshopUtility/from",new Font("Verdana", 1, 18));
		System.out.println("done");
	}
}
