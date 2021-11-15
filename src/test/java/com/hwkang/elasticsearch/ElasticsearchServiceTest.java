package com.hwkang.elasticsearch;

import com.hwkang.elasticsearch.search.data.ReservationIndex;
import com.hwkang.elasticsearch.search.service.impl.ElasticsearchServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ElasticsearchServiceTest {

    @Autowired
    ElasticsearchServiceImpl elasticsearchService;

    @Test
    void createIndexTest() {
        String index = elasticsearchService.createIndex();
        System.out.println("*****index : " + index);
    }

    @Test
    void createDocument() {
        List<ReservationIndex> list = new ArrayList<>();
        for(int i=0; i<10; i++) {
            list.add(new ReservationIndex(i));
        }

        elasticsearchService.insertDocument(list);
    }
}
