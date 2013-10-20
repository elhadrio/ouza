package ouza.project.ant;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import ouza.project.logger.OuZaLogger;

public class JDoom {

	private static Element project;
	private static org.jdom2.Document document;

	public JDoom(final String url, final String projectName,
			final String classname) throws IOException {
		File fichier = new File(url);
		lirefichierXml(fichier);
		setClassName(classname, projectName);
		setIDpath(projectName);
		enregistre(url);
	}

	public final void enregistre(final String fichier)
			throws java.io.IOException {

		final XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());

		sortie.output((Document) document, new FileOutputStream(fichier));

	}

	public final void lirefichierXml(final File file) {
		final SAXBuilder sxb = new SAXBuilder();
		try {
			document = sxb.build(file);
		} catch (JDOMException e) {
			OuZaLogger.LOGGER.error(e);
		} catch (IOException e) {
			OuZaLogger.LOGGER.error(e);
		}
		project = document.getRootElement();
	}

	public final void setClassName(final String classname,
			final String projectname) {
		if ((project instanceof Element)) {
			final List<Element> listtarget = project.getChildren("target");
			final Iterator<Element> iterator = listtarget.iterator();

			while (iterator.hasNext()) {
				final Element courant = (Element) iterator.next();
				if (courant.getChild("java") != null) {
					courant.getChild("java").getAttribute("classname")
							.setValue(classname);

					courant.getChild("java").getChild("classpath")
							.getAttribute("refid")
							.setValue(projectname + ".classpath");

				}
			}
		}

	}

	public final void setIDpath(final String idpath) {
		project.getChild("path").getAttribute("id")
				.setValue(idpath + ".classpath");

		if ((project instanceof Element)) {
			final List<Element> listtarget = project.getChildren("target");
			final Iterator<Element> i = listtarget.iterator();

			while (i.hasNext()) {
				final Element courant = (Element) i.next();
				if (courant.getChild("javac") != null) {

					courant.getChild("javac").getChild("classpath")
							.getAttribute("refid")
							.setValue(idpath + ".classpath");

				}
			}
		}
	}

	public final void addJar(final String jarUrl) {
		if ((project instanceof Element)) {

			final Element pathelement = new Element("pathelement");
			pathelement.setAttribute("location", jarUrl);
			project.getChild("path").addContent(pathelement);
		}
	}

}
