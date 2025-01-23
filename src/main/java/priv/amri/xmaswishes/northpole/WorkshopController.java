package priv.amri.xmaswishes.northpole;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import priv.amri.xmaswishes.model.Status;
import priv.amri.xmaswishes.model.Wish;

import java.util.List;

@RestController
public class WorkshopController {

    DatabaseLogic databaseLogic;

    WorkshopController(DatabaseLogic databaseLogic) {
        this.databaseLogic = databaseLogic;
    }

    @GetMapping("/get/by/status")
    List<Wish> getWishesByStatus(@RequestParam(name = "status") Status status) {
        return databaseLogic.getWishesByStatus(status);
    }

    @PutMapping("/update/status")
    void updateStatus(@RequestParam(name = "name") String name, @RequestParam(name = "status") Status status) {
        databaseLogic.updateStatus(name, status);
    }

}
