package ouza.project.view.component.onglet.editor;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import jsyntaxpane.DefaultSyntaxKit;
import ouza.project.launcher.Main;

public class EditorOngletCreator extends JPanel implements ChangeListener {

	private static final int HEIGHT_20 = 20;

	private static final long serialVersionUID = 1L;

	private final transient EditorOnglet editorOngletPanel;
	private transient JLabel titleLabel;
	private static final JTabbedPane TABBEDPANE = new JTabbedPane();

	public EditorOngletCreator(final String text) {

		super();
		DefaultSyntaxKit.initKit();
		TABBEDPANE.addChangeListener(this);
		editorOngletPanel = new EditorOnglet();
		editorOngletPanel.getEditorpane().setContentType("text/java");
		editorOngletPanel.getEditorpane().setText(text);

	}

	public final void addOnglet(final String titre) {

		final JPanel pan = headerOfOngletPanel(titre);

		TABBEDPANE.addTab(titre, editorOngletPanel);
		TABBEDPANE.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

		final int nbre = TABBEDPANE.indexOfComponent(editorOngletPanel);
		TABBEDPANE.setSelectedComponent(editorOngletPanel);
		TABBEDPANE.setTabComponentAt(nbre, pan);

	}

	private JPanel headerOfOngletPanel(final String titre) {

		final CloseButton closeBtn = new CloseButton();
		CloseButtonAction.close(closeBtn);
		titleLabel = new JLabel(titre);

		final JPanel headerPanel = new JPanel();
		headerPanel.setOpaque(false);
		headerPanel.setLayout(new BorderLayout());
		headerPanel.add(this.titleLabel, BorderLayout.WEST);
		headerPanel.add(closeBtn, BorderLayout.EAST);
		headerPanel.setSize((int) (titleLabel.getSize().getWidth() + closeBtn
				.getSize().getWidth()), HEIGHT_20);

		return headerPanel;
	}

	public static EditorOnglet getSelectedTab() {
		return (EditorOnglet) EditorOngletCreator.getTabbedpane()
				.getSelectedComponent();

	}

	@Override
	public final void stateChanged(final ChangeEvent arg0) {

		if (EditorOngletCreator.getTabbedpane().getTabCount() == 0) {

			Main.getWindow().setTitle("Java -  - OuZa");

		} else {

			getSelectedTab().getEditorpane().addKeyListener(new KeyListener() {

				@Override
				public void keyTyped(final KeyEvent event) {
					// empty method
				}

				@Override
				public void keyReleased(final KeyEvent event) {
					// empty method
				}

				@Override
				public void keyPressed(final KeyEvent event) {

					if (getSelectedTab().isEdited()) {

						Main.getWindow().setTitle(
								"Java 		- "
										+ getSelectedTab()
												.getProjectModeleSelector()
												.getClassName() + "		- OuZa");
					} else {
						final int index = EditorOngletCreator.getTabbedpane()
								.getSelectedIndex();
						final JPanel pan = (JPanel) EditorOngletCreator
								.getTabbedpane().getTabComponentAt(index);
						final JLabel titre = (JLabel) pan.getComponent(0);
						titre.setText("*" + titre.getText());
						getSelectedTab().setEdited(true);
					}
				}

			});

		}

	}

	public static boolean isSelectectedTabNull() {

		return getSelectedTab() == null;
	}

	public final JLabel getTitre() {
		return titleLabel;
	}

	public final void setTitre(final JLabel titre) {
		this.titleLabel = titre;
	}

	public final EditorOnglet getContenu() {
		return editorOngletPanel;
	}

	public static JTabbedPane getTabbedpane() {
		return TABBEDPANE;
	}

}
