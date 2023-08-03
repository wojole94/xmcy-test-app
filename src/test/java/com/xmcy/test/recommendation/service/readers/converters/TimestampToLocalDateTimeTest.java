package com.xmcy.test.recommendation.service.readers.converters;

import com.xmcy.test.recommendation.service.exception.MalformedInputDataException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TimestampToLocalDateTimeTest {
    @Test
    void convert_whenStringContainsStringWithNumbers_returnCorrectDate() {
        String timestamp = "1641034800000";

        TimestampToLocalDateTime converter = new TimestampToLocalDateTime();
        Object result = converter.convert(timestamp);

        assertConverterResultCorrect(result);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"  ", "\t", "\n", "abcd"})
    void convert_whenStringIsEmpty_thenThrowException(String timestamp) {
        TimestampToLocalDateTime converter = new TimestampToLocalDateTime();

        assertThrows(MalformedInputDataException.class, () -> converter.convert(timestamp));
    }

    private void assertConverterResultCorrect(Object result){
        assertNotNull(result);
        assertInstanceOf(LocalDateTime.class, result);
        LocalDateTime resultCasted = (LocalDateTime) result;
        assertEquals(2022, resultCasted.getYear());
        assertEquals(1, resultCasted.getMonthValue());
        assertEquals(1, resultCasted.getDayOfMonth());
        assertEquals(11, resultCasted.getHour());
        assertEquals(0, resultCasted.getMinute());
    }
}
