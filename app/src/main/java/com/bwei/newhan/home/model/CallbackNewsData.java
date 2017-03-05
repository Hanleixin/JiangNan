package com.bwei.newhan.home.model;

import java.util.ArrayList;

public interface CallbackNewsData<T> {

    void success(ArrayList<T> newsContents);
}
