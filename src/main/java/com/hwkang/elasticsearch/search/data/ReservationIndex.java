package com.hwkang.elasticsearch.search.data;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

/*
    resource/** 경로의 파일을 찾는다. 해당 파일에 사용할 분석기, 필터 등을 설정하면 인덱스 생성 시에 적용된다.
 */
//@Setting(settingPath = "elastic-setting.json")
@Data
@Document (indexName = "reservation")
@NoArgsConstructor
public class ReservationIndex {

    @Id
    @Field(type = FieldType.Long)
    private int reservationId;

    @Field(type = FieldType.Keyword)
    private String serviceId;

    @GeoPointField
    private GeoPoint location;

    @Field(type = FieldType.Date)
    private String startDate;

    public ReservationIndex(int reservationId) {
        this.reservationId = reservationId;
    }
}
