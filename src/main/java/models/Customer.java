package models;

public class Customer {

    private String name,aeName,region,locationInstall,businessId,capital,province,khet,khwang,employee,contactTelNum,contactFax,contact,contactName;
    private double packetCost;
    private int id;

    public Customer(int id,String name, String aeName, String region, String locationInstall, String businessId, String capital, String province, String khet, String khwang, String employee, String contactTelnum, String contactFax,
                    String contact, String contactName, double packetCost){
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
        this.packetCost = packetCost;

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

    public void setPacketCost(double packetCost) {
        this.packetCost = packetCost;
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
        return this.getFormat(capital);
    }

    private String getFormat(String str){
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

    public double getPacketCost() {
        return packetCost;
    }

}
