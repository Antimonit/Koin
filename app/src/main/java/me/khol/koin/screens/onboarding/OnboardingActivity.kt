package me.khol.koin.screens.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.commit
import io.reactivex.rxkotlin.plusAssign
import me.khol.koin.R
import me.khol.koin.base.BaseActivity
import me.khol.koin.di.Scopes
import me.khol.koin.repository.Action
import me.khol.koin.repository.Step
import me.khol.koin.screens.main.MainActivity
import me.khol.koin.screens.onboarding.step.one.OneFragment
import me.khol.koin.screens.onboarding.step.three.ThreeFragment
import me.khol.koin.screens.onboarding.step.two.TwoFragment
import org.koin.android.ext.android.getKoin
import org.koin.android.viewmodel.ext.android.viewModel

class OnboardingActivity : BaseActivity() {

    private val viewModel: OnboardingViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getKoin().createScope(Scopes.ONBOARDING)

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
        getKoin().getScope(Scopes.ONBOARDING).close()
    }

    override fun onBackPressed() {
        // Don't pass the back press to framework.
//        super.onBackPressed()
        viewModel.stepBack()
    }
}