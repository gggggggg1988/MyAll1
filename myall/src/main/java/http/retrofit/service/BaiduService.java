package http.retrofit.service;

import entity.Data;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/1/7 0007.
 */

public interface BaiduService  {



    @GET("book/search")
    Observable<Data> getData(@Query("q") String name,
                       @Query("tag") String tag, @Query("start") int start,
                       @Query("count") int count);
}
