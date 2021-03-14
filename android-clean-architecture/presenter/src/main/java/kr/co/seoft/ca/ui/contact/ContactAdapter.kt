package kr.co.seoft.ca.ui.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import kr.co.seoft.ca.databinding.ItemConatctBinding
import kr.co.seoft.ca.domain.entity.ContactEntity
import kr.co.seoft.ca.util.recycler_view_util.BindViewHolder

class ContactAdapter(private val onContactListener: OnContactListener) :
    ListAdapter<ContactEntity, BindViewHolder<ContactEntity>>(
        object : DiffUtil.ItemCallback<ContactEntity>() {
            override fun areItemsTheSame(oldItem: ContactEntity, newItem: ContactEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ContactEntity,
                newItem: ContactEntity
            ): Boolean {
                return oldItem == newItem
            }

            override fun getChangePayload(oldItem: ContactEntity, newItem: ContactEntity): Any {
                return 0
            }

        }) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BindViewHolder<ContactEntity> {
        return ContactViewHolder(
            ItemConatctBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onContactListener
        )
    }

    override fun onBindViewHolder(holder: BindViewHolder<ContactEntity>, position: Int) {
        holder.bind(getItem(position))
    }
}