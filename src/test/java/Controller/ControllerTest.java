package Controller;

import CommercialGoods.Goods;
import DB.PrimitiveDB;
import Exceptions.AdditionFailed;
import Exceptions.ObjectNotFound;
import Users.Admin;
import Users.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ControllerTest {
    private PrimitiveDB db = new PrimitiveDB(true);
    private Controller controller = new Controller(db);

    ControllerTest() throws AdditionFailed {
    }

    @BeforeAll
    void setUp() throws AdditionFailed, NoSuchAlgorithmException {
        Admin admin = new Admin("Blazej", "bykowar", db);
        Customer customer = new Customer("Kamil", "swieczka", 23, db);
        Goods good = new Goods("Dark bread", "Bakery", 12, 34.65, true);
        db.addNewGood(good);
        db.addNewGood(new Goods("White bread", "Bakery", 12, 2.50, true) );
        db.addNewGood(new Goods("Kaiser roll", "Bakery", 132, 12.50, true));
        db.addNewGood(new Goods("Lay's Paprika", "Candy", 122, 22.50, true));

    }
    @AfterEach
    void logout() throws ObjectNotFound, NoSuchAlgorithmException {
        controller.setAdminLogged(false);
        controller.setLogged(false);
        controller.setLoggedName(null);
    }
    @Test
    void loginCustomer() throws NoSuchAlgorithmException {
        controller.login("Kamil", "swieczka");
        assertAll(
                () -> assertEquals(controller.getLoggedName(), "Kamil"),
                () -> assertTrue(controller.isLogged() ),
                () -> assertFalse(controller.isAdminLogged() )
        );
    }
    @Test
    void loginAdmin() throws NoSuchAlgorithmException {
        controller.login("Blazej", "bykowar");
        assertAll(
                () -> assertEquals(controller.getLoggedName(), "Blazej"),
                () -> assertTrue(controller.isLogged() ),
                () -> assertTrue(controller.isAdminLogged() )
        );
    }

    @Test
    void loginFailed() throws NoSuchAlgorithmException {
        controller.login("Window", "carpet");
        assertAll(
                () -> assertNull(controller.getLoggedName()),
                () -> assertFalse(controller.isLogged() ),
                () -> assertFalse(controller.isAdminLogged() )
        );
    }

    @Test
    void logoutAdmin() throws ObjectNotFound, NoSuchAlgorithmException {
        controller.setAdminLogged(true);
        controller.setLogged(true);
        controller.setLoggedName("Blazej");
        controller.logout("bykowar");
        assertAll(
                () -> assertNull(controller.getLoggedName()),
                () -> assertFalse(controller.isLogged() ),
                () -> assertFalse(controller.isAdminLogged() )
        );
    }
    @Test
    void logoutCustomer() throws ObjectNotFound, NoSuchAlgorithmException {
        controller.setLogged(true);
        controller.setLoggedName("Kamil");
        controller.logout("swieczka");
        assertAll(
                () -> assertNull(controller.getLoggedName()),
                () -> assertFalse(controller.isLogged() ),
                () -> assertFalse(controller.isAdminLogged() )
        );
    }
    @Test
    void logoutCustomerFailed() throws ObjectNotFound, NoSuchAlgorithmException {
        controller.setLoggedName("Kamil");
        controller.setLogged(true);
        controller.logout("apple");
        assertAll(
                () -> assertEquals(controller.getLoggedName(), "Kamil"),
                () -> assertTrue(controller.isLogged() ),
                () -> assertFalse(controller.isAdminLogged() )
        );
    }

    @Test
    void logOutAdminFailed() throws ObjectNotFound, NoSuchAlgorithmException {
        controller.setLoggedName("Blazej");
        controller.setLogged(true);
        controller.setAdminLogged(true);
        controller.logout("apple");
        assertAll(
                () -> assertEquals(controller.getLoggedName(), "Blazej"),
                () -> assertTrue(controller.isLogged() ),
                () -> assertTrue(controller.isAdminLogged() )
        );
    }

    @Test
    void registerAdmin() {
    }

    @Test
    void registerCustomer() {
    }

    @Test
    void chooseProductToBuyFromAll() {
    }

    @Test
    void chooseProductToBuyFromType() {
    }

    @Test
    void buyProduct() {
    }
}