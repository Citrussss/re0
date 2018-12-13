package com.king.re0.service;


import com.king.re0.Key;
import com.king.re0.base.entity.InfoEntity;
import com.king.re0.base.error.ApiException;
import com.king.re0.dao.TokenRepository;
import com.king.re0.dao.UserRepository;
import com.king.re0.entity.TokenEntity;
import com.king.re0.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.Executors;

@RequestMapping("/user")
@RestController
public class UserController {
    private InfoEntity<Object> infoEntity = new InfoEntity<>();
    /*   @Autowired
       private UserService userService;*/
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final Scheduler scheduler = Schedulers.fromExecutor(Executors.newFixedThreadPool(100));

    @Autowired
    public UserController(UserRepository userRepository, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @GetMapping("/tourist")
    public Flux<Object> touristLogin(@RequestHeader(value = "Authorization") String authorization) {
        Flux<Object> defer = Flux.defer(() -> {
            UserEntity userEntity = new UserEntity();
            userEntity.setName("测试用例");
            userEntity = userRepository.save(userEntity);
            TokenEntity encode = encode(userEntity);
            Map<String, Object> data = new HashMap<>();
            data.put(Key.token, encode);
            data.put(Key.user, userEntity);
            return Flux.just(data);
        });
        return defer.subscribeOn(scheduler);
    }

    /**
     * map :mobile and password
     *
     * @param
     * @return
     */

    @PostMapping("/login")
    public Object login(@RequestBody UserEntity body) {
        Optional<UserEntity> userEntity = userRepository.findByMobile(body.getMobile());
        if (!userEntity.isPresent()) throw new ApiException(10, "用户不存在");
        else if (!userEntity.get().getPassword().equals(body.getPassword())) throw new ApiException(10, "用户或密码不正确");
        UserEntity entity = userEntity.get();
        TokenEntity encode = encode(entity);
        Map<String, Object> data = new HashMap<>();
        data.put(Key.user, entity);
        data.put(Key.token, encode);
        return data;
    }

    /**
     * map :mobile and password
     *
     * @param
     * @return
     */

    @PostMapping("/register")
    public Object register(@RequestBody UserEntity body) {
        Optional<UserEntity> result = userRepository.findByMobile(body.getMobile());
        if (result.isPresent()) throw new ApiException(10, "用户已存在");
        else {
            UserEntity newUser = new UserEntity();
            newUser.setMobile(body.getMobile());
            newUser.setPassword(body.getPassword());
            return userRepository.save(newUser);
        }
    }

    /**
     * 返回所有的用户信息
     *
     * @return
     */
    @GetMapping("/findAll")
    public Flux<List<UserEntity>> findAll() {
        return Flux.just(userRepository.findAll())
                .subscribeOn(scheduler);
    }

    /*public TokenEntity Encode(UserEntity entity){
        StringBuilder buffer = new StringBuilder();
        buffer.append(entity.getId()).append("|").append(System.currentTimeMillis());
        String token=null;
        try {
            token = Base64.getEncoder().encodeToString(buffer.toString().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }finally {
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setId(entity.getId());
            tokenEntity.setToken(token);
            tokenRepository.save(tokenEntity);
            return tokenEntity;
        }
    }*/
    public TokenEntity decode(String token) {
        Optional<TokenEntity> repository = tokenRepository.findByToken(token);
        return repository.orElse(null);
    }

    public TokenEntity encode(UserEntity userEntity) {
        Optional<TokenEntity> repository = tokenRepository.findByUserId(userEntity.getId());
        if (!repository.isPresent()) {
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUserId(userEntity.getId());
            tokenRepository.save(tokenEntity);
            return encode(userEntity);
        }
        repository.ifPresent(tokenEntity -> {
            String token = Base64.getEncoder().encodeToString((String.valueOf(userEntity.getId()) + "|" + System.currentTimeMillis()).getBytes(StandardCharsets.UTF_8));
            tokenEntity.setToken(token);
            tokenRepository.save(tokenEntity);
        });
        return repository.get();
    }
}
