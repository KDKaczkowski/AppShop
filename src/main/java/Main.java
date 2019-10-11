import Controller.Controller;
import DB.PrimitiveDB;
import Exceptions.AdditionFailed;
import Exceptions.ObjectNotFound;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws AdditionFailed, ObjectNotFound, NoSuchAlgorithmException {
        PrimitiveDB db = new PrimitiveDB(false);
        Controller controller = new Controller(db);
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome in grocery store. Feel free to register and buy whatever you want!");
        int helpful;



        while(true){
            controller.showMenu();
            System.out.println("Choose an action that you want to do by typing number");

            break;
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
