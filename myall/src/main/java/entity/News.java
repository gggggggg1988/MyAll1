package entity;

import java.util.List;

/**
 * Created by Administrator on 2016/5/17 0017.
 */
public class News {
    private int status;
    private String error;
    private int count;
    private List<Data> data;
    public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;
    }

    public void setError(String error) {
        this.error = error;
    }
    public String getError() {
        return error;
    }

    public void setCount(int count) {
        this.count = count;
    }
    public int getCount() {
        return count;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
    public List<Data> getData() {
        return data;
    }




}
