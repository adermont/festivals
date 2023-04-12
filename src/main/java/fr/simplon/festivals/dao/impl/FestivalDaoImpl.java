package fr.simplon.festivals.dao.impl;

import fr.simplon.festivals.dao.FestivalDao;
import fr.simplon.festivals.entity.Festival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

/**
 * Composant d'accès aux données des festivals.
 */
@Repository
public class FestivalDaoImpl implements FestivalDao
{
    private final FestivalRepository festivalRepository;

    @Autowired
    public FestivalDaoImpl(FestivalRepository festivalRepository)
    {
        this.festivalRepository = festivalRepository;
    }

    @Override
    public Collection<Festival> findAll()
    {
        return festivalRepository.findAll(Sort.by(Sort.Order.asc("debut")));
    }

    @Override
    public Festival findById(Long pFestivalId)
    {
        return festivalRepository.findById(pFestivalId).orElseThrow(() -> new RuntimeException("Identifiant invalide: " + pFestivalId));
    }

    @Override
    public void saveAll(List<Festival> pFestivals)
    {
        festivalRepository.saveAll(pFestivals);
    }

    @Override
    public void save(Festival festival)
    {
        festivalRepository.save(festival);
    }

}
