package service.impl;

import dao.PersonMapper;
import entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.PersonService;

import java.util.List;

/**
 * @Description
 * @auther Mr.DayDream
 * @create 2019-02-13 19:12
 * @deprecated
 */
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    public int insert(Person person) {
        return personMapper.insert(person);
    }

    public int deleteById(String id) {
        return personMapper.deleteById(id);
    }

    public int updateById(Person person) {
        return personMapper.updateById(person);
    }

    public Person findById(String id) {
        return personMapper.findById(id);
    }

    public List<Person> findAll() {
        return personMapper.findAll();
    }
}

