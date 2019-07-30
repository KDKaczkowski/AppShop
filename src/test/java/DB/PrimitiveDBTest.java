package DB;

import Exceptions.AdditionFailed;
import Exceptions.ObjectNotFound;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimitiveDBTest {

    @org.junit.jupiter.api.Test
    void getAdminByName() {
    }

    @org.junit.jupiter.api.Test
    void getCustomerByName() {
    }

    @org.junit.jupiter.api.Test
    void getCustomerById() {
    }

    @org.junit.jupiter.api.Test
    void getAdminById() {
    }

    @org.junit.jupiter.api.Test
    void getPasswordByName() {
    }

    @Test
    void getGoodByName() throws ObjectNotFound, AdditionFailed {
        assertNotNull(new PrimitiveDB().getGoodByName("Dark bread"));
    }
    @org.junit.jupiter.api.Test
    void getGoodByNameButNoObject() {
        ObjectNotFound exception = assertThrows(
                ObjectNotFound.class,
                () -> new PrimitiveDB().getGoodByName("Jajko")
        );
    }

    @org.junit.jupiter.api.Test
    void getGoodsOfType() {
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