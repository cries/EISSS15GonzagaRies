package de.fhkoeln.gm.eis.ss15.xmpp.client;

import javax.swing.SwingUtilities;

public class StartApp {
	public static void main(String[] args)
    {
        // schedule this for the event dispatch thread (edt)
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                XMPPClient Client1 = new XMPPClient("localhost", 5222, "user1", "login");
                XMPPClient Client2 = new XMPPClient("localhost", 5222, "user2", "login");
                XMPPClient Admin = new XMPPClient("localhost", 5222, "admin", "login");
            }
        });
    }
}
