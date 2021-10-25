package co.vitali.koindi.di

import android.app.Application
import androidx.room.Room
import co.vitali.koindi.db.CountriesDB
import co.vitali.koindi.db.CountriesDao
import co.vitali.koindi.BuildConfig
import co.vitali.koindi.FirstViewModel
import co.vitali.koindi.SecondViewModel
import co.vitali.koindi.network.CountriesService
import co.vitali.koindi.repositories.ContentRepository
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.dsl.module
import retrofit2.Retrofit
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.converter.gson.GsonConverterFactory


val viewModelDependency = module {
    viewModel { FirstViewModel(androidApplication(), get()) }
    viewModel { SecondViewModel() }
}


/**
 * Network dependency module.
 * Provides Retrofit dependency with OkHttp logger.
 */
val networkDependency = module {

    single {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl(BuildConfig.BASE_URL).build()
    }

    single {
        get<Retrofit>().create(CountriesService::class.java)
    }
}

val dataBaseDependency = module {
    fun provideDataBase(application: Application): CountriesDB {
        return Room.databaseBuilder(application, CountriesDB::class.java, "countries.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideDao(dataBase: CountriesDB): CountriesDao {
        return dataBase.countriesDao()
    }

    single { provideDataBase(androidApplication()) }
    single { provideDao(get()) }
}

val contentDependency = module {
    // single instance of HelloRepository
    single<ContentRepository> { ContentRepository(get(), get()) }
}

val appComponent = listOf(
    dataBaseDependency,
    networkDependency,
    contentDependency,
    viewModelDependency
)