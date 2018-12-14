package org.sample.shop.service;

import org.sample.shop.dto.ServiceResult;

public interface SimpleUserService {

    ServiceResult register(int type, String username, String password);

    ServiceResult login(String username, String password);
}
