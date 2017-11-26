package models;

public class Package {

    private int id;
    private double price;
    private String name;
    private String net;
    private String voice;
    private String data;
    private String mobile;
    private String tvs;

    public Package(int id, String name, double price, String net, String voice, String data, String mobile, String tvs){
        this.id = id;
        this.name = name;
        this.price = price;
        this.net = net;
        this.voice = voice;
        this.data = data;
        this.mobile = mobile;
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

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String moblie) {
        this.mobile = moblie;
    }

    public String getTvs() {
        return tvs;
    }

    public void setTvs(String tvs) {
        this.tvs = tvs;
    }

    public String toString(){
        return this.name;
    }
}
