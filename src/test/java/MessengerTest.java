import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class MessengerTest {
    private Messenger msg;
    private Template tmpl;
    private Client client;
    private MailServer ms;
    private TemplateEngine te;

    private String textToSend = "test";
    private String emailToSend = "test@test.test";

    @Test
    public void testWithSpy() {
        tmpl = spy(Template.class);
        client = spy(Client.class);
        ms = spy(MailServer.class);
        te = spy(TemplateEngine.class);
        msg = new Messenger(ms, te);
        when(te.prepareMessage(tmpl, client)).thenReturn(textToSend);
        when(client.getEmail()).thenReturn(emailToSend);
        msg.sendMessage(client, tmpl);
        verify(te).prepareMessage(tmpl, client);
        verify(ms).send(emailToSend, textToSend);
    }

    @Test
    public void testWithMock() {
        tmpl = mock(Template.class);
        client = mock(Client.class);
        ms = mock(MailServer.class);
        te = mock(TemplateEngine.class);
        msg = new Messenger(ms, te);
        when(te.prepareMessage(tmpl, client)).thenReturn(textToSend);
        when(client.getEmail()).thenReturn(emailToSend);
        msg.sendMessage(client, tmpl);
        verify(te).prepareMessage(tmpl, client);
        verify(ms).send(emailToSend, textToSend);
    }

}