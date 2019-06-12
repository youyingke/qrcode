package com.hawk.qrcode.barcode.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hawk.qrcode.barcode.model.Data;
import com.hawk.qrcode.barcode.model.House;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Lenovo on 2019-06-11.
 */
@RestController
@RequestMapping("/api")
public class HouseController {

    @GetMapping("/getHouse")
    public Map<String,Object> getHouse()
    {


        House house1=new House();

        house1.setId(2);
        house1.setBuddingname("东丽");

        List<House> list=new ArrayList<>();

        list.add(house1);

        Data data=new Data();
        data.setList(list);
        data.setStatus("1000");
        Map<String,Object> map=new HashMap<>();
        map.put("data",data);
        map.put("status","1000");




        return map;
    }
    @GetMapping("/getHouseBySerach")
    public Map<String,Object> getHouse(@RequestParam("buddingname") String bname,@RequestParam("address") String adress,@RequestParam("propertyunit") String unit)
    {


        House house=new House();

        house.setId(1);
        house.setBuddingname("河东");
        house.setBuddingname(bname);
        house.setAddress(adress);
        house.setPropertyunit(unit);
        House house1=new House();

        house1.setId(2);
        house1.setBuddingname("东丽");
        house1.setBuddingname(bname+house1.getBuddingname());
        house1.setAddress(adress+house1.getBuddingname());
        house1.setPropertyunit(unit+house1.getBuddingname());
        List<House> list=new ArrayList<>();
        list.add(house);
        list.add(house1);

        Data data=new Data();
        data.setList(list);
        data.setStatus("1000");
        Map<String,Object> map=new HashMap<>();
        map.put("data",data);
        map.put("status","1000");

        return map;
    }

    @PostMapping("/updateBudding")

    public Map<String,Object> updateBudding(@RequestBody JSONObject jsonObject)
    {

        System.out.println(jsonObject.toString());
        /*使用	<dependency>    <groupId>com.alibaba</groupId><artifactId>fastjson</artifactId><version>1.2.54</version>    </dependency>
        接受前台json字符串
        */
        House house= JSON.toJavaObject(jsonObject,House.class);
        House updateHouse=new House();
        updateHouse.setId(1);
        updateHouse.setBuddingname(house.getBuddingname());
        updateHouse.setId(house.getId());
        updateHouse.setRegion(house.getRegion());
        updateHouse.setPropertyunit(house.getPropertyunit());


        House house1=new House();

        house1.setId(2);
        house1.setBuddingname("东丽");

        List<House> list=new ArrayList<>();
        list.add(updateHouse);
        list.add(house1);

        Data data=new Data();
        data.setList(list);
        data.setStatus("1000");
        Map<String,Object> map=new HashMap<>();
        map.put("data",data);
        map.put("status","1000");

       return map;
    }

    @GetMapping("/getHouseById/{id}")
    public Map<String,Object> getBudding(@PathVariable("id") Integer id)
    {


        House house=new House();

        house.setId(id);
        house.setBuddingname("河东");




        Data data=new Data();

        data.setId(house.getId());
        data.setBuddingname(house.getBuddingname());

        data.setStatus("1000");
        Map<String,Object> map=new HashMap<>();
        map.put("data",data);
        map.put("status","1000");

        return map;
    }
    @GetMapping("/deleteBudding")
    public Map<String,Object> deleteBudding(@RequestParam("ids") List<String> ids)
    {

        System.out.println(ids);
        for(int i=0;i<ids.size();i++)
        {
            System.out.println(ids.get(i));
        }

        House house=new House();

        house.setId(1);
        house.setBuddingname("河东");
        house.setRegion("已删除");
        House house1=new House();

        house1.setId(2);
        house1.setBuddingname("东丽");
        house.setRegion("已删除");
        List<House> list=new ArrayList<>();
        list.add(house);
        list.add(house1);

        Data data=new Data();
        data.setList(list);
        data.setStatus("1000");
        Map<String,Object> map=new HashMap<>();
        map.put("data",data);
        map.put("status","1000");

        return map;
    }

}
