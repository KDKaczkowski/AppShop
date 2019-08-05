package Users;

import DB.PrimitiveDB;
import Exceptions.ObjectNotFound;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Customer extends User {
    private Basket basket;
    private List<Basket> historicalBaskets = new ArrayList<Basket>();
    private double cashOnAccount;

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public List<Basket> getHistoricalBaskets() {
        return historicalBaskets;
    }

    public void setHistoricalBaskets(List<Basket> historicalBaskets) {
        this.historicalBaskets = historicalBaskets;
    }

    public double getCashOnAccount() {
        return cashOnAccount;
    }

    public void setCashOnAccount(double cashOnAccount) {
        this.cashOnAccount = cashOnAccount;
    }
    public Customer(String name, String password, double cash, PrimitiveDB db){
        try{
            db.getCustomerByName(name);
        }
        catch(ObjectNotFound objectNotFound){
            this.setName(name);
            try{
                this.setPassword(password);
            } catch (NoSuchAlgorithmException e){

            }
            this.setCashOnAccount(cash);
            db.addCustomer(this);
        }
    }
    public Customer(PrimitiveDB db) {
        Scanner input = new Scanner(System.in);
        int i = 1;
        double temp;

        while(i++ > 0) {
            System.out.println("Enter name of customer:");
            if(i>2)
                input.nextLine();
            String name = input.nextLine();
            try {
                db.getCustomerByName(name);
            } catch (ObjectNotFound objectNotFound) {
                if (name.isEmpty() || name.equals(" ")) {
                    System.out.println("Wrong name! Try again");
                    i=1;
                    continue;
                }
                System.out.println("Enter password of customer");
                try {
                    this.setPassword(input.nextLine());
                } catch (NoSuchAlgorithmException e) {
                    System.out.println("Cant create customer");
                    return;
                }

                System.out.println("Enter how many cash " + name + " have");
                try{
                    temp = input.nextDouble();
                } catch(InputMismatchException e){
                    System.out.println("Wrong value! You have to enter cash as number.");
                    i = 2;
                    continue;
                }
                this.setName(name);
                this.setCashOnAccount(input.nextDouble());
                db.addCustomer(this);
                return;
            }
            System.out.println("Name already taken! Try with another name");
            i=1;
        }
        }
}
