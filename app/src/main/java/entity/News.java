package entity;

import java.util.List;

/**
 * Created by Administrator on 2016/5/17 0017.
 */
public class News {
    /**
     * error_code : 0
     * reason : Succes
     * result : [{"ctime":"2015-10-21 12:56","title":"李宝荣任国管局局长、党组书记 焦焕成到龄卸任","description":"10月20日，国管局召开领导干部会议，宣布中共中央关于国管局主要领导调整的决定，李宝荣同志任国管局局长、党组书记，焦焕成同志已到任职年龄，不再担任国管局局长、党组书记职务。焦焕成同志表示，衷心拥护中央的决定。在国管局工作的30多年，是人生经","picUrl":"http://s.cimg.163.com/cnews/2015/10/21/2015102111454712c3a_550.jpg.119x83.jpg","url":"http://news.163.com/15/1021/12/B6F0FNPP00014AED.html#f=dlist"},{"ctime":"2015-10-21 11:35","title":"朱立伦暂定11月访美，台媒称接待规格或高于蔡英文","description":"国民党2016参选人朱立伦10月21日表示，访美行程暂时会订在11月中上旬，协调好后再宣布。对于是否还会有其它出访计划，朱立伦说，比较困难，可能会派代表或与对方在台湾的代表接触，包括日本、东南亚、欧美国家等，让他们了解国民党的政策。据透露，","picUrl":"http://s.cimg.163.com/cnews/2015/10/21/20151021105327df31f_550.jpg.119x83.jpg","url":"http://news.163.com/15/1021/11/B6ERQ2DM00014AED.html#f=dlist"}]
     */

    private int error_code;
    private String reason;
    /**
     * ctime : 2015-10-21 12:56
     * title : 李宝荣任国管局局长、党组书记 焦焕成到龄卸任
     * description : 10月20日，国管局召开领导干部会议，宣布中共中央关于国管局主要领导调整的决定，李宝荣同志任国管局局长、党组书记，焦焕成同志已到任职年龄，不再担任国管局局长、党组书记职务。焦焕成同志表示，衷心拥护中央的决定。在国管局工作的30多年，是人生经
     * picUrl : http://s.cimg.163.com/cnews/2015/10/21/2015102111454712c3a_550.jpg.119x83.jpg
     * url : http://news.163.com/15/1021/12/B6F0FNPP00014AED.html#f=dlist
     */

    private List<NewsBean.ResultBean> result;

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<NewsBean.ResultBean> getResult() {
        return result;
    }

    public void setResult(List<NewsBean.ResultBean> result) {
        this.result = result;
    }

}
