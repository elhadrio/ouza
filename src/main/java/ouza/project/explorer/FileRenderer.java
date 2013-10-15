package ouza.project.explorer;

import java.awt.Component;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import ouza.project.modele.CurrentFile;
import ouza.project.tools.ChargeurRessource;
import ouza.project.tools.IconLaoder;

public class FileRenderer extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = 1L;

	public final Component getTreeCellRendererComponent(final JTree tree,
			final Object value, final boolean selected, final boolean expanded,
			final boolean leaf, final int row, final boolean hasFocus) {

		final JLabel label = (JLabel) super.getTreeCellRendererComponent(tree,
				value, selected, expanded, leaf, row, hasFocus);
		final File file = (File) value;

		label.setText(file.getName());

		iconFileSelector(label, file);

		return label;
	}

	private void iconFileSelector(final JLabel label, final File file) {
		File work = CurrentFile.getWorkSpaceFile();
		if (file.isFile()) {
			/*
			 * ((DefaultTreeCellRenderer) label).setIcon(chargeur
			 * .getIcon("crystal_java.png"));
			 */

		}

		if (file.getName().equals("src")) {
			((DefaultTreeCellRenderer) label).setIcon(IconLaoder.SOURCE_ICON);
		}

		if (file.getParentFile().getName().equals("src")) {
			// ((DefaultTreeCellRenderer)
			// label).setIcon(IconLaoder.SOURCE_ICON);
		}
		if (file.getParentFile().equals(work)) {

			((DefaultTreeCellRenderer) label).setIcon(IconLaoder.PROJECT_ICON);
		}
	}
}