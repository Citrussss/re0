package com.king.re0.service;


import com.king.re0.base.aotu.ExecutorManager;
import com.king.re0.dao.MemoRepository;
import com.king.re0.dao.TokenRepository;
import com.king.re0.dao.UserRepository;
import com.king.re0.entity.MemoEntity;
import com.king.re0.entity.TokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RequestMapping("/memo")
@RestController
public class MemoController {

    private final MemoRepository memoRepository;
    private final HttpServletRequest httpServletRequest;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final ExecutorManager executorManager;

    @Autowired
    public MemoController(MemoRepository memoRepository, HttpServletRequest httpServletRequest, UserRepository userRepository, TokenRepository tokenRepository, ExecutorManager executorManager) {
        this.memoRepository = memoRepository;
        this.httpServletRequest = httpServletRequest;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.executorManager = executorManager;
    }

    @GetMapping("/list")
    public Flux<List<MemoEntity>> getList() {
        return Flux.just(memoRepository.findAll()).subscribeOn(executorManager.getScheduler());
    }

//    @PostMapping("/add")
//    private InfoEntity addMemo(@RequestHeader(value="Authorization") String authorization){
////        userRepository.findById(decode(authorization).getUserId());
////        return ;
//    }

    private TokenEntity decode(String token) {
        return tokenRepository.findByToken(token).orElse(null);
    }
}
