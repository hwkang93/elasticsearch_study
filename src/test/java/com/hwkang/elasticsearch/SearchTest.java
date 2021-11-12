package com.hwkang.elasticsearch;

import com.hwkang.elasticsearch.search.data.SearchIndex;
import joptsimple.internal.Strings;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.DisplayName.class)
public class SearchTest {

    @Test
    @DisplayName("Index 값 정상적으로 생성되는지 확인")
    void searchRequestIndexTest() {

        Assertions.assertThat(SearchIndex.ALL.toString()).isEqualTo(Strings.EMPTY);
        Assertions.assertThat(SearchIndex.PICTURE.toString()).isNotEqualTo("PICTURE");
    }
}
