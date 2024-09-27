package io.hhplus.tdd.point.service;

import io.hhplus.tdd.point.domain.model.entity.PointHistory;
import io.hhplus.tdd.point.domain.model.entity.UserPoint;
import io.hhplus.tdd.point.domain.vo.TransactionType;
import io.hhplus.tdd.point.dto.PointChargeCommand;
import io.hhplus.tdd.point.dto.PointUseCommand;
import io.hhplus.tdd.point.repository.PointHistoryRepository;
import io.hhplus.tdd.point.repository.UserPointRepository;
import org.apache.catalina.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) //Mockito 확장을 통해 Mockito가 테스트에서 사용할 목업 객체 주입
class PointServiceImplTest {

    @Mock
    private PointHistoryRepository pointHistoryRepository; // PointHistoryRepository 목 객체로 만듦

    @Mock
    private UserPointRepository userPointRepository; // UserPointRepository 목 객체로 만듦

    @InjectMocks
    private PointServiceImpl pointService;


    @Test
    @DisplayName("사용자의 포인트 조회-성공케이스")
    void invalidUserId(){
        //given
        long userId = 1L;
        long currentAmount = 1000L;
        UserPoint userPoint = new UserPoint(userId, currentAmount, System.currentTimeMillis());

        //when
        when(userPointRepository.findById(userId)).thenReturn(userPoint);
        //then
        UserPoint result = pointService.getUserPoint(userId);
    }

    @Test
    @DisplayName("사용자의 포인트 이용내역 조회-성공케이스")
    //given
    void SelectUserPointHistory() {
        long currentUserId = 1L;
        long currentAmount = 1000L;
        UserPoint userPoint = new UserPoint(currentUserId, currentAmount, System.currentTimeMillis());
        List<PointHistory> pointHistoryList = new ArrayList<>();
        pointHistoryList.add(new PointHistory(1L, currentUserId, currentAmount, TransactionType.CHARGE, System.currentTimeMillis()));
    //when
        when(userPointRepository.findById(eq(currentUserId))).thenReturn(userPoint);
        when(pointHistoryRepository.selectAllByUserId(eq(currentUserId))).thenReturn(pointHistoryList);

    //then
    List<PointHistory> result = pointService.getUserPointHistory(currentUserId);
    }


    @Test
    @DisplayName("사용자는 포인트를 충전할 수 있다.")
    void  포인트충전_성공케이스(){
        //given
        long userId = 1L;
        long currentAmount = 10000L;
        long chargeAmount = 25000L;
        UserPoint userPoint = new UserPoint(userId, currentAmount, System.currentTimeMillis());
        when(userPointRepository.findById(1L)).thenReturn(userPoint);
        //when
        UserPoint chargedPoint = pointService.charge(PointChargeCommand.of(userId, chargeAmount));

        //then
        assertNotNull(chargedPoint);
        assertEquals(currentAmount + chargeAmount, chargedPoint.point());
        verify(userPointRepository).save(chargedPoint);
        //verify(userPointRepository, times(1)).findById(userId);
        verify(pointHistoryRepository).insert(userId, chargeAmount, TransactionType.CHARGE, chargedPoint.updateMillis());
    }

    @Test
    @DisplayName("유효하지 않은 금액포인트 충전시 예외발생한다.")
    void throwException_WhenInvalidAmount(){
    //given
    long userID = 1L;
    long chargeAmount = 0L;
    PointChargeCommand command = PointChargeCommand.of(userID, chargeAmount);
    UserPoint userPoint = mock(UserPoint.class);

    //when 유저가 존재한다고 가정
    when(userPointRepository.findById(userID)).thenReturn(userPoint);
    doThrow(new IllegalArgumentException("최소 충전 금액은 10000원입니다."))
            .when(userPoint).charge(chargeAmount);

    //then
    final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> pointService.charge(command));
    assertEquals("최소 충전 금액은 10000원입니다.", exception.getMessage());
    verify(userPointRepository).findById(userID);
    verify(userPoint).charge(chargeAmount);
    verifyNoMoreInteractions(userPointRepository, pointHistoryRepository);
    }

    @Test
    @DisplayName("사용할 포인트가 0이하일 때 예외케이스")
    void 사용할포인트_0이하일때_예외케이스(){
        //Given
        long userID = 1L;
        long useAmount = -500L;
        UserPoint userPoint = mock(UserPoint.class);
        given(userPointRepository.findById(anyLong())).willReturn(userPoint);
        doThrow(new IllegalArgumentException("사용할 포인트는 0보다 커야 합니다."))
                .when(userPoint).use(anyLong());
        PointUseCommand command = PointUseCommand.of(userID, useAmount);

        //When&Then
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> pointService.use(command));
        assertEquals("사용할 포인트는 0보다 커야 합니다.", exception.getMessage());
        verify(userPoint).use(useAmount);
    }

}
