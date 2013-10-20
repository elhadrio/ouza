package ouza.project.modele;

import java.io.File;

import ouza.project.tools.FileManager;

public class SimpleJavaProject {

	private static final String SRC = "src";

	private File file;

	private String packagePath = "";
	private String projectPath = "";
	private String classPath = "";

	public SimpleJavaProject(final File fil) {
		this.file = fil;
	}

	public SimpleJavaProject(final String project, final String pack,
			final String classe) {

		projectPath = CurrentFile.getWorkSpacePath() + project;
		packagePath = projectPath + "//src//" + pack;
		setClassPath(packagePath.replace(".", "//") + "//" + classe+".java");

	}

	public final String findOutProjectNameAndPath() {
		final File workSpace = CurrentFile.getWorkSpaceFile();
		File temp = file;
		String projectName = null;
		while (!temp.equals(workSpace)) {

			projectName = temp.getName();
			projectPath = temp.getAbsolutePath();
			temp = temp.getParentFile();
		}

		return projectName;

	}

	public final String findOutPackageNameAndPath() {

		String packageName = "";
		File workSpace = CurrentFile.getWorkSpaceFile();

		if (file != null) {
			File temp = file;
			if (isProjectFileOrWorkSpaceFile(workSpace, temp)) {
				packagePath = "";

			} else if (temp.isFile()) {
				temp = file.getParentFile();
				if (isProjectFile(temp)) {
					packageName = "";

				} else {
					packageName = packageNameParser(temp);

				}
			} else {
				temp = file;
				packageName = packageNameParser(temp);

			}
			packagePath = temp.getAbsolutePath();
		}

		return packageName;

	}

	public final String packageNameParser(final File filee) {
		String packageName = "";
		File temp = filee;
		while (isPackageDerictory(temp)) {

			packageName = temp.getName() + "." + packageName;
			temp = temp.getParentFile();

		}
		return substractLastChar(packageName);
	}

	private String substractLastChar(final String packageName) {
		String temp = packageName;
		int i = packageName.length();
		if (i != 0) {
			temp = temp.substring(0, i - 1);
		}
		return temp;
	}

	private boolean isPackageDerictory(final File temp) {
		return temp != null && (!temp.getName().equals(SRC));
	}

	public final boolean isSimpleJavaProject() {
		String projectName = findOutProjectNameAndPath();

		File projectFile = new File(CurrentFile.getWorkSpacePath()
				+ projectName);
		File[] files = FileManager.searchDirectoryByName(projectFile, "src");

		if (files != null) {
			for (File temp : files) {
				if (temp.getParentFile().equals(projectFile)) {
					return true;
				}

			}
		}
		return false;

	}
	
	public void createProject(){
		new File(projectPath).mkdirs();
	}
	public void createPackage(){
		new File(packagePath).mkdirs();
	}
	
	private boolean isProjectFile(final File filee) {

		findOutProjectNameAndPath();
		return new File(projectPath).equals(filee);
	}

	private boolean isProjectFileOrWorkSpaceFile(final File workSpace,
			final File temp) {
		return (temp.equals(workSpace) || temp.getParentFile()
				.equals(workSpace));

	}

	public final String getPackagePath() {
		return packagePath;
	}

	public final void setPackagePath(final String packPath) {
		this.packagePath = packPath;
	}

	public final String getProjectPath() {
		return projectPath;
	}

	public final void setProjectPath(final String projPath) {
		this.projectPath = projPath;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

}
