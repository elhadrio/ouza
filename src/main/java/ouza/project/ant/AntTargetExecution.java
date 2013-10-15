package ouza.project.ant;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

import ouza.project.logger.OuZaLogger;

// Execute the targets written on build.xml
public class AntTargetExecution {

	public AntTargetExecution(final String projectUrl, final String target) {

		File buildfile = new File(projectUrl + "//build.xml");
		Project project = new Project();

		project.setUserProperty("ant.file", projectUrl + "//build.xml");
//		project.setProperty("java.home", "C://Program Files (x86)//Java/jdk1.7.0_25//bin");
		DefaultLogger antDefaultLogger = new DefaultLogger();
		antDefaultLogger.setEmacsMode(true);
		antDefaultLogger.setErrorPrintStream(System.err);
		antDefaultLogger.setOutputPrintStream(System.out);

		antDefaultLogger.setMessageOutputLevel(Project.MSG_INFO);
		project.addBuildListener(antDefaultLogger);

		try {
			project.fireBuildStarted();
			project.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();
			project.addReference("ant.projectHelper", helper);
			helper.parse(project, buildfile);

			project.executeTarget(target);
			project.fireBuildFinished(null);

		} catch (BuildException e) {
			OuZaLogger.LOGGER.error("build expection", e);
		}
	}

}