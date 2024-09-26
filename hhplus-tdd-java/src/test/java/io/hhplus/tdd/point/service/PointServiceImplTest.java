package io.hhplus.tdd.point.service;

import io.hhplus.tdd.point.domain.model.entity.UserPoint;
import io.hhplus.tdd.point.repository.PointHistoryRepository;
import io.hhplus.tdd.point.repository.UserPointRepository;
import org.apache.catalina.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //Mockito 확장을 통해 Mockito가 테스트에서 사용할 목업 객체 주입
class PointServiceImplTest {

    @Mock
    private PointHistoryRepository pointHistoryRepository; // PointHistoryRepository 목 객체로 만듦

    @Mock
    private UserPointRepository userPointRepository; // UserPointRepository 목 객체로 만듦

    @InjectMocks
    private PointServiceImpl pointService;


}
