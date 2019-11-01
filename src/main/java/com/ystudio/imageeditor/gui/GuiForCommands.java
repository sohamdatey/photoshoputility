package com.ystudio.imageeditor.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.ystudio.imageeditor.imagehandler.ImageEditor;

public class GuiForCommands extends JFrame implements ActionListener {

	// JTextField
	static JTextField location;
	// JTextField
	static JTextField fontName;
	// JTextField
	static JTextField fontType;
	// JTextField
	static JTextField fontSize;

	static JLabel locationLabel;
	static JLabel fontNameLabel;
	static JLabel fontTypeLabel;
	static JLabel fontSizeLabel;

	// JFrame
	static JFrame f;
	// JButton
	static JButton b;
	// label to diaplay text
	static JLabel l;
	// default constructor
	static JLabel processingAppenders;

	GuiForCommands() {
	}

	// main class
	public static void main(String[] args) {
		// create a new frame to stor text field and button
		f = new JFrame("Batch Image Processor");
		// create a label to display text
		l = new JLabel("");

		// create a new button
		b = new JButton("Run");
		// create a object of the text class
		GuiForCommands te = new GuiForCommands();
		// addActionListener to button
		b.addActionListener(te);
		// create a object of JTextField with 16 columns
		location = new JTextField(15);
		// create a panel to add buttons and textfield

		processingAppenders = new JLabel();
		locationLabel = new JLabel();
		fontTypeLabel = new JLabel();
		fontNameLabel = new JLabel();
		fontSizeLabel = new JLabel();
		processingAppenders = new JLabel();

		locationLabel.setText("Enter Location");
		fontTypeLabel.setText("Enter Font Type");
		fontNameLabel.setText("Enter Font Name");
		fontSizeLabel.setText("Enter Font Size");

		fontName = new JTextField(15);
		fontType = new JTextField(15);
		fontSize = new JTextField(15);

		JPanel p = new JPanel();

		// add buttons and textfield to panel
		p.add(locationLabel);
		p.add(location);
		p.add(fontNameLabel);
		p.add(fontName);
		p.add(fontTypeLabel);
		p.add(fontType);
		p.add(fontSizeLabel);
		p.add(fontSize);
		p.add(b);
		p.add(l);
		p.add(processingAppenders);

		// add panel to frame
		f.add(p);

		// set the size of frame
		f.setSize(300, 500);

		f.show();
	}

	// if the button is pressed
	public void actionPerformed(ActionEvent e) {
		String s = e.getActionCommand();
		if (s.equals("Run")) {
			// set the text of the label to the text of the field
			if (location.getText().equals("")) {
				l.setText("Please Enter A Folder Location");
			} else {
				l.setText("You Entered Location : " + location.getText());

				String arguments[] = new String[4];
				arguments[0] = location.getText().trim();
				arguments[1] = fontName.getText().trim();
				arguments[2] = fontType.getText().trim();
				arguments[3] = fontSize.getText().trim();

				try {
					ImageEditor.processImages(arguments);
					processingAppenders.setText("Processing Done.");
				} catch (Exception exception) {
					processingAppenders.setText(exception.toString());
				}
			}

		}
	}
}
