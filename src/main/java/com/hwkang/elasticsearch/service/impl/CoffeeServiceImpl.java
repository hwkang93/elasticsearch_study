package com.hwkang.elasticsearch.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwkang.elasticsearch.data.Coffee;
import com.hwkang.elasticsearch.service.CoffeeService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CoffeeServiceImpl implements CoffeeService {

    private final RestHighLevelClient restHighLevelClient;

    @Override
    public void addDocument(Coffee coffee) throws IOException {
        IndexRequest indexRequest = new IndexRequest("cafe")
                .source("title", coffee.getTitle(),
                        "price", coffee.getPrice());

        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    @Override
    public List<Coffee> searchTermQueryByTitle(String title) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("title", title));

        return getCoffeeList(searchSourceBuilder);
    }

    @Override
    public List<Coffee> searchMatchPhraseQueryByTitle(String title) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchPhraseQuery("title", title));

        return getCoffeeList(searchSourceBuilder);
    }

    private List<Coffee> getCoffeeList(SearchSourceBuilder searchSourceBuilder) throws IOException{
        SearchRequest searchRequest = new SearchRequest("cafe");
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        List<Coffee> coffeeList = new ArrayList<>();

        for(SearchHit hit : searchResponse.getHits()) {
            ObjectMapper objectMapper = new ObjectMapper();
            Coffee coffee = objectMapper.readValue(hit.getSourceAsString(), Coffee.class);
            coffeeList.add(coffee);
        }

        return coffeeList;
    }
}
