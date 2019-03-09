package me.khol.koin.screens.onboarding.step.three

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import me.khol.koin.R
import me.khol.koin.screens.onboarding.step.one.ThreeViewModel
import org.koin.android.ext.android.inject

class ThreeFragment : Fragment() {

    private val viewModel: ThreeViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_three, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.btn_continue).setOnClickListener {
            viewModel.continueToNextStep()
        }
    }
}