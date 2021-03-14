package kr.co.seoft.ca.util

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore

// https://dajkim76.tistory.com/507
class ViewModelLazy<T : ViewModel>(
    private val viewModelClass: Class<T>,
    private val storeProducer: () -> ViewModelStore,
    private val viewModelProducer: (() -> T)? = null
) : Lazy<T> {
    private var cached: T? = null
    override val value: T
        get() {
            val viewModel = cached
            return if (viewModel == null) {
                val factory = if (viewModelProducer != null) {
                    object : ViewModelProvider.Factory {
                        @Suppress("UNCHECKED_CAST")
                        override fun <T : ViewModel> create(modelClass: Class<T>): T {
                            return viewModelProducer.invoke() as T
                        }
                    }
                } else {
                    ViewModelProvider.NewInstanceFactory()
                }
                val store = storeProducer()
                ViewModelProvider(store, factory).get(viewModelClass).also {
                    cached = it
                }
            } else {
                viewModel
            }
        }

    override fun isInitialized() = cached != null
}

inline fun <reified T : ViewModel> ComponentActivity.viewModel(
    noinline viewModelProducer: (() -> T)? = null
): Lazy<T> = ViewModelLazy(
    T::class.java,
    { viewModelStore },
    viewModelProducer
)

inline fun <reified T : ViewModel> Fragment.viewModel(
    noinline viewModelProducer: (() -> T)? = null
): Lazy<T> = ViewModelLazy(
    T::class.java,
    { viewModelStore },
    viewModelProducer
)

inline fun <reified T : ViewModel> Fragment.activityViewModel(
    noinline viewModelProducer: (() -> T)? = null
): Lazy<T> = ViewModelLazy(
    T::class.java,
    { requireActivity().viewModelStore },
    viewModelProducer
)