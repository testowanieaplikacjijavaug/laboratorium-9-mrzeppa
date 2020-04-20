import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FriendshipsMongoTest {

    @Mock
    private FriendsCollection friendsCollection;

    @InjectMocks
    private FriendshipsMongo friendshipsMongo;

    @Test
    public void mockingWorksAsExpected() {
        Person joe = new Person("Joe");

        when(friendsCollection.findByName("Joe")).thenReturn(joe);

        assertThat(friendsCollection.findByName("Joe")).isEqualTo(joe);
    }

    @Test
    public void alexDoesNotHaveFriends() {
        assertThat(friendshipsMongo.getFriendsList("Alex")).isEmpty();
    }

    @Test
    public void joeHas5Friends() {
        List<String> expected = Arrays.asList("friend1", "friend2", "friend3", "friend4", "friend5");
        Person joe = mock(Person.class);
        when(friendsCollection.findByName("Joe")).thenReturn(joe);
        when(joe.getFriends()).thenReturn(expected);

        assertThat(friendshipsMongo.getFriendsList("Joe")).hasSize(5).containsOnly("friend1", "friend2", "friend3", "friend4", "friend5");

        verify(friendsCollection).findByName("Joe");
        verify(joe).getFriends();
    }

    @Test
    public void joeWithAlexAreNotFriends() {
        Person joe = mock(Person.class);
        when(friendsCollection.findByName("Joe")).thenReturn(joe);
        when(joe.getFriends()).thenReturn(new ArrayList<>());

        assertThat(friendshipsMongo.areFriends("Joe", "Alex")).isFalse();
        verify(friendsCollection).findByName("Joe");
        verify(joe).getFriends();
    }

    @Test
    public void joeAndMarcAreFriends() {
        Person joe = mock(Person.class);
        when(friendsCollection.findByName("Joe")).thenReturn(joe);
        when(joe.getFriends()).thenReturn(Collections.singletonList("Marc"));

        assertThat(friendshipsMongo.areFriends("Joe", "Marc")).isTrue();
        verify(friendsCollection).findByName("Joe");
        verify(joe).getFriends();
    }

    @Test
    public void nameOfJoeIsJoe() {
        Person joe = mock(Person.class);
        when(joe.getName()).thenReturn("Joe");

        assertThat(joe.getName()).isEqualTo("Joe");
        verify(joe).getName();
    }

    @Test
    public void addingFriendWithEmptyStringThrowsException() {
        Person joe = mock(Person.class);

        doThrow(new IllegalArgumentException()).when(joe)
                .addFriend(anyString());

        assertThatThrownBy(() -> joe.addFriend("")).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void settingNameWithEmptyStringThrowsException() {
        Person joe = mock(Person.class);

        doThrow(new IllegalArgumentException()).when(joe).setName(anyString());

        assertThatThrownBy(() -> joe.setName("")).isInstanceOf(IllegalArgumentException.class);
    }



}