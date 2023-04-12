package fr.simplon.festivals.controller;

import fr.simplon.festivals.dao.FestivalDao;
import fr.simplon.festivals.entity.Festival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

/**
 * Classe de mapping des URLs liées aux fonctionnalités des festivals.
 */
@Controller
public class FestivalController
{
    // Ne pas injecter avec @Autowired sinon le code ne sera pas utilisable en dehors de Spring Boot.
    // Utiliser plutôt l'injection @Autowired avec le constructeur.
    private final FestivalDao festivalDao;

    /**
     * Constructeur.
     *
     * @param pFestivalDao le DAO d'accès aux données des festivals.
     */
    @Autowired
    public FestivalController(FestivalDao pFestivalDao)
    {
        this.festivalDao = pFestivalDao;
    }

    /**
     * Retourne la page qui liste les festivals. Cette page contient également une carte interactive.
     * <p>
     *
     * @param model Le modèle utilisé par Thymeleaf pour faire le lien avec la page HTML.
     * @return Le nom de la vue associée à la page demandée.
     */
    @GetMapping(path = "/festivals")
    public String getFestivals(Model model)
    {
        Collection<Festival> festivals = festivalDao.findAll();
        model.addAttribute("festivals", festivals);
        return "festivals";
    }

    /**
     * Retourne la page qui permet de saisir les informations d'un nouveau festival.
     *
     * @param model Le modèle utilisé par Thymeleaf pour faire le lien avec la page HTML.
     * @return Le nom de la vue associée à la page demandée.
     */
    @GetMapping(path = "/ajouter-festival")
    public String formulaireAjouterFestival(Model model)
    {
        model.addAttribute("festival", new Festival());
        return "ajouter-festival";
    }

    /**
     * Enregistre les informations d'un nouveau festival.
     *
     * @param festival   Le nouveau festival à enregistrer.
     * @param validation Les résultats de validation des données du formulaire.
     * @return La vue de la page d'accueil si tout s'est bien passé, sinon on reste sur la même page.
     */
    @PostMapping(path = "/ajouter-festival")
    public String actionAjouterFestival(
            @ModelAttribute("festival") Festival festival, BindingResult validation)
    {
        if (!validation.hasErrors())
        {
            festivalDao.save(festival);
            return "redirect:/festivals";
        }
        else
        {
            return "ajouter-festival";
        }
    }

    /**
     * Retourne le formulaire d'édition d'un festival.
     *
     * @param festivalId Identifiant du festival, passé dans un paramètre de l'URL .
     * @param model      Modèle Thymeleaf.
     * @return Le formulaire d'édition d'un festival.
     */
    @GetMapping(path = "/editer-festival")
    public String formulaireEditerFestival(@RequestParam Long festivalId, Model model)
    {
        model.addAttribute("festival", festivalDao.findById(festivalId));
        return "editer-festival";
    }

    /**
     * Enregistre les informations d'un festival.
     *
     * @param festival   Le festival à enregistrer.
     * @param validation Le résultat de validation du festival par Spring Validation.
     * @return La page d'accueil si tout s'est bien passé.
     */
    @PostMapping(path = "/editer-festival")
    public String actionEditerFestival(@ModelAttribute Festival festival, BindingResult validation)
    {
        if (!validation.hasErrors())
        {
            festivalDao.save(festival);
            return "redirect:/festivals";
        }
        else
        {
            return "editer-festival";
        }
    }
}
