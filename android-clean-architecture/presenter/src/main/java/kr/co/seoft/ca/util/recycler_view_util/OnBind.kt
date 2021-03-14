package kr.co.seoft.ca.util.recycler_view_util

interface OnBind<in T> {
    fun bind(item: T)
}