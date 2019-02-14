package controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.PersonService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @auther Mr.DayDream
 * @create 2019-02-13 19:12
 * @deprecated
 */
@Controller
@RequestMapping("person/")
public class PersonController {
    @Autowired
    private PersonService personService;

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(){
        return "list";
    }

    @RequestMapping(value = "json_list",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> list(int pageNumber,int pageSize){
        PageHelper.startPage(pageNumber,pageSize);
        List<Person> personList= personService.findAll();
        PageInfo<Person> pageInfo=new PageInfo<Person>(personList);
        Map<String,Object> map=new HashMap();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

}

