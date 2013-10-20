package ouza.project.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.JTree;

import ouza.project.explorer.file.PackageExplorer;
import ouza.project.modele.CurrentFile;
import ouza.project.view.component.menubar.MenuBar;
import ouza.project.view.component.onglet.console.ConsoleAction;
import ouza.project.view.component.onglet.console.ConsoleOngletCreator;
import ouza.project.view.component.onglet.editor.EditorOnglet;
import ouza.project.view.component.onglet.editor.EditorOngletCreator;
import ouza.project.view.component.onglet.editor.OngletManager;
import ouza.project.view.component.toolbar.ToolBar;

public class OuZaWindow extends JFrame implements WindowListener {

	private static final int NUMBER_20 = 20;

	private static final int NUMBER_80 = 80;

	private static final int NUMBER_700 = 700;

	private static final int NUMBER_1300 = 1300;

	private static final int NUMBER_425 = 425;

	private static final int NUMBER_150 = 150;

	private static final long serialVersionUID = 1L;

	private static JTree explorerTree;

	public OuZaWindow() {

		super();
		bodyCreator();
		new MenuBar().addMenuBar(this);
		this.addWindowListener(this);
		windowState();

	}

	public final void windowState() {
		this.setTitle("Java -  - Oussama & Zakia");
		this.setSize(NUMBER_1300, NUMBER_700);
		this.setResizable(true);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public final void bodyCreator() {
		final Container globalContainer = getContentPane();
		globalContainer.setLayout(new BorderLayout(2, 2));
		globalContainer.add(this.splitCreator(), BorderLayout.CENTER);
		globalContainer.add(this.borderCreator(), BorderLayout.SOUTH);
		globalContainer.add(toolBarCreator(), BorderLayout.NORTH);
	}

	public final JLabel borderCreator() {
		final JLabel borderLabel = new JLabel("Application d\u00e9mar\u00e9e");
		borderLabel.setBorder(BorderFactory.createLoweredBevelBorder());
		return borderLabel;

	}

	public final JPanel editorPanelCreator() {
		final JPanel editorCentrePanel = new JPanel();
		editorCentrePanel.setLayout(new BorderLayout());
		editorCentrePanel.add(EditorOngletCreator.getTabbedpane());
		return editorCentrePanel;
	}

	public final JPanel packageExplorerPanelCreator() {

		final JPanel packExplorPanel = new JPanel();
		packExplorPanel.setLayout(new BorderLayout());

		new PackageExplorer().creatTree(CurrentFile.getWorkSpacePath());

		explorerTree = PackageExplorer.getTree();
		packExplorPanel.add(new JScrollPane(explorerTree));

		return packExplorPanel;
	}

	public final JPanel consolePanelCreator() {

		final JPanel consolePanel = new JPanel();
		consolePanel.setLayout(new BorderLayout());
		consolePanel.add(ConsoleOngletCreator.getTabbedpane());

		final JPanel panel = new JPanel();

		panel.setPreferredSize(new Dimension(NUMBER_80, NUMBER_20));

		final JButton clearConsoleBtn = new JButton("C");
		panel.add(clearConsoleBtn);
		ConsoleAction.clearOnglet(clearConsoleBtn);
		consolePanel.add(panel, BorderLayout.LINE_END);
		return consolePanel;
	}

	public final JSplitPane splitCreator() {
		final JSplitPane horizontalSplit = new JSplitPane(
				JSplitPane.HORIZONTAL_SPLIT,
				this.packageExplorerPanelCreator(), this.editorPanelCreator());
		horizontalSplit.setDividerLocation(NUMBER_150);

		final JSplitPane verticalSplit = new JSplitPane(
				JSplitPane.VERTICAL_SPLIT, horizontalSplit,
				this.consolePanelCreator());
		verticalSplit.setDividerLocation(NUMBER_425);
		return verticalSplit;
	}

	public final JToolBar toolBarCreator() {

		return new ToolBar().getOutils();
	}

	@Override
	public void windowActivated(final WindowEvent arg0) {
		// method empty

	}

	@Override
	public void windowClosed(final WindowEvent arg0) {
		// method empty
	}

	@Override
	public final void windowClosing(final WindowEvent arg0) {

		final int reponse = JOptionPane.showConfirmDialog(null, "Exit OuZa ",
				"Exit & Save", JOptionPane.OK_CANCEL_OPTION);
		if (reponse == 0) {

			int tabCount = EditorOngletCreator.getTabbedpane().getTabCount();

			for (int i = 0; i < tabCount; i++) {

				EditorOnglet currentOnglet = (EditorOnglet) EditorOngletCreator
						.getTabbedpane().getComponentAt(i);

				OngletManager.saveOnglet(i);

				EditorOngletCreator.getTabbedpane().remove(currentOnglet);

				tabCount = EditorOngletCreator.getTabbedpane().getTabCount();

			}
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		} else {
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		}
	}

	@Override
	public void windowDeactivated(final WindowEvent arg0) {
		// do nothing
	}

	@Override
	public void windowDeiconified(final WindowEvent arg0) {
		// do nothing
	}

	@Override
	public void windowIconified(final WindowEvent arg0) {
		// do nothing
	}

	@Override
	public void windowOpened(final WindowEvent arg0) {
		// do nothing
	}

	public static JTree getExplorerTree() {
		return explorerTree;
	}

	public static void setExplorerTree(final JTree explorTree) {
		OuZaWindow.explorerTree = explorTree;
	}

}
