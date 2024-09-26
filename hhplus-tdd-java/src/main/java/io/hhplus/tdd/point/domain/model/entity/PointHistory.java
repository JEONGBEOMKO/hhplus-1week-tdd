package io.hhplus.tdd.point.domain.model.entity;

import io.hhplus.tdd.point.domain.vo.TransactionType;
import io.hhplus.tdd.point.repository.PointHistoryRepository;

import java.util.List;

public record PointHistory(
        long id,
        long userId,
        long amount,
        TransactionType type,
        long updateMillis
) {

    public static List<PointHistory> selectAllByUserId(long userId, PointHistoryRepository pointHistoryRepository) {
        return pointHistoryRepository.selectAllByUserId(userId);
    }
}
