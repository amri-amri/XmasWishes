package priv.amri.xmaswishes.datacloud;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import priv.amri.xmaswishes.model.Wish;

@RestController
public class DatabaseController {


    DatabaseLogic databaseLogic;

    public DatabaseController(DatabaseLogic databaseLogic) {
        this.databaseLogic = databaseLogic;
    }

    @PostMapping("/persist")
    String persistWish(@RequestParam(name = "name") String name, @RequestParam(name = "text") String text){
        return databaseLogic.persistWish(new Wish(name, text));
    }

    @GetMapping("/flush")
    String flushDatabase(){
        databaseLogic.flushDatabase();
        return "database flushed";
    }


}
