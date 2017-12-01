package entity;

import java.util.List;

/**
 * Created by cantian on 2017/12/1.
 */

public class JuHeNewsBean {

    @Override
    public String toString() {
        return "JuHeNewsBean{" +
                "reason='" + reason + '\'' +
                ", result=" + result +
                ", error_code=" + error_code +
                '}';
    }

    private String reason;
        private Result result;
        private int error_code;

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getReason() {
            return reason;
        }

        public void setResult(Result result) {
            this.result = result;
        }

        public Result getResult() {
            return result;
        }

        public void setError_code(int error_code) {
            this.error_code = error_code;
        }

        public int getError_code() {
            return error_code;
        }

        /**
         * Copyright 2017 bejson.com
         */


        /**
         * Auto-generated: 2017-12-01 14:18:13
         *
         * @author bejson.com (i@bejson.com)
         * @website http://www.bejson.com/java2pojo/
         */
        public class Result {
            @Override
            public String toString() {
                return "Result{" +
                        "stat='" + stat + '\'' +
                        ", data=" + data +
                        '}';
            }

            private String stat;
            private List<Data> data;

            public void setStat(String stat) {
                this.stat = stat;
            }

            public String getStat() {
                return stat;
            }

            public void setData(List<Data> data) {
                this.data = data;
            }

            public List<Data> getData() {
                return data;
            }

        }


    /**
     * Auto-generated: 2017-12-01 14:18:13
     *
     * @author bejson.com (i@bejson.com)
     * @website http://www.bejson.com/java2pojo/
     */
    public class Data {
        @Override
        public String toString() {
            return "Data{" +
                    "uniquekey='" + uniquekey + '\'' +
                    ", title='" + title + '\'' +
                    ", date='" + date + '\'' +
                    ", category='" + category + '\'' +
                    ", author_name='" + author_name + '\'' +
                    ", url='" + url + '\'' +
                    ", thumbnail_pic_s='" + thumbnail_pic_s + '\'' +
                    ", thumbnail_pic_s02='" + thumbnail_pic_s02 + '\'' +
                    ", thumbnail_pic_s03='" + thumbnail_pic_s03 + '\'' +
                    '}';
        }

        private String uniquekey;
        private String title;
        private String date;
        private String category;
        private String author_name;
        private String url;
        private String thumbnail_pic_s;
        private String thumbnail_pic_s02;
        private String thumbnail_pic_s03;
        public void setUniquekey(String uniquekey) {
            this.uniquekey = uniquekey;
        }
        public String getUniquekey() {
            return uniquekey;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }

        public void setDate(String date) {
            this.date = date;
        }
        public String getDate() {
            return date;
        }

        public void setCategory(String category) {
            this.category = category;
        }
        public String getCategory() {
            return category;
        }

        public void setAuthor_name(String author_name) {
            this.author_name = author_name;
        }
        public String getAuthor_name() {
            return author_name;
        }

        public void setUrl(String url) {
            this.url = url;
        }
        public String getUrl() {
            return url;
        }

        public void setThumbnail_pic_s(String thumbnail_pic_s) {
            this.thumbnail_pic_s = thumbnail_pic_s;
        }
        public String getThumbnail_pic_s() {
            return thumbnail_pic_s;
        }

        public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
            this.thumbnail_pic_s02 = thumbnail_pic_s02;
        }
        public String getThumbnail_pic_s02() {
            return thumbnail_pic_s02;
        }

        public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
            this.thumbnail_pic_s03 = thumbnail_pic_s03;
        }
        public String getThumbnail_pic_s03() {
            return thumbnail_pic_s03;
        }

    }
    }


