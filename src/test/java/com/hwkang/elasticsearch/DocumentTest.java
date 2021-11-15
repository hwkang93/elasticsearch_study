package com.hwkang.elasticsearch;

import com.hwkang.elasticsearch.search.data.SearchIndex;
import com.hwkang.elasticsearch.search.service.DocumentService;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class DocumentTest {

    private final String ID = "test";

    @Autowired
    DocumentService documentService;

    @BeforeEach
    void beforeEach() throws IOException {
        for(int i=0; i<10; i++) {
            Map<String, Object> body = new HashMap<>();

            body.put("title", String.format("TITLE_%d", i));
            body.put("description", String.format("DESCRIPTION_%d", i));

            documentService.postDocument(SearchIndex.PICTURE, ID, body);
        }
    }

    //@AfterEach
    void afterEach() throws IOException {
        documentService.deleteDocument(SearchIndex.PICTURE, ID);
    }

    @Test
    @DisplayName("document 목록 조회")
    void getDocumentList() throws IOException {
        //1.given

        //2.when
        Map<String,Object> resultMap = documentService.getDocument(SearchIndex.PICTURE, ID);

        //3.then
        resultMap.keySet().forEach(key -> {
            Object value = resultMap.get(key);
            System.out.println("key : " + key + " , value : " + value);
        });
        assertThat(resultMap.size()).isEqualTo(10);
    }
/*
    @Test
    @DisplayName("document 수정")
    void putDocument() {
        //1.given
        documentService.putDocument();
        //2.when

        //3.then
    }
*/

}
