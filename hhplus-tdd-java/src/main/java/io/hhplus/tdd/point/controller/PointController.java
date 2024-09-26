package io.hhplus.tdd.point.controller;

import io.hhplus.tdd.point.domain.model.entity.PointHistory;
import io.hhplus.tdd.point.domain.model.entity.UserPoint;
import io.hhplus.tdd.point.dto.PointChargeReq;
import io.hhplus.tdd.point.dto.PointUseCommand;
import io.hhplus.tdd.point.dto.PointUseReq;
import io.hhplus.tdd.point.service.PointService;
import io.hhplus.tdd.point.service.PointServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/point")
public class PointController {

    private final PointService pointService;
    private static final Logger log = LoggerFactory.getLogger(PointController.class);

    public PointController(PointServiceImpl pointServiceImpl){
        this.pointService = pointServiceImpl;
    }

    /**
     * TODO - 특정 유저의 포인트를 조회하는 기능을 작성해주세요.
     */
    @GetMapping("{id}")
    public UserPoint point(
            @PathVariable long id
    ) {
        log.info("point 조회 요청 id : {}", id);
        return this.pointService.getUserPoint(id);
    }

    /**
     * TODO - 특정 유저의 포인트 충전/이용 내역을 조회하는 기능을 작성해주세요.
     */
    @GetMapping("{id}/histories")
    public List<PointHistory> history(
            @PathVariable long id
    ) {
        log.info("point 충전/이용 내역 조회 요청 id:{}", id);
        return this.pointService.getUserPointHistory(id);
    }

    /**
     * TODO - 특정 유저의 포인트를 충전하는 기능을 작성해주세요.
     */
    @PatchMapping("{id}/charge")
    public UserPoint charge(
            @PathVariable("id") long id,
            @RequestBody PointChargeReq request
            ) {
        //return new UserPoint(0, 0, 0);
        return pointService.charge(request.toCommand(id));

    }

    /**
     * TODO - 특정 유저의 포인트를 사용하는 기능을 작성해주세요.
     */
    @PatchMapping("{id}/use")
    public UserPoint use(
            @PathVariable long id,
            @RequestBody PointUseReq request
            ) {
        return null;
    }
}
