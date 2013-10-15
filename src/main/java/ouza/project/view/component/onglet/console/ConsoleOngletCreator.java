package ouza.project.view.component.onglet.console;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import ouza.project.view.component.onglet.editor.CloseButton;

public class ConsoleOngletCreator {

	private static final int NUMBER_20 = 20;
	private static final int NUMBER_80 = 80;

	private static final JTabbedPane TABBED = new JTabbedPane();
	private transient ConsoleOngletPanel consoleOngletPnl;

	public ConsoleOngletCreator() {
		consoleOngletPnl = new ConsoleOngletPanel();
	}

	public final void setTextToOnglet(final String consoleText) {

		consoleOngletPnl.getEdit().setText(consoleText);
	}

	public final void addOnglet(final String title) {

		final JPanel headerPanel = headerOfOngletPanel(title);
		TABBED.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		TABBED.addTab(title, consoleOngletPnl);

		final int index = TABBED.indexOfComponent(consoleOngletPnl);

		TABBED.setSelectedComponent(consoleOngletPnl);
		TABBED.setTabComponentAt(index, headerPanel);

	}

	private JPanel headerOfOngletPanel(final String title) {

		final JPanel headerpanel = new JPanel();

		final CloseButton closeBtn = new CloseButton();
		ConsoleAction.closeOnglet(closeBtn);
		final JLabel titleLabel = new JLabel(title);

		headerpanel.setOpaque(false);
		headerpanel.setLayout(new BorderLayout());
		headerpanel.add(titleLabel, BorderLayout.WEST);
		headerpanel.add(closeBtn, BorderLayout.EAST);
		headerpanel.setPreferredSize(new Dimension(NUMBER_80, NUMBER_20));
		return headerpanel;

	}

	public static void removeSelectedTab() {
		TABBED.remove(getSelectedTab());
	}

	public static ConsoleOngletPanel getSelectedTab() {
		return (ConsoleOngletPanel) TABBED.getSelectedComponent();

	}

	public final ConsoleOngletPanel getConsoleOngletPanel() {
		return consoleOngletPnl;
	}

	public final void setConsoleOngletPanel(
			final ConsoleOngletPanel consoleOnglet) {
		this.consoleOngletPnl = consoleOnglet;
	}

	public static JTabbedPane getTabbedpane() {
		return TABBED;
	}
}
