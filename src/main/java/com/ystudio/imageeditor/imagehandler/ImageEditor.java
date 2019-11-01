package com.ystudio.imageeditor.imagehandler;

import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;

import com.ystudio.imageeditor.imageUtils.ColorUtility;
import com.ystudio.imageeditor.imageio.ImageIO;

import ij.ImagePlus;
import ij.process.ImageProcessor;

public class ImageEditor {

	/**
	 * this would append the text at the bottom of the image
	 */
	private static int appendText(String directoryLocation, Font font)  {
		int filesProcessed = 0;
		int fileNameLocationAdjusterX = 120;
		int fileNameLocationAdjusterY = 10;

		for (final File fileEntry : ImageIO.listOfImagesInFolder(directoryLocation)) {
			
			
			ImagePlus imgPlus = new ImagePlus(directoryLocation + "/" + fileEntry.getName());
			
			ImageProcessor imgProcessor = imgPlus.getProcessor();
			
			int width = imgProcessor.getWidth();
			int height = imgProcessor.getHeight();
			int textStartingPointX = width - fileNameLocationAdjusterX;
			int textStartingPointY = height - fileNameLocationAdjusterY;

			Rectangle workingRectangle = new Rectangle(textStartingPointX, textStartingPointY,
					fileNameLocationAdjusterX, fileNameLocationAdjusterY);

			boolean isTendentTowardsWhite = ColorUtility.isTendentTowardsWhite(imgProcessor, workingRectangle);

			if (isTendentTowardsWhite) {
				// create black color
				Color color = new Color(0, 0, 0);
				imgProcessor.setColor(color);
			} else {
				// create white color
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
		return filesProcessed;
	}

	public static int processImages(String args[]) throws Exception {
		int filesProcessed = 0;
		String arguments[] = sanitizeAndValidateArguments(args);
		try {

			filesProcessed = appendText(arguments[0],
					new Font(arguments[1], Integer.parseInt(arguments[2]), Integer.parseInt(arguments[3])));
		} catch (Exception e) {
			
			 FileWriter fstream=new FileWriter(arguments[0]+ "/exceptions.txt");
	         BufferedWriter out=new BufferedWriter(fstream);
	         out.write(Arrays.asList(e.getStackTrace()).toString());
	         out.close();
	         
			throw new Exception("Please Enter Valid Details");
		}
		return filesProcessed;
	}


	


	private static String[] sanitizeAndValidateArguments(String[] args) throws Exception {
		
		String[] arguments = new String[4]; 
		arguments[1] = "Verdana";
		arguments[2] = "1";
		arguments[3] = "18";
		if(args!=null)
			{
				if(args[0]==null || args[0].trim().equals("")){
					throw new Exception("Please Enter Valid Location");
				}
				arguments[0] = args[0];
				if(args[1]!=null && !args[1].trim().equals("")){
					arguments[1] = args[1];
				}
				if(args[2]!=null && !args[2].trim().equals("")){
					arguments[2] = args[2];
				}
				if(args[3]!=null && !args[3].trim().equals("")){
					arguments[3] = args[3];
				}
			}
		else{
			throw new Exception("Sanitize Arguments Exception");
		}
		return arguments;
		
	}
}
