package ouza.project.view;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

import ouza.project.tools.IconLaoder;

public class OuZaOpeningWindow extends JWindow {

	private static final int NUMBER_300 = 300;
	private static final int NUMBER_550 = 550;
	private static final long serialVersionUID = 1L;

	public OuZaOpeningWindow() {
		super();
		this.openingWindowContainerCreator();
		this.windowState();
	}

	private void windowState() {
		this.pack();

		this.setLocation(NUMBER_550, NUMBER_300);
		this.setVisible(true);
	}

	private void openingWindowContainerCreator() {
		final Container container = getContentPane();

		final JPanel openingWinPanel = openingWindowPanelCreator();

		container.add(openingWinPanel);

		openingWinPanel.setOpaque(false);
	}

	private JPanel openingWindowPanelCreator() {
		final JPanel openingWinPanel = new JPanel();
		openingWinPanel.setLayout(new BorderLayout());

		final JLabel backofficeLabel = new JLabel(
				IconLaoder.OUZA_ICON);

		openingWinPanel.add(backofficeLabel, BorderLayout.CENTER);
		return openingWinPanel;
	}

}
