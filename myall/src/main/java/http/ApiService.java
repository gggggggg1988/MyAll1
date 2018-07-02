package http;

import java.util.List;
import java.util.Map;

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
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * @Description: 提供的系列接口
 * @author: <a href="http://www.xiaoyaoyou1212.com">DAWI</a>
 * @date: 2016-12-30 16:42
 */
public interface ApiService<T> {
    @GET()
    Observable<T> get(@Url String url, @QueryMap Map<String, String> maps);

    @FormUrlEncoded
    @POST()
    Observable<T> post(@Url() String url, @FieldMap Map<String, String> maps);

    @FormUrlEncoded
    @POST()
    Observable<T> postForm(@Url() String url, @FieldMap Map<String, Object> maps);

    @POST()
    Observable<T> postBody(@Url() String url, @Body RequestBody requestBody);

    @HEAD()
    Observable<T> head(@Url String url, @QueryMap Map<String, String> maps);

    @OPTIONS()
    Observable<T> options(@Url String url, @QueryMap Map<String, String> maps);

    @FormUrlEncoded
    @PUT()
    Observable<T> put(@Url() String url, @FieldMap Map<String, String> maps);

    @FormUrlEncoded
    @PATCH()
    Observable<T> patch(@Url() String url, @FieldMap Map<String, String> maps);

    @FormUrlEncoded
    @DELETE()
    Observable<T> delete(@Url() String url, @FieldMap Map<String, String> maps);

    @Streaming
    @GET()
    Observable<T> downFile(@Url() String url, @QueryMap Map<String, String> maps);

    @Multipart
    @POST()
    Observable<T> uploadFiles(@Url() String url, @Part() List<MultipartBody.Part> parts);

}
