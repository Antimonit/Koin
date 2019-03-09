package me.khol.koin.base

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable

/**
 * Base fragment that all other custom fragment should extend
 */
abstract class BaseFragment : Fragment() {

    protected val disposables = CompositeDisposable()

    override fun onDestroyView() {
        super.onDestroyView()
        disposables.clear()
    }
}
