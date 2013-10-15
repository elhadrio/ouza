package ouza.project.view.component.onglet.console;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public final class ConsoleAction {

	private ConsoleAction() {
		// empty constuctor
	}
	
	
	public static void closeOnglet(final JButton obj) {
		obj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent action) {

				final JPanel headerPanel = (JPanel) obj.getParent();
				// JLabel title = (JLabel) headerPanel.getComponent(0);

				int iterator = 0;
				while (iterator <= ConsoleOngletCreator.getTabbedpane()
						.getTabCount()) {

					if (ConsoleOngletCreator.getTabbedpane()
							.getTabComponentAt(iterator).equals(headerPanel)) {

						break;
					}

					iterator++;
				}

				ConsoleOngletCreator.getTabbedpane().remove(iterator);

			}
		});
	}

	public static void clearOnglet(final JButton obj) {
		obj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent action) {
				final ConsoleOngletPanel selectedConsole = ConsoleOngletCreator
						.getSelectedTab();
				selectedConsole.getEdit().setText("");
			}
		});

	}

	public static void closeAllExcutedProject(final JButton obj) {
		obj.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent action) {
				new ConsoleManager().removeAllExcutedProjectOnglet();
			}
		});

	}

}
