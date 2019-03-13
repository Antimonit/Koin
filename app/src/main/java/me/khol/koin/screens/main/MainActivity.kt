package me.khol.koin.screens.main

import android.os.Bundle
import android.widget.Toast
import me.khol.koin.base.BaseActivity
import me.khol.koin.di.Scopes
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.koin.standalone.StandAloneContext.getKoin

class MainActivity : BaseActivity() {

    companion object {

        const val KEY_ID = "id"
    }

    private val viewModel: MainViewModel by viewModel { parametersOf(intent.getIntExtra(KEY_ID, 0)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toast.makeText(this, "Loaded ${viewModel.id}", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        getKoin().koinContext.getScope(Scopes.SIGNED_IN).close()
    }
}