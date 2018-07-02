package entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/5/8 0008.
 */

public class Data implements Parcelable{
    private String news_id;
    private String title;
    private String top_image;
    private String text_image0;
    private String text_image1;
    private String source;
    private String content,test;

    public Data( String title) {
        this.news_id = "23";
        this.title = title;
        this.top_image = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498289754907&di=7a0b85443630406252a7d827bc5ee375&imgtype=0&src=http%3A%2F%2Fwww.zhlzw.com%2FUploadFiles%2FArticle_UploadFiles%2F201204%2F20120412123914329.jpg";
        this.text_image0 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498289754904&di=959461e803a7ee572f76b307847be12a&imgtype=0&src=http%3A%2F%2Fwww.zhlzw.com%2FUploadFiles%2FArticle_UploadFiles%2F201204%2F20120412123915795.jpg";

        this.text_image1 = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1498289754899&di=7ddb2ec1fa09d956e87e32a15eca84d6&imgtype=0&src=http%3A%2F%2Fwww.zhlzw.com%2FUploadFiles%2FArticle_UploadFiles%2F201210%2F2012102913350569.jpg";
        this.source = "新华社";
        this.content = "12312312312323453454565756";
        this.digest = "qwer";
        this.reply_count = "12";
        this.editTime = "12:23";
    }

    private String digest;
    private String reply_count;
    private String editTime;

    protected Data(Parcel in) {
        news_id = in.readString();
        title = in.readString();
        top_image = in.readString();
        text_image0 = in.readString();
        text_image1 = in.readString();
        source = in.readString();
        content = in.readString();
        digest = in.readString();
        reply_count = in.readString();
        editTime = in.readString();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(news_id);
        dest.writeString(title);
        dest.writeString(top_image);
        dest.writeString(text_image0);
        dest.writeString(text_image1);
        dest.writeString(source);
        dest.writeString(content);
        dest.writeString(digest);
        dest.writeString(reply_count);
        dest.writeString(editTime);
    }
}
