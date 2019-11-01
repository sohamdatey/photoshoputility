package com.ystudio.imageeditor.imageio;

import java.io.File;

import ij.ImagePlus;
import ij.io.FileSaver;

public class ImageIO {
	
	public static File[] listOfImagesInFolder(String directoryLocation) {
		final File folder = new File(directoryLocation);
		return folder!=null && folder.listFiles().length>0 ?folder.listFiles():new File[0];
	}

	public static void saveFile(String directoryLocation, final File fileEntry, ImagePlus imgPlus) {
		FileSaver fs = new FileSaver(imgPlus);
		
		String imageEditorOutput =directoryLocation +"/sohamRocks";
		File directory = new File(imageEditorOutput);
		if (! directory.exists()){
		    directory.mkdir();
		    // If you require it to make the entire directory path including parents,
		    // use directory.mkdirs(); here instead.
		}
	
		fs.saveAsJpeg(imageEditorOutput + "/" + fileEntry.getName());
	}
}