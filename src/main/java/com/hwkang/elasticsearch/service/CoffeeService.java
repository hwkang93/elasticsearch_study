package com.hwkang.elasticsearch.service;

import com.hwkang.elasticsearch.data.Coffee;

import java.io.IOException;
import java.util.List;

public interface CoffeeService {

    void addDocument(Coffee coffee) throws IOException;

    /*
        파라미터 title 이 Elasticsearch 에 저장된 문서 중 title 필드에 일치하는 단어가 있으면 Document 를 리턴
     */
    List<Coffee> searchTermQueryByTitle(String title) throws IOException;

    /*
        파라미터 title 이 Elasticsearch 에 저장된 문서 중 title 필드 전체 구문과 일치하면, Document 를 리턴
     */
    List<Coffee> searchMatchPhraseQueryByTitle(String title) throws IOException;
}
