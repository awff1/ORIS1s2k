package ru.itis.dis403.lab_03;

public class Row {
    private String f = "empty.jpg";
    private String s = "empty.jpg";
    private String t = "empty.jpg";

    public String getF() {
        return f;
    }
    public void setF(String f) {
        this.f = f;
    }

    public String getS() {
        return s;
    }
    public void setS(String s) {
        this.s = s;
    }

    public String getT() {
        return t;
    }
    public void setT(String t) {
        this.t = t;
    }

    public String getCell(int column) {
        return switch (column) {
            case 0 -> getF();
            case 1 -> getS();
            case 2 -> getT();
            default -> "";
        };
    }

    public void setCell(int column, String value) {
        switch (column) {
            case 0 -> setF(value);
            case 1 -> setS(value);
            case 2 -> setT(value);
        }
    }
}
