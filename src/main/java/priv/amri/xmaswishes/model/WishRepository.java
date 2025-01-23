package priv.amri.xmaswishes.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

    @Query("SELECT w FROM Wish w WHERE w.status = :status")
    List<Wish> findWishesByStatus(@Param("status") Status status);

    @Query("SELECT w FROM Wish w WHERE w.name = :name")
    Wish findByName(@Param("name") String name);

}