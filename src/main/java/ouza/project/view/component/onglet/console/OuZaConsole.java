package ouza.project.view.component.onglet.console;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class OuZaConsole {

	
	
	private void updateTextArea(final JTextArea edit, final String text) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				edit.append(text);
			}
		});
	}

	public final void redirectSystemStreams(final JTextArea edit) {
		final OutputStream out = new OutputStream() {
			@Override
			public void write(final int byt) throws IOException {
				updateTextArea(edit, String.valueOf((char) byt));
			}

			@Override
			public void write(final byte[] byt, final int off, final int len)
					throws IOException {
				updateTextArea(edit, new String(byt, off, len));
			}

			@Override
			public void write(final byte[] byt) throws IOException {
				write(byt, 0, byt.length);
			}
		};

		System.setOut(new PrintStream(out, true));
		System.setErr(new PrintStream(out, true));
	}
}
