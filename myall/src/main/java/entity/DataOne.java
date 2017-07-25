package entity;

/**
 * Created by Administrator on 2017/5/8 0008.
 */

public class DataOne {
    private String news_id;
    private String title;
    private String top_image;
    private String text_image0;
    private String text_image1;
    private String source;
    private String content;

    public DataOne(String title) {
        this.news_id = "23";
        this.title = title;
        this.top_image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498289754907&di=7a0b85443630406252a7d827bc5ee375&imgtype=0&src=http%3A%2F%2Fwww.zhlzw.com%2FUploadFiles%2FArticle_UploadFiles%2F201204%2F20120412123914329.jpg";
        this.text_image1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498289754904&di=959461e803a7ee572f76b307847be12a&imgtype=0&src=http%3A%2F%2Fwww.zhlzw.com%2FUploadFiles%2FArticle_UploadFiles%2F201204%2F20120412123915795.jpg";

        this.text_image0 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498289754899&di=7ddb2ec1fa09d956e87e32a15eca84d6&imgtype=0&src=http%3A%2F%2Fwww.zhlzw.com%2FUploadFiles%2FArticle_UploadFiles%2F201210%2F2012102913350569.jpg";
        this.source = "新华社";
        this.content = "12312312312323453454565756";
        this.digest = "qwer";
        this.reply_count = "12";
        this.editTime = "12:23";
    }

    private String digest;
    private String reply_count;
    private String editTime;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getNews_id() {
        return news_id;
    }

    public void setNews_id(String news_id) {
        this.news_id = news_id;
    }

    public String getReply_count() {
        return reply_count;
    }

    public void setReply_count(String reply_count) {
        this.reply_count = reply_count;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getText_image0() {
        return text_image0;
    }

    public void setText_image0(String text_image0) {
        this.text_image0 = text_image0;
    }

    public String getText_image1() {
        return text_image1;
    }

    public void setText_image1(String text_image1) {
        this.text_image1 = text_image1;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTop_image() {
        return top_image;
    }

    public void setTop_image(String top_image) {
        this.top_image = top_image;
    }
}
