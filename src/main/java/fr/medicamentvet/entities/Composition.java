package fr.medicamentvet.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * This class describes the composition of a Medicament object.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Composition {

    private int idComposition;
    private String substanceActive;
    private String quantite;
    private String unite;

    public Composition() {
        super();
    }

    public Composition(int idComposition, String substanceActive, String quantite, String unite) {
        super();
        this.idComposition = idComposition;
        this.substanceActive = substanceActive;
        this.quantite = quantite;
        this.unite = unite;
    }

    public int getIdComposition() {
        return idComposition;
    }

    public String getSubstanceActive() {
        return substanceActive;
    }

    public String getQuantite() {
        return quantite;
    }

    public String getUnite() {
        return unite;
    }

    @Override
    public String toString() {
        return String.format("%d %s %s %s", idComposition, substanceActive, quantite, unite);
    }
}