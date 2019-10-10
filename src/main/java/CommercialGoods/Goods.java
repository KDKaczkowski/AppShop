package CommercialGoods;

import Exceptions.AdditionFailed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public  class Goods implements Products{
    @NotBlank
    private String name;
    @NotBlank
    private String type;
    @NotEmpty
    private double numberOfGoods;
    @NotEmpty
    private double price;
    @NotEmpty
    private boolean pricePerUnit;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getNumberOfGoods() {
        return numberOfGoods;
    }

    public void setNumberOfGoods(double numberOfGoods) {
        this.numberOfGoods = numberOfGoods;
    }

    public boolean isPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(boolean pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
    public Goods(){ }
    public Goods(String name, String type, double numberOfGoods, double price, boolean pricePerUnit) throws AdditionFailed {
        if(price <= 0 || numberOfGoods < 0 || name.isEmpty() || name.equals(" "))
            throw new AdditionFailed();
        this.name = name;
        this.type = type;
        this.numberOfGoods = numberOfGoods;
        this.price = price;
        this.pricePerUnit = pricePerUnit;
    }

    public boolean equals(Goods good){
        return ( name.equals( good.name)
                && type.equals( good.type)
                && price == good.price
        );
    }

}
