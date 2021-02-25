package database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ClientDAO {
    @Query("Select * from rank_list")
    List<Client> getClientRank();
    @Insert
    void insertClient(Client client);
    @Update
    void updateClient(Client client);
    @Delete
    void deleteClient(Client delete);
}
