package ouza.project.view.component.popmenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;

import ouza.project.explorer.file.PackageExplorer;
import ouza.project.tools.FileManager;
import ouza.project.tools.IconLaoder;
import ouza.project.view.OuZaWindow;
import ouza.project.view.component.menubar.NewMenu;

public class PopMenu extends JPopupMenu {

	private static final long serialVersionUID = 1L;

	public PopMenu() {
		super();

		NewMenu newMenu = new NewMenu();
		this.add(newMenu.getNewMenu());

		JMenuItem openMI = new JMenuItem("  Open	");
		openMI.setIcon(IconLaoder.DELETE_ICON);
		openAction(openMI);
		addSeparator();
		this.add(openMI);

		// JMenuItem deleteMI = new JMenuItem("  Delete	");
		// deleteMI.setIcon(OuZaWindow.getChargeur().getIcon("delete-file-icon.png"));
		// deleteAction(deleteMI);
		// this.add(deleteMI);

	}

	public final void openAction(final Object obj) {
		((AbstractButton) obj).addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent action) {

				PackageExplorer.fileOpen();
				OuZaWindow.getExplorerTree().updateUI();

			}
		});
	}

	public final void deleteAction(final Object obj) {
		((AbstractButton) obj).addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent action) {

				final int repense = JOptionPane.showConfirmDialog(null,
						" voulez vous le supprimer  ?");

				if (repense == 0) {

					FileManager.deleteDir(PackageExplorer.getFile());
				}

				OuZaWindow.getExplorerTree().updateUI();

			}
		});
	}

}
