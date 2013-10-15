package ouza.project.dialog;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ouza.project.ant.AntBuildFile;
import ouza.project.explorer.selection.Selection;
import ouza.project.launcher.Main;
import ouza.project.logger.OuZaLogger;
import ouza.project.modele.CurrentFile;
import ouza.project.modele.ProjectModeleSelector;
import ouza.project.tools.FileManager;
import ouza.project.tools.IconLaoder;
import ouza.project.view.OuZaWindow;
import ouza.project.view.component.onglet.editor.EditorOnglet;
import ouza.project.view.component.onglet.editor.EditorOngletCreator;

public class BoiteNewClass extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private transient JButton finishButton;
	private transient JButton cancelButton;

	private transient JTextField classNameTF;
	private transient JTextField projectNameTF;
	private transient JTextField packageNameTF;

	private transient JCheckBox boxButton;
	private transient JButton projectBrowserBtn;
	private transient JButton packagerBrowserBtn;

	private transient JLabel main;

	private transient JLabel headerLabel = new JLabel();
	private transient JPanel headerPanel;

	private static boolean selectionMode = true;

	private static final int WINDOW_HEIGHT_300 = 300;
	private static final int WINDOW_HEIGHT_100 = 100;
	private static final int WINDOW_WIDTH_500 = 500;

	private static final int GRID_GAP_10 = 10;
	private static final int GRID_GAP_5 = 5;
	private static final int GRID_ROWS_5 = 5;
	private static final int GRID_ROWS_2 = 2;
	private static final int GRID_COLS_1 = 1;

	private static final int COLS_20 = 20;

	private static final String MODIFIERS_STRING = "Modifiers";

	private static final String NAME_STRING = "Name";

	private static final String PACKAGE_STRING = "Package";

	private static final String SOURCE_FOLD = "Source Folder ";

	public BoiteNewClass(final String title, final String iconName) {
		super(Main.getWindow(), "New Java " + title, true);

		this.bodyCreator(iconName);
		this.windowState();

	}

	public final void windowState() {

		setSize(WINDOW_WIDTH_500, WINDOW_HEIGHT_300);
		setResizable(false);
		setLocationRelativeTo(Main.getWindow());

	}

	public final JPanel browsePanelCreator() {
		final JPanel panelBrowse = new JPanel();
		panelBrowse.setLayout(new GridLayout(GRID_ROWS_5, GRID_COLS_1,
				GRID_GAP_10, GRID_GAP_10));

		projectBrowserBtn = new JButton("Browse");
		projectBrowserBtn.addActionListener(this);
		panelBrowse.add(projectBrowserBtn);

		packagerBrowserBtn = new JButton("Browse");
		packagerBrowserBtn.addActionListener(this);
		panelBrowse.add(packagerBrowserBtn);

		return panelBrowse;
	}

	public final JPanel headerPanelCreator(final String iconName) {
		headerPanel = new JPanel();
		headerPanel.setLayout(new GridLayout(GRID_ROWS_2, GRID_COLS_1,
				GRID_GAP_5, GRID_GAP_5));

		ImageIcon icon = null;
		if (iconName.equals("class")) {
			icon = IconLaoder.CLASS_ICON;
		} else if (iconName.equals("enum")) {
			icon = IconLaoder.ENUM_ICON;
		} else if (iconName.equals("interface")) {
			icon = IconLaoder.INTERFACE_ICON;
		}
		final JLabel classIcon = new JLabel(icon);
		headerPanel.add(classIcon);
		headerPanel.setPreferredSize(new Dimension(WINDOW_WIDTH_500,
				WINDOW_HEIGHT_100));
		headerPanel.setBackground(Color.WHITE);
		headerPanel.add(headerLabel, BorderLayout.WEST);
		return headerPanel;
	}

	public final JPanel formulairePanelCreator() {
		final JPanel formulairePanel = new JPanel();
		formulairePanel.setLayout(new BorderLayout());
		formulairePanel.add(browsePanelCreator(), BorderLayout.EAST);
		formulairePanel.add(labelPanelCreator(), BorderLayout.WEST);
		formulairePanel.add(textFeildPanelcreator(), BorderLayout.CENTER);

		return formulairePanel;
	}

	public final JPanel labelPanelCreator() {
		final JPanel panelLabel = new JPanel();
		panelLabel.setLayout(new GridLayout(GRID_ROWS_5, GRID_COLS_1,
				GRID_GAP_10, GRID_GAP_10));

		final JLabel sourceFolderLabel = new JLabel(SOURCE_FOLD);
		panelLabel.add(sourceFolderLabel);

		final JLabel packageLabel = new JLabel(PACKAGE_STRING);
		panelLabel.add(packageLabel);
		final JLabel nameLabel = new JLabel(NAME_STRING);
		panelLabel.add(nameLabel);

		final JLabel modifiersLabel = new JLabel(MODIFIERS_STRING);
		panelLabel.add(modifiersLabel);

		main = new JLabel("main");
		panelLabel.add(main);

		return panelLabel;

	}

	public final JPanel textFeildPanelcreator() {
		final JPanel panelTF = new JPanel();
		panelTF.setLayout(new GridLayout(GRID_ROWS_5, GRID_COLS_1, GRID_GAP_10,
				GRID_GAP_10));

		projectNameTF = new JTextField(COLS_20);

		projectNameTF.setEditable(false);
		panelTF.add(projectNameTF);

		packageNameTF = new JTextField(COLS_20);
		packageNameTF.setEditable(false);
		panelTF.add(packageNameTF);

		classNameTF = new JTextField(COLS_20);
		classeNameListenner(classNameTF);
		panelTF.add(classNameTF);

		final JRadioButton radio1 = new JRadioButton("public", true);
		panelTF.add(radio1);

		boxButton = new JCheckBox("", false);

		panelTF.add(boxButton);

		return panelTF;
	}

	public final JPanel finishCancelPanelCreator() {
		final JPanel finishCancelPanel = new JPanel();

		finishButton = new JButton("Finish");
		finishButton.addActionListener(this);
		finishCancelPanel.add(finishButton);

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		finishCancelPanel.add(cancelButton);
		return finishCancelPanel;

	}

	public final void bodyCreator(final String iconName) {
		final Container contenu = getContentPane();

		final Box verticalLayout = Box.createVerticalBox();

		verticalLayout.add(headerPanelCreator(iconName));
		verticalLayout.add(formulairePanelCreator());
		verticalLayout.add(finishCancelPanelCreator());

		contenu.add(verticalLayout);
	}

	public final void errorMessage() {
		headerPanel.remove(1);

		if (BoiteNewClass.isClassNameVadlide(classNameTF.getText())) {

			if (isStartwithUpperCase(classNameTF.getText())) {

				headerLabel = new JLabel();

			} else {
				headerLabel = new JLabel(IconLaoder.WARNING_ICON);
				headerLabel.setText(" Type classNameTextField is discouraged");

			}

		} else {

			headerLabel = new JLabel(IconLaoder.ERROR_ICON);
			headerLabel.setText("Type classNameTextField is invalid");
		}

		headerPanel.setLayout(new BorderLayout());
		headerPanel.add(headerLabel, BorderLayout.WEST);

		headerLabel.updateUI();
		headerPanel.updateUI();
	}

	public final void classeNameListenner(final JTextField nameProject) {
		nameProject.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(final KeyEvent arg0) {
				// methoed empty
			}

			@Override
			public void keyReleased(final KeyEvent arg0) {
				errorMessage();
			}

			@Override
			public void keyPressed(final KeyEvent arg0) {
				// methoed empty
			}
		});
	}

	public final void actionPerformed(final ActionEvent event) {
		final Object source = event.getSource();

		browseButtonAction(source);
		finishButtonAction(source);
		cancelButtonAction(source);

		OuZaWindow.getExplorerTree().updateUI();
	}

	private void cancelButtonAction(final Object source) {
		if (source.equals(cancelButton)) {
			this.dispose();
		}
	}

	private void finishButtonAction(final Object source) {
		if (source.equals(finishButton)) {
			newOngletAndNewClassGenerator();
		}
	}

	private void newOngletAndNewClassGenerator() {
		if (BoiteNewClass.isClassNameVadlide(classNameTF.getText())) {

			final File file = new File(CurrentFile.getPackagePath(),
					classNameTF.getText() + ".java");
			String init;

			init = generateBuildFileAndClassFileContent();

			createNewOngletAndClassFile(file, init);

			this.dispose();

		}
	}

	private String generateBuildFileAndClassFileContent() {
		String init;
		if (boxButton.isSelected()) {

			generateOrUpdateBuildFile();

			init = mainClassTextInit();

		} else {

			init = classTextInit();
		}
		return init;
	}

	private void createNewOngletAndClassFile(final File file, final String init) {
		new EditorOngletCreator(init).addOnglet(classNameTF.getText());
		FileManager.saveFile(file.getAbsolutePath(), init);

		final EditorOnglet editeur = (EditorOnglet) EditorOngletCreator
				.getSelectedTab();
		updatePMS(editeur);
	}

	private void generateOrUpdateBuildFile() {
		final File antBuildFile = new File(CurrentFile.getProjectPath()
				+ "//build.xml");

		if (isNotNullAndExist(antBuildFile)) {
			final AntBuildFile antBuild = updateBuildFile(antBuildFile);

			antBuild.write(antBuildFile.getAbsolutePath());
		} else {
			createBuildFile();

		}
	}

	private boolean isNotNullAndExist(final File antBuildFile) {
		return antBuildFile != null && antBuildFile.exists();
	}

	private void updatePMS(final EditorOnglet editeur) {
		ProjectModeleSelector pMS = new ProjectModeleSelector(
				projectNameTF.getText(), packageNameTF.getText(),
				classNameTF.getText());
		pMS.setProjectPath(CurrentFile.getProjectPath());

		editeur.setProjectModeleSelector(pMS);
		CurrentFile.setpMS(pMS);
	}

	private void createBuildFile() {
		final AntBuildFile antBuild = new AntBuildFile();
		try {
			antBuild.create(projectNameTF.getText(), packageNameTF.getText()
					+ "." + classNameTF.getText());
		} catch (FileNotFoundException e1) {

			OuZaLogger.LOGGER.error("specified not found", e1);

		} catch (IOException e1) {

			OuZaLogger.LOGGER.error("error", e1);
		}
		antBuild.write(CurrentFile.getProjectPath() + "//build.xml");
	}

	private AntBuildFile updateBuildFile(final File antBuildFile) {
		final AntBuildFile antBuild = new AntBuildFile();
		antBuild.read(antBuildFile);
		antBuild.addNewProjectExcutorTarger(antBuildFile,
				projectNameTF.getText(), packageNameTF.getText() + "."
						+ classNameTF.getText());
		return antBuild;
	}

	private void browseButtonAction(final Object source) {
		if (source.equals(projectBrowserBtn)) {

			selectionMode = true;
			new Selection(this, CurrentFile.getWorkSpacePath());
		} else if (source.equals(packagerBrowserBtn)) {

			selectionMode = false;

			new Selection(this, CurrentFile.getProjectPath() + "\\src\\");

		}
	}

	private String classTextInit() {
		String init;
		init = "package " + packageNameTF.getText() + ";" + '\n' + '\n'
				+ " public class " + classNameTF.getText() + " { " + '\n'
				+ '\n' + " } ";
		return init;
	}

	private String mainClassTextInit() {
		String init;
		init = "package " + packageNameTF.getText() + ";" + '\n' + '\n'
				+ " public class " + classNameTF.getText() + " { " + '\n'
				+ '\n' + "	public static void main( String[] args ) {" + '\n'
				+ '\n' + "		} " + '\n' + '\n' + " } ";
		return init;
	}

	public final void setMainVisibility(final boolean status) {
		main.setVisible(status);
		boxButton.setVisible(status);
		boxButton.setEnabled(status);
	}

	public final void remplirFormulaire(final ProjectModeleSelector pMS) {
		this.projectNameTF.setText(pMS.getProjectName());
		this.packageNameTF.setText(pMS.getPackageName());
		this.classNameTF.setText("");
	}

	public final void browserActivation(final boolean activation) {
		this.projectBrowserBtn.setEnabled(activation);
	}

	public final void browser1Activation(final boolean activation) {
		this.packagerBrowserBtn.setEnabled(activation);
	}

	public static boolean isSelectionMode() {
		return selectionMode;
	}

	public static void setSelectionMode(final boolean selection) {
		BoiteNewClass.selectionMode = selection;
	}

	public final boolean isStartwithUpperCase(final String entree) {
		final Pattern pattern = Pattern.compile("([A-Z])([a-zA-Z0-9]*)");

		final Matcher matcher = pattern.matcher(entree);

		return matcher.matches();

	}

	public static boolean isClassNameVadlide(final String entree) {

		final Pattern pattern = Pattern.compile("^[a-zA-Z]+[0-9]*");

		final Matcher matcher = pattern.matcher(entree);

		return matcher.matches();
	}

}
