package models;

public class Package {

    protected int id;
    protected double price;
    protected String name;
    protected double net;
    protected int voice;
    protected double data;
    protected int mobileQuantity;
    protected double mobileSpeed;
    protected int mobileTimes;
    protected int tvs;
    protected String status;

    public Package(int id, String name, double price, double net, int voice, double data, int mobileQuantity, double mobileSpeed, int mobileTimes, int tvs, String status){
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
        this.status = status;
    }

    private String getFormatInteger(String str){
//        String string = String.format("%.2f", Integer.parseInt(str));
//        String priceStr = str.substring(str.length()-2,str.length());
        String priceStr = "";
        int count = 1;
        for (int i = str.length()-1 ; i >= 0 ; i--){
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

    public String getFormatDouble(double price) {
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

    public double getPrice(){
        return price;
    }

    public String getPriceString() {

        return this.getFormatDouble(price);
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getNet() {
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

    public double getData() {
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

    public double getMobileSpeed() {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNetString(){
        return this.getFormatDouble(net);
    }

    public String getVoiceString(){
        return this.getFormatInteger(String.valueOf(voice));
    }

    public String getDataString(){
        return this.getFormatDouble(data);
    }

    public String getMobileQuantityString(){
        return this.getFormatInteger(String.valueOf(mobileQuantity));
    }

    public String getMobileSpeedString(){
        return this.getFormatDouble(mobileSpeed);
    }

    public String getMobileTimesString(){
        return this.getFormatInteger(String.valueOf(mobileTimes));
    }

    public String getTvsString(){
        return this.getFormatInteger(String.valueOf(tvs));
    }
}
