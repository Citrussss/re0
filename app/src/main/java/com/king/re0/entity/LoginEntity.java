package com.king.re0.entity;

import com.king.re0.base.error.ApiException;
import io.netty.util.internal.StringUtil;
import lombok.Data;

@Data
public class LoginEntity {
    private Long mobile;
    private String password;
    public void checkRegistrationLegal() {
        if (StringUtil.isNullOrEmpty(getPassword())) throw new ApiException(10, "密码不能为空");
    }
}
