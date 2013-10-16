package ouza.project.tools;

import javax.swing.ImageIcon;

public final class IconLaoder {

	private IconLaoder() {
		// TODO Auto-generated constructor stub
	}

	private static ChargeurRessource chargeur = new ChargeurRessource(
			"/ouza/project/icon/");

	public static final ImageIcon SAVE_ICON = chargeur.getIcon("save.jpg");

	public static final ImageIcon SAVEALL_ICON = chargeur
			.getIcon("saveAll.jpg");

	public static final ImageIcon CLOSE_ICON = chargeur.getIcon("close.jpg");

	public static final ImageIcon DELETE_ICON = chargeur.getIcon("delete.jpg");

	public static final ImageIcon RUN_ICON = chargeur.getIcon("run.jpg");

	public static final ImageIcon NEW_ICON = chargeur.getIcon("new.jpg");

	public static final ImageIcon COPY_ICON = chargeur.getIcon("copy.jpg");

	public static final ImageIcon CUT_ICON = chargeur.getIcon("cut.jpg");

	public static final ImageIcon PASTE_ICON = chargeur.getIcon("paste.jpg");

	public static final ImageIcon SELLECT_ALL_ICON = chargeur
			.getIcon("sellectALL.jpg");

	public static final ImageIcon PROJECT_ICON = chargeur
			.getIcon("project.jpg");

	public static final ImageIcon SOURCE_ICON = chargeur.getIcon("source.jpg");

	public static final ImageIcon PACKAGE_ICON = chargeur
			.getIcon("package.jpg");

	public static final ImageIcon CLASS_ICON = chargeur.getIcon("class.jpg");

	public static final ImageIcon INTERFACE_ICON = chargeur
			.getIcon("interface.jpg");

	public static final ImageIcon ENUM_ICON = chargeur.getIcon("enum.jpg");

	public static final ImageIcon ERROR_ICON = chargeur.getIcon("error.jpg");

	public static final ImageIcon WARNING_ICON = chargeur
			.getIcon("warning.jpg");

	public static final ImageIcon APP_ICON = chargeur.getIcon("app.jpg");

	public static final ImageIcon OUZA_ICON = chargeur.getIcon("ouza.png");

	public static final ImageIcon UNDO_ICON = chargeur.getIcon("undo.jpg");

	public static final ImageIcon REDO_ICON = chargeur.getIcon("redo.jpg");

}
