package websocket;

import javax.websocket.*;
import com.fazecast.jSerialComm.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/ws")
public class TempServer {

    @OnOpen
    public void open(Session session) throws IOException, EncodeException {
        ReadSerial readSerial = new ReadSerial(session);
        session.getBasicRemote().sendText("Showing Temperatures");
    }

    @OnMessage
    public void handleMessage(String message, Session session) throws IOException, EncodeException {
        for (Session peer : session.getOpenSessions())
            peer.getBasicRemote().sendText(message);
    }
}