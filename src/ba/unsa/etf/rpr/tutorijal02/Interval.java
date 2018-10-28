package ba.unsa.etf.rpr.tutorijal02;

public class Interval {

    private double poc_tacka = 0;
    private double kraj_tacka = 0;
    private boolean poc_tacka_pripada = false;
    private boolean kraj_tacka_pripada = false;

    public Interval(double poc_tacka, double kraj_tacka, boolean poc_tacka_pripada, boolean kraj_tacka_pripada) {
        if (poc_tacka > kraj_tacka)
            throw new IllegalArgumentException("Pogresan interval");

        this.poc_tacka = poc_tacka;
        this.kraj_tacka = kraj_tacka;
        this.poc_tacka_pripada = poc_tacka_pripada;
        this.kraj_tacka_pripada = kraj_tacka_pripada;


    }

    public boolean isNull() {
        if (poc_tacka == 0 && kraj_tacka == 0 && poc_tacka_pripada == false && kraj_tacka_pripada == false)
            return true;
        else
            return false;
    }

    public boolean isIn(double var) {

        if (var == poc_tacka && !poc_tacka_pripada)
            return false;

        if (var == kraj_tacka && !kraj_tacka_pripada)
            return false;

        if (var < poc_tacka || var > kraj_tacka)
            return false;

        return true;
    }

    public Interval intersects(Interval i) {
        if ((i.poc_tacka > poc_tacka && i.poc_tacka > kraj_tacka) || (i.poc_tacka < poc_tacka && poc_tacka > i.kraj_tacka))
            return new Interval(poc_tacka,kraj_tacka, poc_tacka_pripada, kraj_tacka_pripada);

        Interval nova = new Interval(poc_tacka, kraj_tacka, false, false);

        if (i.poc_tacka > poc_tacka)
            nova.poc_tacka = poc_tacka;
        if (i.kraj_tacka < kraj_tacka)
            nova.kraj_tacka = i.kraj_tacka;

        if (isIn(nova.poc_tacka) && i.isIn(nova.poc_tacka))
            nova.poc_tacka_pripada = true;

        if (isIn(nova.kraj_tacka) && i.isIn(nova.kraj_tacka))
            nova.kraj_tacka_pripada = true;

        return nova;


    }

    public Interval intersect(Interval drugi, Interval prvi) {
        return prvi.intersects(drugi);
    }

    @Override

    public boolean equals(Object o) {
        Interval tmp = (Interval) o;

        if (poc_tacka_pripada == tmp.poc_tacka_pripada && kraj_tacka_pripada == tmp.kraj_tacka_pripada && poc_tacka == tmp.poc_tacka && kraj_tacka == tmp.kraj_tacka)
            return true;
        else
            return false;

    }

    @Override

    public String toString() {

        String s = new String();
        if (this.equals(new Interval(poc_tacka, kraj_tacka, poc_tacka_pripada, kraj_tacka_pripada)))
            return "()";
        if (poc_tacka_pripada)
            s += "[";
        else
            s += "(";
        s += poc_tacka + "," + kraj_tacka;

        if (kraj_tacka_pripada)
            s += "]";
        else
            s += ")";
        return s;
    }
}









