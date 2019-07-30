import CommercialGoods.Goods;
import DB.PrimitiveDB;
import Exceptions.AdditionFailed;
import Exceptions.ObjectNotFound;

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



    }
}
