package org.sample.shop.customer.service;

import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.entity.OrderDetail;
import org.sample.shop.common.entity.Seckill;

import java.util.List;

public interface SeckillService {
    /**
     * 列出最新创建的秒杀活动
     */
    ServiceResult<List<Seckill>> listLatestSeckill(int start, int offset);

    /**
     * 获取秒杀地址
     */
    ServiceResult<Seckill> getSeckillWithMD5ById(Long seckillId);

    /**
     * 这个MD5实际上是然并卵的，但是聊胜于无。。
     * 实际生产情况需要有人机验证措施。
     */
    ServiceResult<OrderDetail> executeSeckill(Long seckillId, Long userId, String md5);
}
