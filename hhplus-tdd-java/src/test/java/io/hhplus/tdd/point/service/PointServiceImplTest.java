package io.hhplus.tdd.point.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PointServiceImplTest {

    @Autowired
    private PointService pointService;

    @Test
    @DisplayName("포인트 충전 동시성 테스트")
    void chargePoint() throws InterruptedException {
        //
    }


    @Test
    @DisplayName("포인트 사용 동시성 테스트")
    void usePoint() throws InterruptedException {
        //
    }
}
