package flyger;

public class Resor {
    //instans variabler
    private int antalResenarer, antalDagar;

    private double prisResa;

    private String idResa, typAvResa, flygBolag, samarbetsPartner;

    //konstrukturn
    public Resor(String idResa, double prisResa, int antalResenarer, int antalDagar, String typAvResa, String flygBolag, String samarbetsPartner) {
        this.idResa = idResa;
        this.prisResa = prisResa;
        this.antalResenarer = antalResenarer;
        this.antalDagar = antalDagar;
        this.typAvResa = typAvResa;
        this.flygBolag = flygBolag;
        this.samarbetsPartner = samarbetsPartner;
    }

    //genererade getters och setters
    public String getIdResa() {
        return idResa;
    }


    public double getPrisResa() {
        return prisResa;
    }

    public void setPrisResa(double prisResa) {
        this.prisResa = prisResa;
    }

    public void setAntalDagar(int antalDagar) {
        this.antalDagar = antalDagar;
    }

    public String getTypAvResa() {
        return typAvResa;
    }

    //to string Sträng metoden som retunerar alla variabler
    @Override
    public String toString() {
        return
                "Flyg id: " + idResa +
                "\nTyp av resa: " + typAvResa +
                "\nPris: " + prisResa +
                "\nAntal resenärer: " + antalResenarer +
                "\nAntal dagar: " + antalDagar +
                "\nFlygbolag: " + flygBolag +
                "\nSamarbetspartner: " + samarbetsPartner;
    }
}
