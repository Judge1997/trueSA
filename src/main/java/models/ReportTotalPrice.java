package models;

public class ReportTotalPrice {

    private int id;
    private String name;
    private double price;

    public ReportTotalPrice(int id, String name, double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void addPrice(double price){
        this.price += price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPriceString() {
        String str = String.format("%.2f", price);
        String priceStr = str.substring(str.length()-2,str.length());
        int count = 0;
        for (int i = str.length()-3 ; i >= 0 ; i--){
            String subStr = str.substring(i,i+1);
            if(count == 3 && i != 0){
                subStr = ","+subStr;
                count = 0;
            }
            priceStr = subStr+priceStr;
            count++;
        }

        return priceStr;
    }

    public double getPrice(){
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
