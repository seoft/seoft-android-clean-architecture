package kr.co.seoft.ca.ui.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.co.seoft.ca.databinding.ActivityLoginBinding
import kr.co.seoft.ca.ui.contact.ContactActivity
import kr.co.seoft.ca.util.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLoginBinding.inflate(layoutInflater).apply { setContentView(this.root) }
    }

    private val viewModel by viewModel<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        initObserver()
    }

    private fun initObserver() {
        viewModel.loginComplete.observe(this) {
            if (it) {
                "Complete to login".toast(this)
                ContactActivity.start(this)
            } else "Fail to login".toast(this)
        }
    }

}