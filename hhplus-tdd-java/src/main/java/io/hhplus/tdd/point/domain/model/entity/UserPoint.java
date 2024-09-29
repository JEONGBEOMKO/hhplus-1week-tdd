package io.hhplus.tdd.point.domain.model.entity;

import io.hhplus.tdd.point.repository.UserPointRepository;

// get 메서드 자동생성
public record UserPoint(
        long id,
        long point,
        long updateMillis
) {

    public static UserPoint empty(long id)
    {
        return new UserPoint(id, 0, System.currentTimeMillis());
    }

    // 사용자가 포인트를 충전한다.
    public UserPoint charge(final long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("충전할 포인트는 0보다 커야합니다.");
        }

        if (amount < 10000) {
            throw new IllegalArgumentException("최소 충전 금액은 10000원 입니다.");
        }

        if (this.point + amount > 1000000) {
            throw new IllegalArgumentException("최대 잔고를 초과할 수 없습니다.");
        }

        return new UserPoint(this.id, this.point + amount, System.currentTimeMillis());
    }

    // 사용자 포인트를 사용한다
    public UserPoint use(final  long amount) {
        if (amount <= 0 ) {
            throw new IllegalArgumentException("사용할 포인트는 0보다 커야 합니다.");
        }

        if (this.point < amount) {
            throw new IllegalArgumentException("잔고가 부족합니다.");
        }

        return new UserPoint(this.id, this.point-amount, System.currentTimeMillis());
    }
}
