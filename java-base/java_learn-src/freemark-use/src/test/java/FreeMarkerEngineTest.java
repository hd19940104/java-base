import org.bling.freemark.engine.FreeMarkerEngine;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FreeMarkerEngineTest {

    @Test
    public void testSqlTemplateWithoutFilmYear() {
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
        String expected = "select film_title\n   from film_table\n  where last_name  = \"ford\"\n    and first_name = \"harrison\"\n";
        String result = freeMarkerEngine.process("sql/film.ftl", null);
        Assert.assertEquals(expected, result);
    }


    @Test
    public void testSqlTemplateWithFilmYear() {
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
        String expected = "select film_title\n   from film_table\n  where last_name  = \"ford\"\n    and first_name = \"harrison\"\n    and film_title in\n         (select film_title\n            from film_table\n            where film_year equals 1984);";
        Map<String, String> model = new HashMap<>();
        model.put("film_year", "1984");
        String result = freeMarkerEngine.process("sql/film.ftl", model);
        Assert.assertEquals(expected, result);
    }

    @Test
    public void testSqlTemplateWithTitles() {
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
        String expected = "select film_title\n   from film_table\n  where last_name  = \"ford\"\n    and first_name = \"harrison\"\n    and film_title in (\n    \"Star wars\",\n    \"Indiana Jones\"\n    )\n";
        Map<String, Object> model = new HashMap<>();
        List<String> titles = new LinkedList<>();
        titles.add("Star wars");
        titles.add("Indiana Jones");
        model.put("titles", titles);
        String result = freeMarkerEngine.process("sql/film.ftl", model);
        Assert.assertEquals(expected, result);
    }



}
