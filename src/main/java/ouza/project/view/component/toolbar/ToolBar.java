package ouza.project.view.component.toolbar;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import ouza.project.actions.OuZaAction;
import ouza.project.explorer.file.PackageExplorer;
import ouza.project.modele.CurrentFile;
import ouza.project.tools.ChargeurRessource;
import ouza.project.tools.IconLaoder;
import ouza.project.view.OuZaWindow;
import ouza.project.view.component.onglet.editor.EditorOngletCreator;

public class ToolBar {

	private JToolBar outils;

	
	public ToolBar() {

		toolBarCreator();

	}

	private void toolBarCreator() {

		outils = new JToolBar();

		addMouseListener();

		saveBtnCreator();
		saveAllBtnCreator();
		outils.addSeparator();

		cutBtnCreator();
		copyBtnCreator();
		pasteBtnCreator();

		outils.addSeparator();

		runBtnCreator();

		outils.addSeparator();

		projectBtnCreator();
		packageBtnCreator();

		outils.addSeparator();

		classBtnCreator();

		interfacaBtnCreator();

		enumBtnCreator();

	}

	private void addMouseListener() {
		outils.addMouseMotionListener(new MouseMotionListener() {

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

	private void saveAllBtnCreator() {
		final JButton saveAllBtn = new JButton(IconLaoder.SAVEALL_ICON);
		saveAllBtn.addActionListener(OuZaAction.saveALL());
		outils.add(saveAllBtn);
	}

	private void enumBtnCreator() {
		final JButton enumBtn = new JButton(IconLaoder.ENUM_ICON);
		enumBtn.addActionListener(OuZaAction.newEnumAction());
		outils.add(enumBtn);
	}

	private void interfacaBtnCreator() {
		final JButton interfaceBtn = new JButton(
				IconLaoder.INTERFACE_ICON);
		interfaceBtn.addActionListener(OuZaAction.newInterfaceAction());
		outils.add(interfaceBtn);
	}

	private void classBtnCreator() {
		final JButton classBtn = new JButton(
				IconLaoder.CLASS_ICON);
		classBtn.addActionListener(OuZaAction.newClassAction());
		outils.add(classBtn);
	}

	private void packageBtnCreator() {
		final JButton packageBtn = new JButton(
				IconLaoder.PACKAGE_ICON);
		packageBtn.addActionListener(OuZaAction.newPackageAction());
		outils.add(packageBtn);
	}

	private void projectBtnCreator() {
		final JButton projectBtn = new JButton(
				IconLaoder.PROJECT_ICON);
		projectBtn.addActionListener(OuZaAction.newProjectAction());
		outils.add(projectBtn);
	}

	private void runBtnCreator() {
		final JButton runBtn = new JButton(IconLaoder.RUN_ICON);
		runBtn.addActionListener(OuZaAction.run());
		outils.add(runBtn);
	}

	private void pasteBtnCreator() {
		final JButton pasteBtn = new JButton(IconLaoder.PASTE_ICON);
		pasteBtn.addActionListener(OuZaAction.paste());
		outils.add(pasteBtn);
	}

	private void copyBtnCreator() {
		final JButton copyBtn = new JButton(IconLaoder.COPY_ICON);
		copyBtn.addActionListener(OuZaAction.copy());
		outils.add(copyBtn);
	}

	private void cutBtnCreator() {
		final JButton cutBtn = new JButton(IconLaoder.CUT_ICON);

		cutBtn.addActionListener(OuZaAction.cut());
		outils.add(cutBtn);
	}

	private void saveBtnCreator() {
		final JButton saveBtn = new JButton(IconLaoder.SAVE_ICON);
		saveBtn.addActionListener(OuZaAction.save());
		outils.add(saveBtn);
	}

	public final JToolBar getOutils() {
		return outils;
	}

	public final void setOutils(final JToolBar outil) {
		this.outils = outil;
	}
}
