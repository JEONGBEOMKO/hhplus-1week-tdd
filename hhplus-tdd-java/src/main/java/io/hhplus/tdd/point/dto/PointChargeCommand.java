package io.hhplus.tdd.point.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PointChargeCommand {
    private final long userId;
    private final long amount;

    public  static PointChargeCommand of(final long userId, final  long amount){
        return new PointChargeCommand(userId, amount);
    }
}
