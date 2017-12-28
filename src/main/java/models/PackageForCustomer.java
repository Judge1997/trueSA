package models;

public class PackageForCustomer extends Package {

    private String startTime, endTime;

    public PackageForCustomer(int id, String name, double price, double net, int voice, double data, int mobileQuantity, double mobileSpeed, int mobileTimes, int tvs, String status, String startTime, String endTime) {
        super(id, name, price, net, voice, data, mobileQuantity, mobileSpeed, mobileTimes, tvs, status);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
