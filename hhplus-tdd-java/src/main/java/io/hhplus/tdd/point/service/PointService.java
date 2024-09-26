package io.hhplus.tdd.point.service;


import io.hhplus.tdd.point.domain.model.entity.PointHistory;
import io.hhplus.tdd.point.domain.model.entity.UserPoint;
import io.hhplus.tdd.point.dto.PointChargeCommand;
import io.hhplus.tdd.point.dto.PointUseCommand;

import java.util.List;

public interface PointService {



    UserPoint getUserPoint(long userId);

    List<PointHistory> getUserPointHistory(long userId);

    UserPoint charge(PointChargeCommand command);

    UserPoint use(PointUseCommand command);
}
