package ouza.project.explorer.file;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import ouza.project.launcher.Main;
import ouza.project.modele.CurrentFile;
import ouza.project.modele.ProjectModeleSelector;
import ouza.project.tools.FileManager;
import ouza.project.view.component.onglet.editor.EditorOnglet;
import ouza.project.view.component.onglet.editor.EditorOngletCreator;
import ouza.project.view.component.onglet.editor.OngletManager;
import ouza.project.view.component.popmenu.PopMenu;

public class PackageExplorer {

	private static File file;

	private static transient JTree tree;

	private static ProjectModeleSelector pMS;

	public final void creatTree(final String repertoire) {
		final File root = new File(repertoire);
		final FileTreeModel fileTreeModel = new FileTreeModel(root);

		tree = new JTree(fileTreeModel);
		tree.setCellRenderer(new ouza.project.explorer.FileRenderer());

		addTreeSelectionListener();
		addMouseListener();

		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);

		tree.setRootVisible(false);
	}

	private void addTreeSelectionListener() {
		tree.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(final TreeSelectionEvent event) {
				final TreePath path = event.getPath();
				file = (File) path.getLastPathComponent();

				pMS = new ProjectModeleSelector(file);
				CurrentFile.setpMS(pMS);
				
			}
		});
	}

	private void addMouseListener() {
		tree.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(final MouseEvent event) {
				final int buttonDown = event.getButton();
				if (event.getClickCount() == 2
						&& buttonDown == MouseEvent.BUTTON1) {
					fileOpen();
				}
			}

			@Override
			public void mousePressed(final MouseEvent event) {
				tree.setSelectionPath(tree.getClosestPathForLocation(
						event.getX(), event.getY()));
			}

			@Override
			public void mouseReleased(final MouseEvent event) {
				if (event.isPopupTrigger()) {
					new PopMenu().show(tree, event.getX(), event.getY());
				}
			}

			@Override
			public void mouseEntered(final MouseEvent event) {
				// method empty
			}

			@Override
			public void mouseExited(final MouseEvent event) {
				// method empty
			}
		});
	}

	public static void setTree(final JTree tre) {
		PackageExplorer.tree = tre;
	}

	public static JTree getTree() {
		return tree;
	}

	public static void fileOpen() {
		if (file.isFile() && OngletManager.openOneTimeOnglet(file)) {

			final String contenu = FileManager.lireFile(file.getAbsolutePath());

			new EditorOngletCreator(contenu).addOnglet(file.getName());

			final EditorOnglet editpane = (EditorOnglet) EditorOngletCreator
					.getSelectedTab();

			editpane.setProjectModeleSelector(pMS);

			Main.getWindow().setTitle(
					"Java 		- " + file.getAbsolutePath() + "		- OuZa");
		}
	}

	public static ProjectModeleSelector getProjectModeleSelector() {
		return pMS;
	}

	public static void setProjectModeleSelector(final ProjectModeleSelector pMSe) {
		PackageExplorer.pMS = pMSe;
	}

	public static final File getFile() {
		return file;
	}

	public static final void setFile(final File fil) {
		PackageExplorer.file = fil;
	}

	public static final ProjectModeleSelector getpMS() {
		return pMS;
	}

	public static final void setpMS(final ProjectModeleSelector pMSS) {
		PackageExplorer.pMS = pMSS;
	}

}