package com.bitcodetech.fragments4

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.bitcodetech.fragments4.databinding.AddContactFragmentBinding

class AddContactFragment : Fragment() {

    private lateinit var binding : AddContactFragmentBinding

    interface OnContactAddedListener {
        fun onContactAdded(
            contact: Contact
        )
    }

    var onContactAddedListener : OnContactAddedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        binding = AddContactFragmentBinding.inflate(inflater)
        setupListeners()

        /*requireActivity().actionBar!!.setDisplayHomeAsUpEnabled(true)
        requireActivity().actionBar!!.setHomeAsUpIndicator(android.R.drawable.ic_media_previous)
        */

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    private fun setupListeners() {
        binding.btnAddContact.setOnClickListener {
            val contact = Contact(
                binding.edtContactName.text.toString(),
                binding.edtContactNumber.text.toString(),
                R.mipmap.ic_launcher
            )

            onContactAddedListener?.onContactAdded(contact)
            removeCurrentFragment()
        }
    }

    private fun removeCurrentFragment() {
        parentFragmentManager.beginTransaction()
            .remove(this)
            .commit()
    }
}