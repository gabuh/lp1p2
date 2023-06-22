package br.ifsp.edu.lp1p2.controller.item;

public enum Tamanho {
    PP(0.0),
    P(0.02),
    M(0.04),
    G(0.06),
    GG(0.08),
    EXGG(0.1);

    private final double v;
    Tamanho(double v) {
        this.v = v;
    }

    public double getV() {
        return v;
    }
}
