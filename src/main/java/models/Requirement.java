package models;

public class Requirement {

    private String detail;
    private int id;
    private String date;

    public Requirement(int id,String detail, String date){
        this.id = id;
        this.detail = detail;
        this.date = date;
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
}
