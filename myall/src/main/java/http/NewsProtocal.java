package http;

import com.google.gson.Gson;

import entity.News;

/**
 * Created by Administrator on 2016/5/17 0017.
 */
public class NewsProtocal extends BaseProtocal<News> {
    @Override
    protected News parseJson(String JSON) {
        Gson gson = new Gson();
        News news = gson.fromJson(JSON, News.class);
        return news;
    }
}
