package com.hwkang.elasticsearch.search.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwkang.elasticsearch.search.data.SearchIndex;
import com.hwkang.elasticsearch.search.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final RestHighLevelClient restHighLevelClient;

    @Override
    public void postDocument(SearchIndex index, String id, Map<String, Object> body) throws IOException {
        IndexRequest indexRequest = new IndexRequest(toStringOf(index))
                .id(id)
                .source(body);

        restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String, Object> getDocument(SearchIndex index, String id) throws IOException {
        GetRequest getRequest = new GetRequest(toStringOf(index), id);

        GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.convertValue(response, Map.class);
    }

    @Override
    public void putDocument(SearchIndex index, String id, Map<String, Object> body) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest(toStringOf(index), id)
                .doc(body);

        restHighLevelClient.update(updateRequest, RequestOptions.DEFAULT);
    }

    @Override
    public void deleteDocument(SearchIndex index, String id) throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest(toStringOf(index), id);

        restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    private String toStringOf(SearchIndex index) {
        if(index == SearchIndex.ALL) {
            throw new IllegalArgumentException("SearchIndex.ALL is not allowed");
        }

        return index.toString();
    }
}
