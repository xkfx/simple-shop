package org.sample.shop.common.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.sample.shop.common.dto.ServiceResult;
import org.sample.shop.common.entity.Seckill;
import org.sample.shop.customer.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillServiceTest {

    @Autowired
    private SeckillService seckillService;

    @Test
    public void executeSeckill() {
        ServiceResult<List<Seckill>> result1 = seckillService.listLatestSeckill(0, 1);
        System.out.println(result1);
        if (result1.isSuccess()) {
            List<Seckill> list = result1.getData();
            Seckill seckill = list.get(0);
            seckill = seckillService.getSeckillWithMD5ById(111L).getData();
            // seckillService.executeSeckill();
        }
    }
}
