package ouza.project.tools;

import javax.swing.ImageIcon;

public class ChargeurRessource {
	private transient String base;

	public  ChargeurRessource(final String bas) {
		this.base = bas;
	}

	public ChargeurRessource() {
		this("");
	}

	public final ImageIcon getIcon(final String icon) {

		return new ImageIcon(getClass().getResource(this.base + icon));
	}

}
