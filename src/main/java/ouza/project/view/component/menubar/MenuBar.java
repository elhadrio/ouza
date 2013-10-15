package ouza.project.view.component.menubar;

import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import ouza.project.actions.OuZaAction;
import ouza.project.explorer.file.PackageExplorer;
import ouza.project.modele.CurrentFile;
import ouza.project.tools.IconLaoder;
import ouza.project.view.OuZaWindow;
import ouza.project.view.component.onglet.editor.EditorOngletCreator;

public class MenuBar {

	private final transient JMenuBar barreMenusMB;

	private transient JMenu editMenu;

	private transient JMenu fileMenu;

	private transient JMenu runMenu;

	private transient JMenu helpMenu;

	public MenuBar() {

		barreMenusMB = new JMenuBar();
		fileJMenuCreator();
		editMenuCreator();
		runMenuCreator();
		helpMenuCreator();
		addMouseListener();

	}

	public final void addMenuBar(final OuZaWindow fen) {
		fen.setJMenuBar(barreMenusMB);

	}

	private void addMouseListener() {
		barreMenusMB.addMouseMotionListener(new MouseMotionListener() {

			@Override
			public void mouseMoved(final MouseEvent mouseEvent) {
				if (EditorOngletCreator.isSelectectedTabNull()) {

					CurrentFile.setpMS(PackageExplorer
							.getProjectModeleSelector());
				} else {
					CurrentFile.setpMS(EditorOngletCreator.getSelectedTab()
							.getProjectModeleSelector());

				}
			}

			@Override
			public void mouseDragged(final MouseEvent mouseEvent) {
				// empty method
			}
		});
	}

	private void fileJMenuCreator() {
		fileMenu = new JMenu("File");
		fileMenu.addActionListener(OuZaAction.fileAction());

		final NewMenu newMenu = new NewMenu();
		fileMenu.add(newMenu.getNewMenu());
		openFileItemCreator();

		fileMenu.addSeparator();

		closeItemCreator();
		closeAllItemCreator();

		fileMenu.addSeparator();

		saveItemCreator();
		saveAllItemCreator();

		fileMenu.addSeparator();
		switchWorkItemCreator();
		barreMenusMB.add(fileMenu);
	}

	private void editMenuCreator() {
		editMenu = new JMenu("Edit");
		undoItemCreator();
		redoItemCreator();

		editMenu.addSeparator();

		copytemCreator();
		cutItemCreator();
		pasteItemCreator();

		editMenu.addSeparator();

		selectAllItemCreator();
		barreMenusMB.add(editMenu);

	}

	private void runMenuCreator() {
		runMenu = new JMenu("Run");
		runItemCreator();
		barreMenusMB.add(runMenu);
	}

	private void helpMenuCreator() {
		helpMenu = new JMenu("Help");
		aboutMeItemCreator();
		barreMenusMB.add(helpMenu);
	}

	private void aboutMeItemCreator() {
		final JMenuItem aboutMeMI = new JMenuItem("OuZA");
		helpMenu.add(aboutMeMI);

	}

	private void openFileItemCreator() {
		/*
		 * final JMenuItem openFileMI = new JMenuItem("  openfile	");
		 * openFileMI.setIcon(OuZaWindow.getChargeur().getIcon("open16.png"));
		 * OuZaAction.openFiletAction(openFileMI); fileMenu.add(openFileMI);
		 */

	}

	private void closeItemCreator() {
		final JMenuItem closeMI = new JMenuItem("  Close ");
		closeMI.setIcon(IconLaoder.CLOSE_ICON);
		closeMI.addActionListener(OuZaAction.close());
		fileMenu.add(closeMI);

	}

	private void closeAllItemCreator() {
		/*
		 * final JMenuItem closeAllMI = new JMenuItem("  Close All");
		 * OuZaAction.closeALL(closeAllMI); fileMenu.add(closeAllMI);
		 */

	}

	private void saveItemCreator() {
		final JMenuItem saveMI = new JMenuItem("  Save");
		saveMI.setIcon(IconLaoder.SAVE_ICON);

		saveMI.addActionListener(OuZaAction.save());
		fileMenu.add(saveMI);

	}

	private void saveAllItemCreator() {
		final JMenuItem saveAllMI = new JMenuItem("  Save All	");
		saveAllMI.setIcon(IconLaoder.SAVEALL_ICON);
		saveAllMI.addActionListener(OuZaAction.saveALL());
		fileMenu.add(saveAllMI);

	}

	private void switchWorkItemCreator() {
		final JMenuItem switchWorkMI = new JMenuItem("  Switch Workspace");
		switchWorkMI.addActionListener(OuZaAction.switchWork());
		fileMenu.add(switchWorkMI);

	}

	private void copytemCreator() {
		final JMenuItem copyMI = new JMenuItem("  Copy	");
		copyMI.setIcon(IconLaoder.COPY_ICON);

		copyMI.addActionListener(OuZaAction.copy());

		editMenu.add(copyMI);
	}

	private void cutItemCreator() {
		final JMenuItem cutMI = new JMenuItem("  Cut	");
		cutMI.setIcon(IconLaoder.CUT_ICON);

		cutMI.addActionListener(OuZaAction.cut());
		editMenu.add(cutMI);
	}

	private void pasteItemCreator() {
		final JMenuItem pasteMI = new JMenuItem("  Paste	");
		pasteMI.setIcon(IconLaoder.PASTE_ICON);

		pasteMI.addActionListener(OuZaAction.paste());
		editMenu.add(pasteMI);
	}

	private void selectAllItemCreator() {
		final JMenuItem selectAllMI = new JMenuItem("  Sellect All	 ");
		selectAllMI.setIcon(IconLaoder.SELLECT_ALL_ICON);

		selectAllMI.addActionListener(OuZaAction.selectAll());
		editMenu.add(selectAllMI);

	}

	private void undoItemCreator() {

		final JMenuItem undoMI = new JMenuItem("  Undo	");
		undoMI.setIcon(IconLaoder.UNDO_ICON);
		
		// OuZaAction.undoAction(undoMI);
		editMenu.add(undoMI);

	}

	private void redoItemCreator() {
		final JMenuItem redoMI = new JMenuItem("  Redo	");
		redoMI.setIcon(IconLaoder.REDO_ICON);
		redoMI.setAccelerator(KeyStroke.getKeyStroke('y', Toolkit
				.getDefaultToolkit().getMenuShortcutKeyMask(), false));
		// OuZaAction.redoAction(redoMI);
		editMenu.add(redoMI);
	}

	private void runItemCreator() {
		final JMenuItem runMI = new JMenuItem("Run");
		runMI.addActionListener(OuZaAction.run());
		runMenu.add(runMI);

	}

}
