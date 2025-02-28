package com.tl.user.provider.impl;

import com.tl.user.dto.UserLoginDTO;
import com.tl.user.inter.IUserMobileRPCService;
import com.tl.user.provider.service.UserMobileService;
import jakarta.annotation.Resource;
import org.apache.dubbo.config.annotation.DubboService;


@DubboService
public class UserMobileRPCService implements IUserMobileRPCService {

    @Resource
    private UserMobileService userMobileService;
    @Override
    public UserLoginDTO login(String mobile) {
        return userMobileService.login(mobile);
    }
}
