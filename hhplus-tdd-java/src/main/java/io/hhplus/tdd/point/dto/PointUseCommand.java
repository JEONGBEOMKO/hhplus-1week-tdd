package io.hhplus.tdd.point.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PointUseCommand {
    private final long userId;
    private final long amount;

    public static PointUseCommand of(final long userId, final long amount){
        return new PointUseCommand(userId, amount);
    }
}
