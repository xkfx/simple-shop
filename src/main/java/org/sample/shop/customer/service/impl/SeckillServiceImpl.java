package org.sample.shop.customer.service.impl;

import org.sample.shop.common.dao.SeckillDAO;
import org.sample.shop.common.dao.impl.SeckillDAOImpl;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.entity.OrderDetail;
import org.sample.shop.common.entity.Seckill;
import org.sample.shop.customer.service.SeckillService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService {

    private SeckillDAO seckillDAO = new SeckillDAOImpl();

    @Override
    public ServiceResult<List<Seckill>> listLatestSeckill(int start, int offset) {
        return null;
    }

    @Override
    public ServiceResult<Seckill> getSeckillWithMD5ById(Long seckillId) {
        return null;
    }

    @Override
    public ServiceResult<OrderDetail> executeSeckill(Long seckillId, Long userId, String md5) {
        return null;
    }
}
