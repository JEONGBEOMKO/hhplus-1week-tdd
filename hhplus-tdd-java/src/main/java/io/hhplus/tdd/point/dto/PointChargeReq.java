package io.hhplus.tdd.point.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PointChargeReq {
    private long amount;

    public PointChargeCommand toCommand(final long userId) {
        return PointChargeCommand.of(userId, amount);
    }

}
