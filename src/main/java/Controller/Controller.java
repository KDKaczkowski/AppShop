package Controller;

import CommercialGoods.Goods;
import DB.PrimitiveDB;
import Exceptions.ObjectNotFound;
import Users.Admin;
import Users.Customer;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Controller {
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
    public void registerAdmin() throws NoSuchAlgorithmException{
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

            new Admin(name, password, db);

        }
        else{
            System.out.println("You can not add admin as Customer");
        }
    }
    public void registerCustomer(){
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
}
