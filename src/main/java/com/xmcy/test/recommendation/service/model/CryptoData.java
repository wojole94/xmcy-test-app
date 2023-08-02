package com.xmcy.test.recommendation.service.model;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;
import com.xmcy.test.recommendation.service.readers.converters.TimestampToLocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CryptoData {
    @CsvCustomBindByName(converter = TimestampToLocalDateTime.class)
    private LocalDateTime timestamp;

    @CsvBindByName
    private String symbol;

    @CsvBindByName
    private Double price;

}
