package service;

import entity.Person;

import java.util.List;

/**
 * @Description
 * @auther Mr.DayDream
 * @create 2019-02-13 19:12
 * @deprecated
 */
public interface PersonService {
    int insert(Person person);

    int deleteById(String id);

    int updateById(Person person);

    Person findById(String id);

    List<Person> findAll();

}
