package io.hhplus.tdd.point.service;

import io.hhplus.tdd.point.aggregate.entity.PointHistory;
import io.hhplus.tdd.point.aggregate.entity.UserPoint;
import io.hhplus.tdd.point.repository.PointHistoryRepository;
import io.hhplus.tdd.point.repository.UserPointRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// 의존성 주입
@RequiredArgsConstructor
public class PointServiceImpl implements PointService {
    private final UserPointRepository userPointRepository;
    private final PointHistoryRepository pointHistoryRepository;


    @Override
    public UserPoint findUserPointByUserId(long userId) {
        return this.userPointRepository.findById(userId);
    }

    @Override
    public List<PointHistory> findPointHistoriesByUserId(long userId) {
        return null;
    }

    @Override
    synchronized public UserPoint chargeUserPoint(long userId, long amount) {
        return null;
    }

    @Override
    synchronized public UserPoint useUserPoint(long userId, long amount) {
        return null;
    }
}
