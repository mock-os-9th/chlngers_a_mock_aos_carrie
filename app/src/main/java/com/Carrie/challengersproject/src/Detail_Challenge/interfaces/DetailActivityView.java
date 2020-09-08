package com.Carrie.challengersproject.src.Detail_Challenge.interfaces;

import com.Carrie.challengersproject.src.Detail_Challenge.models.DetailResponse;

public interface DetailActivityView {

    void DetailSuccess(DetailResponse detailResponse);

    void DetailFailure(String message);

}
