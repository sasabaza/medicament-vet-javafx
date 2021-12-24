package fr.medicamentvet.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import fr.medicamentvet.utils.LocalDateDeserializer;
import fr.medicamentvet.utils.LocalDateSerializer;
import fr.medicamentvet.utils.Static;
import java.time.LocalDate;
import java.util.List;

/**
 * This class describes the Résumé des caractéristiques du produit (RCP): the characteristics of a medicament.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rcp {

    private List<String> imageURLList;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate dateValidation;

    private String lienRcp;
    private List<String> titreList;
    private List<String> contenuList;

    public Rcp() {
        super();
    }

    public Rcp(List<String> imageURLList, LocalDate dateValidation, String lienRcp, List<String> titreList, List<String> contenuList) {
        super();
        this.imageURLList = imageURLList;
        this.dateValidation = dateValidation;
        this.lienRcp = lienRcp;
        this.titreList = titreList;
        this.contenuList = contenuList;
    }

    public List<String> getImageURLList() {
        return imageURLList;
    }

    public LocalDate getDateValidation() {
        return dateValidation;
    }

    public String getLienRcp() {
        return lienRcp;
    }

    public List<String> getTitreList() {
        return titreList;
    }

    public List<String> getContenuList() {
        return contenuList;
    }

    /**
     * The method creates a two-dimensional String array from titreList, contenuList, imageURLList instance variables.
     *
     * @return Two-dimensional String array containing titreList, contenuList and imageURLList
     */
    public String[][] toArrayString2D() {

        int size1 = this.titreList.size();
        int size2 = 0;

        List<String> imageList = this.imageURLList;

        if (imageList != null) {
            size2 = this.imageURLList.size();
        }

        int maximumSize = Math.max(size1, size2);

        String[][] arrayString2D = new String[3][maximumSize];

        for (int i = 0; i < size1; i++) {
            arrayString2D[0][i] = this.titreList.get(i);
            arrayString2D[1][i] = this.contenuList.get(i);
        }

        if (imageList != null) {
            for (int i = 0; i < size2; i++) {
                arrayString2D[2][i] = this.imageURLList.get(i);
            }
        } else {
            arrayString2D[2] = null;
        }

        return arrayString2D;
    }

    @Override
    public String toString() {

        return "dateValidation: " + dateValidation + Static.SPACE_AMP_AMP_SPACE_TEXT +
                "lienRcp: " + lienRcp + Static.SPACE_AMP_AMP_SPACE_TEXT +
                "imageURLList: " + imageURLList + Static.SPACE_AMP_AMP_SPACE_TEXT +
                "titreList: " + titreList + Static.SPACE_AMP_AMP_SPACE_TEXT +
                "contenuList: " + contenuList;
    }
}
