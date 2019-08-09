import CommercialGoods.Goods;
import DB.PrimitiveDB;
import Exceptions.AdditionFailed;
import Exceptions.ObjectNotFound;
import Users.Admin;
import Users.Customer;

import java.util.Map;

public class Main {
    public static void main(String[] args) throws AdditionFailed, ObjectNotFound {
        PrimitiveDB db = new PrimitiveDB(false);


        for( Map.Entry<String, Goods> entry : db.getMapOfGoods().entries()){
            String key= entry.getKey();
            Goods value = entry.getValue();
            System.out.println("KLUCZ: " + key + " WARTOSC: " + value.getName() + "  TYP: "+ value.getType());
        }

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


    }
}
