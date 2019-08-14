package Users;

import DB.PrimitiveDB;
import Exceptions.ObjectNotFound;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Admin extends User {


    public Admin(String name, String password, PrimitiveDB db) throws NoSuchAlgorithmException {
        try {
            db.getAdminByName(name);
            db.getCustomerByName(name);
        }
        catch (ObjectNotFound o){
            this.setName(name);
            try{
                this.setPassword(password);
            } catch(NoSuchAlgorithmException e){ }
            db.addAdmin(this);
        }
        System.out.println("Name already taken");


    }

    public Admin() {
    }

    public Admin(PrimitiveDB db) throws ObjectNotFound, NoSuchAlgorithmException {

        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter name of admin:");
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
                System.out.println("Enter password of admin");
                String password = input.nextLine();
                if (password.isBlank()) {
                    System.out.println("Wrong password! Try again");
                    continue;
                }



                //SETTING
                try {
                    this.setPassword(password);
                } catch (NoSuchAlgorithmException e) {
                    System.out.println("Cant create admin");
                    return;
                }
                this.setName(name);
                db.addAdmin(this);
                return;
            }
            System.out.println("Name already taken! Try with another name");
        }



    }


}
