package kr.co.seoft.ca.ui.contact

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.co.seoft.ca.databinding.ActivityContactBinding
import kr.co.seoft.ca.util.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class ContactActivity : AppCompatActivity() {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, ContactActivity::class.java))
        }
    }

    private val binding by lazy {
        ActivityContactBinding.inflate(layoutInflater).apply { setContentView(this.root) }
    }

    private val viewModel by viewModel<ContactViewModel>()

    private val layoutManager = LinearLayoutManager(this)

    private val adapter by lazy { ContactAdapter(viewModel.onContactListener) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.recyclerView.also {
            it.adapter = adapter
            it.layoutManager = layoutManager
            it.setHasFixedSize(true)
            it.addOnScrollListener(object :
                RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (layoutManager.findLastVisibleItemPosition() > adapter.itemCount - 5) {
                        viewModel.requestMoreContact()
                    }
                }
            })
        }

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.requestInitContact()
        }

        viewModel.contacts.observe(this) {
            adapter.submitList(it) {
                if (viewModel.needScrollToTop) {
                    layoutManager.scrollToPosition(0)
                    viewModel.needScrollToTop = false
                }
            }
            binding.swipeRefreshLayout.isRefreshing = false
        }

        viewModel.hideSwipeRefreshProgress.observe(this) {
            binding.swipeRefreshLayout.isRefreshing = false
        }

        viewModel.showToast.observe(this) {
            it.toast(this)
        }

        viewModel.requestInitContact()
    }

}