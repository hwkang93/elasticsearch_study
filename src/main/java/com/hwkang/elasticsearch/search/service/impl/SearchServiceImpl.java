package com.hwkang.elasticsearch.search.service.impl;

import com.hwkang.elasticsearch.search.data.SearchIndex;
import com.hwkang.elasticsearch.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final RestHighLevelClient restHighLevelClient;

    @Override
    public List<Map<String, Object>> searchTermQueryByTitle(String title) throws IOException {
        return this.searchTermQueryByTitle(title, SearchIndex.ALL);
    }

    @Override
    public List<Map<String, Object>> searchTermQueryByTitle(String title, SearchIndex index) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
                .query(QueryBuilders.termQuery("title", title));

        return this.searchQuery(index.toString(), searchSourceBuilder);
    }

    @Override
    public List<Map<String, Object>> searchMatchPhraseQueryByTitle(String title) throws IOException {
        return this.searchMatchPhraseQueryByTitle(title, SearchIndex.ALL);
    }

    @Override
    public List<Map<String, Object>> searchMatchPhraseQueryByTitle(String title, SearchIndex index) throws IOException {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
                .query(QueryBuilders.matchPhraseQuery("title", title));

        return this.searchQuery(index.toString(), searchSourceBuilder);
    }

    private List<Map<String, Object>> searchQuery(String index, SearchSourceBuilder searchSourceBuilder) throws IOException {
        //검색 객체 생성
        SearchRequest searchRequest = new SearchRequest(index)
                .source(searchSourceBuilder);

        //검색
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);

        //검색 결과 후처리
        List<Map<String, Object>> responseDataList = new ArrayList<>();

        for(SearchHit hit : searchResponse.getHits()) {
            responseDataList.add(hit.getSourceAsMap());
        }

        return responseDataList;
    }
}
