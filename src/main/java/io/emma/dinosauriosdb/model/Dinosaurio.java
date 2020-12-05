package io.emma.dinosauriosdb.model;

public class Dinosaurio {
    private int id;
    private String name;
    private String periodo;
    private String habitat;

    public Dinosaurio(int id, String name, String periodo, String habitat) {
        this.id = id;
        this.name = name;
        this.periodo = periodo;
        this.habitat = habitat;
    }
    public Dinosaurio(){};

    public Dinosaurio(String name, String periodo, String habitat) {
        this.name = name;
        this.periodo = periodo;
        this.habitat = habitat;
    }

    public  Dinosaurio(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPeriodo() {
        return periodo;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    @Override
    public String toString() {
        return "Dinosaurio{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", periodo='" + periodo + '\'' +
                ", habitat='" + habitat + '\'' +
                '}';
    }
}
