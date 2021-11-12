package com.hwkang.elasticsearch.search.data;

import joptsimple.internal.Strings;

public enum SearchIndex {
    PICTURE, ALL;

    @Override
    public String toString() {
        if(this == SearchIndex.ALL) {
            return Strings.EMPTY;
        }

        return super.toString().toLowerCase();
    }
}
