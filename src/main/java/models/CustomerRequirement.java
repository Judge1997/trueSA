package models;

public class CustomerRequirement {

    private int id;
    private int idCustomer;
    private int idRequirement;
//    private String status;

    public CustomerRequirement(int id, int idCustomer, int idRequirement){
        this.id = id;
        this.idCustomer = idCustomer;
        this.idRequirement = idRequirement;
//        this.status = status;
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

    public int getIdRequirement() {
        return idRequirement;
    }

    public void setIdRequirement(int idRequirement) {
        this.idRequirement = idRequirement;
    }

//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
}
