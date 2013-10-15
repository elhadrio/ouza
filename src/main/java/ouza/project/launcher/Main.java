package ouza.project.launcher;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import ouza.project.view.OuZaOpeningWindow;
import ouza.project.view.OuZaWindow;
import ouza.project.view.OuZaWorkSpaceWindow;

public final class Main {

	private static final int NUMBER_300 = 600;
	private static OuZaWindow window;
	private static OuZaOpeningWindow openingWindow;

	private Main() {
		// empty constructor
	}

	public static void main(final String[] args) throws InterruptedException {

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		openingWindow = new OuZaOpeningWindow();

		Thread openingThread = new Thread() {
			public void run() {

				openingWindow.dispose();

				new OuZaWorkSpaceWindow();

			}
		};
		Thread.sleep(NUMBER_300);
		openingThread.start();

	}

	public static OuZaWindow getWindow() {
		return window;
	}

	public static void setWindow(final OuZaWindow windowIDE) {
		Main.window = windowIDE;
	}

}
