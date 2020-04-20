import java.util.Collection;
import java.util.HashSet;

public class RaceResultService {
    private Collection<Client> clients = new HashSet<Client>();

    public Collection<Client> getClients() {
        return clients;
    }

    public void setClients(Collection<Client> clients) {
        this.clients = clients;
    }

    public void addSubscriber(Client client){
        clients.add(client);
    }

    public void send(Message message){
        for (Client client: clients){
            client.receive(message);
        }
    }

    public void removeSubscriber(Client client){
        clients.remove(client);
    }
}