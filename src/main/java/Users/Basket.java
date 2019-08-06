package Users;

import CommercialGoods.Goods;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<Goods> goodsToBuy = new ArrayList<>();

    public List<Goods> getGoodsToBuy() {
        return goodsToBuy;
    }
    public void addGoodToBasket(Goods good){
        goodsToBuy.add(good);

    }
    public void clearBasket(){
        goodsToBuy.clear();
    }

    public Basket() {
    }
}
