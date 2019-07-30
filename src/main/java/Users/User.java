package Users;

import DB.PrimitiveDB;
import Exceptions.ObjectNotFound;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.UUID;

public abstract class User {
    private UUID id = UUID.randomUUID();
    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public UUID getId() {
        return id;
    }

    public void setPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] messageDigest = md.digest(password.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        this.password = no.toString(16);

        while (this.password.length() < 32) {
            this.password = "0" + this.password;
        }

    }
    private void createCustomer(PrimitiveDB db){
        Customer customer = new Customer();
        Scanner input = new Scanner(System.in);
        System.out.println("Enter name of admin:");
        customer.setName(input.nextLine());
        try {
            db.getAdminByName(customer.getName());
        }
        catch(ObjectNotFound objectNotFound){
            System.out.println("Enter password of admin");
            try {
                customer.setPassword(input.nextLine());
            }
            catch (NoSuchAlgorithmException nosuchalgorithexception){
                System.out.println("Can't create customer");
            }

                db.addCustomer(customer);
            return;
        }
        System.out.println("Name already taken! Try with another name");
        createCustomer(db);
    }


}
