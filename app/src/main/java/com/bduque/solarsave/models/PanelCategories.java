package com.bduque.solarsave.models;

public class PanelCategories {
    private String categoria;
    private String mes;
    private float energia;

    public PanelCategories(String categoria, String mes, float energia) {
        this.categoria = categoria;
        this.mes = mes;
        this.energia = energia;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public float getEnergia() {
        return energia;
    }

    public void setEnergia(float energia) {
        this.energia = energia;
    }


}
