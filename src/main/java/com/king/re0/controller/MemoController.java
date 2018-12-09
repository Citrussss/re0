package com.king.re0.controller;


import com.king.re0.base.entity.InfoEntity;
import com.king.re0.base.result.Result;
import com.king.re0.entity.MemoEntity;
import com.king.re0.service.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.king.re0.base.result.ResultCode.SUCCESS;

@RequestMapping("/memo")
@RestController
public class MemoController {

    private MemoRepository memoRepository;
    @Autowired
    private HttpServletRequest httpServletRequest;

    public MemoController(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    @GetMapping("/list")
    private InfoEntity<List<MemoEntity>> getList(){
        return Result.<List<MemoEntity>>builder().code(SUCCESS).data(memoRepository.findAll()).build();
    }
}
