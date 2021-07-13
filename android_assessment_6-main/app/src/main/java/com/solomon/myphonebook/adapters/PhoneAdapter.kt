package com.solomon.myphonebook.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.solomon.myphonebook.databinding.ContactListBinding
import com.solomon.myphonebook.models.Contact

class PhoneAdapter(var contacts: List<Contact>, val clicker: (Contact) -> Unit):RecyclerView.Adapter<PhoneAdapter.PhoneViewHolder>() {
    inner class PhoneViewHolder(private var binding: ContactListBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(contacts: Contact){
            binding.apply {
                txId.text = contacts.id.toString()
                txName.text = contacts.name
                txNumber.text = contacts.number
                root.setOnClickListener { clicker(contacts) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneViewHolder {
        val binding = ContactListBinding.inflate(LayoutInflater.from(parent.context))
        return PhoneViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhoneViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}