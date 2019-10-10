package Controller;

import CommercialGoods.Goods;
import DB.PrimitiveDB;
import Exceptions.AdditionFailed;
import Exceptions.ObjectNotFound;
import Users.Admin;
import Users.Customer;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Controller implements Control{
    private PrimitiveDB db;
    private String loggedName;
    private boolean adminLogged;
    private boolean logged;

    public String getLoggedName() {
        return loggedName;
    }

    public void setLoggedName(String loggedName) {
        this.loggedName = loggedName;
    }

    public boolean isAdminLogged() {
        return adminLogged;
    }

    public void setAdminLogged(boolean adminLogged) {
        this.adminLogged = adminLogged;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

    public Controller(PrimitiveDB db) {
        this.db = db;
        logged = false;
        adminLogged = false;
    }
    public void getLoginValues() throws NoSuchAlgorithmException {
        if(!logged) {
            String name;
            String password;
            Scanner input = new Scanner(System.in);
            String temporary;

            System.out.println("Enter name to login (Or press enter to exit):");
            name = input.nextLine();
            if ( name.isEmpty() ) {
                return;
            }
            while (name.isBlank()) {
                System.out.println("Invalid data - name can not be only spaces. Try again. You can press enter if you want to quit. In other case enter anything");

                temporary = input.nextLine();
                if (temporary.isEmpty())
                    return;
                else
                    name = input.nextLine();
            }

            System.out.println("Enter password to login (Or press enter to exit):");
            password = input.nextLine();
            if (password.isEmpty())
                return;
            while (password.isBlank()) {
                System.out.println("Invalid data - password can not be only spaces. Try again. You can press enter if you want to quit. In other case enter anything");
                temporary = input.nextLine();
                if (temporary.isEmpty())
                    return;
                else
                    password = input.nextLine();
            }
            this.login(name, password);
        }
        else{
            System.out.println("You are already logged in. If that is not you, than log out first");
        }
    }
    public void login(String name, String password) throws NoSuchAlgorithmException {
        Admin temp = new Admin();
        temp.setPassword( password );
        try {
            if (db.getAdminByName( name ).getPassword().equals( temp.getPassword() )) {
                loggedName = name ;
                adminLogged = true;
                logged = true;
                System.out.println("Logged successfully");
            }
            else
                throw new ObjectNotFound();
        }  catch (ObjectNotFound notFound){
            try{
                if(db.getCustomerByName( name ).getPassword().equals( temp.getPassword() )){
                    loggedName = name;
                    adminLogged = false;
                    logged = true;
                    System.out.println("Logged successfully");
                }
                else
                    throw new ObjectNotFound();
            } catch (ObjectNotFound notFound1){
                System.out.println("Invalid data can not login.");
            }
        }
    }
    public void getLogoutValues() throws NoSuchAlgorithmException, ObjectNotFound {
        if(logged){
            String password;
            String temporary;
            Scanner input = new Scanner( System.in );
            System.out.println("Enter Password to log out:");
            password = input.nextLine();
            while( password.isBlank() ) {
                System.out.println("Invalid password, can not log out. Try again. You can press enter if you want to quit. In other case enter anything");
                temporary = input.nextLine();
                if (temporary.isEmpty())
                    return;
                else
                    password = input.nextLine();
            }
            this.logout( password );
        }
        System.out.println("Nobody is logged. Can not log out");
    }
    public void logout(String password) throws ObjectNotFound, NoSuchAlgorithmException {
        Admin temp = new Admin();
        temp.setPassword( password );
        if(adminLogged){
            if( temp.getPassword().equals( db.getAdminByName( loggedName ).getPassword() ) ){
                adminLogged = false;
                logged = false;
                loggedName = null;
                System.out.println("Logged out successfully");
            }
            else {
                System.out.println("Wrong password, can not log out.");
            }
        }
        else{
            if( temp.getPassword().equals( db.getCustomerByName( loggedName ).getPassword() )){
                logged = false;
                loggedName = null;
                System.out.println("Logged out successfully");
            }
            else {
                System.out.println("Wrong password, can not log out.");
            }
        }


    }
    public void getRegisterAdminValues() throws NoSuchAlgorithmException {
        if (adminLogged){
            Scanner input = new Scanner(System.in);
            String name;
            String password;
            System.out.println("Enter name:");
            name = input.nextLine();
            while (name.isBlank()){
                System.out.println("Invalid data - name is empty or it is only space. Try again.");
                name = input.nextLine();
            }
            password = input.nextLine();
            while (password.isBlank()){
                System.out.println("Invalid data - name is empty or it is only space. Try again.");
                password = input.nextLine();
            }

            registerAdmin(name,password);

        }
        else{
            System.out.println("You can not add admin as Customer");
        }
    }
    public void registerAdmin(String name, String password) throws NoSuchAlgorithmException{
        new Admin(name, password, db);
    }
    public void getRegisterCustomerValues(){
        Scanner input = new Scanner(System.in);
        String name;
        String password;
        double cash = 0;
        System.out.println("Enter name:");
        name = input.nextLine();
        while (name.isBlank()){
            System.out.println("Invalid data - name is empty or it is only space. Try again.");
            name = input.nextLine();
        }
        password = input.nextLine();
        while (password.isBlank()){
            System.out.println("Invalid data - name is empty or it is only space. Try again.");
            password = input.nextLine();
        }
        if(adminLogged){
            System.out.println("Enter how many cash customer have");
            cash = input.nextDouble();
            while(cash < 0){
                System.out.println("Cash have to be greater than 0");
                cash = input.nextDouble();
            }

        }
        registerCustomer(name,password,cash);

    }
    public void registerCustomer(String name, String password, double cash){
        new Customer(name, password, cash, db);
    }
    public void chooseProductToBuyFromAll(){
        if(logged && !adminLogged){
            Scanner input = new Scanner(System.in);
            System.out.println("------Choose a product to buy from the list below------");
            db.printAllProducts();
            System.out.println("Enter name of product that you want buy or click enter if you did not want to buy anything: ");
            String temp = input.nextLine();
            if(temp.isEmpty())
                return;
            try{
                buyProduct( temp );
            } catch (ObjectNotFound notFound){
                System.out.println("You typed wrong name of product. Buying failed");
            }
        }

    }
    public void chooseProductToBuyFromType(String type){
        if(logged && !adminLogged){
            Scanner input = new Scanner(System.in);
            System.out.println("------Choose a product to buy from the list below------");
            db.printAllProductsOfType(type);
            System.out.println("Enter name of product that you want buy or click enter if you did not want to buy anything: ");
            String temp = input.nextLine();
            if(temp.isEmpty())
                return;
            try{
                buyProduct( temp );
            } catch (ObjectNotFound notFound){
                System.out.println("You typed wrong name of product. Buying failed");
            }
        }
    }
    public void buyProduct(String name) throws ObjectNotFound{

        Goods tempGood = db.getGoodByName( name );
        Customer tempCustomer = db.getCustomerByName( loggedName );
        double howManyGoods;

        if(tempGood.getNumberOfGoods() > 0){

            Scanner input = new Scanner(System.in);
            System.out.println("-----" + name +" informations-----");
            db.printSpecificProduct(name);
            System.out.println("Enter how many " + name + " you want to buy");
            howManyGoods = input.nextDouble();

            if( howManyGoods > tempGood.getNumberOfGoods()){
                System.out.println("You can not buy that much products");
                System.out.println("Try again with less products (You can type 0 if you dont want to buy anything)");
                buyProduct( name );
            }
            else if (howManyGoods * tempGood.getPrice() > tempCustomer.getCashOnAccount() ){
                System.out.println("You do not have enough money to buy that much products");
                System.out.println("Try again with less products (You can type 0 if you dont want to buy anything)");
                buyProduct( name );
            }
            else{
                db.getCustomerByName( loggedName ).setCashOnAccount(
                        tempCustomer.getCashOnAccount() - howManyGoods * tempGood.getPrice()
                );
                db.addOrRemoveExistingGood(name, -howManyGoods);
                db.getCustomerByName( loggedName ).getBasket().addGoodToBasket(
                        db.getGoodByName( name )
                );
            }
        }
        else{
            System.out.println("We are sorry, but we ran out of this product. ");
        }
    }

    public void getAddProductValues() throws AdditionFailed {
        if(adminLogged){
            String name, type;
            double numberOfGoods, price;
            boolean pricePerUnit = false;
            Scanner input = new Scanner(System.in);
            System.out.println("Name of the product:");
            name = input.nextLine();
            while(name.isBlank()){
                System.out.println("Name can not be empty neither can be only spaces. Try again.");
                name = input.nextLine();
            }
            System.out.println("----List of types----");
            System.out.println("Bakery");
            System.out.println("Candy");
            System.out.println("Dairy");
            System.out.println("Drink");
            System.out.println("Meat");
            System.out.println("Type of the product:");
            type = input.nextLine();
            type = type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();
            while( type.isBlank() && db.inTYPES( type ) ){
                if(type.isBlank())
                    System.out.println("Type can not be empty neither can be only spaces. Try again.");
                else
                    System.out.println("Only proper type is accepted");
                type = input.nextLine();
                type = type.substring(0, 1).toUpperCase() + type.substring(1).toLowerCase();
            }

            System.out.println("How much pieces of this products:");
            numberOfGoods = input.nextDouble();
            while( numberOfGoods < 0 ){
                System.out.println("Use number greater or equal to zero. Try again.");
                numberOfGoods = input.nextDouble();
            }

            System.out.println("Price of this product:");
            price = input.nextDouble();
            while( price < 0 ){
                System.out.println("Use price greater or equal to zero. Try again.");
                price = input.nextDouble();
            }
            System.out.println("If this product is going to be sold by pieces enter 1, if it will be sold by kilograms enter 2");
            int temp;
            temp = input.nextInt();
            while( temp != 1 && temp !=0){
                System.out.println("Use number greater or equal to zero. Try again.");
                temp = input.nextInt();
            }
            pricePerUnit = temp == 1;
            addNewProduct(name, type, numberOfGoods, price, pricePerUnit);


        }
    }

    public void addNewProduct(String name, String type, double numberOfGoods, double price, boolean pricePerUnit) throws AdditionFailed {
            db.addNewGood(new Goods(name, type, numberOfGoods, price, pricePerUnit ));
    }
    public void addExistingProduct(String name, double numberOfGoods){
            db.addOrRemoveExistingGood(name, numberOfGoods);
    }
    public void getChangeProductValues() throws ObjectNotFound{
        Goods temp = new Goods();
        Scanner input = new Scanner(System.in);
        int tempInt;
        double tempDouble;
        String text;
        System.out.println("Enter name of product that you want to change");
        db.printAllProducts();
        String name = input.nextLine();
        try{
            temp = db.getGoodByName(name);
        } catch(ObjectNotFound notFound){
            System.out.println("Product not found. Back to the MENU");
            return;
        }
        while(true) {
            System.out.println("Enter which value you want to change. If you want to save all changes enter 0");
            db.printSpecificProduct(temp.getName(), temp.getType());
            tempInt = input.nextInt();
            while( tempInt < 0 || tempInt > 5){
                System.out.println("Use value between 0-5");
                tempInt= input.nextInt();
            }
            switch (tempInt){
                case 0:
                    changeProduct( db.getGoodByName(name), temp.getName(), temp.getType(), temp.getNumberOfGoods(), temp.getPrice(), temp.isPricePerUnit() );
                    return;
                case 1:
                    System.out.println("Current name: " + temp.getName());
                    System.out.println("New name:");
                    text = input.nextLine();
                    while( text.isBlank() ){
                        System.out.println("Name have to be text. Not only spaces or nothing. Try again");
                        text = input.nextLine();
                    }
                    temp.setName( text );
                case 2:
                    System.out.println("Current type: " + temp.getType());
                    System.out.println("New type:");
                    text = input.nextLine();
                    while( text.isBlank() ){
                        System.out.println("Type have to be text. Not only spaces or nothing. Try again");
                        text = input.nextLine();
                    }
                    temp.setType( text );
                case 3:
                    System.out.println("Current number of goods: " + temp.getNumberOfGoods());
                    System.out.println("New number of goods:");
                    tempDouble = input.nextDouble();
                    while( tempDouble < 0 ){
                        System.out.println("Number of goods have to be greater or at least equal to 0. Try again");
                        tempDouble = input.nextDouble();
                    }
                    temp.setNumberOfGoods( tempDouble );
                case 4:
                    System.out.println("Current price: " + temp.getPrice());
                    System.out.println("New price:");
                    tempDouble = input.nextDouble();
                    while( tempDouble < 0 ){
                        System.out.println("Price have to be greater or at least equal to 0. Try again");
                        tempDouble = input.nextDouble();
                    }
                    temp.setPrice( tempDouble );
                case 5:
                    System.out.println("Is paid per unit: " + temp.getName());
                    System.out.println("Paid per unit(write true or false):");
                    text = input.nextLine();
                    while( !text.equals("true") && !text.equals("false") ){
                        System.out.println("Only true or false value is acceptable. Try again");
                        text = input.nextLine();
                    }
                    if( text.equals("true")){
                        temp.setPricePerUnit( true );
                    }
                    else{
                        temp.setPricePerUnit( false );
                    }
            }
        }


    }
    public void changeProduct(Goods good, String name, String type, double numberOfGoods, double price, boolean pricePerUnit){

    }
}
