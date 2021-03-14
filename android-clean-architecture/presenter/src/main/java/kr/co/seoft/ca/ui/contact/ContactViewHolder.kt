package kr.co.seoft.ca.ui.contact

import kr.co.seoft.ca.databinding.ItemConatctBinding
import kr.co.seoft.ca.domain.entity.ContactEntity
import kr.co.seoft.ca.util.recycler_view_util.BindViewHolder

class ContactViewHolder(
    private val binding: ItemConatctBinding,
    private val onContactListener: OnContactListener
) : BindViewHolder<ContactEntity>(binding.root) {

    override fun bind(item: ContactEntity) {
        binding.contact = item
        binding.onContactListener = onContactListener
    }
}