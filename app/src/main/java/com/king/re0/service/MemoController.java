package com.king.re0.service;


import com.king.re0.base.aotu.ExecutorManager;
import com.king.re0.dao.MemoRepository;
import com.king.re0.dao.TokenRepository;
import com.king.re0.dao.UserRepository;
import com.king.re0.entity.MemoEntity;
import com.king.re0.entity.TokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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

    @PostMapping("/add")
    public Object addMemoToken(@RequestBody MemoEntity memoEntity, @RequestHeader(value = "Authorization") String authorization) {
        Optional<TokenEntity> tokenEntity = tokenRepository.findByToken(authorization);
        tokenEntity.ifPresent(
                it -> {
                    memoEntity.setUserEntity(it.getUserEntity());
                    memoRepository.save(memoEntity);
                }
        );
        return memoEntity;
    }
    @GetMapping("/findMyList")
    public Object findByToken(@RequestHeader(value = "Authorization") String authorization){
        Optional<List<MemoEntity>> memoEntities = tokenRepository.findByToken(authorization).flatMap(it -> memoRepository.findAllByUserEntity(it.getUserEntity()));
        return memoEntities.orElseGet(ArrayList::new);
    }
    @GetMapping("/findSSSS")
    public Object findSSSS(@RequestBody HashMap<String,BigDecimal> requestBody){
        Optional<List<MemoEntity>> memoEntities = memoRepository.findSSSS(requestBody.get("1"),requestBody.get("2"),requestBody.get("3"));
        return memoEntities.orElseGet(ArrayList::new);
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