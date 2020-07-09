package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Query("select u from Meal u where u.user.id = :id")
    List<Meal> getAllByUserId(@Param("id") Integer id, Sort sort);

    @Transactional
    @Modifying
    @Query("delete from Meal m where m.user.id = :user_id AND m.id = :id")
    int deleteByUserId(@Param("user_id") int userId,@Param("id") int id);

    @Query("select m from Meal m where m.user.id = :user_id and m.dateTime>=:start and m.dateTime<:end")
    List<Meal> getBetweenInclusive(@Param("start") LocalDateTime startDateTime,@Param("end") LocalDateTime endDateTime,@Param("user_id") int userId,Sort sort);
}
