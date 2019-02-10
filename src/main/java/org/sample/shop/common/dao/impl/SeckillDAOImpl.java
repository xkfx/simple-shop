package org.sample.shop.common.dao.impl;

import org.sample.shop.common.dao.SeckillDAO;
import org.sample.shop.common.db.QueryRunnerProxy;
import org.sample.shop.common.entity.Seckill;

import java.util.List;

public class SeckillDAOImpl implements SeckillDAO {
    @Override
    public List<Seckill> listLatestSeckill(int start, int offset) {
        return QueryRunnerProxy.query("seckill_listLatestSeckill", start, offset);
    }
}
