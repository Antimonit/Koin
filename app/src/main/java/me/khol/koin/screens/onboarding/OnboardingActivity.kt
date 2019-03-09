package me.khol.koin.screens.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.commit
import io.reactivex.rxkotlin.plusAssign
import me.khol.koin.R
import me.khol.koin.base.BaseActivity
import me.khol.koin.di.Scopes
import me.khol.koin.screens.main.MainActivity
import me.khol.koin.screens.onboarding.step.one.OneFragment
import me.khol.koin.screens.onboarding.step.three.ThreeFragment
import me.khol.koin.screens.onboarding.step.two.TwoFragment
import org.koin.android.ext.android.get
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.getViewModel
import org.koin.androidx.viewmodel.ext.viewModel

class OnboardingActivity : BaseActivity() {

    // TODO: How to inject scoped ViewModel?

//    private val viewModel: OnboardingViewModel by inject()
//    private val viewModel: OnboardingViewModel by viewModel()
//    private val viewModel: OnboardingViewModel by sharedViewModel()
    private lateinit var viewModel: OnboardingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        val scope = getKoin().createScope(Scopes.ONBOARDING)
        viewModel = get(scope = scope)
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        disposables += viewModel.observeNavigation()
            .subscribe { action: Action ->
                when (action) {
                    Action.BACK -> {
                        super.onBackPressed()
                    }
                    Action.FINISH -> {
                        startActivity(Intent(this, MainActivity::class.java).apply {
                            putExtra(MainActivity.KEY_ID, 42)
                            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        })
                        finish()
                    }
                }
            }

        disposables += viewModel.observeStep()
            .subscribe { step: Step ->
                val fragment = when (step) {
                    Step.ONE -> OneFragment()
                    Step.TWO -> TwoFragment()
                    Step.THREE -> ThreeFragment()
                }
                supportFragmentManager.commit {
                    replace(R.id.contents, fragment)
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        getKoin().deleteScope(Scopes.ONBOARDING)
    }

    override fun onBackPressed() {
        // Don't pass the back press to framework.
//        super.onBackPressed()
        viewModel.stepBack()
    }
}