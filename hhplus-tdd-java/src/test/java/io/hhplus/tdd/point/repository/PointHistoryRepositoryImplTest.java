package io.hhplus.tdd.point.repository;

import io.hhplus.tdd.database.PointHistoryTable;
import io.hhplus.tdd.point.aggregate.entity.PointHistory;
import io.hhplus.tdd.point.aggregate.vo.TransactionType;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@WebMvcTest({PointHistoryRepositoryImpl.class, PointHistoryTable.class})
class PointHistoryRepositoryImplTest {

    @Autowired
    private PointHistoryRepository repository;
    @Autowired
    private PointHistoryTable table;

    @Test
    @DisplayName("PointHistoryTable 에 PointHistory 를 저장")
    void save() {
        //
        //Given
        long userId = 1L;
        long amount = 100L;
        TransactionType type = TransactionType.CHARGE;
        long updateMillis = System.currentTimeMillis();

        //When
        PointHistory history = repository.save(userId, amount, type, updateMillis);

        //Then
        assertNotNull(history);
        assertEquals(userId, history.id());
        assertEquals(amount, history.amount());
        assertEquals(type, history.type());
        assertEquals(updateMillis, history.updateMillis());
    }

    @Test
    void findById() {
        //
    }
}
