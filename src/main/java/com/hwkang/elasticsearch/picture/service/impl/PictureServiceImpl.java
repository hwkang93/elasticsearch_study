package com.hwkang.elasticsearch.picture.service.impl;

import com.hwkang.elasticsearch.picture.data.PictureDto;
import com.hwkang.elasticsearch.picture.service.PictureService;
import com.hwkang.elasticsearch.search.service.SearchService;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
public class PictureServiceImpl implements PictureService {

    private final SearchService searchService;

    @Override
    public List<PictureDto> searchByTitle(String title) throws IOException {
        searchService.searchMatchPhraseQueryByTitle(title);
        return null;
    }
}
