package org.sample.shop.customer.service.impl;

import org.sample.shop.common.dao.CartDAO;
import org.sample.shop.common.dao.impl.CartDAOImpl;
import org.sample.shop.common.db.connmanager.LocalConnectionProxy;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.entity.Cart;
import org.sample.shop.common.exception.DaoException;
import org.sample.shop.customer.service.CartService;

import java.util.List;

import static org.sample.shop.common.enums.business.BusinessCode.*;

public class CartServiceImpl implements CartService {

    private CartDAO cartDAO = new CartDAOImpl();

    @Override
    public ServiceResult<Cart> addItem(Long uid, Long itemId) {
        try {
            cartDAO.addItem(uid, itemId);
            return new ServiceResult<>(cart_addItem);
        } catch (DaoException e) {
            return new ServiceResult<>(cart_addItem_fail);
        } finally {
            LocalConnectionProxy.close();
        }
    }

    @Override
    public ServiceResult<Cart> removeItem(Long id) {
        try {
            int updateCount = cartDAO.removeItem(id);
            if (updateCount == 0) {
                return new ServiceResult<>(cart_removeItem_empty);
            }
            return new ServiceResult<>(cart_removeItem);
        } catch (DaoException e) {
            return new ServiceResult<>(cart_removeItem_fail);
        } finally {
            LocalConnectionProxy.close();
        }
    }

    @Override
    public ServiceResult<List<Cart>> listByUid(Long uid) {
        try {
            return new ServiceResult<>(cart_getByUid, cartDAO.listByUid(uid));
        } catch (DaoException e) {
            return new ServiceResult<>(cart_getByUid_fail);
        } finally {
            LocalConnectionProxy.close();
        }
    }
}
