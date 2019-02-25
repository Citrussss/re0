package com.king.re0.service;


import com.king.re0.Key;
import com.king.re0.base.aotu.ExecutorManager;
import com.king.re0.base.entity.InfoEntity;
import com.king.re0.base.error.ApiException;
import com.king.re0.dao.TokenRepository;
import com.king.re0.dao.UserRepository;
import com.king.re0.entity.TokenEntity;
import com.king.re0.entity.UserEntity;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/user")
@RestController
public class UserController {
    private InfoEntity<Object> infoEntity = new InfoEntity<>();
    /*   @Autowired
       private UserService userService;*/
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final ExecutorManager executorManager;

    @Autowired
    public UserController(UserRepository userRepository, TokenRepository tokenRepository, ExecutorManager executorManager) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.executorManager = executorManager;
    }

    @GetMapping("/tourist")
    public Object touristLogin() {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("游客");
        userEntity = userRepository.save(userEntity);
        TokenEntity encode = encode(userEntity);
        Map<String, Object> data = new HashMap<>();
        data.put(Key.token, encode);
        data.put(Key.user, userEntity);
        return data;
    }

    @GetMapping("/fluxTourist")
    public Object fluxTouristLogin() {
        return Flux.create(it -> it.next(touristLogin()));
    }

    /**
     * map :mobile and password
     *
     * @param
     * @return
     */

    @PostMapping("/login")
    public Object login(@RequestBody UserEntity body) {
        if (body.getMobile() == null || body.getPassword() == null) throw new ApiException(10, "用户或密码不得为空");
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
            body.checkRegistrationLegal();
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
    public Object findAll() {
        return userRepository.findAll();
    }

    /**
     * 修改密码
     * @param body
     * @return
     */
    @PostMapping("/password")
    public Object changePasswordByToken(@RequestBody UserEntity body) {
        Optional<UserEntity> result = userRepository.findByMobile(body.getMobile());
        if (!result.isPresent()) throw new ApiException(10, "用户不存在");
        else if (StringUtil.isNullOrEmpty(body.getPassword())) {
            throw new ApiException(10, "新的密码不能为空");
        } else {
            result.ifPresent(it -> {
                it.setPassword(body.getPassword());
                userRepository.save(it);
            });
            return result.get();
        }
    }

    @GetMapping("/findById")
    public Object findUserById(@RequestParam(name = "id") long id) {
        Optional<UserEntity> result = userRepository.findById(id);
        if(result.isPresent())return result.get();
        else throw new ApiException(10,"查找的用户不存在");
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
    private TokenEntity decode(String token) {
        Optional<TokenEntity> repository = tokenRepository.findByToken(token);
        return repository.orElse(null);
    }

    private TokenEntity encode(UserEntity userEntity) {
        Optional<TokenEntity> repository = tokenRepository.findByUserEntity(userEntity);
        if (!repository.isPresent()) {
            TokenEntity tokenEntity = new TokenEntity();
            tokenEntity.setUserEntity(userEntity);
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
