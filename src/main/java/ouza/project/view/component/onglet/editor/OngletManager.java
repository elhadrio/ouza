package ouza.project.view.component.onglet.editor;

import java.io.File;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ouza.project.tools.FileManager;

public final class OngletManager {

	private OngletManager() {
		// empty constructor
	}

	public static void saveNotice(final int index) {
		String title;

		final EditorOnglet selectedOnglet = (EditorOnglet) EditorOngletCreator
				.getTabbedpane().getComponentAt(index);

		final JPanel pan = (JPanel) EditorOngletCreator.getTabbedpane()
				.getTabComponentAt(index);
		final JLabel titre1 = (JLabel) pan.getComponent(0);
		title = titre1.getText().replace("*", "");
		titre1.setText(title);
		selectedOnglet.setEdited(false);
	}

	// save the content of an onglet on the related file
	public static void saveOnglet(final int index) {

		saveNotice(index);
		final EditorOnglet selectedOnglet = (EditorOnglet) EditorOngletCreator
				.getTabbedpane().getComponentAt(index);

		FileManager.saveFile(selectedOnglet.getProjectModeleSelector()
				.getClassPath(), selectedOnglet.getEditorpane().getText());

	}

	public static void saveAllOnglet() {
		for (int i = 0; i < EditorOngletCreator.getTabbedpane().getTabCount(); i++) {
			saveOnglet(i);
		}

	}

	// close the onglet
	public static void closeOnglet(final int index) {

		EditorOnglet selectedOnglet = (EditorOnglet) EditorOngletCreator
				.getTabbedpane().getComponentAt(index);

		if (selectedOnglet.isEdited()) {

			final int reponse = JOptionPane.showConfirmDialog(null,
					"Voulez vous Sauvgarder "
							+ selectedOnglet.getProjectModeleSelector()
									.getClassName() + " ?", "Confirm save",
					JOptionPane.YES_NO_CANCEL_OPTION);

			if (reponse == 0) {
				saveOnglet(index);
				EditorOngletCreator.getTabbedpane().remove(selectedOnglet);
			}

			if (reponse == 1) {
				EditorOngletCreator.getTabbedpane().remove(selectedOnglet);
			}

		} else {
			EditorOngletCreator.getTabbedpane().remove(selectedOnglet);
		}
	}

	public static void closeAllOnglet() {
		int tabCount = EditorOngletCreator.getTabbedpane().getTabCount();

		for (int i = 0; i < tabCount; i++) {

			final EditorOnglet currentOnglet = (EditorOnglet) EditorOngletCreator
					.getTabbedpane().getComponentAt(i);

			OngletManager.saveOnglet(i);

			EditorOngletCreator.getTabbedpane().remove(currentOnglet);

			tabCount = EditorOngletCreator.getTabbedpane().getTabCount();

		}
	}

	// open a file only one time
	public static boolean openOneTimeOnglet(final File file) {

		boolean returnStatement = true;
		final int tabCount = EditorOngletCreator.getTabbedpane().getTabCount();
		if (tabCount != 0) {

			int iterator;
			EditorOnglet courant;
			File courantFile = null;
			for (iterator = 0; iterator <= tabCount - 1; iterator++) {

				courant = (EditorOnglet) EditorOngletCreator.getTabbedpane()
						.getComponentAt(iterator);

				courantFile = new File(courant.getProjectModeleSelector()
						.getClassPath());

				if (courantFile.equals(file)) {

					EditorOngletCreator.getTabbedpane().setSelectedComponent(
							courant);

					returnStatement = false;

				}
			}

		}

		return returnStatement;
	}
}
