package ouza.project.view.component.onglet.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;

public final class CloseButtonAction {

	private CloseButtonAction() {
		// Empty constructor
	}

	public static void close(final Object obj) {
		((AbstractButton) obj).addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent action) {
				OngletManager.closeOnglet(EditorOngletCreator.getTabbedpane()
						.getSelectedIndex());

			}
		});
	}

}
