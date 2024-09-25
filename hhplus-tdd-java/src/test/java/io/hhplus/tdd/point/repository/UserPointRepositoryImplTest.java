package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.database.UserPointTable;
import io.hhplus.tdd.point.aggregate.entity.UserPoint;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@WebMvcTest({UserPointRepositoryImpl.class, UserPointTable.class})
class UserPointRepositoryImplTest {

    @Autowired
    private UserPointRepository userPointRepository;
    @Autowired
    private UserPointTable userPointTable;

    @Test
    @DisplayName("UserPointTable 에 UserPoint 저장")
    void save() {
        //
        long userId = 1L;
        long amount = 100L;

        //When
        UserPoint userPoint = userPointRepository.save(userId, amount);

        //Then
        assertNotNull(userPoint);
        assertEquals(userId, userPoint.id());
        assertEquals(amount, userPoint.point());
    }

    @Test
    void findById() {
        //
    }
}
