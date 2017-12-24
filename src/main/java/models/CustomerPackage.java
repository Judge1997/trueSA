package models;

public class CustomerPackage {

    private int id;
    private int idCustomer;
    private int idPackage;
    private String status;

    public CustomerPackage(int id, int idCustomer, int idPackage ,String status){
        this.id = id;
        this.idCustomer = idCustomer;
        this.idPackage = idPackage;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdPackage() {
        return idPackage;
    }

    public void setIdPackage(int idPackage) {
        this.idPackage = idPackage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
