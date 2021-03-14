package kr.co.seoft.ca.util.recycler_view_util

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BindViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView), OnBind<T>