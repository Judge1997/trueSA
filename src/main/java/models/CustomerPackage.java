package models;

public class CustomerPackage {

    private int id;
    private int idCustomer;
    private int idPackage;

    public CustomerPackage(int id, int idCustomer, int idPackage){
        this.id = id;
        this.idCustomer = idCustomer;
        this.idPackage = idPackage;
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
}
