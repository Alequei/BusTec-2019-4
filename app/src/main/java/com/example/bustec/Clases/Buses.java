package com.example.bustec.Clases;

public class Buses {
    private Integer id;
    private String placa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @Override
    public String toString() {
        return "Buses{" +
                "id=" + id +
                ", placa='" + placa + '\'' +
                '}';
    }
}
