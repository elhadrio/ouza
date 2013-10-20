package ouza.project.explorer.selection;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import ouza.project.dialog.BoiteNewClass;
import ouza.project.dialog.BoiteNewPackage;
import ouza.project.explorer.FileRenderer;
import ouza.project.modele.CurrentFile;
import ouza.project.modele.ProjectModeleSelector;

public class Selection extends JDialog {

	private static final int HEIGHT_300 = 300;

	private static final int WIDTH_250 = 250;

	private static final long serialVersionUID = 1L;

	private static JTree treeExplorer;

	private transient ProjectModeleSelector pMS;

	public Selection(final JDialog parent, final String repertoire) {

		super(parent, "", true);

		treeCreator(parent, repertoire);

		this.body(parent);
		this.windowState();

	}

	private void treeCreator(final JDialog parent, final String repertoire) {

		final File root = new File(repertoire);
		final FileTreeModel fileTreeModele = new FileTreeModel(root);
		treeExplorer = new JTree(fileTreeModele);
		treeExplorer.setCellRenderer(new FileRenderer());

		addMouseListener(parent);
		addTreeSelectionLisener();

		treeExplorer.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		treeExplorer.setRootVisible(false);
	}

	private void addMouseListener(final JDialog parent) {
		treeExplorer.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(final MouseEvent arg0) {

				final int buttonDown = arg0.getButton();
				if (arg0.getClickCount() == 2
						&& buttonDown == MouseEvent.BUTTON1) {
					selectionMode(parent);
				}

			}

			public void mouseEntered(final MouseEvent arg0) {
				// methoed empty
			}

			public void mouseExited(final MouseEvent arg0) {
				// methoed empty
			}

			public void mousePressed(final MouseEvent arg0) {
				// method empty
			}

			public void mouseReleased(final MouseEvent arg0) {
				// method empty
			}
		});
	}

	private void addTreeSelectionLisener() {
		treeExplorer.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(final TreeSelectionEvent event) {
				final TreePath path = event.getPath();
				final File file = (File) path.getLastPathComponent();

				pMS = new ProjectModeleSelector(file);
				CurrentFile.setpMS(pMS);

			}
		});
	}

	public final void windowState() {
		this.setSize(WIDTH_250, HEIGHT_300);
		this.setVisible(true);
	}

	public final void body(final JDialog parent) {
		final Container container = new Container();
		container.setLayout(new BorderLayout());

		final JPanel okCancelPanel = new JPanel();
		okCancelPanel.setLayout(new BorderLayout());
		final Button okBtn = new Button("OK");
		okBtnAction(parent, okBtn);
		final Button cancelBtn = new Button("Cancel");
		cancelBtnAction(cancelBtn);

		okCancelPanel.add(okBtn, BorderLayout.WEST);
		okCancelPanel.add(cancelBtn, BorderLayout.CENTER);

		container.add(new JScrollPane(treeExplorer), BorderLayout.CENTER);
		container.add(okCancelPanel, BorderLayout.SOUTH);
		this.add(container);
	}

	private void cancelBtnAction(final Button cancelBtn) {
		cancelBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {

				dispose();

			}
		});
	}

	private void okBtnAction(final JDialog parent, final Button okBtn) {
		okBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				selectionMode(parent);
				dispose();

			}
		});
	}

	public final void selectionMode(final JDialog parent) {
		if (parent instanceof BoiteNewClass) {

			((BoiteNewClass) parent).remplirFormulaire(pMS);

			this.dispose();
		}

		if (parent instanceof BoiteNewPackage) {
			((BoiteNewPackage) parent).setProjectName(pMS.getProjectName());
			this.dispose();
		}
	}

}