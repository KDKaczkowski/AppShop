package DB;

import CommercialGoods.Goods;
import Exceptions.AdditionFailed;
import Exceptions.ObjectNotFound;
import Users.Admin;
import Users.Customer;
import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;

import java.util.*;

public class PrimitiveDB  implements DB{

    private final String [] TYPES = {"Bakery", "Candy", "Dairy", "Drink", "Meat"};
    private boolean inTYPES(String type){
        for (int i = 0; i < 5; i++) {
            if(type == TYPES[i])
                return true;
        }
        return false;
    }

    public PrimitiveDB() throws AdditionFailed{
        this.STARTER();
    }

    private Set <Admin> setOfAdmins = new HashSet<Admin>();

    private Set <Customer> setOfCustomers = new HashSet<Customer>();


    private SetMultimap<String, Goods> mapOfGoods= MultimapBuilder.hashKeys().hashSetValues().build();

    private List<String> listOfBakeries = new ArrayList<String>();

    private List<String> listOfCandies = new ArrayList<String>();

    private List<String> listOfDaires = new ArrayList<String>();

    private List<String> listOfDrinks = new ArrayList<String>();

    private List<String> listOfMeats = new ArrayList<String>();
    ////////////////////////////////////////////////////////////
    public Admin getAdminByName(String name) throws ObjectNotFound {
        for(Admin admin: setOfAdmins){
            if(admin.getName() == name)
                return admin;
        }
        throw new ObjectNotFound();

    }

    public Customer getCustomerByName(String name) throws ObjectNotFound {
        for(Customer customer: setOfCustomers){
            if(customer.getName() == name)
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
            if(admin.getName() == name)
                return admin.getName();
        }
        for(Customer customer: setOfCustomers){
            if(customer.getName() == name)
                return customer.getName();
        }
        throw new ObjectNotFound();
    }

    public Goods getGoodByName(String name) throws ObjectNotFound {
        for(Goods value: mapOfGoods.values()){
            if(value.getName() == name)
                return value;
        }
        throw new ObjectNotFound();
    }

    public List<Goods> getGoodsOfType(String type) throws ObjectNotFound {
        List<Goods> results = new ArrayList<Goods>();
        if(inTYPES( type )) {
            for (Map.Entry<String, Goods> entry : mapOfGoods.entries()) {
                String key = type;
                Goods value = entry.getValue();
                results.add(value);
            }
        }
        throw new ObjectNotFound();

    }


    public void addNewGood(Goods goods) throws AdditionFailed {
        if( inTYPES( goods.getType() ) && !mapOfGoods.containsValue(goods) )
            mapOfGoods.put(goods.getType(), goods);
        else
            throw new AdditionFailed();
    }

    public void addOrRemoveExistingGood(String name, double amount) throws AdditionFailed {
        try {
            if(getGoodByName(name).getPrice() < amount)
                throw new AdditionFailed();
            getGoodByName(name).setPrice( getGoodByName(name).getPrice()+amount);
        } catch (ObjectNotFound objectNotFound){
            objectNotFound.printStackTrace();
            throw new AdditionFailed();
        }
    }

    public Goods getGoodByNameAndType(String name, String type) throws ObjectNotFound {
        if( inTYPES( type ) ) {
            for (Map.Entry<String, Goods> entry : mapOfGoods.entries()) {
                String key = type;
                Goods value = entry.getValue();
                if( name == value.getName() )
                    return value;
            }
        }
        throw new ObjectNotFound();
    }

    public SetMultimap<String, Goods> getMapOfGoods() {
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

    public List<String> getListOfBakeries() {
        return listOfBakeries;
    }

    public void addBakery(String name) {
        this.listOfBakeries.add(name);
    }

    public List<String> getListOfCandies() {
        return listOfCandies;
    }

    public void addCandy(String name) {
        this.listOfCandies.add(name);
    }

    public List<String> getListOfDaires() {
        return listOfDaires;
    }

    public void addDairy(String name) {
        this.listOfDaires.add(name);
    }

    public List<String> getListOfDrinks() {
        return listOfDrinks;
    }

    public void addDrink(String name) {
        this.listOfDrinks.add(name);
    }

    public List<String> getListOfMeats() {
        return listOfMeats;
    }

    public void addMeat(String name) {
        this.listOfMeats.add(name);
    }

    private void STARTER() throws AdditionFailed {
        mapOfGoods.put("Bakery", new Goods("Dark bread", "Bakery", 12, 12, true));
        // BAKERY /////
        this.addBakery("White bread");
        this.addBakery("Dark bread");
        this.addBakery("Sliced white bread");
        this.addBakery("Sliced dark bread");
        this.addBakery("Kaiser roll");
        this.addBakery("Chocolate donut");
        this.addBakery("Donut");
        this.addBakery("Pudding donut");
        // CANDY /////
        this.addCandy("Dark chocolate");
        this.addCandy("Milky chocolate");
        this.addCandy("White chocolate");
        this.addCandy("Mars");
        this.addCandy("NoName");
        this.addCandy("Snickers");
        this.addCandy("Knoppers");
        this.addCandy("Jelly");
        this.addCandy("Python's jellies");
        this.addCandy("Lay's Paprika");
        this.addCandy("Lay's Fromage");
        // DAIRY /////
        this.addDairy("Milk");
        this.addDairy("Chocolate milk");
        this.addDairy("Yoghurt with strawberries");
        this.addDairy("Yoghurt with pineapple");
        this.addDairy("Yoghurt");
        this.addDairy("Cheese");
        this.addDairy("White Cheese");
        // DRINKS /////
        this.addDrink("Coca Cola");
        this.addDrink("Water");
        this.addDrink("Sprite");
        this.addDrink("Monster");
        this.addDrink("Mountain Dew");
        this.addDrink("Coconut water");
        this.addDrink("Zbyszko trzy cytryny");
        this.addDrink("RedBull");
        // MEATS /////
        this.addMeat("Ham");
        this.addMeat("Parma ham");
        this.addMeat("Śląska gięta");
        this.addMeat("Normal Sausage");
        this.addMeat("Roasted chicken");

        //////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////
        // BAKERY /////
        this.addNewGood(new Goods("White bread", TYPES[0], 12, 2.50, true) );
        this.addNewGood(new Goods("Dark bread", TYPES[0], 10, 2.90, true) );
        this.addNewGood(new Goods("Sliced white bread", TYPES[0], 12, 2.50, true));
        this.addNewGood(new Goods("Sliced dark bread", TYPES[0], 12, 2.50, true));
        this.addNewGood(new Goods("Kaiser roll", TYPES[0], 12, 2.50, true));
        this.addNewGood(new Goods("Chocolate donut", TYPES[0], 12, 2.50, true));
        this.addNewGood(new Goods("Donut", TYPES[0], 12, 2.50, true));
        this.addNewGood(new Goods("Pudding donut", TYPES[0], 12, 2.50, true));
        // CANDY /////
        this.addNewGood(new Goods("Dark chocolate", TYPES[1], 12, 2.50, true));
        this.addNewGood(new Goods("Milky chocolate", TYPES[1], 12, 2.50, true));
        this.addNewGood(new Goods("White chocolate", TYPES[1], 12, 2.50, true));
        this.addNewGood(new Goods("Mars", TYPES[1], 12, 2.50, true));
        this.addNewGood(new Goods("NoName", TYPES[1], 12, 2.50, true));
        this.addNewGood(new Goods("Snickers", TYPES[1], 12, 2.50, true));
        this.addNewGood(new Goods("Knoppers", TYPES[1], 12, 2.50, true));
        this.addNewGood(new Goods("Jelly", TYPES[1], 12, 2.50, true));
        this.addNewGood(new Goods("Python's jellies", TYPES[1], 12, 2.50, true));
        this.addNewGood(new Goods("Lay's Paprika", TYPES[1], 12, 2.50, true));
        this.addNewGood(new Goods("Lay's Fromage", TYPES[1], 12, 2.50, true));
        // DAIRY /////
        this.addNewGood(new Goods("Milk", TYPES[2], 12, 2.50, true));
        this.addNewGood(new Goods("Chocolate milk", TYPES[2], 12, 2.50, true));
        this.addNewGood(new Goods("Yoghurt with strawberries", TYPES[2], 12, 2.50, true));
        this.addNewGood(new Goods("Yoghurt with pineapple", TYPES[2], 12, 2.50, true));
        this.addNewGood(new Goods("Yoghurt", TYPES[2], 12, 2.50, true));
        this.addNewGood(new Goods("Cheese", TYPES[2], 12, 2.50, true));
        this.addNewGood(new Goods("White Cheese", TYPES[2], 12, 2.50, true));
        // DRINKS /////
        this.addNewGood(new Goods("Coca Cola", TYPES[3], 12, 2.50, true));
        this.addNewGood(new Goods("Water", TYPES[3], 12, 2.50, true));
        this.addNewGood(new Goods("Sprite", TYPES[3], 12, 2.50, true));
        this.addNewGood(new Goods("Monster", TYPES[3], 12, 2.50, true));
        this.addNewGood(new Goods("Mountain Dew", TYPES[3], 12, 2.50, true));
        this.addNewGood(new Goods("Coconut water", TYPES[3], 12, 2.50, true));
        this.addNewGood(new Goods("Zbyszko trzy cytryny", TYPES[3], 12, 2.50, true));
        this.addNewGood(new Goods("RedBull", TYPES[3], 12, 2.50, true));
        // MEATS /////
        this.addNewGood(new Goods("Ham", TYPES[4], 12, 2.50, false));
        this.addNewGood(new Goods("Parma ham", TYPES[4], 12, 2.50, false));
        this.addNewGood(new Goods("Śląska gięta", TYPES[4], 12, 2.50, false));
        this.addNewGood(new Goods("Normal Sausage", TYPES[4], 12, 2.50, false));
        this.addNewGood(new Goods("Roasted chicken", TYPES[4], 12, 2.50, false));
    }
}
