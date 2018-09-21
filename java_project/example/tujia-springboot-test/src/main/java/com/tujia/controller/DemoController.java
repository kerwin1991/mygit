package com.tujia.controller;

import com.tujia.dto.Animal;
import com.tujia.dto.People;
import com.tujia.service.PeopleGCacheService;
import com.tujia.web.ListRequest;
import javafx.beans.binding.ObjectExpression;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * ceshi
 */
@RestController
public class DemoController {


    @Resource
    private PeopleGCacheService peopleGCacheService;

    @GetMapping(value = "/demo/first")
    public Object test() {
        System.out.println("我叫王晓鹏呀！！");
        return true;
    }

    /***
     *
     * {
     * 	"filter":{
     * 		"username":"kerwin",
     * 		"gender":2
     *        }
     * }
     */
    @ResponseBody
    @RequestMapping(value = "/demo/people", method = RequestMethod.POST)
    public Object people(@RequestBody ListRequest<People> request) {
        People filter = request.getFilter();
        System.out.println(filter);
        System.out.println(filter.getCities()[0]);
        System.out.println(filter.getCities()[1]);
        return true;
    }

    @ResponseBody
    @RequestMapping(value = "/demo/animal", method = RequestMethod.POST)
    public Object animal(@RequestBody ListRequest<Animal> request) {
        Animal filter = request.getFilter();
        System.out.println(filter);
        return true;
    }

    @GetMapping(value = "/people/{id}")
    public Object getPeople(@PathVariable(value = "id") Integer id) {
        People ret = peopleGCacheService.getFormGcache(id);
        return ret == null ? false : ret;
    }

    public static void main(String[] args) {
        /*String[] arr = {"北京市","朝阳区"};
        System.out.println(arr[0]+"--"+arr[1]);
        List<String> list = Arrays.asList(arr);
        System.out.println(list.size());
        System.out.println(list);
        boolean 北京市 = list.contains("北京市");
        System.out.println(北京市);*/
        BigDecimal bigDecimal = new BigDecimal("100.001");
        System.out.println(bigDecimal.toString());
    }

}
