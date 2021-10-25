package co.vitali.koindi.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CountryEntity::class], version = 1, exportSchema = false)
abstract class CountriesDB : RoomDatabase() {

    abstract fun countriesDao(): CountriesDao

//    companion object {
//
//        @Volatile
//        private var INSTANCE: CountriesDB? = null
//
//        fun getInstance(context: Context): CountriesDB =
//            INSTANCE ?: synchronized(this){
//                INSTANCE ?: buildDataBase(context).also { INSTANCE = it }
//            }
//
//        private fun buildDataBase(context: Context): CountriesDB =
//            Room.databaseBuilder(context.applicationContext, CountriesDB::class.java, "countries.db")
//                .build()
//    }
}