package Users;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private Basket basket;
    private List<Basket> historicalBaskets = new ArrayList<Basket>();
    private double cashOnAccount;


    public Customer() {
    }
}
