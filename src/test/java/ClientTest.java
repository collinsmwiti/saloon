// imports
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;

//class client test
public class ClientTest {

//for connectivity to the database
  @Rule
  public DatabaseRule database = new DatabaseRule();
// test to check if the descriptions which are the same are returned in a true manner
  @Test
  public void equals_returnsTrueIfDescriptionsAretheSame() {
    Client firstClient = new Client("June", 1);
    Client secondClient = new Client("June", 1);
    assertTrue(firstClient.equals(secondClient));
  }

// test to save returns if the are true
  @Test
  public void save_returnsTrueIfDescriptionsAretheSame() {
    Client myClient = new Client("June", 1);
    myClient.save();
    assertTrue(Client.all().get(0).equals(myClient));
  }

// test to return all instances as true
  @Test
  public void all_returnsAllInstancesOfClient_true() {
    Client firstClient = new Client("June", 1);
    firstClient.save();
    Client secondClient = new Client("Jane", 1);
    secondClient.save();
    assertEquals(true, Client.all().get(0).equals(firstClient));
    assertEquals(true, Client.all().get(1).equals(secondClient));
  }

// boths tests used to assign and modify unique ids
  @Test
  public void save_assignsIdToObject() {
    Client myClient = new Client("June", 1);
    myClient.save();
    Client savedClient = Client.all().get(0);
    assertEquals(myClient.getId(), savedClient.getId());
  }

  @Test
  public void getId_clientInstantiateWithAnID() {
    Client myClient = new Client("June", 1);
    myClient.save();
    assertTrue(myClient.getId() > 0);
  }

// test used to find and check that the client has the correct id
  @Test
  public void find_returnsClientWithSameId_secondClient() {
    Client firstClient = new Client("June", 1);
    firstClient.save();
    Client secondClient = new Client("Jane", 1);
    secondClient.save();
    assertEquals(Client.find(secondClient.getId()), secondClient);
  }

// test used to enhance relationship between parent-class stylist and child-class client
@Test
    public void save_savesStylistIdIntoDB_true() {
      Stylist myStylist = new Stylist("Caroline");
      myStylist.save();
      Client myClient = new Client("June", myStylist.getId());
      myClient.save();
      Client savedClient = Client.find(myClient.getId());
      assertEquals(savedClient.getStylistId(), myStylist.getId());
    }

// test used for updates
@Test
public void update_updatesClientDescription_true() {
  Client myClient = new Client("June", 1);
  myClient.save();
  myClient.update("Jeniffer");
  assertEquals("Jeniffer", Client.find(myClient.getId()).getDescription());
}

// test used to delete clients
@Test
public void delete_deletesClient_true() {
  Client myClient = new Client("June", 1);
  myClient.save();
  int myClientId = myClient.getId();
  myClient.delete();
  assertEquals(null, Client.find(myClientId));
}


 }
