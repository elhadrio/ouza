package ouza.project.view.component.onglet.editor;

import java.awt.BorderLayout;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ouza.project.modele.ProjectModeleSelector;

public class EditorOnglet extends JPanel {

	private static final long serialVersionUID = 1L;

	private transient ProjectModeleSelector pMS;

	private boolean edited = false;

	private transient JEditorPane editorPane;

	public EditorOnglet() {

		super();
		this.setLayout(new BorderLayout());
		editorPane = new JEditorPane();
		JScrollPane scrollPane = new JScrollPane(editorPane);
		this.add(scrollPane);

	}

	public final JEditorPane getEditorpane() {
		return editorPane;
	}

	public final void setEditorpane(final JEditorPane editorpane) {
		this.editorPane = editorpane;
	}

	public final boolean isEdited() {
		return edited;
	}

	public final void setEdited(final boolean edit) {
		this.edited = edit;
	}

	public final ProjectModeleSelector getProjectModeleSelector() {
		return pMS;
	}

	public final void setProjectModeleSelector(final ProjectModeleSelector pMSe) {
		this.pMS = pMSe;
	}

}
