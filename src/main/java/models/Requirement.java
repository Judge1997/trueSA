package models;

public class Requirement {

    private String detail;
    private int id;
    private int idCustomer;
    private String date;

    public Requirement(int id, int idCustomer, String detail, String date){
        this.id = id;
        this.detail = detail;
        this.date = date;
        this.idCustomer = idCustomer;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }
}
