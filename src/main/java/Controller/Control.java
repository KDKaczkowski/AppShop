package Controller;

import Exceptions.ObjectNotFound;

import java.security.NoSuchAlgorithmException;

public interface Control {
    public void getLoginValues() throws NoSuchAlgorithmException;
    public void login(String name, String password) throws NoSuchAlgorithmException ;
    public void getLogoutValues() throws NoSuchAlgorithmException, ObjectNotFound;
    public void logout(String password)throws ObjectNotFound, NoSuchAlgorithmException ;
    public void registerAdmin() throws NoSuchAlgorithmException;
    public void registerCustomer();
    public void buyProduct(String name) throws ObjectNotFound;

}
