import CommercialGoods.Goods;
import DB.PrimitiveDB;
import Exceptions.AdditionFailed;
import Exceptions.ObjectNotFound;
import Users.Admin;

import java.util.Map;

public class Main {
    public static void main(String[] args) throws AdditionFailed, ObjectNotFound {
        PrimitiveDB db = new PrimitiveDB();


        for( Map.Entry<String, Goods> entry : db.getMapOfGoods().entries()){
            String key= entry.getKey();
            Goods value = entry.getValue();
            System.out.println("KLUCZ: " + key + " WARTOSC: " + value.getName() + "  TYP: "+ value.getType());
        }

        for(Goods element : db.getGoodsOfType("Bakery")){
            System.out.println(element.getName());
        }
        for(Admin el : db.getSetOfAdmins()){
            System.out.println(el.getId() + "   Name" + el.getName() +"   " + el.getPassword());
        }
        System.out.println(db.getAdminByName(db.getAdminByName("Bartosz").getName()).getName());
        System.out.println(db.getAdminByName("Jerzy").getName());
        System.out.println(db.getAdminByName("jajo").getName());



    }
}
