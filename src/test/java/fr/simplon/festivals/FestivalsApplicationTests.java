package fr.simplon.festivals;

import fr.simplon.festivals.dao.FestivalDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FestivalsApplicationTests
{
    @Autowired
    private FestivalDao festivalDao;

    @Test
    void contextLoads()
    {
        Assertions.assertNotNull(festivalDao);
    }

}
