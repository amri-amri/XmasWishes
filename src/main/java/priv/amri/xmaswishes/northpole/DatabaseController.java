package priv.amri.xmaswishes.northpole;

import org.springframework.web.bind.annotation.*;
import priv.amri.xmaswishes.model.Status;
import priv.amri.xmaswishes.model.Wish;

import java.util.List;

@RestController
public class DatabaseController {

    DatabaseLogic databaseLogic;

    DatabaseController(DatabaseLogic databaseLogic) {
        this.databaseLogic = databaseLogic;
    }

    @PostMapping("/persist")
    String persistWishes(@RequestBody List<Wish> wishes){
        try {
            for (Wish wish : wishes) {
                databaseLogic.persistWish(wish.getName(), wish.getText(), Status.WISHED, "bla bla bla");
            }
            return "wishes persisted";
        } catch (Exception e) {
            return "error persisting wishes";
        }
    }

}
