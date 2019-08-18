package Controller;

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
    public Controller(PrimitiveDB db) {
        this.db = db;
        logged = false;
        adminLogged = false;
    }
    public void login() throws NoSuchAlgorithmException {
        Admin temp = new Admin();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name to login:");
        temp.setName( input.nextLine() );
        while (temp.getName().isBlank()){
            System.out.println("Invalid data - name is empty or it is only space. Try again.");
            temp.setName( input.nextLine() );
        }
        System.out.println("Enter password:");
        temp.setPassword( input.nextLine() );
        while(temp.getPassword().isBlank() ){
            System.out.println("Invalid data - password is empty or it is only space. Try again.");
            temp.setPassword( input.nextLine() );
        }
        try {
            if (db.getAdminByName( temp.getName() ).getPassword().equals( temp.getPassword() )) {
                loggedName = temp.getName() ;
                adminLogged = true;
                logged = true;
                System.out.println("Logged successfully");
            }
            else
                throw new ObjectNotFound();
        }  catch (ObjectNotFound notFound){
            try{
                if(db.getCustomerByName( temp.getName() ).getPassword().equals( temp.getPassword() )){
                    loggedName = temp.getName();
                    adminLogged = false;
                    logged = true;
                    System.out.println("Logged successfully");
                }
                else
                    throw new ObjectNotFound();
            }
            catch (ObjectNotFound notFound1){
                System.out.println("Invalid data can not login.");
            }
        }
    }

    public void logout()throws NoSuchAlgorithmException{
        Admin temp = new Admin();
        String temporary;
        Scanner input = new Scanner( System.in );
        System.out.println("Enter Password to logout:");
        temp.setPassword( input.nextLine() );
        while( temp.getPassword().isBlank() ){
            System.out.println("Invalid password, can not logout. If you want to try again press enter, in other case enter anything:");
            temporary = input.nextLine();
            if(temporary.isEmpty())
                temp.setPassword( input.nextLine() );
            else
                return;
        }
        logged = false;
        adminLogged = false;
        loggedName = null;
        System.out.println("Logged out successfully");
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
        if(db.getGoodByName( name ).getNumberOfGoods() > 0){
            double howManyGoods;
            Scanner input = new Scanner(System.in);
            System.out.println("-----" + name +" informations-----");
            db.printSpecificProduct(name);
            System.out.println("Enter how many " + name + " you want to buy");
            howManyGoods = input.nextDouble();

            if( howManyGoods > db.getGoodByName( name ).getNumberOfGoods()){
                System.out.println("You can not buy that much products");
                System.out.println("Try again with less products (You can type 0 if you dont want to buy anything)");
                buyProduct( name );
            }
            else if (howManyGoods * db.getGoodByName( name ).getPrice() > db.getCustomerByName( loggedName ).getCashOnAccount() ){
                System.out.println("You do not have enough money to buy that much products");
                System.out.println("Try again with less products (You can type 0 if you dont want to buy anything)");
                buyProduct( name );
            }
            else{
                db.getCustomerByName( loggedName ).setCashOnAccount(
                        db.getCustomerByName( loggedName ).getCashOnAccount() - howManyGoods * db.getGoodByName( name ). getPrice()
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
