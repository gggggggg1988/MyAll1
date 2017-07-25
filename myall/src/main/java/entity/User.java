package entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Administrator on 2016/6/5 0005.
 */
@Table(name = "students")
public class User extends Model {
    @Column(name = "name")
    public String name;
    @Column(name = "password")
    public String password;
}
