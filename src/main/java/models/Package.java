package models;

public class Package {

    private int id;
    private double price;
    private String name;
    private int net;
    private int voice;
    private int data;
    private int mobileQuantity;
    private int mobileSpeed;
    private int mobileTimes;
    private int tvs;

    public Package(int id, String name, double price, int net, int voice, int data, int mobileQuantity, int mobileSpeed, int mobileTimes, int tvs){
        this.id = id;
        this.name = name;
        this.price = price;
        this.net = net;
        this.voice = voice;
        this.data = data;
        this.mobileQuantity = mobileQuantity;
        this.mobileSpeed = mobileSpeed;
        this.mobileTimes = mobileTimes;
        this.tvs = tvs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return String.format("%.2f",this.price);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNet() {
        return net;
    }

    public void setNet(int net) {
        this.net = net;
    }

    public int getVoice() {
        return voice;
    }

    public void setVoice(int voice) {
        this.voice = voice;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getTvs() {
        return tvs;
    }

    public void setTvs(int tvs) {
        this.tvs = tvs;
    }

    public String toString(){
        return this.name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getMobileQuantity() {
        return mobileQuantity;
    }

    public void setMobileQuantity(int mobileQuantity) {
        this.mobileQuantity = mobileQuantity;
    }

    public int getMobileSpeed() {
        return mobileSpeed;
    }

    public void setMobileSpeed(int mobileSpeed) {
        this.mobileSpeed = mobileSpeed;
    }

    public int getMobileTimes() {
        return mobileTimes;
    }

    public void setMobileTimes(int mobileTimes) {
        this.mobileTimes = mobileTimes;
    }
}
