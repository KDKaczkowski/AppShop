package CommercialGoods;

public  class Goods {
    private String name;
    private String type;
    private int numberOfGoods;
    private double price;
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

    public int getNumberOfGoods() {
        return numberOfGoods;
    }

    public void setNumberOfGoods(int numberOfGoods) {
        this.numberOfGoods = numberOfGoods;
    }

    public boolean isPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(boolean pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public Goods(String name, String type, int numberOfGoods, double price, boolean pricePerUnit) {
        this.name = name;
        this.type = type;
        this.numberOfGoods = numberOfGoods;
        this.price = price;
        this.pricePerUnit = pricePerUnit;
    }
}
