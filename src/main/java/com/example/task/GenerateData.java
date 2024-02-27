package com.example.task;

import com.example.dao.EletricDao;
import com.example.dao.PriceDao;
import com.example.dao.StationDao;
import com.example.entity.Eletric;
import com.example.entity.Price;
import com.example.entity.Station;
import com.example.service.EletricService;
import com.example.service.UserService;
import com.example.utils.RedisCache;
import com.example.websocket.WebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class GenerateData {
    @Autowired
    private EletricDao eletricDao;
    @Autowired
    private StationDao stationDao;
    @Autowired
    private PriceDao priceDao;
    @Autowired
    private RedisCache redisCache;


//    @Scheduled(cron = "0 0 6-18 * * *")
    @Scheduled(cron = "0/5 * * * * ?")
    public void generateData() {
        List<Price> prices=priceDao.selectAll();
        for(var i:prices){
            redisCache.setCacheObject(i.getId().toString(),i.getPrice());
        }
        List<Station> ids = stationDao.selectAllId();
        List<Eletric> eletrics = new ArrayList<>();

        for (var id : ids) {
            Eletric eletric = new Eletric();
            eletric.setStationId(id.getId());
            Double mount=generateRandomFloat(10.0f, 30.0f);
            eletric.setMount(mount);

            eletric.setTime(getTime());
            Double money = mount * (Double) redisCache.getCacheObject(id.getProvinceId().toString())/1000 * 70 * id.getPieces()/50;

            eletric.setProfit(money);
            eletrics.add(eletric);

        }
        eletricDao.insertBatch(eletrics);

    }

    // 生成指定范围内的随机浮点数
    private Double generateRandomFloat(float min, float max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
    public LocalDateTime getTime() {
        int year = ThreadLocalRandom.current().nextInt(2023, 2024);
        int month = ThreadLocalRandom.current().nextInt(1, 13);
        int day = ThreadLocalRandom.current().nextInt(1, LocalDateTime.of(year, month, 1, 0, 0, 0).toLocalDate().lengthOfMonth() + 1);
        int hour = ThreadLocalRandom.current().nextInt(0, 24);
        int minute = ThreadLocalRandom.current().nextInt(0, 60);
        int second = ThreadLocalRandom.current().nextInt(0, 60);

        LocalDateTime randomDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
//        LocalDateTime randomDateTime = LocalDateTime.of(2024, 2, 27, hour, minute, second);
        // 将随机生成的日期时间设置给 Eletric 对象
        return randomDateTime;
    }
}
