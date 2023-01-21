package com.rpx.bsmv2.domain;

public interface IResourceConvert<T, S> {

    T convertFrom();

    S convertTo(final T obj);

    T convertToUpdate(T obj);
}
