package com.bwei.newhan.home.model;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Iterator;

public class HttpUtist {

    public static <T> void loadDataFromServer(String url, final Class<T> clazz, final CallbackNewsData callbackData) {
        x.http().get(new RequestParams(url), new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                ArrayList<T> newsContents = new ArrayList<>();
                try {
                    JSONObject jsonbject = new JSONObject(result);
                    Iterator<String> keys = jsonbject.keys();
                    while (keys.hasNext()){
                        String next = keys.next();
                        JSONArray jsonArray = jsonbject.optJSONArray(next);
                        for (int i = 0; i <jsonArray.length() ; i++) {

                            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
                            T newContent = gson.fromJson(jsonObject1.toString(), clazz);
                            newsContents.add(newContent);

                        }



                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }
                callbackData.success(newsContents);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });


    }

}