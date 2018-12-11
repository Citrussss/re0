package com.king.re0.service;


import com.king.re0.base.entity.InfoEntity;
import com.king.re0.base.result.Result;
import com.king.re0.dao.MemoRepository;
import com.king.re0.dao.TokenRepository;
import com.king.re0.dao.UserRepository;
import com.king.re0.entity.MemoEntity;
import com.king.re0.entity.TokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static com.king.re0.base.result.ResultCode.SUCCESS;

@RequestMapping("/memo")
@RestController
public class MemoController {

    private final MemoRepository memoRepository;
    private final HttpServletRequest httpServletRequest;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Autowired
    public MemoController(MemoRepository memoRepository, HttpServletRequest httpServletRequest, UserRepository userRepository, TokenRepository tokenRepository) {
        this.memoRepository = memoRepository;
        this.httpServletRequest = httpServletRequest;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
    }

    @GetMapping("/list")
    private InfoEntity<List<MemoEntity>> getList(){
        return Result.<List<MemoEntity>>builder().code(SUCCESS).data(memoRepository.findAll()).build();
    }
//    @PostMapping("/add")
//    private InfoEntity addMemo(@RequestHeader(value="Authorization") String authorization){
////        userRepository.findById(decode(authorization).getUserId());
////        return ;
//    }

    public TokenEntity decode(String token) {
        Optional<TokenEntity> repository = tokenRepository.findByToken(token);
        return repository.orElse(null);
    }
}
