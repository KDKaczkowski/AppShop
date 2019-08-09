package DB;

import CommercialGoods.Goods;
import Exceptions.AdditionFailed;
import Exceptions.ObjectNotFound;
import Users.Admin;
import Users.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PrimitiveDBTest{
    PrimitiveDB db = new PrimitiveDB(true);
    Admin admin;
    Customer customer;
    Goods good;

    PrimitiveDBTest() throws AdditionFailed {
    }

    @BeforeAll
    void start() throws NoSuchAlgorithmException, AdditionFailed{
        admin = new Admin("Blazej", "bykowar", db);
        customer = new Customer("Kamil", "swieczka", 23, db);
        good = new Goods("Dark bread", "Bakery", 34, 34.65, true);
        db.addNewGood( good );
        db.addNewGood(new Goods("White bread", "Bakery", 12, 2.50, true) );
        db.addNewGood(new Goods("Kaiser roll", "Bakery", 132, 12.50, true));
        db.addNewGood(new Goods("Lay's Paprika", "Candy", 122, 22.50, true));


    }

    @Test
    void getAdminByName() throws ObjectNotFound {
        assertNotNull(db.getAdminByName("Blazej"));
    }
    @Test
    void getAdminByNameFailed(){
        System.out.println("Richard the Lionheart");
        ObjectNotFound notFound = assertThrows(
                ObjectNotFound.class,
                () -> db.getAdminByName("Richard the Lionheart")
        );
    }

    @Test
    void getCustomerByName() throws ObjectNotFound {
        assertNotNull(db.getCustomerByName("Kamil"));
    }

    @Test
    void getCustomerByNameFailed(){
        System.out.println("Motivation to work");
        ObjectNotFound notFound = assertThrows(
                ObjectNotFound.class,
                () -> db.getCustomerByName("Motivation to work")
        );
    }

    @Test
    void getCustomerById() throws ObjectNotFound {

        assertTrue(db.getCustomerById( customer.getId() ).equals( customer ) );
    }


    @Test
    void getCustomerByIdFailed(){
        System.out.println("Notes in 4'33'' ");
        ObjectNotFound notFound = assertThrows(
                ObjectNotFound.class,
                () -> db.getCustomerById(UUID.randomUUID())
        );
    }

    @Test
    void getAdminById() throws ObjectNotFound{

        assertTrue( db.getAdminById( admin.getId() ).equals( admin ) );
    }

    @Test
    void getAdminByIdFailed(){
        System.out.println("Favourite job");
        ObjectNotFound notFound = assertThrows(
                ObjectNotFound.class,
                () -> db.getCustomerById(UUID.randomUUID())
        );
    }

    @Test
    void getPasswordByNameOfAdmin() throws ObjectNotFound{
        assertEquals(db.getPasswordByName("Blazej"), db.getAdminByName("Blazej").getPassword() );

    }



    @Test
    void getPasswordByNameOfCustomer() throws ObjectNotFound{
        assertEquals(db.getPasswordByName("Kamil"), db.getCustomerByName("Kamil").getPassword());

    }

    @Test
    void getPasswordByNameOfFailed(){
        System.out.println("Holy Grail");
        ObjectNotFound notFound = assertThrows(
                ObjectNotFound.class,
                () -> db.getPasswordByName("Holy Grail")
        );
    }


    @Test
    void getPasswordByNameOfAdminFailed(){
        System.out.println("Spoderman");
        ObjectNotFound notFound = assertThrows(
                ObjectNotFound.class,
                () -> db.getPasswordByName("Spoderman")
        );
    }

    @Test
    void getPasswordByNameOfCustomerFailed(){
        System.out.println("Mickey Mouse");
        ObjectNotFound notFound = assertThrows(
                ObjectNotFound.class,
                () ->db.getPasswordByName("Mickey Mouse")
        );
    }

    @Test
    void getGoodByName() throws ObjectNotFound{
        assertEquals(good, db.getGoodByName("Dark bread") );

    }

    @Test
    void getGoodByNameFailed(){
        ObjectNotFound notFound = assertThrows(
                ObjectNotFound.class,
                () -> db.getGoodByName("Millipede")
        );
    }

    @Test
    void getGoodsOfType() throws ObjectNotFound{
        assertNotNull(db.getGoodsOfType("Bakery"));
    }

    @Test
    void getGoodsOfTypeFailed(){
        System.out.println("Own Web application");
        ObjectNotFound exception = assertThrows(
                ObjectNotFound.class,
                () -> db.getGoodsOfType("Own Web application")
        );
    }


    @Test
    void addOrRemoveExistingGood() {
    }

    @Test
    void getGoodByNameAndType() throws ObjectNotFound{
        assertEquals(good, db.getGoodByNameAndType("Dark bread", "Bakery") );
    }

    @Test
    void getGoodByNameAndTypeFailed(){
        System.out.println("Pieniadze z komunii");
        ObjectNotFound notFound = assertThrows(
                ObjectNotFound.class,
                () -> db.getGoodByNameAndType("Pieniadze z komunii", "Imaginacja")
        );
    }
}