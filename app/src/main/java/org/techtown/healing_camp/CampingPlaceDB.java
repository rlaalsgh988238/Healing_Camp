package org.techtown.healing_camp;

import static androidx.room.OnConflictStrategy.REPLACE;

import android.content.Context;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Entity;
import androidx.room.Insert;
import androidx.room.PrimaryKey;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;

@Entity(tableName = "Camping_Place")
class CampingPlaceDB {
    @ColumnInfo(name = "title") public String title;
    @ColumnInfo(name = "nameCampingPlace") public String campingPlaceName;
    @ColumnInfo(name = "InfoCampingPlace") public String InfoCampingPlace;
    @ColumnInfo(name = "urlCampingPlace") public String urlCampingPlace;
    @ColumnInfo(name = "whereCampingPlace") public String whereCampingPlace;
    @ColumnInfo(name = "elseCampingPlace") public String elseCampingPlace;

    @PrimaryKey(autoGenerate = true) public int index;
}

@Dao
interface CampingPlaceDBDao{
    @Insert(onConflict = REPLACE) void campingPlaceInsert(CampingPlaceDBDao campingPlaceDB);
    @Delete void campingPlaceDelete(CampingPlaceDBDao campingPlaceDB);
    @Update void campingPlaceUpdate(CampingPlaceDBDao campingPlaceDB);
}

@Database(entities = {CampingPlaceDB.class}, version = 1)
abstract class CampingDatabase extends RoomDatabase{
    public abstract CampingPlaceDBDao campingPlaceDBDao();
    public static CampingDatabase INSTANCE;
    public static CampingDatabase getDBInstance(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), CampingDatabase.class, "DB_NAME").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }
}