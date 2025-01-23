package priv.amri.xmaswishes.northpole;

import priv.amri.xmaswishes.model.Status;
import priv.amri.xmaswishes.model.Wish;
import priv.amri.xmaswishes.model.WishRepository;

import java.util.List;

@org.springframework.stereotype.Service
public class DatabaseLogic {

    DatabaseLogic(WishRepository wishRepository) {
        this.wishRepository = wishRepository;
    }

    private final WishRepository wishRepository;


    public String persistWish(String name, String text, Status status, String etc) {
        try {
            wishRepository.saveAndFlush(new Wish(name, text, status, etc));
            return "wish persited";
        } catch (Exception e) {
            return "error";
        }
    }

    public List<Wish> getWishesByStatus(Status status) {
        return wishRepository.findWishesByStatus(status);
    }

    public void updateStatus(String name, Status status) {
        Wish wish = wishRepository.findByName(name);
        wish.setStatus(status);
        wishRepository.save(wish);
    }
}
