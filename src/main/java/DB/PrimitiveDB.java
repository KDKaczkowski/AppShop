package DB;

import CommercialGoods.Goods;
import Exceptions.AdditionFailed;
import Exceptions.ObjectNotFound;
import Users.Admin;
import Users.Customer;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class PrimitiveDB  implements DB{

   private final String [] TYPES = {"Bakery", "Candy", "Dairy", "Drink", "Meat"};
   public boolean inTYPES(String type){
        for (int i = 0; i < 5; i++) {
            if(type.equals(TYPES[i]))
                return true;
        }
        return false;
    }

    public String[] getTYPES() {
        return TYPES;
    }


    public PrimitiveDB(boolean clear) throws AdditionFailed{
        if(!clear)
            this.STARTER();
    }

    private Set <Admin> setOfAdmins = new HashSet<Admin>();

    private Set <Customer> setOfCustomers = new HashSet<Customer>();


    private ListMultimap<String, Goods> mapOfGoods= MultimapBuilder.hashKeys().arrayListValues().build();


    public Admin getAdminByName(String name) throws ObjectNotFound {
        for(Admin admin: setOfAdmins){
            if(admin.getName().equals(name))
                return admin;
        }
        throw new ObjectNotFound();

    }

    public Customer getCustomerByName(String name) throws ObjectNotFound {
        for(Customer customer: setOfCustomers){
            if(customer.getName().equals(name))
                return customer;
        }
        throw new ObjectNotFound();
    }

    public Customer getCustomerById(UUID id) throws ObjectNotFound {
        for(Customer customer: setOfCustomers){
            if(customer.getId() == id)
                return customer;
        }
        throw new ObjectNotFound();
    }

    public Admin getAdminById(UUID id) throws ObjectNotFound {
        for(Admin admin: setOfAdmins){
            if(admin.getId() == id)
                return admin;
        }
        throw new ObjectNotFound();
    }

    public String getPasswordByName(String name) throws ObjectNotFound {
        for(Admin admin: setOfAdmins){
            if(admin.getName().equals(name))
                return admin.getPassword();
        }
        for(Customer customer: setOfCustomers){
            if(customer.getName().equals(name))
                return customer.getPassword();
        }
        throw new ObjectNotFound();
    }



    public Goods getGoodByName(String name) throws ObjectNotFound {
        for(Goods value: mapOfGoods.values()) {
            if (value.getName().equals(name)){
                return value;
        }
        }
        throw new ObjectNotFound();
    }

    public List<Goods> getGoodsOfType(String type) throws ObjectNotFound {

        if(inTYPES( type )) {
            return mapOfGoods.get(type);
        }
        throw new ObjectNotFound();

    }


    public void addNewGood(Goods goods) throws AdditionFailed{
        if( inTYPES( goods.getType() ) ) {
            try {
                getGoodByName(goods.getName());
            } catch (ObjectNotFound notFound) {
                mapOfGoods.put(goods.getType(), goods);
                return;
            }
            throw new AdditionFailed();
        }else
            throw new AdditionFailed();
    }

    public void addOrRemoveExistingGood(String name, double amount){
        double temp;
        try{
            temp = this.getGoodByName(name).getNumberOfGoods();
        } catch(ObjectNotFound objectNotFound){
            return;
        }
        try {
            if(temp + amount < 0)
                throw new AdditionFailed();
        } catch (AdditionFailed additionFailed){
            return;
        }
        try {
            this.getGoodByName(name).setNumberOfGoods(temp + amount);
        } catch(ObjectNotFound o ){}
    }

    public Goods getGoodByNameAndType(String name, String type) throws ObjectNotFound {
        if( inTYPES( type ) ) {
            for (Map.Entry<String, Goods> entry : mapOfGoods.entries()) {
                //String key = type;
                Goods value = entry.getValue();
                if( value.getName().equals(name))
                    return value;
            }
        }
        throw new ObjectNotFound();
    }

    @Override
    public void printAllProducts() {
        for( Map.Entry<String, Goods> entry : mapOfGoods.entries()){
            String key= entry.getKey();
            Goods value = entry.getValue();
            System.out.println();
            System.out.println("Type: " + key + "    Name: " + value.getName() + "    Price: "+ value.getPrice() + "     Number of Products: " + value.getNumberOfGoods() + "    Price per unit: " + value.isPricePerUnit());
        }
    }

    @Override
    public void printAllProductsOfType(String type) {
        try{
            for(Goods element : getGoodsOfType( type )){
                System.out.println();
                System.out.println("Type: " + element.getType() + "     Name: " + element.getName() + "   Price: "+ element.getPrice() + "    Number of Products: " + element.getNumberOfGoods() + "    Price per unit: " + element.isPricePerUnit());
            }
        } catch (ObjectNotFound notFound){
            System.out.println("Can not find this type of products");
        }

    }

    @Override
    public void printSpecificProduct(String name){
        try {
            Goods element = getGoodByName(name);
            System.out.println("1. Type: " + element.getType() );
            System.out.println("2. Name: " + element.getName() );
            System.out.println("3. Price: "+ element.getPrice() );
            System.out.println("4. Number of Products: " + element.getNumberOfGoods());
            System.out.println("5. Price per unit: " + element.isPricePerUnit());
        } catch (ObjectNotFound notFound){
            System.out.println("Can not find this product");
        }
    }

    @Override
    public void printSpecificProduct(String name, String type){
        try {
            Goods element = getGoodByNameAndType(name, type);
            System.out.println("1. Type: " + element.getType() );
            System.out.println("2. Name: " + element.getName() );
            System.out.println("3. Price: "+ element.getPrice() );
            System.out.println("4. Number of Products: " + element.getNumberOfGoods());
            System.out.println("5. Price per unit: " + element.isPricePerUnit());
        } catch (ObjectNotFound notFound){
            System.out.println("Can not find this product");
        }
    }
    public ListMultimap<String, Goods> getMapOfGoods() {
        return mapOfGoods;
    }

    public Set<Admin>  getSetOfAdmins() {
        return setOfAdmins;
    }

    public void addAdmin(Admin admin) {
        this.setOfAdmins.add(admin);
    }

    public Set<Customer> getSetOfCustomers() {
        return setOfCustomers;
    }

    public void addCustomer(Customer customer) {
        this.setOfCustomers.add(customer);
    }



    private void STARTER() throws AdditionFailed{

        //////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////
        // BAKERY /////
        this.addNewGood(new Goods("White bread", TYPES[0], 12, 2.50, true) );
        this.addNewGood(new Goods("Dark bread", TYPES[0], 10, 2.90, true) );
        this.addNewGood(new Goods("Sliced white bread", TYPES[0], 12, 2.50, true));
        this.addNewGood(new Goods("Sliced dark bread", TYPES[0], 12, 2.90, true));
        this.addNewGood(new Goods("Kaiser roll", TYPES[0], 12, 0.39, true));
        this.addNewGood(new Goods("Chocolate donut", TYPES[0], 12, 3.50, true));
        this.addNewGood(new Goods("Donut", TYPES[0], 12, 3.10, true));
        this.addNewGood(new Goods("Pudding donut", TYPES[0], 12, 3.50, true));
        // CANDY /////
        this.addNewGood(new Goods("Dark chocolate", TYPES[1], 12, 3.99, true));
        this.addNewGood(new Goods("Milky chocolate", TYPES[1], 12, 3.99, true));
        this.addNewGood(new Goods("White chocolate", TYPES[1], 12, 3.99, true));
        this.addNewGood(new Goods("Mars", TYPES[1], 12, 2.10, true));
        this.addNewGood(new Goods("NoName", TYPES[1], 12, 2.50, true));
        this.addNewGood(new Goods("Snickers", TYPES[1], 12, 2.90, true));
        this.addNewGood(new Goods("Knoppers", TYPES[1], 12, 2.50, true));
        this.addNewGood(new Goods("Jelly beans", TYPES[1], 12, 4.99, true));
        this.addNewGood(new Goods("Python's jellies", TYPES[1], 12, 7.99, true));
        this.addNewGood(new Goods("Lay's Paprika", TYPES[1], 12, 5.99, true));
        this.addNewGood(new Goods("Lay's Fromage", TYPES[1], 12, 5.99, true));
        this.addNewGood(new Goods("M&M's", TYPES[1], 15, 4.80, true));
        // DAIRY /////
        this.addNewGood(new Goods("Milk", TYPES[2], 12, 2.49, true));
        this.addNewGood(new Goods("Chocolate milk", TYPES[2], 12, 4.49, true));
        this.addNewGood(new Goods("Yoghurt with strawberries", TYPES[2], 12, 2.50, true));
        this.addNewGood(new Goods("Yoghurt with pineapple", TYPES[2], 12, 2.50, true));
        this.addNewGood(new Goods("Yoghurt", TYPES[2], 12, 1.99, true));
        this.addNewGood(new Goods("Cheese", TYPES[2], 12, 5.39, true));
        this.addNewGood(new Goods("White Cheese", TYPES[2], 12, 3.99, true));
        // DRINKS /////
        this.addNewGood(new Goods("Coca Cola", TYPES[3], 12, 5.99, true));
        this.addNewGood(new Goods("Water", TYPES[3], 12, 1.19, true));
        this.addNewGood(new Goods("Sprite", TYPES[3], 12, 4.49, true));
        this.addNewGood(new Goods("Monster", TYPES[3], 12, 6.29, true));
        this.addNewGood(new Goods("Mountain Dew", TYPES[3], 12, 3.59, true));
        this.addNewGood(new Goods("Coconut water", TYPES[3], 12, 8.99, true));
        this.addNewGood(new Goods("Zbyszko trzy cytryny", TYPES[3], 12, 10.99, true));
        this.addNewGood(new Goods("RedBull", TYPES[3], 12, 1.99, true));
        // MEATS /////
        this.addNewGood(new Goods("Ham", TYPES[4], 12, 26.89, false));
        this.addNewGood(new Goods("Parma ham", TYPES[4], 12, 45.99, false));
        this.addNewGood(new Goods("Śląska gięta", TYPES[4], 12, 12.69, false));
        this.addNewGood(new Goods("Normal Sausage", TYPES[4], 12, 19.99, false));
        this.addNewGood(new Goods("Roasted chicken", TYPES[4], 12, 18.99, false));

        //////ADMIN////////

        try {
            Admin admin2 = new Admin("Jerzy", "Woroniczak", this);
            Admin admin3 = new Admin("Bartosz", "hulajnoga", this);
            Customer customer1 = new Customer("Jack", "lejna", 350, this);
            Customer customer3 = new Customer("Andrew", "Duda", 20,  this);

        }catch(NoSuchAlgorithmException e){        }
        //catch(ObjectNotFound notFound){}



    }
}
