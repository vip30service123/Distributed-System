import java.io.Serializable;

public class Advertising implements Serializable {
    double TV;
    double Radio;
    double Newspaper;
    double Sales;

    Advertising (double TV, double Radio, double Newspaper, double Sales) {
        this.TV = TV;
        this.Radio = Radio;
        this.Newspaper = Newspaper;
        this.Sales = Sales;
    }

    Advertising () {
        TV = 0;
        Radio = 0;
        Newspaper = 0;
        Sales = 0;
    }

    public double getTV() {
        return TV;
    }

    public void setTV(double TV) {
        this.TV = TV;
    }

    public double getRadio() {
        return Radio;
    }

    public void setRadio(double radio) {
        Radio = radio;
    }

    public double getNewspaper() {
        return Newspaper;
    }

    public void setNewspaper(double newspaper) {
        Newspaper = newspaper;
    }

    public double getSales() {
        return Sales;
    }

    public void setSales(double sales) {
        Sales = sales;
    }
}
