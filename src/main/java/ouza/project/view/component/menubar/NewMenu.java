package ouza.project.view.component.menubar;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import ouza.project.actions.OuZaAction;
import ouza.project.tools.IconLaoder;

public class NewMenu extends JMenu {

	private static final long serialVersionUID = 1L;
	private transient JMenu newMenuu;

	public NewMenu() {

		super();

		newMenuu = new JMenu("  New	");
		newMenuu.setIcon(IconLaoder.NEW_ICON);

		newProjectItemCreator();
		newPackageItemCreator();

		newMenuu.addSeparator();

		newClassItemCreator();
		newEnumItemCreator();
		newInterfacecItemCreator();
	}

	private void newProjectItemCreator() {
		final JMenuItem projectMI = new JMenuItem("  Java Project	");
		projectMI.setIcon(IconLaoder.PROJECT_ICON);
		newMenuu.add(projectMI);
		projectMI.addActionListener(OuZaAction.newProjectAction());

	}

	private void newClassItemCreator() {

		final JMenuItem classMI = new JMenuItem("  Classe");
		classMI.setIcon(IconLaoder.CLASS_ICON);
		classMI.addActionListener(OuZaAction.newClassAction());
		newMenuu.add(classMI);
	}

	private void newEnumItemCreator() {
		final JMenuItem enumMI = new JMenuItem("  Enum");
		enumMI.setIcon(IconLaoder.ENUM_ICON);
		enumMI.addActionListener(OuZaAction.newEnumAction());
		newMenuu.add(enumMI);

	}

	private void newInterfacecItemCreator() {
		final JMenuItem interfaceMI = new JMenuItem("  Interface");
		interfaceMI.setIcon(IconLaoder.INTERFACE_ICON);

		interfaceMI.addActionListener(OuZaAction.newInterfaceAction());
		newMenuu.add(interfaceMI);

	}

	private void newPackageItemCreator() {
		final JMenuItem packageMI = new JMenuItem("  Package");
		packageMI.setIcon(IconLaoder.PACKAGE_ICON);
		packageMI.addActionListener(OuZaAction.newPackageAction());
		newMenuu.add(packageMI);

	}

	public final JMenu getNewMenu() {
		return newMenuu;
	}

	public final void setNewMenu(final JMenu menu) {
		this.newMenuu = menu;
	}

}
