package kr.co.seoft.ca.binding

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("onLongClick")
fun setOnLongClick(view: View, clickListener: View.OnLongClickListener?) {
    clickListener ?: return
    view.setOnLongClickListener(clickListener)
}