package entity;

import java.time.LocalDate;

public class Book {
    private int id;
    private int car_id;
    private Car car;
    private Model model;

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    private Brand brand;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", car_id=" + car_id +
                ", car=" + car +
                ", name='" + name + '\'' +
                ", idno='" + idno + '\'' +
                ", mpno='" + mpno + '\'' +
                ", mail='" + mail + '\'' +
                ", strt_date=" + strt_date +
                ", fnsh_date=" + fnsh_date +
                ", bCase='" + bCase + '\'' +
                ", prc=" + prc +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrc() {
        return prc;
    }

    public void setPrc(int prc) {
        this.prc = prc;
    }

    public String getbCase() {
        return bCase;
    }

    public void setbCase(String bCase) {
        this.bCase = bCase;
    }

    public LocalDate getFnsh_date() {
        return fnsh_date;
    }

    public void setFnsh_date(LocalDate fnsh_date) {
        this.fnsh_date = fnsh_date;
    }

    public LocalDate getStrt_date() {
        return strt_date;
    }

    public void setStrt_date(LocalDate strt_date) {
        this.strt_date = strt_date;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMpno() {
        return mpno;
    }

    public void setMpno(String mpno) {
        this.mpno = mpno;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    private String name;
    private String idno;
    private String mpno;
    private String mail;
    private LocalDate strt_date;
    private LocalDate fnsh_date;
    private String bCase;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    private int prc;
    private String note;
}
