package models;

public class Customer {

    private String name,aeName,region,locationInstall,businessId,capital,province,khet,khwang,employee,contactTelNum,contactFax,contact,contactName;
    private double average;
    private int id,voice,internet,data,mobiles,tvs;

    public Customer(int id,String name, String aeName, String region, String locationInstall, String businessId, String capital, String province, String khet, String khwang, String employee, String contactTelnum, String contactFax,
                    String contact, String contactName, double average, int voice, int internet, int data, int mobiles, int tvs){
        this.id = id;
        this.name = name;
        this.aeName = aeName;
        this.region = region;
        this.locationInstall = locationInstall;
        this.businessId = businessId;
        this.capital = capital;
        this.province = province;
        this.khet = khet;
        this.khwang = khwang;
        this.employee = employee;
        this.contactTelNum = contactTelnum;
        this.contactFax = contactFax;
        this.contact = contact;
        this.contactName = contactName;
        this.average = average;
        this.voice = voice;
        this.internet = internet;
        this.data = data;
        this.mobiles = mobiles;
        this.tvs = tvs;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAeName(String aeName) {
        this.aeName = aeName;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setLocationInstall(String locationInstall) {
        this.locationInstall = locationInstall;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setKhet(String khet) {
        this.khet = khet;
    }

    public void setKhwang(String khwang) {
        this.khwang = khwang;
    }

    public void setEmployee(String employee) {
        this.employee = employee;
    }

    public void setContactTelNum(String contactTelNum) {
        this.contactTelNum = contactTelNum;
    }

    public void setContactFax(String contactFax) {
        this.contactFax = contactFax;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public void setVoice(int voice) {
        this.voice = voice;
    }

    public void setInternet(int internet) {
        this.internet = internet;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setMobiles(int mobiles) {
        this.mobiles = mobiles;
    }

    public void setTvs(int tvs) {
        this.tvs = tvs;
    }

    public String getName() {
        return name;
    }

    public String getAeName() {
        return aeName;
    }

    public String getRegion() {
        return region;
    }

    public String getLocationInstall() {
        return locationInstall;
    }

    public String getBusinessId() {
        return businessId;
    }

    public String getCapital() {
        return capital;
    }

    public String getProvince() {
        return province;
    }

    public String getKhet() {
        return khet;
    }

    public String getKhwang() {
        return khwang;
    }

    public String getEmployee() {
        return employee;
    }

    public String getContactTelNum() {
        return contactTelNum;
    }

    public String getContactFax() {
        return contactFax;
    }

    public String getContact() {
        return contact;
    }

    public String getContactName() {
        return contactName;
    }

    public double getAverage() {
        return average;
    }

    public int getVoice() {
        return voice;
    }

    public int getInternet() {
        return internet;
    }

    public int getData() {
        return data;
    }

    public int getMobiles() {
        return mobiles;
    }

    public int getTvs() {
        return tvs;
    }

    public String getAverageTwoPoint(){
        return String.format("%.2f",this.average);
    }

}
