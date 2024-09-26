package io.hhplus.tdd.point.repository;


import io.hhplus.tdd.point.domain.model.entity.UserPoint;

public interface UserPointRepository {
    UserPoint save(UserPoint userPoint);
    UserPoint findById(long userId);
}
