package http;

import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cantian on 2018/1/18.
 */

public class ARetrofitRequest {
    private Retrofit m_builder = new Retrofit.Builder().baseUrl(RetrofitGlobleConfig.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
            .build();
    public ARetrofitRequest() {

    }

    public <T> T create(Class<T > cls) {
        return m_builder.create(cls);
    }

}
