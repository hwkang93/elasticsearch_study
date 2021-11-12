package com.hwkang.elasticsearch.search.service;

import com.hwkang.elasticsearch.search.data.SearchIndex;

import java.io.IOException;
import java.util.Map;

public interface DocumentService {

    void postDocument(SearchIndex index, String id, Map<String, Object> body) throws IOException;

    Map<String, Object> getDocument(SearchIndex index, String id) throws IOException;

    void putDocument(SearchIndex index, String id, Map<String, Object> body) throws IOException;

    void deleteDocument(SearchIndex index, String id) throws IOException;

}
