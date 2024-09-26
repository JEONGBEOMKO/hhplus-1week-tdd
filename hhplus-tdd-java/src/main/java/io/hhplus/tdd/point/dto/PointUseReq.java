package io.hhplus.tdd.point.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PointUseReq {
    private long amount;

    public PointUseCommand toCommand(final long userId) {
        return PointUseCommand.of(userId, amount);
    }

}
