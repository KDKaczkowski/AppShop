package Users;

import javax.validation.constraints.NotBlank;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public abstract class User {
    private UUID id = UUID.randomUUID();
    @NotBlank
    private String name;
    @NotBlank
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
    public boolean equals(User user){
        return ( this.getId().toString().equals( user.getId().toString() )
                && this.getName().equals( user.getName() )
                && this.getPassword().equals( user.getPassword())
                && this.getClass() == user.getClass()
        );
    }
    /*private void createCustomer(String name, String password, double cash, PrimitiveDB db){
        Customer customer = new Customer(db);
        System.out.println("Enter name of admin:");
        customer.setName(name);
        try {
            db.getAdminByName(customer.getName());
        }
        catch(ObjectNotFound objectNotFound){

            try {
                customer.setPassword(password);
            }
            catch (NoSuchAlgorithmException nosuchalgorithexception){

            }
            customer.setCashOnAccount(cash);
            db.addCustomer(customer);

        }

    }*/


}
