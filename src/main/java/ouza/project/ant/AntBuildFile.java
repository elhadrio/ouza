package ouza.project.ant;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

import ouza.project.logger.OuZaLogger;

// generate ant build file (build.xml)

public class AntBuildFile {

	private static final String INIT_BUILD = "init,build-project";

	private static final String BUILD_PROJECT = "build-project";

	private static final String MESSAGE = "message";

	private static final String YES = "yes";

	private static final String JAVA = "java";

	private static final String SRC = "src";

	private static final String VAR_TARGET = "${target}";

	private static final String VAR_SOURCE = "${source}";

	private static final String INCLUDEANTRUNTIME = "includeantruntime";

	private static final String DESTDIR = "destdir";

	private static final String CLASSNAME2 = "classname";

	private static final String FAILONERROR = "failonerror";

	private static final String FORK = "fork";

	private static final String VAR_DEBUGLEVEL = "${debuglevel}";

	private static final String TRUE = "true";

	private static final String DEBUG = "debug";

	private static final String JAVAC = "javac";

	private static final String BUILD_SUBPROJECTS = "build-subprojects";

	private static final String BUILD_SUBPRO_PRO = "build-subprojects,build-project";

	private static final String BUILD = "build";

	private static final String DEPENDS = "depends";

	private static final String DELETE = "delete";

	private static final String CLEAN = "clean";

	private static final String ALL_JAVA = "**/*.java";

	private static final String EXCLUDE = "exclude";

	private static final String FILESET = "fileset";

	private static final String TODIR = "todir";

	private static final String INCLUDEEMPTYDIRS = "includeemptydirs";

	private static final String DIR = "dir";

	private static final String FALSE = "false";

	private static final String COPY = "copy";

	private static final String MKDIR = "mkdir";

	private static final String INIT = "init";

	private static final String BIN = "bin";

	private static final String LOCATION2 = "location";

	private static final String DOT_CLASSPATH = ".classpath";

	private static final String ID_STRING = "id";

	private static final String PATH2 = "path";

	private static final String SOURCE = "source";

	private static final String VERSION_1_7 = "1.7";

	private static final String TARGET = "target";

	private static final String SOURCE_LINES_VARS = "source,lines,vars";

	private static final String VALUE = "value";

	private static final String DEBUGLEVEL = "debuglevel";

	private static final String ENV = "env";

	private static final String ENVIRONMENT = "environment";

	private static final String PROPERTY_STRING = "property";

	private static final String DOT = ".";

	private static final String BASEDIR = "basedir";

	private static final String PROJECT = "project";

	private static final String DEFAULT = "default";

	private static final String DOT_PROJECT = ".project";

	private static final String NAME = "name";

	private transient Document document;

	private transient Element root;

	public final void create(final String projectName, final String className)
			throws IOException {

		document = new Document();
		rootElement(projectName, className);
		document.setRootElement(root);

	}
	public final void write(final String path) {
		final XMLOutputter xmlOutput = new XMLOutputter();
		try {
			xmlOutput.output(document, new FileOutputStream(new File(path)));
		} catch (FileNotFoundException e) {
			OuZaLogger.LOGGER.error("file speciefied not found ", e);
		} catch (IOException e) {
			OuZaLogger.LOGGER.error("IO Exception", e);
		}
	}

	public final Element read(final File file) {
		final SAXBuilder sxb = new SAXBuilder();
		try {
			document = sxb.build(file);
		} catch (JDOMException e) {
			OuZaLogger.LOGGER.error("JDom exception", e);
		} catch (IOException e) {
			OuZaLogger.LOGGER.error("IO Exception", e);
		}
		return (Element) document.getRootElement();
	}

	private void rootElement(final String projectName, final String className) {
		root = new Element(PROJECT);

		root.setAttribute(BASEDIR, DOT).setAttribute(DEFAULT, className)
				.setAttribute(NAME, projectName + DOT_PROJECT);
		property();
		root.addContent(path(projectName));
		targets(projectName, className);

	}

	private void property() {

		root.addContent(envProperty());

		root.addContent(debugProperty());

		root.addContent(targetProperty());

		root.addContent(sourcerPoperty());
	}

	private void targets(final String projectName, final String className) {
		root.addContent(initTarget());

		root.addContent(cleanTarget());

		root.addContent(cleanAllTarget());

		root.addContent(buildTarget());

		root.addContent(buildsubprojectsTarget());

		root.addContent(buildProjectTarget(projectName));

		root.addContent(projectExcutionTarget(projectName, className));

	}

	public static void addElementToElement(final Element parentElement,
			final Element sonElement) {
		parentElement.addContent(sonElement);
	}

	private Element envProperty() {
		final Element child = new Element(PROPERTY_STRING);
		child.setAttribute(ENVIRONMENT, ENV);
		return child;
	}

	private  Element debugProperty() {

		final Element child = new Element(PROPERTY_STRING);
		child.setAttribute(NAME, DEBUGLEVEL);
		child.setAttribute(VALUE, SOURCE_LINES_VARS);
		return child;
	}

	private Element targetProperty() {

		final Element child = new Element(PROPERTY_STRING);
		child.setAttribute(NAME, TARGET);
		child.setAttribute(VALUE, VERSION_1_7);
		return child;

	}

	private static Element sourcerPoperty() {

		final Element child = new Element(PROPERTY_STRING);
		child.setAttribute(NAME, SOURCE);
		child.setAttribute(VALUE, VERSION_1_7);
		return child;

	}

	private Element path(final String projectName) {
		final Element path = new Element(PATH2);
		path.setAttribute(ID_STRING, projectName + DOT_CLASSPATH).addContent(
				pathElement(BIN));
		return path;
	}

	private static Element pathElement(final String location) {

		return new Element("pathelement").setAttribute(LOCATION2, location);
	}

	private Element initTarget() {
		final Element target = new Element(TARGET);

		target.setAttribute(NAME, INIT);
		target.addContent(new Element(MKDIR).setAttribute(DIR, BIN));
		target.addContent(new Element(COPY)
				.setAttribute(INCLUDEEMPTYDIRS, FALSE)
				.setAttribute(TODIR, BIN)
				.addContent(
						new Element(FILESET).setAttribute(DIR, SRC).addContent(
								new Element(EXCLUDE).setAttribute(NAME,
										ALL_JAVA))));
		return target;
	}

	private static Element cleanTarget() {
		final Element target1 = new Element(TARGET);
		target1.setAttribute(NAME, CLEAN).addContent(
				new Element(DELETE).setAttribute(DIR, BIN));
		return target1;
	}

	private Element cleanAllTarget() {
		final Element target2 = new Element(TARGET);
		target2.setAttribute(DEPENDS, CLEAN).setAttribute(NAME, "cleanall");
		return target2;
	}

	private Element buildTarget() {
		final Element target = new Element(TARGET);
		target.setAttribute(DEPENDS, BUILD_SUBPRO_PRO)
				.setAttribute(NAME, BUILD);
		return target;
	}

	private Element buildsubprojectsTarget() {
		final Element target = new Element(TARGET);
		target.setAttribute(NAME, BUILD_SUBPROJECTS);
		return target;
	}

	private Element javacElement(final String projectName) {
		final Element javacElement = new Element(JAVAC)
				.setAttribute(DEBUG, TRUE)
				.setAttribute(DEBUGLEVEL, VAR_DEBUGLEVEL)
				.setAttribute(DESTDIR, BIN)
				.setAttribute(FORK, YES)
				.setAttribute(INCLUDEANTRUNTIME, FALSE)
				.setAttribute(SOURCE, VAR_SOURCE)
				.setAttribute(TARGET, VAR_TARGET)
				.addContent(new Element(SRC).setAttribute(PATH2, SRC))
				.addContent(classPathElement(projectName));

		return javacElement;
	}

	private Element javaElement(final String projectName, final String className) {
		return new Element(JAVA).setAttribute(CLASSNAME2, className)
				.setAttribute(FAILONERROR, TRUE).setAttribute(FORK, YES)
				.addContent(classPathElement(projectName));
	}

	private Element classPathElement(final String projectName) {
		return new Element("classpath").setAttribute("refid", projectName
				+ DOT_CLASSPATH);
	}

	private Element buildProjectTarget(final String projectName) {
		final Element target = new Element(TARGET);
		target.setAttribute(DEPENDS, INIT).setAttribute(NAME, BUILD_PROJECT);
		target.addContent(new Element("echo").setAttribute(MESSAGE,
				"${ant.project.name}: ${ant.file}"));
		target.addContent(javacElement(projectName));

		return target;
	}

	private Element projectExcutionTarget(final String projectName,
			final String className) {
		final Element target = new Element(TARGET);
		target.setAttribute(NAME, className).setAttribute(DEPENDS, INIT_BUILD)
				.addContent(javaElement(projectName, className));
		return target;
	}

	

	public final void addJar(final String jarUrl) {
		if ((root instanceof Element)) {

			final Element pathelement = new Element("pathelement");
			pathelement.setAttribute("location", jarUrl);
			root.getChild("path").addContent(pathelement);
		}
	}

	public final void addNewProjectExcutorTarger(final File file,
			final String projectName, final String className) {

		root = read(file);
		addElementToElement(root, projectExcutionTarget(projectName, className));
	}

}
