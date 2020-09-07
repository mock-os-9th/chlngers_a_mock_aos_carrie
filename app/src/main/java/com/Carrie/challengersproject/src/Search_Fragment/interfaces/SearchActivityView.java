package com.Carrie.challengersproject.src.Search_Fragment.interfaces;

import com.Carrie.challengersproject.src.Search_Fragment.models.SearchResponse;

public interface SearchActivityView {

    void SearchSuccess(SearchResponse searchResponse);

    void SearchFailure(SearchResponse searchResponse);
}
