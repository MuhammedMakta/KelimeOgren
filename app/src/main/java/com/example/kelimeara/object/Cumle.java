package com.example.kelimeara.object;

import java.io.Serializable;

public class Cumle implements Serializable {
    private int cumle_id;
    private String cumle_ingilizce;

    public Cumle() {
    }

    public Cumle(int cumle_id, String cumle_ingilizce) {
        this.cumle_id = cumle_id;
        this.cumle_ingilizce = cumle_ingilizce;
    }

    public int getCumle_id() {
        return cumle_id;
    }

    public void setCumle_id(int cumle_id) {
        this.cumle_id = cumle_id;
    }

    public String getCumle_ingilizce() {
        return cumle_ingilizce;
    }

    public void setCumle_ingilizce(String cumle_ingilizce) {
        this.cumle_ingilizce = cumle_ingilizce;
    }
}
