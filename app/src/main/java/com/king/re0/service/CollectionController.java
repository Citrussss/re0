package com.king.re0.service;


import com.king.re0.dao.CollectionRepository;
import com.king.re0.dao.MemoRepository;
import com.king.re0.dao.TokenRepository;
import com.king.re0.entity.CollectionEntity;
import com.king.re0.entity.TokenEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("/collect")
@RestController
public class CollectionController {

    private final CollectionRepository collectionRepository;
    private final MemoRepository memoRepository;
    private final TokenRepository tokenRepository;

    public CollectionController(CollectionRepository collectionRepository, MemoRepository memoRepository, TokenRepository tokenRepository) {
        this.collectionRepository = collectionRepository;
        this.memoRepository = memoRepository;
        this.tokenRepository = tokenRepository;
    }

    @PostMapping("add")
    public Object addByToken(@RequestHeader(value = "Authorization") String authorization, @RequestParam("memoId") Long... memoIds) {
        List<CollectionEntity> list = new ArrayList<>();
        Optional<TokenEntity> tokenEntity = tokenRepository.findByToken(authorization);
        if (tokenEntity.isPresent()) {
            for (Long memoId : memoIds) {
                memoRepository.findById(memoId).ifPresent(it -> {
                            CollectionEntity entity = new CollectionEntity();
                            entity.setUser(tokenEntity.get().getUserEntity());
                            entity.setMemo(it);
                            list.add(entity);
                        }
                );
            }
            collectionRepository.saveAll(list);
            return true;
        }
        return false;
    }

    @GetMapping("delete")
    public Object deleteByToken(@RequestParam("collectionId") Long... collectionIds) {
        for (Long collectionId : collectionIds) {
            collectionRepository.deleteById(collectionId);
        }
        return true;
    }

    @GetMapping
    public Object findAllByToken(@RequestHeader(value = "Authorization") String authorization) {
        Optional<TokenEntity> tokenEntity = tokenRepository.findByToken(authorization);
        if (tokenEntity.isPresent()) {
            Optional<List<CollectionEntity>> entity = collectionRepository.findAllByUser(tokenEntity.get().getUserEntity());
            if (entity.isPresent()) return entity.get();
        }
        return new ArrayList<>();
    }
}
