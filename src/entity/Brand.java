package entity;

public class Brand
{
    private int id;

    public Brand() {
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Brand(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Brand( String name) {
        this.id = id;
        this.name = name;
    }
}
