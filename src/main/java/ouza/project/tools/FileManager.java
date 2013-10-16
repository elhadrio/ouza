package ouza.project.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;

import ouza.project.logger.OuZaLogger;
import ouza.project.view.component.onglet.editor.EditorOngletCreator;
import ouza.project.view.component.onglet.editor.OngletManager;

public final class FileManager {
	private static BufferedReader reader;

	private FileManager() {
		// empty constructor

	}

	// delete a directory
	public static void deleteDir(final File dir) {

		if (dir.isFile()) {
			dir.delete();
		}
		if (dir.isDirectory()) {
			final File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isDirectory()) {
					deleteDir(files[i]);
					files[i].delete();
				} else {
					files[i].delete();
				}
			}
			dir.delete();
		}
		if (dir.exists()) {
			deleteDir(dir);
		}

	}

	public static File[] searchDirectoryByName(final File parant,
			final String fileName) {

		final File[] files = parant.listFiles(new FilenameFilter() {

			@Override
			public boolean accept(final File dir, final String name) {
				boolean returnStatment = false;
				if (name.equals(fileName)) {
					returnStatment = true;
				}
				return returnStatment;
			}
		});

		return files;

	}

	// read the content of a file
	public static String lireFile(final String path) {
		StringBuffer buf = null;

		try {

			reader = new BufferedReader(new FileReader(path));
			String ligne;
			buf = new StringBuffer();
			ligne = reader.readLine();
			while (ligne  != null) {
				buf.append(ligne + (char) '\n');

			}

		} catch (FileNotFoundException e) {
			OuZaLogger.LOGGER.error("specified file not found", e);
		} catch (IOException e) {
			OuZaLogger.LOGGER.error("IOException", e);
		}

		return buf.toString();
	}

	// save a file
	public static void saveFile(final String url, final String text) {

		OngletManager.saveNotice(EditorOngletCreator.getTabbedpane()
				.getSelectedIndex());

		try {
			final PrintWriter print = new PrintWriter(new FileWriter(new File(
					url)));
			print.print(text);
			print.close();

		} catch (FileNotFoundException e1) {
			OuZaLogger.LOGGER.error("specified file not found", e1);
		} catch (IOException e1) {
			OuZaLogger.LOGGER.error("specified file not found", e1);
		}
	}

	// get file name from a path
	public static String nameFile(final String pathFile) {
		final String name;
		if ("".equals(pathFile)) {
			name = "";

		} else {
			name = new File(pathFile).getName();
		}
		return name;
	}

	public static File parent(final File file) {
		return file.getParentFile();
	}

	public static File grandparent(final File file) {
		return file.getParentFile().getParentFile();
	}

	public static File granddparent(final File file) {
		return file.getParentFile().getParentFile().getParentFile();
	}
}