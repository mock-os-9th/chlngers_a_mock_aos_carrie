package com.Carrie.challengersproject.src.Camera_Fragment.interfaces;

import com.Carrie.challengersproject.src.Camera_Fragment.models.MyCertifyResponse;

public interface MyCertifyView {

    void MyCertifySuccess(MyCertifyResponse myCertifyResponse);

    void MyCertifyFailure(String message);
}
