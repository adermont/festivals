package fr.simplon.festivals;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fr.simplon.festivals.dao.impl.FestivalRepository;
import fr.simplon.festivals.entity.Festival;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Ce composant charge des données initiales en base de données lorsqu'il n'en trouve aucune.
 */
@Component
public class DataLoader implements ApplicationRunner
{
    private final FestivalRepository mFestivalRepository;

    @Autowired
    public DataLoader(FestivalRepository pFestivalRepository)
    {
        this.mFestivalRepository = pFestivalRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception
    {
        if (mFestivalRepository.count() == 0)
        {
            ClassPathResource resource = new ClassPathResource("static/json/festivals.json");
            ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();
            List<Festival> festivals = objectMapper.readValue(resource.getInputStream(), new TypeReference<>()
            {
            });
            mFestivalRepository.saveAll(festivals);
        }
    }

}
