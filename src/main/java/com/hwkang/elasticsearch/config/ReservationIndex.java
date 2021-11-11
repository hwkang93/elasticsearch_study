package com.hwkang.elasticsearch.config;

import lombok.Data;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.context.annotation.Profile;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Data
@Document(indexName = "reservation")
@Setting(settingPath = "elastic-setting.json")
public class ReservationIndex {

    @Id
    @Field(type = FieldType.Long)
    private int reservationId;

    @Field(type = FieldType.Keyword)
    private String name;

    @Field(type = FieldType.Keyword)
    private String description;

    @GeoPointField
    private GeoPoint location;

    @Field(type = FieldType.Date)
    private String date;
}
