package fr.medicamentvet.entities;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * This class is ModeleDestineVente object.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModeleDestineVente {

    private int idMdv;
    private String libelle;
    private String codeGTIN;
    private String numeroAMM;

    public ModeleDestineVente() {
        super();
    }

    public ModeleDestineVente(int idMdv, String libelle, String codeGTIN, String numeroAMM) {
        super();
        this.idMdv = idMdv;
        this.libelle = libelle;
        this.codeGTIN = codeGTIN;
        this.numeroAMM = numeroAMM;
    }

    public int getIdMdv() {
        return idMdv;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getCodeGTIN() {
        return codeGTIN;
    }

    public String getNumeroAMM() {
        return numeroAMM;
    }

    @Override
    public String toString() {
        return String.format("%d %s %s %s", idMdv, libelle, codeGTIN, numeroAMM);
    }
}
