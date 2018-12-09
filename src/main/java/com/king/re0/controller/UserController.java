package com.king.re0.controller;


import com.king.re0.Key;
import com.king.re0.base.entity.InfoEntity;
import com.king.re0.base.error.ApiException;
import com.king.re0.base.result.Result;
import com.king.re0.entity.UserEntity;
import com.king.re0.entity.UserTokenEntity;
import com.king.re0.service.TokenRepository;
import com.king.re0.service.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.king.re0.base.result.ResultCode.SUCCESS;

@RequestMapping("/user")
@RestController
public class UserController {
    private InfoEntity<Object> infoEntity=new InfoEntity<>();
 /*   @Autowired
    private UserService userService;*/

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenRepository tokenRepository;
   /* @Autowired
    private MemoRepository memoRepository;*/

    /**
     * map :mobile and password
     * @param
     * @return
     */

    @PostMapping("/login")
    public Object login(@RequestBody UserEntity body) {
        Optional<UserEntity> userEntity = userRepository.findByMobile(body.getMobile());
        if (!userEntity.isPresent()) throw new ApiException(10, "用户不存在");
        else if(!userEntity.get().getPassword().equals(body.getPassword()))throw new ApiException(10,"用户或密码不正确");
        UserEntity entity = userEntity.get();
        UserTokenEntity encode = Encode(entity);
        Map<String,Object> data =new HashMap<>();
        data.put(Key.user,entity);
        data.put(Key.token,encode);
        return Result.<Map>builder().code(SUCCESS).data(data).build();
    }
    /**
     * map :mobile and password
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
            return Result.<UserEntity>builder().code(SUCCESS).data(userRepository.save(newUser)).build();
        }
    }
    public UserTokenEntity Encode(UserEntity entity){
        StringBuffer buffer = new StringBuffer();
        buffer.append(entity.getMobile()).append("|").append(System.currentTimeMillis());
        String token=null;
        try {
            token = Base64.getEncoder().encodeToString(buffer.toString().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }finally {
            UserTokenEntity tokenEntity = new UserTokenEntity();
            tokenEntity.setMobile(entity.getMobile());
            tokenEntity.setToken(token);
            tokenRepository.save(tokenEntity);
            return tokenEntity;
        }
    }
}
