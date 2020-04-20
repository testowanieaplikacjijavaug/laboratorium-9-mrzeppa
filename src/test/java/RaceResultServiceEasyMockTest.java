import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.easymock.EasyMock.*;

public class RaceResultServiceEasyMockTest {

    private RaceResultService rrs;
    private Client c;
    private Collection<Client> clients;

    @BeforeEach
    public void setUp() {
        rrs = new RaceResultService();
        c = mock(Client.class);
        clients = mock(Collection.class);
        rrs.setClients(clients);
    }

    @AfterEach
    public void tearDown() {
        c = null;
        clients = null;
        rrs = null;
    }

    @Test
    public void testAddSubscriber() {
        replay(c);
        rrs.addSubscriber(c);
        verify(c);
    }

    @Test
    public void testRemoveSubscriber() {
        replay(c);
        rrs.removeSubscriber(c);
        verify(c);
    }
}