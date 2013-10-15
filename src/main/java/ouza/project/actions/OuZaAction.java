package ouza.project.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;

import ouza.project.dialog.BoiteNewClass;
import ouza.project.dialog.BoiteNewPackage;
import ouza.project.dialog.BoiteNewProject;
import ouza.project.modele.CurrentFile;
import ouza.project.view.OuZaWindow;
import ouza.project.view.component.onglet.console.ConsoleManager;
import ouza.project.view.component.onglet.editor.EditorOnglet;
import ouza.project.view.component.onglet.editor.EditorOngletCreator;
import ouza.project.view.component.onglet.editor.OngletManager;

public final class OuZaAction {

	private OuZaAction() {
		// empty constructor
	}

	public static ActionListener fileAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {

				CurrentFile.setpMS(EditorOngletCreator.getSelectedTab()
						.getProjectModeleSelector());

			}
		};

	}

	public static ActionListener copy() {
		return new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				final EditorOnglet selectedOnglet = EditorOngletCreator
						.getSelectedTab();

				if (!EditorOngletCreator.isSelectectedTabNull()) {
					selectedOnglet.getEditorpane().copy();
					
				}

			}
		};
	}

	public static ActionListener cut() {
		return new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				final EditorOnglet selectedOnglet = EditorOngletCreator
						.getSelectedTab();

				if (!EditorOngletCreator.isSelectectedTabNull()) {
					selectedOnglet.getEditorpane().cut();
				}

			}
		};
	}

	public static ActionListener paste() {
		return new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				final EditorOnglet selectedOnglet = EditorOngletCreator
						.getSelectedTab();
				if (!EditorOngletCreator.isSelectectedTabNull()) {
					selectedOnglet.getEditorpane().paste();
				}

			}
		};
	}

	public static ActionListener selectAll() {
		return new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				final EditorOnglet selectedOnglet = EditorOngletCreator
						.getSelectedTab();

				if (!EditorOngletCreator.isSelectectedTabNull()) {
					selectedOnglet.getEditorpane().selectAll();
				}
			}

		};
	}

	public static ActionListener switchWork() {
		return new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {

				final JFileChooser filechooser = new JFileChooser();
				filechooser.setDialogTitle("S�lectionner votre WorkSpace");
				filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				final int valeur = filechooser.showOpenDialog(null);
				if (valeur == JFileChooser.APPROVE_OPTION) {
					CurrentFile.setWorkSpacePath(filechooser.getSelectedFile()
							.getAbsolutePath() + "\\");
					OuZaWindow.getExplorerTree().updateUI();
				}
			}

		};
	}

	public static ActionListener run() {

		return new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {

				if (CurrentFile.getpMS() != null) {

					if (!EditorOngletCreator.isSelectectedTabNull()) {
						new ConsoleManager().runThreadCreator(CurrentFile
								.getProjectName());
					}
				}

			}
		};

	}

	public static ActionListener save() {
		return new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				final EditorOnglet selectedOnglet = EditorOngletCreator
						.getSelectedTab();

				if (!EditorOngletCreator.isSelectectedTabNull()) {
					if (selectedOnglet.isEdited()) {
						OngletManager.saveOnglet(EditorOngletCreator
								.getTabbedpane().getSelectedIndex());

					}
				}

			}
		};
	}

	public static ActionListener saveALL() {
		return new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				if (!EditorOngletCreator.isSelectectedTabNull()) {
					OngletManager.saveAllOnglet();
				}

			}
		};
	}

	public static ActionListener close() {
		return new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {

				if (!EditorOngletCreator.isSelectectedTabNull()) {
					OngletManager.closeOnglet(EditorOngletCreator
							.getTabbedpane().getSelectedIndex());
				}

			}
		};
	}

	public static ActionListener closeALL() {
		return new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				OngletManager.closeAllOnglet();
			}
		};
	}

	public static ActionListener newProjectAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				final BoiteNewProject boiteNewProject = new BoiteNewProject();
				boiteNewProject.setVisible(true);

			}

		};

	}

	public static ActionListener openFiletAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				// method empty
			}

		};

	}

	public static void undoAction() {

		// final UndoManager undo = new UndoManager();
		// JEditorPane editorPane = EditorOngletCreator.getSelectedTab()
		// .getEditorpane();
		//
		// Document doc = EditorOngletCreator.getSelectedTab()
		// .getEditorpane().getDocument();
		// doc.addUndoableEditListener(new UndoableEditListener() {
		// public void undoableEditHappened(UndoableEditEvent evt) {
		// undo.addEdit(evt.getEdit());
		// }
		// });

		// ((AbstractButton) obj).addActionListener(new ActionListener() {
		//
		// @Override
		// public void actionPerformed(final ActionEvent event) {
		// // method empty

		/*
		 * editorPane.getActionMap().put("Undo", new AbstractAction("Undo") {
		 * public void actionPerformed(final ActionEvent evt) { try { if
		 * (undo.canUndo()) { undo.undo(); } } catch (CannotUndoException e) {
		 * OuZaLogger.getLogger().error(e); } } });
		 * 
		 * editorPane.getInputMap().put(KeyStroke.getKeyStroke("control Z" ),
		 * "Undo");
		 */
	}

	// });

	// }

	public static ActionListener redoAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				// method empty

			}

		};

	}

	public static ActionListener newClassAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {

				newClasseOrEnumOrInterface("Class", "newclass_wiz.gif", true);

			}

		};

	}

	public static ActionListener newInterfaceAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				newClasseOrEnumOrInterface("Interface", "newint_wiz.gif", false);
			}

		};

	}

	public static ActionListener newEnumAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				newClasseOrEnumOrInterface("Enum", "newenum_wiz.gif", false);
			}

		};

	}

	private static void newClasseOrEnumOrInterface(final String name,
			final String icon, final boolean isClass) {
		final BoiteNewClass newClass = new BoiteNewClass(name, icon);

		if (CurrentFile.getpMS() != null) {

			final String packageName = CurrentFile.getPackageName();

			if ("".equals(packageName)) {
				newClass.browser1Activation(false);
			}

			newClass.remplirFormulaire(CurrentFile.getpMS());
			if (isClass) {
				newClass.setMainVisibility(isClass);
			}
		}
		newClass.setVisible(true);

	}

	public static ActionListener newPackageAction() {
		return new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent event) {
				BoiteNewPackage newPackage = new BoiteNewPackage();
				if (CurrentFile.getpMS() != null) {

					newPackage.remplireFormulaire(CurrentFile.getProjectName(),
							CurrentFile.getPackageName());

				}
				newPackage.setVisible(true);

			}

		};

	}

}
