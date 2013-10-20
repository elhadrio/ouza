package ouza.project.modele;

import java.io.File;

public class SimpleFile {

	private transient String className;

	public final boolean isSimpleFile(final File file) {

		final File workSpace = CurrentFile.getWorkSpaceFile();

		boolean returnStatement = true;

		if (file.isFile()) {
			File temp = file.getParentFile();
			while (!temp.equals(workSpace)) {
				temp = temp.getParentFile();
				if (temp.getName().equals("src")) {
					returnStatement = false;
				}
			}
		}
		return returnStatement;

	}

	public final String getClassName() {
		return className;
	}

	public final void setClassName(final String name) {
		this.className = name;
	}
}
