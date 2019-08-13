package Users;

import DB.PrimitiveDB;
import Exceptions.ObjectNotFound;

import javax.validation.constraints.NotEmpty;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Customer extends User {
    private Basket basket = new Basket();
    private List<Basket> historicalBaskets = new ArrayList<Basket>();
    @NotEmpty
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

            if (name.isEmpty() || name.equals(" "))
                return;
            if (password.isEmpty() || password.equals(" "))
                return;
            if(cash < 0)
                return;

            try{
                this.setPassword(password);
            } catch (NoSuchAlgorithmException e){
                return;
            }
            this.setName(name);
            this.setCashOnAccount(cash);
            db.addCustomer(this);
        }
        return;
    }
    public Customer(PrimitiveDB db){
        double temp;

        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter name of customer:");
            //NAME
            String  name = input.nextLine();
            try {
                db.getCustomerByName(name);
                db.getAdminByName(name);
            } catch (ObjectNotFound objectNotFound) {
                if (name.isEmpty() || name.equals(" ")) {
                    System.out.println("Wrong name! Try again");
                    continue;
                }

                //PASSWORD
                System.out.println("Enter password of customer");
                String password = input.nextLine();
                if (password.isEmpty() || password.equals(" ")) {
                    System.out.println("Wrong password! Try again");
                    continue;
                }

                //CASH
                System.out.println("Enter how many cash " + name +" have");
                try{
                    temp = input.nextDouble();
                } catch(InputMismatchException e){
                    System.out.println("Wrong value! You have to enter cash as number.");
                    continue;

                }

                //SETTING
                try {
                    this.setPassword(password);
                } catch (NoSuchAlgorithmException e) {
                    System.out.println("Cant create customer");
                    return;
                }
                this.setName(name);
                this.setCashOnAccount(temp);
                db.addCustomer(this);
                return;
            }
            System.out.println("Name already taken! Try with another name");
        }
    }


    public void createNewBasket(){
        if (!historicalBaskets.isEmpty())
            historicalBaskets.add(this.basket);
        this.basket.clearBasket();
    }


}
