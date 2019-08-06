package DB;

import CommercialGoods.Goods;
import Exceptions.AdditionFailed;
import Exceptions.ObjectNotFound;
import Users.Admin;
import Users.Customer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class PrimitiveDBTest{
    PrimitiveDB db = new PrimitiveDB(true);
    Admin admin;
    Customer customer;
    PrimitiveDBTest() throws AdditionFailed {
    }
    @BeforeAll
    void start(){
        try{
            admin = new Admin("Blazej", "bykowar", db);
            customer = new Customer("Kamil", "swieczka", 23, db);
            db.addNewGood(new Goods("White bread", "Bakery", 12, 2.50, true) );
            db.addNewGood(new Goods("Kaiser roll", "Bakery", 132, 12.50, true));
            db.addNewGood(new Goods("Lay's Paprika", "Candy", 122, 22.50, true));
        }catch(NoSuchAlgorithmException e){}
        catch (AdditionFailed b){}

    }

    @org.junit.jupiter.api.Test
    void getAdminByName() throws ObjectNotFound {
        assertNotNull(db.getAdminByName("Bartosz"));
    }

    @org.junit.jupiter.api.Test
    void getCustomerByName() throws ObjectNotFound {
        assertNotNull(db.getCustomerByName("Kamil"));
    }

    @org.junit.jupiter.api.Test
    void getCustomerById() throws ObjectNotFound {
       assertEquals(23, db.getCustomerById(customer.getId()).getCashOnAccount());
    }

    @org.junit.jupiter.api.Test
    void getAdminById() {
    }

    @org.junit.jupiter.api.Test
    void getPasswordByName() {
    }

    @Test
    void getGoodByName() throws ObjectNotFound, AdditionFailed {
        assertNotNull(db.getGoodByName("Dark bread"));
    }
    @org.junit.jupiter.api.Test
    void getGoodByNameButNoObject() {
        ObjectNotFound exception = assertThrows(
                ObjectNotFound.class,
                () -> db.getGoodByName("Jajko")
        );
    }

    @org.junit.jupiter.api.Test
    void getGoodsOfType() throws AdditionFailed, ObjectNotFound{
        assertNotNull(db.getGoodsOfType("Bakery"));
    }

    @Test
    void ShouldReturnExceptionGetGoodsOfType() throws ObjectNotFound, AdditionFailed{
        ObjectNotFound exception = assertThrows(
                ObjectNotFound.class,
                () -> db.getGoodsOfType("Jajko")
        );
    }

    @org.junit.jupiter.api.Test
    void addNewGood() {
    }

    @org.junit.jupiter.api.Test
    void addOrRemoveExistingGood() {
    }

    @org.junit.jupiter.api.Test
    void getGoodByNameAndType() {
    }
}