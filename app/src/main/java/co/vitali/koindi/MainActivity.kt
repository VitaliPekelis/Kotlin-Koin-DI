package co.vitali.koindi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    // Lazy Inject ViewModel
    val myFirstModel: FirstViewModel by viewModel()

    val mySecondViewModel: SecondViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo = myFirstModel.repo

    }
}