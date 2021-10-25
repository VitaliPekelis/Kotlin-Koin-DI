package co.vitali.koindi.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCountry(country: CountryEntity) :Long

    @Query("SELECT * FROM countries")
    suspend fun getCountries(): List<CountryEntity>?
}