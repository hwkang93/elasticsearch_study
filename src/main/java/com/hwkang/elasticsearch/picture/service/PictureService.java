package com.hwkang.elasticsearch.picture.service;

import com.hwkang.elasticsearch.picture.data.PictureDto;

import java.io.IOException;
import java.util.List;

public interface PictureService {

    List<PictureDto> searchByTitle(String title) throws IOException;


}
