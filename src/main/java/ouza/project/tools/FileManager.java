package ouza.project.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

import ouza.project.logger.OuZaLogger;
import ouza.project.view.component.onglet.editor.EditorOngletCreator;
import ouza.project.view.component.onglet.editor.OngletManager;

public final class FileManager {

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
		BufferedReader reader = null;
		try {

			reader = new BufferedReader(new FileReader(path));
			String ligne = "";
			buf = new StringBuffer();

			while ((ligne = reader.readLine()) != null) {

				buf.append(ligne + (char) '\n');
			}
			reader.close();
		} catch (FileNotFoundException e) {
			OuZaLogger.LOGGER.error("specified file not found", e);
		} catch (IOException e) {
			OuZaLogger.LOGGER.error("IOException", e);
		} finally {
			IOUtil.closeQuietly(reader);
		}

		return buf.toString();
	}

	// save a file
	public static void saveFile(final String url, final String text) {

		OngletManager.saveNotice(EditorOngletCreator.getTabbedpane()
				.getSelectedIndex());

		FileWriter fw = null;
		BufferedWriter bw = null;
		try {

			fw = new FileWriter(new File(url).getAbsolutePath());
			bw = new BufferedWriter(fw);
			bw.write(text);
			bw.close();
		} catch (IOException e) {
			OuZaLogger.LOGGER.error("specified file not found", e);
		} finally {
			IOUtil.closeQuietly(fw, bw);
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