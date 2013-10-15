package ouza.project.explorer.file;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class FileTreeModel implements TreeModel {
	private final transient File root;

	public  FileTreeModel(final File file) {
		root = file;
	}

	public final List<File> getFichiers(final Object parent) {
		final File fileParent = (File) parent;
		final File[] fichiers = fileParent.listFiles(new FileFilter() {

			@Override
			public boolean accept(final File file) {

				if (!isDerictoryBin(file)
						&& !file.getName().trim().equals("medata")) {

					return true;

				}
				return false;

			}

			private boolean isDerictoryBin(final File file) {
				return file.getName().trim().equals("bin");
			}

		});
		Arrays.sort(fichiers, new Comparator<File>() {
			public final int compare(final File file1, final File file2) {
				final boolean dirf1 = file1.isDirectory();
				final boolean dirf2 = file2.isDirectory();
				if (dirf1 && !dirf2) {
					return -1;
				}
				if (!dirf1 && dirf2) {
					return 1;
				}
				return file1.getPath().compareTo(file2.getPath());
			}
		});
		return Arrays.asList(fichiers);
	}

	public final Object getRoot() {
		return root;
	}

	public final Object getChild(final Object parent, final int index) {
		return getFichiers(parent).get(index);
	}

	public final int getChildCount(final Object parent) {
		return getFichiers(parent).size();
	}

	public final int getIndexOfChild(final Object parent, final Object child) {
		return getFichiers(parent).indexOf(child);
	}

	public final  boolean isLeaf(final Object node) {

		return !((File) node).isDirectory();
	}

	public void valueForPathChanged(final TreePath path, final Object newValue) {
	//method empty
	}

	public void addTreeModelListener(final TreeModelListener listener) {
		//method empty
	}

	public void removeTreeModelListener(final TreeModelListener linstener) {
		//method empty
	}
}