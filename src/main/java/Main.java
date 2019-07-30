import CommercialGoods.Goods;
import DB.PrimitiveDB;
import DB.Starter;
import Exceptions.AdditionFailed;
import Exceptions.ObjectNotFound;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws AdditionFailed, ObjectNotFound {
        PrimitiveDB db = new PrimitiveDB();


        for( Map.Entry<String, Goods> entry : db.getMapOfGoods().entrySet()){
            String key= entry.getKey();
            Goods value = entry.getValue();
            System.out.println("KLUCZ: " + key + " WARTOSC: " + value.getName());
        }



    }
}
