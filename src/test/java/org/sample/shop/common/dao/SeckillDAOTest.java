package org.sample.shop.common.dao;

import org.junit.Test;
import org.sample.shop.common.dao.impl.SeckillDAOImpl;
import org.sample.shop.common.entity.Seckill;

import java.util.List;

public class SeckillDAOTest {

    private SeckillDAO seckillDAO = new SeckillDAOImpl();

    @Test
    public void listLatestSeckill() {
        List<Seckill> list = seckillDAO.listLatestSeckill(0, 10);
        System.out.println(list);
    }
}
