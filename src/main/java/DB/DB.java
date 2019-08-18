package DB;

import CommercialGoods.Goods;
import Exceptions.AdditionFailed;
import Exceptions.ObjectNotFound;
import Users.*;

import java.util.List;
import java.util.UUID;

public interface DB {
    public Admin getAdminByName(String name) throws ObjectNotFound;
    public Customer getCustomerByName(String name) throws ObjectNotFound;
    public Customer getCustomerById(UUID id) throws ObjectNotFound;
    public Admin getAdminById(UUID id) throws ObjectNotFound;
    public String getPasswordByName(String name) throws ObjectNotFound;
    public Goods getGoodByName(String name) throws ObjectNotFound;
    public List<Goods> getGoodsOfType(String type) throws ObjectNotFound;
    public void addAdmin(Admin admin) throws AdditionFailed; // THROW That u cannot add admin or user
    public void addCustomer(Customer customer) throws AdditionFailed; //SAME  -||-
    public void addNewGood(Goods goods) throws AdditionFailed; //
    public void addOrRemoveExistingGood(String name, double amount) throws AdditionFailed;// Sprawdz czy trzeba
    public Goods getGoodByNameAndType(String name, String type) throws ObjectNotFound;
    public void printAllProducts();
    public void printAllProductsOfType(String type);
    public void printSpecificProduct(String name);
    public void printSpecificProduct(String name, String type);
}
