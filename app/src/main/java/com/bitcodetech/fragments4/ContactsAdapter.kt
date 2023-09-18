package com.bitcodetech.fragments4

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bitcodetech.fragments4.databinding.ContactViewBinding

class ContactsAdapter(
    private val contacts : ArrayList<Contact>
) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    interface OnContactClickListener {
        fun onContactClick(
            contact: Contact,
            position: Int,
            contactsAdapter: ContactsAdapter
        )
    }

    var onContactClickListener : OnContactClickListener? = null


    inner class ContactViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val binding : ContactViewBinding
        init {
            binding = ContactViewBinding.bind(view)

            binding.root.setOnClickListener {
                onContactClickListener?.onContactClick(
                    contacts[adapterPosition],
                    adapterPosition,
                    this@ContactsAdapter
                )
            }
        }
    }


    override fun getItemCount() = contacts.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.contact_view, null)
        )
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.binding.imgContact.setImageResource(contacts[position].imageId)
        holder.binding.txtContactName.text = contacts[position].name
        holder.binding.txtContactNumber.text = contacts[position].number
    }
}