package com.cornershop.counterstest.presentation.welcome

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.cornershop.counterstest.R
import com.cornershop.counterstest.databinding.ActivityWelcomeBinding
import com.cornershop.counterstest.presentation.common.extension.android.navigateTo
import com.cornershop.counterstest.presentation.home.HomeActivity
import kotlinx.android.synthetic.main.layout_welcome_content.view.*

class WelcomeActivity : AppCompatActivity() {

    private val binding: ActivityWelcomeBinding by lazy {
        ActivityWelcomeBinding.inflate(layoutInflater)
    }

    /** */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupActions()
    }

    /** */
    private fun setupActions() {
        binding.includeLayoutWelcomeContent.buttonStart.setOnClickListener(::onStarAction)
        binding.includeLayoutWelcomeContent.buttonStart.performClick()
    }

    /** */
    private fun onStarAction(v: View) {
        navigateTo(HomeActivity::class.java)
    }
}
