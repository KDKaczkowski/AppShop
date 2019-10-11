package Controller;

import CommercialGoods.Goods;
import Exceptions.AdditionFailed;
import Exceptions.ObjectNotFound;
import Users.Customer;

import java.security.NoSuchAlgorithmException;

public interface Control {
    public void getLoginValues() throws NoSuchAlgorithmException;
    public void login(String name, String password) throws NoSuchAlgorithmException ;
    public void getLogoutValues() throws NoSuchAlgorithmException, ObjectNotFound;
    public void logout(String password)throws ObjectNotFound, NoSuchAlgorithmException;
    public void getRegisterAdminValues() throws NoSuchAlgorithmException;
    public void registerAdmin(String name, String password) throws NoSuchAlgorithmException;
    public void getRegisterCustomerValues();
    public void registerCustomer(String name, String password, double cash);
    public void buyProduct(String name) throws ObjectNotFound;
    public void getAddProductValues() throws AdditionFailed;
    public void addNewProduct(String name, String type, double numberOfGoods, double price, boolean pricePerUnit) throws AdditionFailed;
    public void addExistingProduct(String name, double numberOfGoods);
    public void getChangeProductValues() throws ObjectNotFound;
    public void changeProduct(Goods good, String name, String type, double numberOfGoods, double price, boolean pricePerUnit);
    public void getFindCustomervalues() throws ObjectNotFound;
    public Customer findCustomer(String name) throws ObjectNotFound;
    public void showMenu();
}
