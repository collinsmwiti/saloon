// imports
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

// class stylist test
public class StylistTest {

// connectivity
@Rule
  public DatabaseRule database = new DatabaseRule();

// test to return true if stylists descriptions are the same
@Test
    public void equals_returnsTrueIfDescriptionsAretheSame() {
      Stylist firstStylist = new Stylist("Caroline", "image");
      Stylist secondStylist = new Stylist("Caroline", "image");
      assertTrue(firstStylist.equals(secondStylist));
    }

//test used to check the saved stylists in the database
@Test
    public void save_savesIntoDatabase_true() {
      Stylist myStylist = new Stylist("Caroline", "image");
      myStylist.save();
      assertTrue(Stylist.all().get(0).equals(myStylist));
    }

// test to return all instances of stylists as true

@Test
   public void all_returnsAllInstancesOfStylist_true() {
     Stylist firstStylist = new Stylist("Caroline", "image");
     firstStylist.save();
     Stylist secondStylist = new Stylist("Betty", "image");
     secondStylist.save();
     assertEquals(true, Stylist.all().get(0).equals(firstStylist));
     assertEquals(true, Stylist.all().get(1).equals(secondStylist));
   }

// test used for assigning id to stylists
@Test
  public void save_assignsIdToObject() {
    Stylist myStylist = new Stylist("Caroline", "image");
    myStylist.save();
    Stylist savedStylist = Stylist.all().get(0);
    assertEquals(myStylist.getId(), savedStylist.getId());
  }

@Test
 public void getId_stylistsInstantiateWithAnId_1() {
 Stylist testStylist = new Stylist("Caroline", "image");
 testStylist.save();
 assertTrue(testStylist.getId() > 0);
 }

// test used to find and return stylists with the same id
@Test
  public void find_returnsStylistWithSameId_secondStylist() {
  Stylist firstStylist = new Stylist("Caroline", "image");
  firstStylist.save();
  Stylist secondStylist = new Stylist("Betty", "image");
  secondStylist.save();
  assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
  }

// test to retrieve all clients from the database
@Test
    public void getClients_retrievesALlClientsFromDatabase_clientsList() {
      Stylist myStylist = new Stylist("Caroline", "image" );
      myStylist.save();
      Client firstClient = new Client("June", myStylist.getId());
      firstClient.save();
      Client secondClient = new Client("Jane", myStylist.getId());
      secondClient.save();
      Client[] clients = new Client[] { firstClient, secondClient };
      assertTrue(myStylist.getClients().containsAll(Arrays.asList(clients)));
    }

    // test used for updates
    @Test
    public void update_updatesStylistDescription_true() {
      Stylist myStylist = new Stylist("Caroline", "image");
      myStylist.save();
      myStylist.update("description", "myImage");
      assertEquals("description", Stylist.find(myStylist.getId()).getDescription());
    }

    // test used to delete clients
    @Test
    public void delete_deletesStylist_true() {
      Stylist myStylist = new Stylist("Caroline", "image");
      myStylist.save();
      int myStylistId = myStylist.getId();
      myStylist.delete();
      assertEquals(null, Stylist.find(myStylistId));
    }

}
