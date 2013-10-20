package ouza.project.view.component.onglet.console;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ConsoleOngletPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private String projectName;

	private transient JTextArea editorTA;

	
	public ConsoleOngletPanel() {

		super();
		this.setLayout(new BorderLayout());
		editorTA = new JTextArea();
		JScrollPane scrollpane = new JScrollPane(editorTA);
		this.add(scrollpane);

	}

	public final JTextArea getEdit() {
		return editorTA;
	}

	public final void setEdit(final JTextArea edit) {
		this.editorTA = edit;
	}

	public final  String getProjectName() {
		return projectName;
	}

	public final void setProjectName(final String name) {
		this.projectName = name;
	}

}
