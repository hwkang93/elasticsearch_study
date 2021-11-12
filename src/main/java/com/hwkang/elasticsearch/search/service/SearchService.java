package com.hwkang.elasticsearch.search.service;

import com.hwkang.elasticsearch.search.data.SearchIndex;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface SearchService {

    <T> void addDocument(String index, T t) throws IOException;

    List<Map<String, Object>> searchTermQueryByTitle(String title) throws IOException;

    List<Map<String, Object>> searchTermQueryByTitle(String title, SearchIndex index) throws IOException;

    List<Map<String, Object>> searchMatchPhraseQueryByTitle(String title) throws IOException;

    List<Map<String, Object>> searchMatchPhraseQueryByTitle(String title, SearchIndex index) throws IOException;
}
