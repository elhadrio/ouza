package ouza.project.tools;

import java.io.Closeable;

import ouza.project.logger.OuZaLogger;

public final class IOUtil {
	private IOUtil() {}

	public static void closeQuietly(Closeable... closeables) {
		for (Closeable c : closeables) {
			if (c != null) try {
				c.close();
			} catch(Exception e) {
				OuZaLogger.LOGGER.fatal(e, e);
				
			}
		}
	}
	
	
}