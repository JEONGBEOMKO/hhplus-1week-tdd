package io.hhplus.tdd.point.service;

import io.hhplus.tdd.point.domain.model.entity.PointHistory;
import io.hhplus.tdd.point.domain.model.entity.UserPoint;
import io.hhplus.tdd.point.domain.vo.TransactionType;
import io.hhplus.tdd.point.dto.PointChargeCommand;
import io.hhplus.tdd.point.dto.PointChargeReq;
import io.hhplus.tdd.point.dto.PointUseCommand;
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
    public UserPoint getUserPoint(long userId) {
        if(userId < 0){
            throw new IllegalArgumentException("유효하지 않은 아이디 입니다.");
        }
        return userPointRepository.findById(userId);
    }

    @Override
    public List<PointHistory> getUserPointHistory(long userId) {
        if (userId < 0 ){
            throw new IllegalArgumentException("유효하지 않은 아이디입니다.");
        }
        return pointHistoryRepository.selectAllByUserId(userId);
    }

    @Override
    public UserPoint charge(final PointChargeCommand comdto) {
        final UserPoint userPoint = userPointRepository.findById(comdto.getUserId());
        final UserPoint chargedPoint = userPoint.charge(comdto.getAmount());
        userPointRepository.save(chargedPoint);
        pointHistoryRepository.insert(comdto.getUserId(), comdto.getAmount(), TransactionType.CHARGE, chargedPoint.updateMillis());
        return chargedPoint;
    }

    @Override
    public UserPoint use(PointUseCommand comdto) {
        final UserPoint userPoint = userPointRepository.findById(comdto.getUserId());
        final UserPoint usedPoint = userPoint.use(comdto.getAmount());
        userPointRepository.save(usedPoint);
        pointHistoryRepository.insert(comdto.getUserId(), comdto.getAmount(), TransactionType.USE, usedPoint.updateMillis());
        return usedPoint;

    }

}
