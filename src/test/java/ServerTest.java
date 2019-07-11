import org.slf4j.impl.SimpleLogger;

import com.doit.net.unicom.udp.server.UnicomServerManager;

public class ServerTest {

	public static void main(String[] args) {
		try {
			System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "trace");
			System.setProperty(SimpleLogger.SHOW_SHORT_LOG_NAME_KEY, "true");
			System.setProperty(SimpleLogger.LOG_FILE_KEY, "System.out");
			UnicomServerManager.openTestModel();
			UnicomServerManager.startListener();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
