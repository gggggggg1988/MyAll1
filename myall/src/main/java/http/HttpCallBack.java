package http;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2018/1/6 0006.
 */

public abstract class HttpCallBack<Result> implements CallBack {
    @Override
    public void onSuccess(String data) {
        Gson gson = new Gson();
        Class<?> clz = getClassInfo(this);
        Result result = (Result) gson.fromJson(data, clz);
        onSuccess(result);
    }

    /**
     * 此处很妙，可以巧妙的让接口的方法 改道   以后多用，，抽象方法可以让接口的固定方法改道  修改
     * @param result
     */
    public abstract void onSuccess(Result result);

    public static Class<?> getClassInfo(Object obj){
        //type是java里面所有类型的公共高级接口，他包括原始类型和参数化类型(泛型)
        Type genericType = obj.getClass().getGenericSuperclass();
        //ParameterizedType 参数化类型，就是泛型
        //getActualTypeArguments获取参数化类型的数组 可能有多可泛型
        Type[] actualTypeArguments = ((ParameterizedType) genericType).getActualTypeArguments();
        return (Class<?>) actualTypeArguments[0];

    }
}
