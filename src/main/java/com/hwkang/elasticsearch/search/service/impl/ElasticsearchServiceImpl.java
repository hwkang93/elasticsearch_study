package com.hwkang.elasticsearch.search.service.impl;

import com.hwkang.elasticsearch.search.data.ReservationIndex;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.client.ElasticsearchClient;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ElasticsearchServiceImpl {

    private final ElasticsearchOperations elasticsearchOperations;

    public String createIndex() {

        IndexCoordinates indexCoordinates = elasticsearchOperations.getIndexCoordinatesFor(ReservationIndex.class);
        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(UUID.randomUUID().toString())
                .withObject(new ReservationIndex())
                .build();

        return elasticsearchOperations.index(indexQuery, indexCoordinates);
    }

    public List<ReservationIndex> insertDocument(List<ReservationIndex> reservationIndexList) {

        return (List<ReservationIndex>) elasticsearchOperations.save(reservationIndexList);
    }

}
