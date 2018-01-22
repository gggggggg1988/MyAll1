package http;

import java.util.List;
import java.util.Map;

import entity.Item;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Multipart;
import retrofit2.http.OPTIONS;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by cantian on 2018/1/18.
 */

public interface DataService  {
//    @GET()
//    Observable<Item> get(@Url String url, @QueryMap Map<String, String> maps);

    @GET()
    Observable<Item> get(@Url String url, @Query("data") String data);

    @FormUrlEncoded
    @POST()
    Observable<Item> post(@Url() String url, @FieldMap Map<String, String> maps);

    @FormUrlEncoded
    @POST()
    Observable<Item> postForm(@Url() String url, @FieldMap Map<String, Object> maps);

    @POST()
    Observable<Item> postBody(@Url() String url, @Body RequestBody requestBody);

    @HEAD()
    Observable<Item> head(@Url String url, @QueryMap Map<String, String> maps);

    @OPTIONS()
    Observable<Item> options(@Url String url, @QueryMap Map<String, String> maps);

    @FormUrlEncoded
    @PUT()
    Observable<Item> put(@Url() String url, @FieldMap Map<String, String> maps);

    @FormUrlEncoded
    @PATCH()
    Observable<Item> patch(@Url() String url, @FieldMap Map<String, String> maps);

    @FormUrlEncoded
    @DELETE()
    Observable<Item> delete(@Url() String url, @FieldMap Map<String, String> maps);

    @Streaming
    @GET()
    Observable<Item> downFile(@Url() String url, @QueryMap Map<String, String> maps);

    @Multipart
    @POST()
    Observable<Item> uploadFiles(@Url() String url, @Part() List<MultipartBody.Part> parts);

}
