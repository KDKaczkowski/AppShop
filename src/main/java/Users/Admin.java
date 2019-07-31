package Users;

import DB.PrimitiveDB;
import Exceptions.ObjectNotFound;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Admin extends User {
    public Admin(String name, String password, PrimitiveDB db) throws NoSuchAlgorithmException {
        try {
            db.getAdminByName(name);
        }
        catch (ObjectNotFound o){
            this.setName(name);
            try{
                this.setPassword(password);
            } catch(NoSuchAlgorithmException e){ }
            db.addAdmin(this);
        }



    }

    public Admin(PrimitiveDB db) throws ObjectNotFound, NoSuchAlgorithmException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name of admin:");
        String name = input.nextLine();
        try {
            db.getAdminByName(name).getName();
        } catch (ObjectNotFound objectNotFound){
            this.setName(name);
            System.out.println("Enter password of admin");
            this.setPassword(input.nextLine());
            db.addAdmin(this);
            return;
        }

        System.out.println("Name already taken! Try with another name");
        new Admin(db);



    }


}
