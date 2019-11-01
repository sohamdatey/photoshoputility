package com.ystudio.imageeditor.imageio;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import ij.ImagePlus;
import ij.io.FileSaver;
import ij.process.ImageProcessor;

public class ImageIO {

	private static void imageTest() {

		final File folder = new File("S:/javaProjects/photoshopUtility/from");

		for (final File fileEntry : folder.listFiles()) {

			System.out.println(fileEntry.getName());

			ImagePlus imgPlus = new ImagePlus("S:/javaProjects/photoshopUtility/from/" + fileEntry.getName());
			ImageProcessor imgProcessor = imgPlus.getProcessor();

			int width = imgPlus.getImage().getWidth(imgPlus);
			int height = imgPlus.getImage().getHeight(imgPlus);
			int textStartingPointX =  width - 120;
			int textStartingPointY =  height - 10; 
			
			Rectangle workingRectangle = new Rectangle(width - 120, height - 10, 120, 10);
			System.out.println("width : " + width + " and heigth : " + height);

			boolean isTendentTowardsWhite = isTendentTowardsWhite(imgProcessor, workingRectangle);

			
			

			if(isTendentTowardsWhite){
				Color color = new Color(0, 0, 0);
				imgProcessor.setColor(color);
			}else {
				Color color = new Color(255, 255, 255);
				imgProcessor.setColor(color);
			}
			
			Font font = new Font("Verdana", 1, 18);
			imgProcessor.setFont(font);

			// width>height landscape
			// draw file name
			String [] extentions =fileEntry.getName().split("(\\.+)");
			imgProcessor.drawString(fileEntry.getName().replace("."+extentions[extentions.length-1], ""), textStartingPointX, textStartingPointY);

			imgPlus.setProcessor(imgProcessor);
			FileSaver fs = new FileSaver(imgPlus);
			fs.saveAsJpeg("S:/javaProjects/photoshopUtility/to/" + fileEntry.getName());
		}

	}

	private static boolean isTendentTowardsWhite(ImageProcessor imgProcessor, Rectangle workingRectangle) {
		
		int count =0;
		int threshHold=128;
		for (int i = 0; i < workingRectangle.width; i++) {
			for (int j = 0; j < workingRectangle.height; j++) {
				int[] rgb = new int[3];
				imgProcessor.getPixel(workingRectangle.x+i, workingRectangle.y+j, rgb);
				/*First convert the RGB color value to compute luminance by the following formula*/
				double luminance = 0.2126*rgb[0] + 0.7152*rgb[1] + 0.0722*rgb[2];
				if(luminance>threshHold){
					count++;
				}
			}
		}
		return (count>(workingRectangle.height*workingRectangle.width)/2)? true: false; 
	}

	/**
	 * 
	 */
	private static void imageTest2() {

		final File folder = new File("S:/javaProjects/photoshopUtility/from");

		try {

			for (final File fileEntry : folder.listFiles()) {
				System.out.println(fileEntry.getName());

				final BufferedImage image = javax.imageio.ImageIO.read(fileEntry);

				Graphics g = image.getGraphics();

				Font f = g.getFont().deriveFont(20f);
				Color color = new Color(0, 0, 0);
				g.setFont(f);

				if (image.getWidth() > image.getHeight()) {
					// landscape
					g.drawString(fileEntry.getName().replace(".jpg", ""), 488, 390);
				} else {
					// portrait
					g.drawString(fileEntry.getName().replace(".jpg", ""), 290, 590);
				}
				g.dispose();

				javax.imageio.ImageIO.write(image, "png",
						new File("S:/javaProjects/photoshopUtility/to/" + fileEntry.getName() + ".jpg"));
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		imageTest();
		System.out.println("hello");
	}

}