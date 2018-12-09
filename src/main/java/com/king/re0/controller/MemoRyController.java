package com.king.re0.controller;

import com.king.re0.Key;
import com.king.re0.service.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

//import io.reactivex.Observable;

@RequestMapping("/memo")
@RestController
public class MemoRyController {

    @Autowired
    MemoRepository memoRepository;

    /**
     *
     * @param map mobile and password
     * @return
     */
    @GetMapping("/findAll")
    public Object findAll(@RequestBody Map map){
        String name= (String) map.get(Key.token);
        return map;
    }

}
