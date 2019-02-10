package org.sample.shop.common.dao;

import org.sample.shop.common.entity.Seckill;

import java.util.List;

public interface SeckillDAO {

    List<Seckill> listLatestSeckill(int start, int offset);
}
