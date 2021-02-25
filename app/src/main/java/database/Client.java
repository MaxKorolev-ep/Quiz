package database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "rank_list")

public class Client {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo (name = "name")
    private String name;
    @ColumnInfo (name = "level")
    private String level;
    @ColumnInfo (name = "time")
    private String time;

    public Client(int id, String name, String level, String time) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.time = time;
    }


    @Ignore
    public Client(String name, String level, String time) {
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
