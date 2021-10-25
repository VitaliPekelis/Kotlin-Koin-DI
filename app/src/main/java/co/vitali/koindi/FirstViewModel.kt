package co.vitali.koindi

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import co.vitali.koindi.repositories.ContentRepository
import org.koin.core.component.KoinComponent

class FirstViewModel(androidApplication: Application, val repo : ContentRepository) : AndroidViewModel(androidApplication),
    KoinComponent {



}
