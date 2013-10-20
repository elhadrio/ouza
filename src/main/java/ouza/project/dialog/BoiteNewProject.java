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

import ouza.project.launcher.Main;
import ouza.project.modele.CurrentFile;
import ouza.project.tools.IconLaoder;
import ouza.project.view.OuZaWindow;

public class BoiteNewProject extends JDialog implements ActionListener {

	private static final String NAME_STRING = "Name";

	private static final String CANCEL = "Cancel";

	private static final String FINISH = "Finish";

	private static final int NUMBER_80 = 80;

	private static final int NUMBER_2 = 2;

	private static final int NUMBER_20 = 20;

	private static final int NUMBER_200 = 200;

	private static final int NUMBER_300 = 300;

	private static final long serialVersionUID = 1L;

	private static final int NUMBER_1 = 1;

	private transient JButton finishButton;
	private transient JButton cancelButton;
	private transient JTextField projectNameTF;

	private transient JLabel errorLabel = new JLabel();

	private transient JPanel headerPanel;

	public BoiteNewProject() {

		super(Main.getWindow(), "New Java Project", true);

		this.windowState();

		bodyCreator();

	}

	public final void windowState() {

		this.setSize(NUMBER_300, NUMBER_200);
		this.setResizable(false);
		this.setLocationRelativeTo(Main.getWindow());
	}

	public final void bodyCreator() {
		final Container contenu = getContentPane();

		final Box verticalBox = Box.createVerticalBox();
		verticalBox.add(headerPanelCreator());
		verticalBox.add(centrePanelCreator());
		verticalBox.add(finishCancelPanelCreator());

		contenu.add(verticalBox);
	}

	public final void projectNameListenner(final JTextField nameProject) {
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

		if (!isProjectNameVadlide(projectNameTF.getText())) {
			errorLabelCreator("		Project Name Invalid ");

		} else if (isProjectNameExist(projectNameTF.getText())) {
			errorLabelCreator("		Project Already exists ");
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
		headerPanel.add(new JLabel(IconLaoder.PROJECT_ICON));
		headerPanel.add(errorLabel);
		headerPanel.setBackground(Color.WHITE);
		headerPanel.setPreferredSize(new Dimension(NUMBER_300, NUMBER_80));
		return headerPanel;
	}

	public final JPanel centrePanelCreator() {
		final JPanel centrePanel = new JPanel();
		final JLabel name = new JLabel(NAME_STRING);
		centrePanel.add(name, BorderLayout.WEST);
		projectNameTF = new JTextField(NUMBER_20);
		projectNameListenner(projectNameTF);
		centrePanel.add(projectNameTF, BorderLayout.CENTER);
		return centrePanel;
	}

	public final JPanel finishCancelPanelCreator() {
		final JPanel finishCancelPanel = new JPanel();

		finishButton = new JButton(FINISH);
		finishButton.addActionListener(this);
		finishCancelPanel.add(finishButton);

		cancelButton = new JButton(CANCEL);
		cancelButton.addActionListener(this);
		finishCancelPanel.add(cancelButton);
		return finishCancelPanel;

	}

	public final void actionPerformed(final ActionEvent event) {
		final Object source = event.getSource();

		if (source.equals(finishButton)) {

			if (isProjectNameVadlide(projectNameTF.getText())) {

				new File(CurrentFile.getWorkSpacePath()
						+ (projectNameTF.getText()) + "\\src").mkdirs();

				this.dispose();

			} else {
				errorLabel.setText("   Project Name is not valid");
				errorLabel.updateUI();
			}

			OuZaWindow.getExplorerTree().updateUI();

		}
		if (source.equals(cancelButton)) {

			dispose();
		}

	}

	public static boolean isProjectNameExist(final String entree) {

		return new File(CurrentFile.getWorkSpacePath() + entree).exists();

	}

	public static boolean isProjectNameVadlide(final String entree) {

		final Pattern pattern = Pattern.compile("[a-zA-Z]+");

		final Matcher matcher = pattern.matcher(entree);

		return matcher.matches();
	}

}
