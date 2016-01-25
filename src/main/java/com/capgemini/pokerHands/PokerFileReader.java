package com.capgemini.pokerHands;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PokerFileReader {
	private String fileName;
	private File file;
	private Scanner reader;
	
	public PokerFileReader(String fileName) {
		this.fileName = fileName;
	}
	
	public String getNextLine() {
		if (this.reader == null)
			this.openFile();
		if (reader.hasNextLine())
			return reader.nextLine();
		reader.close();
		return "";
	}
	
	public boolean hasNextLine() {
		if (this.reader == null)
			this.openFile();
		return reader.hasNextLine();
	}
	
	private void openFile() {
		try {
			this.createScanner();
		} catch (InputMismatchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	private void createScanner() throws InputMismatchException, FileNotFoundException {
		file = new File(this.fileName);
		if (!file.exists())
			throw new FileNotFoundException("Nie znaleziono pliku " + this.fileName);
		if (!file.canRead())
			throw new InputMismatchException("Nie można odczytać pliku");
		reader = new Scanner(file);
	}
	
}
