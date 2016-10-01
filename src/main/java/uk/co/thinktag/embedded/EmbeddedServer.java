package uk.co.thinktag.embedded;

import java.util.Set;

import org.apache.activemq.artemis.core.security.CheckType;
import org.apache.activemq.artemis.core.security.Role;
import org.apache.activemq.artemis.jms.server.embedded.EmbeddedJMS;
import org.apache.activemq.artemis.spi.core.security.ActiveMQSecurityManager;

public class EmbeddedServer {

	private static EmbeddedJMS jmsServer;

	{
	    Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            public void run() {
                try {
                    stopServer();
                } catch (Exception e) {
                }
            }
        }));
	}
	
	private EmbeddedServer() {
	}

	public static void main(String args[]) throws Exception {

		Class.forName(org.apache.activemq.artemis.core.server.ActiveMQServerLogger.class.getCanonicalName());

		Class.forName(org.apache.activemq.artemis.core.server.ActiveMQServerLogger_$logger.class.getCanonicalName());

		EmbeddedJMS jmsServer = new EmbeddedJMS();
		jmsServer.setConfigResourcePath("broker.xml");
		jmsServer.setSecurityManager(new ActiveMQSecurityManager() {


			@Override
			public boolean validateUser(String user, String password) {
				return true;
			}

			@Override
			public boolean validateUserAndRole(String user, String password, Set<Role> roles, CheckType checkType) {
				return true;
			}
		});
		jmsServer.start();
	}

	private static void stopServer() throws Exception {
		if (jmsServer != null) {
			jmsServer.stop();
		}
	}
}
