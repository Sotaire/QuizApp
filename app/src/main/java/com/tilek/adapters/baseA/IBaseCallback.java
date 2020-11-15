package com.tilek.adapters.baseA;

public interface IBaseCallback<T> {

    void onSuccess(T result);
    void onFailure(Exception e);

}
