import Controller.Controller;
import DB.PrimitiveDB;
import Exceptions.AdditionFailed;
import Exceptions.ObjectNotFound;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, AdditionFailed, ObjectNotFound {
        PrimitiveDB db = new PrimitiveDB(false);
        Controller controller = new Controller(db);
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome in grocery store. Feel free to register and buy whatever you want!");
        int helpful;



        while(true){
            controller.showMenu();
            System.out.println("Choose an action that you want to do by typing number");
            if( !controller.isLogged() ){
                helpful = input.nextInt();
                while( helpful < 1 || helpful > 2){
                    System.out.println("Only 1, 2 numbers are available. Enter number again");
                    helpful = input.nextInt();
                }
                switch ( helpful ){
                    case 1:
                        controller.getRegisterCustomerValues();
                        System.out.println("Customer registered successfully");
                        break;
                    case 2:
                        controller.getLoginValues();
                        break;
                    case 3:
                        return;


                }
            }
            else{
                if( controller.isAdminLogged() ){
                    helpful = input.nextInt();
                    while( helpful < 1 || helpful > 12){
                        System.out.println("Only 1-12 numbers are available. Enter number again");
                        helpful = input.nextInt();
                    }
                    switch ( helpful ){
                        case 1:
                            controller.getAddProductValues();
                            break;
                        case 2:
                            controller.getAddExistingProductValues();
                            break;
                        case 3:
                            controller.getRegisterAdminValues();
                            break;
                        case 4:
                            controller.getRegisterCustomerValues();
                            break;
                        case 5:
                            controller.getChangeProductValues();
                            break;
                        case 6:
                            controller.getFindCustomervalues();
                            break;
                        case 7:
                            controller.getLogoutValues();;
                            break;
                        case 8:
                            controller.printAllProducts();
                            break;
                        case 9:
                            controller.printAllProductsOfOneType();
                            break;
                        case 10:
                            controller.printSpecificProduct();
                            break;
                        case 11:
                            controller.depositMoney();
                            break;
                        case 12:
                            return;


                    }
                }
                else{
                    helpful = input.nextInt();
                    while( helpful < 1 || helpful > 8){
                        System.out.println("Only 1-8 numbers are available. Enter number again");
                        helpful = input.nextInt();
                    }
                    switch ( helpful ){
                        case 1:
                            controller.depositMoney();
                            break;
                        case 2:
                            System.out.println( controller.findCustomer( controller.getLoggedName() ).getCashOnAccount() );
                            break;
                        case 3:
                            controller.printAllProducts();
                            break;
                        case 4:
                            controller.printAllProductsOfOneType();
                            break;
                        case 5:
                            controller.printSpecificProduct();
                            break;
                        case 6:
                            controller.chooseProductToBuyFromAll();
                            break;
                        case 7:
                            controller.chooseProductToBuyFromType();
                            break;
                        case 8:
                            controller.getLogoutValues();
                            break;
                        case 9:
                            return;
                    }
                }
            }

        }

        /*db.printAllProducts();
        Customer customer = new Customer("Kamil", "yafud", 540, db);
        for(Goods element : db.getGoodsOfType("Bakery")){
            System.out.println(element.getName());
        }
        for(Admin el : db.getSetOfAdmins()){
            System.out.println("   Name" + el.getName() + "Pass  " + el.getPassword().toString());
        }

        try {
            System.out.println(db.getAdminByName("jajo").getName());
        }
        catch(ObjectNotFound o){

        }

        for(Customer el : db.getSetOfCustomers()){
            System.out.println("   Name" + el.getName());
        }

        for(Goods element : db.getGoodsOfType("Candy")){
            System.out.println(element.getName());
        }


        for(Customer el : db.getSetOfCustomers()){
            System.out.println("   Name" + el.getName());
        }*/

    }
}
