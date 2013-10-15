package ouza.project.modele;

import java.io.File;

public final class CurrentFile {

	private static String workSpacePath = "";

	private static ProjectModeleSelector pMS;

	private CurrentFile() {
		// empty constructor
	}

	public static File getWorkSpaceFile() {
		return new File(workSpacePath);
	}

	public static String getProjectName() {
		return pMS.getProjectName();
	}

	public static String getPackageName() {
		return pMS.getPackageName();
	}

	public static String getClassName() {
		return pMS.getClassName();
	}

	public static String getClassPath() {
		return pMS.getClassPath();
	}

	public static String getPackagePath() {
		return pMS.getPackagePath();
	}

	public static String getProjectPath() {
		return pMS.getProjectPath();
	}

	public static String getWorkSpacePath() {
		return workSpacePath;
	}

	public static void setWorkSpacePath(final String path) {
		CurrentFile.workSpacePath = path;
	}

	public static  ProjectModeleSelector getpMS() {
		return pMS;
	}

	public static  void setpMS(final ProjectModeleSelector pMSe) {
		CurrentFile.pMS = pMSe;
	}
}
