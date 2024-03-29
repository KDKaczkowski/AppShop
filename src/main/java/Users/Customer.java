package Users;

import DB.PrimitiveDB;
import Exceptions.ObjectNotFound;

import javax.validation.constraints.NotEmpty;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Customer extends User { //TODO How about private
    private Basket basket = new Basket();
    private List<Basket> historicalBaskets = new ArrayList<Basket>();
    @NotEmpty
    private double cashOnAccount;

    public Customer() {

    }

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

    public void valuesFromTemporaryObject(Customer customer){
        if(this.getName().equals( customer.getName() ) ) {
            this.setCashOnAccount(customer.getCashOnAccount());
            this.setBasket( customer.getBasket() );
            this.setHistoricalBaskets( customer.getHistoricalBaskets() );
        }
    }

    public void setCashOnAccount(double cashOnAccount) {
        this.cashOnAccount = cashOnAccount;
    }
    public Customer(String name, String password, double cash, PrimitiveDB db){
        try{
            db.getCustomerByName(name);
        }
        catch(ObjectNotFound objectNotFound){

            if (name.isBlank())
                return;
            if (password.isBlank())
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
                if (name.isBlank()) {
                    System.out.println("Wrong name! Try again");
                    continue;
                }

                //PASSWORD
                System.out.println("Enter password of customer");
                String password = input.nextLine();
                if (password.isBlank()) {
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


    public void createNewBasket(){ //Cos bylo ze historical baskets jak nie puste to dodac ale to chyba zle zobaczy sie
        historicalBaskets.add(this.basket);
        this.basket.clearBasket();
    }


}
