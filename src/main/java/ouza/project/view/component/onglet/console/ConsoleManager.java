package ouza.project.view.component.onglet.console;

import java.util.ArrayList;
import java.util.List;

import ouza.project.ant.AntTargetExecution;
import ouza.project.modele.ProjectModeleSelector;
import ouza.project.view.component.onglet.editor.EditorOngletCreator;

public class ConsoleManager {

	private static List<Thread> threadList = new ArrayList<Thread>();

	public ConsoleManager() {

	}

	public final boolean search(final String name) {
		for (Thread thread : threadList) {

			if (thread.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public final Thread getThread(final String name) {
		for (Thread thread : threadList) {

			if (thread.getName().equals(name)) {
				return thread;
			}
		}
		return null;
	}

	public final void removeThread(final String name) {

		for (Thread thread : threadList) {

			if (thread.getName().equals(name)) {
				threadList.remove(thread);
				break;
			}
		}

	}

	public final void removeAllThread(final List<Thread> list) {

		threadList.removeAll(list);

	}

	public final void runThreadCreator(final String name) {

		if (search(name)) {

			createNewBrotherThread(name);

		} else {

			createNewThread(name).start();
		}
	}

	public final Thread createNewThread(final String name) {

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				threadCreator(name);
			}
		});
		thread.setName(name);
		threadList.add(thread);

		return thread;
	}

	public final void createNewBrotherThread(final String name) {

		int j = 1;
		while (search(name + j)) {
			System.out.println(name + j);
			j++;
		}
		createNewThread(name + j).start();
	}

	public final void threadCreator(final String name) {

		ConsoleOngletCreator consoleOngletCreator = newOngletCreator(name);

		consoleOngletCreator.getConsoleOngletPanel().setProjectName(name);

		ProjectModeleSelector pMD = EditorOngletCreator.getSelectedTab()
				.getProjectModeleSelector();

		new OuZaConsole().redirectSystemStreams(consoleOngletCreator
				.getConsoleOngletPanel().getEdit());

		new AntTargetExecution(pMD.getProjectPath(), pMD.getPackageName() + "."
				+ pMD.getClassName().replace(".java", ""));
	}

	private ConsoleOngletCreator newOngletCreator(final String name) {
		ConsoleOngletCreator consoleOngletCreator = new ConsoleOngletCreator();
		consoleOngletCreator.setTextToOnglet(name);
		consoleOngletCreator.addOnglet(name);
		return consoleOngletCreator;
	}

	public static List<Thread> getThreadList() {
		return threadList;
	}

	public static void setThreadList(final List<Thread> threads) {
		ConsoleManager.threadList = threads;
	}

	public final List<Thread> threadBrother(final String name) {
		List<Thread> listThread = new ArrayList<Thread>();

		for (Thread thread : threadList) {

			if (thread.getName().startsWith(name)) {
				listThread.add(thread);
			}
		}
		return listThread;
	}

	public final void destroyThread(final String name) {

		Thread thread = getThread(name);
		thread.interrupt();
		removeThread(name);

	}

	public final void removeAllDeadBrotherAndOrdinate(final String name) {
		List<Thread> brotherList = threadBrother(name);
		List<Thread> brotherDeadList = new ArrayList<Thread>();
		for (Thread thread : brotherList) {
			if (!thread.isAlive()) {
				brotherDeadList.add(thread);
			}
		}

		threadList.removeAll(brotherDeadList);
		int i = 0;
		for (Thread thread : brotherList) {
			thread.setName(name + i);
			i++;
		}
		threadList.addAll(brotherList);

	}

	public final void removeAllExcutedProjectOnglet() {

		List<Thread> list = new ArrayList<Thread>();
		for (Thread thread : threadList) {
			if (!thread.isAlive()) {
				removeOngelt(thread.getName());
				list.add(thread);
			}
		}
		threadList.removeAll(list);

	}

	public final ConsoleOngletPanel findOnglet(final String name) {
		int i = 0;
		while (i <= ConsoleOngletCreator.getTabbedpane().getTabCount()) {
			ConsoleOngletPanel consoleOngletPanel = (ConsoleOngletPanel) ConsoleOngletCreator
					.getTabbedpane().getComponentAt(i);

			if (consoleOngletPanel.getProjectName().equals(name)) {

				return consoleOngletPanel;

			}
			i++;
		}
		return null;
	}

	public final void removeOngelt(final String name) {
		ConsoleOngletCreator.getTabbedpane().remove(findOnglet(name));
	}
}
