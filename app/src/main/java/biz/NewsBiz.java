package biz;

import http.NewsProtocal;
import util.Consts;

/**
 * Created by Administrator on 2016/5/17 0017.
 */
public class NewsBiz implements Consts{
    public void getData(int page){
        String url = BASEURL+KEY+PAGE+page+ROWS;
        new NewsProtocal().loadData(url);
    }
}
