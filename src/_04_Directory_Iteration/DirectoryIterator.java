package _04_Directory_Iteration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;

public class DirectoryIterator {
	public static void main(String[] args) {
		// Choose the src directory using JFileChooser
		JFileChooser jfc = new JFileChooser();
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = jfc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File directory = jfc.getSelectedFile();
			addCopyrightToJavaFiles(directory);
		}
	}

	public static void addCopyrightToJavaFiles(File directory) {
		// Get all files and directories in the current directory
		File[] files = directory.listFiles();
		if (files != null) {
			for (File f : files) {
				if (f.isDirectory()) {
					// If the file is a directory, recursively call this method
					addCopyrightToJavaFiles(f);
				} else if (f.getName().endsWith(".java")) {
					// If the file is a .java file, add the copyright statement
					addCopyright(f);
				}
			}
		}
	}

	public static void addCopyright(File javaFile) {
		try (FileWriter fw = new FileWriter(javaFile, true)) {
			// Add a new line and the copyright statement at the end of the file
			fw.write("\n//Copyright © 2019 FirstName LastName\n");
			System.out.println("Added copyright to " + javaFile.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
