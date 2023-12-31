package com.bitcodetech.fragments4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bitcodetech.fragments4.databinding.ContactDetailsFragmentBinding

class ContactDetailsFragment : Fragment() {

    private lateinit var binding : ContactDetailsFragmentBinding
    private lateinit var contact : Contact

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ContactDetailsFragmentBinding.inflate(inflater)

        contact = requireArguments().getSerializable("contact") as Contact
        binding.contact = contact

        binding.imgContact.setImageResource(contact.imageId)
        /*binding.txtContactName.text = contact.name
        binding.txtContactNumber.text = contact.number*/

        return binding.root
    }
}