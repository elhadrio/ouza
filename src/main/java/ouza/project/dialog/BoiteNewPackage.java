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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ouza.project.explorer.selection.Selection;
import ouza.project.launcher.Main;
import ouza.project.modele.CurrentFile;
import ouza.project.tools.IconLaoder;
import ouza.project.view.OuZaWindow;

public class BoiteNewPackage extends JDialog implements ActionListener {

	private static final String SOURCE_FOLDER = "Source Folder";
	private static final String NAME_STRING = "Name";
	private static final int NUMBER_100 = 100;
	private static final int NUMBER_500 = 500;
	private static final int NUMBER_1 = 1;
	private static final int NUMBER_20 = 20;
	private static final int NUMBER_2 = 2;
	private static final int NUMBER_3 = 3;
	private static final int NUMBER_10 = 10;
	private static final int HEIGHT_250 = 250;
	private static final int WIDTH_500 = 500;
	private static final long serialVersionUID = 1L;

	private transient JButton finishButton;
	private transient JButton cancelButton;
	private transient JTextField packageNameTF;
	private transient JButton browseButton;
	private transient JTextField projectNameTF;
	private transient JPanel headerPanel;

	private static JLabel errorLabel = new JLabel();

	public BoiteNewPackage() {
		super(Main.getWindow(), "New java Package", true);

		bodyCreator();
		windowState();

	}

	public final void windowState() {

		this.setLocationRelativeTo(Main.getWindow());
		this.setSize(WIDTH_500, HEIGHT_250);
		this.setResizable(false);
	}

	public final void packagetNameListenner(final JTextField nameProject) {
		nameProject.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(final KeyEvent arg0) {
				// method empty
			}

			@Override
			public void keyReleased(final KeyEvent arg0) {
				errorMessage();
			}

			@Override
			public void keyPressed(final KeyEvent arg0) {
				// method empty
			}
		});
	}

	public final void errorMessage() {
		headerPanel.remove(1);

		errorLabel = new JLabel();
		finishButton.setEnabled(true);

		if (!isPackagetNameVadlide(packageNameTF.getText())) {
			errorLabelCreator("		Package  Name Invalid ");

		} else if (isPackageNameExist(packageNameTF.getText())) {
			errorLabelCreator("		Package Already exists ");
		}

		headerPanel.setLayout(new BorderLayout());
		headerPanel.add(errorLabel, BorderLayout.WEST);
		headerPanel.updateUI();
	}

	private void errorLabelCreator(final String errorMessage) {
		errorLabel = new JLabel(IconLaoder.ERROR_ICON);
		errorLabel.setText(errorMessage);
		finishButton.setEnabled(false);
	}

	public final JPanel headerPanelCreator() {
		headerPanel = new JPanel();

		headerPanel.setLayout(new GridLayout(NUMBER_2, NUMBER_1, NUMBER_20,
				NUMBER_20));
		final JLabel packageIcon = new JLabel(IconLaoder.PACKAGE_ICON);
		headerPanel.add(packageIcon);
		headerPanel.add(errorLabel);
		headerPanel.setBackground(Color.WHITE);
		headerPanel.setPreferredSize(new Dimension(NUMBER_500, NUMBER_100));
		return headerPanel;
	}

	public final JPanel panelLeftCreator() {
		final JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new GridLayout(NUMBER_3, NUMBER_2, NUMBER_10,
				NUMBER_10));

		final JLabel sourcefolderTF = new JLabel(SOURCE_FOLDER);
		labelPanel.add(sourcefolderTF);

		final JLabel name = new JLabel(NAME_STRING);
		labelPanel.add(name);

		return labelPanel;
	}

	public final JPanel panelCentreCreator() {
		final JPanel panelTF = new JPanel();

		panelTF.setLayout(new GridLayout(NUMBER_3, NUMBER_2, NUMBER_10,
				NUMBER_10));

		projectNameTF = new JTextField(NUMBER_10);
		projectNameTF.setEditable(false);
		panelTF.add(projectNameTF);

		packageNameTF = new JTextField(NUMBER_10);
		packagetNameListenner(packageNameTF);
		panelTF.add(packageNameTF);
		return panelTF;
	}

	public final JPanel panelRightCretor() {
		final JPanel browsePanel = new JPanel();
		browsePanel.setLayout(new GridLayout(NUMBER_3, NUMBER_2, NUMBER_10,
				NUMBER_10));

		browseButton = new JButton("browse");
		browseButton.addActionListener(this);
		browsePanel.add(browseButton);

		return browsePanel;
	}

	public final JPanel formulairePanelCreator() {
		final JPanel formulairePanel = new JPanel();
		formulairePanel.setLayout(new BorderLayout());
		formulairePanel.add(panelLeftCreator(), BorderLayout.WEST);
		formulairePanel.add(panelCentreCreator(), BorderLayout.CENTER);
		formulairePanel.add(panelRightCretor(), BorderLayout.EAST);
		return formulairePanel;
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

	public final void bodyCreator() {
		final Container contenu = getContentPane();

		final Box verticalLayout = Box.createVerticalBox();
		verticalLayout.add(headerPanelCreator());
		verticalLayout.add(formulairePanelCreator());
		verticalLayout.add(finishCancelPanelCreator());

		contenu.add(verticalLayout);
	}

	public final void actionPerformed(final ActionEvent event) {
		final Object source = event.getSource();

		if (source.equals(browseButton)) {

			browsTraitement();
		}

		if (source.equals(finishButton)) {
			finishTraitement();
		}
		if (source.equals(cancelButton)) {
			this.dispose();
		}
		OuZaWindow.getExplorerTree().updateUI();
	}

	private void browsTraitement() {
		new Selection((JDialog) this, CurrentFile.getWorkSpacePath());
	}

	private void finishTraitement() {
		if (!projectNameTF.getText().equals("")
				&& isPackagetNameVadlide(packageNameTF.getText())) {

			final String packRelativePath = packageNameTF.getText().replace(
					".", "//");

			new File(CurrentFile.getProjectPath() + "\\src\\"
					+ packRelativePath).mkdirs();
			this.dispose();

		}
	}

	public final boolean isPackagetNameVadlide(final String entree) {

		final Pattern pattern = Pattern
				.compile("([a-zA-Z]+)(((.)?([a-zA-Z]+))*)");

		final Matcher matcher = pattern.matcher(entree);

		return matcher.matches();
	}

	public static boolean isPackageNameExist(final String entree) {

		return new File(CurrentFile.getProjectPath() + "//src//" + entree)
				.exists();

	}

	public final void remplireFormulaire(final String projectName,
			final String packageName) {
		projectNameTF.setText(projectName);
		packageNameTF.setText(packageName);

	}

	public final String getProjectName() {
		return projectNameTF.getText();
	}

	public final void setProjectName(final String projectName) {
		projectNameTF.setText(projectName);
	}
}
