package Users;

import DB.PrimitiveDB;
import Exceptions.ObjectNotFound;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Admin extends User {
    public Admin() {
    }

    private void createAdmin(PrimitiveDB db){
        Admin admin = new Admin();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name of admin:");
        admin.setName(input.nextLine());
        try {
            db.getAdminByName(admin.getName());
        }
        catch(ObjectNotFound objectNotFound){
            System.out.println("Enter password of admin");
            try {
                admin.setPassword(input.nextLine());
            }
            catch (NoSuchAlgorithmException e){
                System.out.println("Cant create admin");
            }
                db.addAdmin(admin);
            return;
        }
        System.out.println("Name already taken! Try with another name");
        createAdmin(db);

    }
}
