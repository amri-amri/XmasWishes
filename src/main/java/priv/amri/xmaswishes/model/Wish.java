package priv.amri.xmaswishes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Wish {



    public Wish(String name, String text, Status status, String etc) {
        this.name = name;
        this.text = text;
        this.status = status;
        this.etc = etc;
    }

    public Wish(String name, String text) {
        this.name = name;
        this.text = text;
    }

    @Id
    private String name;
    //@Column(name = "text")
    private String text;
    //@Column(name = "status")
    public Status status;
    //@Column(name = "etc")
    public String etc;

    public Wish() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }
}

