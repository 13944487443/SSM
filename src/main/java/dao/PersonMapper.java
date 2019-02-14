package dao;

import entity.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description
 * @auther Mr.DayDream
 * @create 2019-02-13 19:12
 * @deprecated
 */
@Repository
public interface PersonMapper {
    int insert(Person person);

    int deleteById(String id);

    int updateById(Person person);

    Person findById(String id);

    List<Person> findAll();
}
