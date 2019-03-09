package me.khol.koin.screens.main

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import me.khol.koin.base.BaseActivity
import org.koin.androidx.viewmodel.ext.viewModel
import org.koin.core.parameter.parametersOf

class MainActivity : BaseActivity() {

    companion object {

        const val KEY_ID = "id"
    }

    private val viewModel: MainViewModel by viewModel { parametersOf(intent.getIntExtra(KEY_ID, 0)) }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        Toast.makeText(this, "Loaded ${viewModel.id}", Toast.LENGTH_SHORT).show()
    }
}