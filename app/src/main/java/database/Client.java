package database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "rank_list")

public class Client {
    @PrimaryKey(autoGenerate = false)
    private int id;
    @ColumnInfo(name = "seconds1")
    private int seconds1;
    @ColumnInfo (name = "name")
    private String name;
    @ColumnInfo (name = "level")
    private String level;
    @ColumnInfo (name = "time")
    private String time;

    public Client(int id, int seconds1, String name, String level, String time) {
        this.id = id;
        this.seconds1=seconds1;
        this.name = name;
        this.level = level;
        this.time = time;
    }


    @Ignore
    public Client(int seconds1,String name, String level, String time) {
        this.id = id;
        this.seconds1 = seconds1;
        this.name = name;
        this.level = level;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeconds1() {
        return seconds1;
    }

    public void setSeconds1(int seconds1) {
        this.seconds1 = seconds1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
