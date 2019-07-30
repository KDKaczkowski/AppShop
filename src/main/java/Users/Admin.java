package Users;

import DB.PrimitiveDB;
import Exceptions.ObjectNotFound;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Admin extends User {
    public Admin(boolean auto, PrimitiveDB db) {
        if(auto == false) {
            Scanner input = new Scanner(System.in);
            System.out.println("Enter name of admin:");
            this.setName(input.nextLine());
            try {
                db.getAdminByName(this.getName());
            } catch (ObjectNotFound objectNotFound) {
                System.out.println("Enter password of admin");
                try {
                    this.setPassword(input.nextLine());
                } catch (NoSuchAlgorithmException e) {
                    System.out.println("Cant create admin");
                }
                db.addAdmin(this);
                return;
            }
            System.out.println("Name already taken! Try with another name");
            new Admin(auto, db);
            return;
        }
        else{

        }
    }
    //\\TODELETE
    private Admin createAdmin(String name, String password, PrimitiveDB db) throws NoSuchAlgorithmException{
        Admin admin = new Admin(false, db);
        admin.setName(name);
        admin.setPassword(password);
        return admin;
    }


}
