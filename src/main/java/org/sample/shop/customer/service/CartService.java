package org.sample.shop.customer.service;

import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.entity.Cart;

import java.util.List;

public interface CartService {

    ServiceResult<Cart> addItem(Long uid, Long itemId);

    ServiceResult<Cart> removeItem(Long id);

    ServiceResult<List<Cart>> listByUid(Long uid);
}
