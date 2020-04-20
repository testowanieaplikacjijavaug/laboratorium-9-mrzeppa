import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RaceResultServiceMockitoTest {

    private RaceResultService rrs;
    private Client c;
    private Message m;
    private HashSet<Client> clients;

    @BeforeEach
    public void setup() {
        c = mock(Client.class);
        m = mock(Message.class);
        rrs = new RaceResultService();
        clients = spy(new HashSet<>());

        rrs.setClients(clients);
    }

    @AfterEach
    public void tearDown() {
        c = null;
        m = null;
        rrs = null;
        clients = null;
    }

    @Test
    public void testRemoveSubscriber() {
        rrs.addSubscriber(c);
        rrs.removeSubscriber(c);
        assertThat(clients).isEmpty();
        verify(clients).remove(any(Client.class));
    }

    @Test
    public void testAddSubscriber() {
        rrs.addSubscriber(c);
        assertThat(clients).hasSize(1).contains(c);
        verify(clients).add(any(Client.class));
    }

    @Test
    public void sendTest() {
        clients.add(c);

        rrs.send(m);

        verify(c).receive(m);
    }

}