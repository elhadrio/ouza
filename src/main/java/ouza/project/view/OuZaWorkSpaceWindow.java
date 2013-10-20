package ouza.project.view;

import javax.swing.JFileChooser;

import ouza.project.launcher.Main;
import ouza.project.modele.CurrentFile;

public class OuZaWorkSpaceWindow {
	public OuZaWorkSpaceWindow() {

		JFileChooser filechooser = new JFileChooser();
		filechooser.setDialogTitle("Sélectionner votre WorkSpace");
		filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		int iterator = filechooser.showOpenDialog(null);
		if (iterator == JFileChooser.APPROVE_OPTION) {
			CurrentFile.setWorkSpacePath(filechooser.getSelectedFile()
					.getAbsolutePath() + "\\");
			Main.setWindow(new OuZaWindow());
		}

	}
}
