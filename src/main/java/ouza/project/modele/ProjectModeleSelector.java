package ouza.project.modele;

import java.io.File;

public class ProjectModeleSelector {

	private String projectName = "";
	private String packageName = "";
	private String className = "";

	private String classPath = "";
	private String packagePath = "";
	private String projectPath = "";

	private SimpleJavaProject simpleJavaProject;

	public ProjectModeleSelector(final String project, final String pack,
			final String classe) {
		this.projectName = project;
		this.packageName = pack;
		this.className = classe;
	}

	public ProjectModeleSelector(final File file) {
		simpleJavaProject = new SimpleJavaProject(file);

		if (simpleJavaProject.isSimpleJavaProject()) {

			projectName = simpleJavaProject.findOutProjectNameAndPath();
			packageName = simpleJavaProject.findOutPackageNameAndPath();

			projectPath = simpleJavaProject.getProjectPath();
			packagePath = simpleJavaProject.getPackagePath();

			if (file.isFile()) {
				className = file.getName();
				classPath = file.getAbsolutePath();
			}

		}

	}

	public final String getProjectName() {
		return projectName;
	}

	public final void setProjectName(final String name) {
		this.projectName = name;
	}

	public final String getPackageName() {
		return packageName;
	}

	public final void setPackageName(final String name) {
		this.packageName = name;
	}

	public final String getClassName() {
		return className;
	}

	public final void setClassName(final String name) {
		this.className = name;
	}

	public final String getClassPath() {
		return classPath;
	}

	public final void setClassPath(final String path) {
		this.classPath = path;
	}

	public final String getPackagePath() {
		return packagePath;
	}

	public final void setPackagePath(final String path) {
		this.packagePath = path;
	}

	public final String getProjectPath() {
		return projectPath;
	}

	public final void setProjectPath(final String path) {
		this.projectPath = path;
	}

	public final SimpleJavaProject getSimpleJavaProject() {
		return simpleJavaProject;
	}

	public final void setSimpleJavaProject(final SimpleJavaProject projectJava) {
		this.simpleJavaProject = projectJava;
	}

}
