package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.domain.model.entity.UserPoint;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Objects;

@Repository
@RequiredArgsConstructor
public class UserPointRepositoryImpl implements UserPointRepository{
    //
    private final UserPointTable userPointTable;

    /*사용자 포인트 저장*/
    @Override
    public UserPoint save(UserPoint userPoint) {
        if(Objects.isNull(userPoint)){
            throw new IllegalArgumentException("사용자가 없습니다.");
        }
        return userPointTable.insertOrUpdate(userPoint.id(), userPoint.point());
    }

    /* 사용자 포인트 조회 */
    @Override
    public UserPoint findById(long userId) {
        //
        return this.userPointTable.selectById(userId);
    }
}
