package ouza.project.view.component.onglet.editor;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicButtonUI;

public class CloseButton extends JButton {

	
	private static final int NUMBER_6 = 6;

	private static final long serialVersionUID = 1L;
	
	private static final int SIZE_17 = 17;

	public CloseButton() {
		super();
		

		setPreferredSize(new Dimension(SIZE_17, SIZE_17));
		setToolTipText("close this tab");

		// Make the button looks the same for all Laf's
		setUI(new BasicButtonUI());
		// Make it transparent
		setContentAreaFilled(false);
		// No need to be focusable
		setFocusable(false);
		setBorder(BorderFactory.createEtchedBorder());
		setBorderPainted(false);
		// Making nice rollover effect
		// we use the same listener for all buttons

		setRolloverEnabled(true);

	}

	
	public void updateUI() {
		// we don't want to update UI for this button
		//empty method
	}

	// paint the cross
	protected final void paintComponent(final Graphics graphic) {
		super.paintComponent(graphic);
		final Graphics2D g2D = (Graphics2D) graphic.create();
		// shift the image for pressed buttons
		if (getModel().isPressed()) {
			g2D.translate(1, 1);
		}
		g2D.setStroke(new BasicStroke(2));
		g2D.setColor(Color.BLACK);
		if (getModel().isRollover()) {
			g2D.setColor(Color.MAGENTA);
		}
	final	int delta = NUMBER_6;
		g2D.drawLine(delta, delta, getWidth() - delta - 1, getHeight() - delta
				- 1);
		g2D.drawLine(getWidth() - delta - 1, delta, delta, getHeight() - delta
				- 1);
		g2D.dispose();
	}
}
