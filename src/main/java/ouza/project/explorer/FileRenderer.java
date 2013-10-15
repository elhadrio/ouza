package ouza.project.explorer;

import java.awt.Component;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import ouza.project.tools.ChargeurRessource;

public class FileRenderer extends DefaultTreeCellRenderer {

	private static final long serialVersionUID = 1L;

	private static ChargeurRessource chargeur = new ChargeurRessource(
			"/ouza/project/icon/");

	

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
		if (file.isFile()) {
			((DefaultTreeCellRenderer) label).setIcon(chargeur
					.getIcon("crystal_java.png"));

		}

		if (file.getName().equals("src")) {
			((DefaultTreeCellRenderer) label).setIcon(chargeur
					.getIcon("add_as_source_folder.gif"));
		}

		if (file.getParentFile().getName().equals("src")) {
			((DefaultTreeCellRenderer) label).setIcon(chargeur
					.getIcon("package_obj.gif"));
		}
		if (file.getParentFile().getName().equals("myspace")) {

			((DefaultTreeCellRenderer) label).setIcon(chargeur
					.getIcon("Fada2iicone.JPG"));
		}
	}
}