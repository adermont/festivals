package fr.simplon.festivals.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * Annotations Spring Validation : https://beanvalidation.org/2.0/spec/#validationapi
 */
@Entity
@ValidDateOrder(dateMin = "debut", dateMax = "fin")
public class Festival
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String nom;

    @URL(regexp = "^(http|https)://.*$")
    private String url;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Sinon problème formulaire Thymeleaf th:field avec les <input type="date">
    private LocalDate debut;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd") // Sinon problème formulaire Thymeleaf th:field avec les <input type="date">
    private LocalDate fin;

    @NotNull
    @NotBlank
    private String ville;

    @Digits(integer = 5, fraction = 0)
    private int cp;

    private String lieu;

    private double lat;

    private double lon;

    public Long getId()
    {
        return id;
    }

    public void setId(Long pId)
    {
        id = pId;
    }

    public String getNom()
    {
        return nom;
    }

    public void setNom(String pNom)
    {
        nom = pNom;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String pUrl)
    {
        url = pUrl;
    }

    public LocalDate getDebut()
    {
        return debut;
    }

    public void setDebut(LocalDate pDebut)
    {
        debut = pDebut;
    }

    public LocalDate getFin()
    {
        return fin;
    }

    public void setFin(LocalDate pFin)
    {
        fin = pFin;
    }

    public String getVille()
    {
        return ville;
    }

    public void setVille(String pVille)
    {
        ville = pVille;
    }

    public int getCp()
    {
        return cp;
    }

    public void setCp(int pCp)
    {
        cp = pCp;
    }

    public String getLieu()
    {
        return lieu;
    }

    public void setLieu(String pLieu)
    {
        lieu = pLieu;
    }

    public double getLat()
    {
        return lat;
    }

    public void setLat(double pLat)
    {
        lat = pLat;
    }

    public double getLon()
    {
        return lon;
    }

    public void setLon(double pLon)
    {
        lon = pLon;
    }
}
