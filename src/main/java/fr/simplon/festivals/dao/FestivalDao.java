package fr.simplon.festivals.dao;

import fr.simplon.festivals.entity.Festival;

import java.util.Collection;
import java.util.List;

/**
 * Interface d'accès aux festivals.
 */
public interface FestivalDao
{
    /**
     * Récupère TOUS les festivals, triés par date.
     *
     * @return La liste de tous les festivals trouvés en BDD.
     */
    Collection<Festival> findAll();

    /**
     * Récupère un seul festival par son identifiant.
     *
     * @param pFestivalId Identifiant du festival recherché.
     * @return Le festival trouvé ou <code>null</code> s'il n'existe pas.
     */
    Festival findById(Long pFestivalId);

    /**
     * Enregistre un festival.
     *
     * @param festival Le festival à sauvegarder.
     */
    void save(Festival festival);

    /**
     * Enregistre un ensemble de festivals.
     *
     * @param pFestivals Liste des festivals à enregistrer.
     */
    void saveAll(List<Festival> pFestivals);
}
