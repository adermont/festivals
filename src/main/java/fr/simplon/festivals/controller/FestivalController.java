package fr.simplon.festivals.controller;

import fr.simplon.festivals.dao.impl.FestivalRepository;
import fr.simplon.festivals.entity.Festival;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collection;

/**
 * Classe de mapping des URLs liées aux fonctionnalités des festivals.
 */
@Controller
public class FestivalController
{
    // Ne pas injecter avec @Autowired sinon le code ne sera pas utilisable en dehors de Spring Boot.
    // Utiliser plutôt l'injection @Autowired avec le constructeur.
    private final FestivalRepository festivalDao;

    /**
     * Constructeur.
     *
     * @param pFestivalDao le DAO d'accès aux données des festivals.
     */
    @Autowired
    public FestivalController(FestivalRepository pFestivalDao)
    {
        this.festivalDao = pFestivalDao;
    }

    /**
     * Retourne la page qui liste les festivals. Cette page contient également une carte interactive.
     * <p>
     *
     * @param model Le modèle utilisé par Thymeleaf pour faire le lien avec la page HTML.
     * @param sort  Le paramètre sur lequel on veut trier les données.
     * @return Le nom de la vue associée à la page demandée.
     */
    @GetMapping(path = "/festivals")
    public String getFestivals(
            @RequestParam(required = false, defaultValue = "debut") String sort,
            @RequestParam(required = false) String order,
            Model model)
    {
        Sort.Order o = "desc".equalsIgnoreCase(order) ? Sort.Order.desc(sort) : Sort.Order.asc(sort);
        Collection<Festival> festivals = festivalDao.findAll(Sort.by(o));

        // Pour inverser le tri la prochaine fois qu'on clique, on modifie la variable "order"
        // avant de l'injecter dans le modèle
        order = "asc".equalsIgnoreCase(order) ? "desc" : "asc";
        model.addAttribute("festivals", festivals);
        model.addAttribute("order", order);
        model.addAttribute("sort", sort);
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
            @ModelAttribute("festival") @Valid Festival festival, BindingResult validation)
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
     * @param model      Le modèle Thymeleaf (le type RedirectAttributes permet de rajouter des attributs dans l'URL).
     * @return La page d'accueil si tout s'est bien passé.
     */
    @PostMapping(path = "/editer-festival")
    public String actionEditerFestival(
            @ModelAttribute @Valid Festival festival, BindingResult validation, RedirectAttributes model)
    {
        model.addAttribute("festival", festival);
        model.addAttribute("festivalId", festival.getId());

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
