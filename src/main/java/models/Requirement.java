package models;

public class Requirement {

    private String net;
    private String voice;
    private String data;
    private String moblie;
    private String tvs;
    private int id;

    public Requirement(int id,String net, String voice, String data, String moblie, String tvs){
        this.id = id;
        this.net = net;
        this.voice = voice;
        this.data = data;
        this.moblie = moblie;
        this.tvs = tvs;
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

    public String getMoblie() {
        return moblie;
    }

    public void setMoblie(String moblie) {
        this.moblie = moblie;
    }

    public String getTvs() {
        return tvs;
    }

    public void setTvs(String tvs) {
        this.tvs = tvs;
    }
}
