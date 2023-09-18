package com.bitcodetech.fragments4

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bitcodetech.fragments4.databinding.ContactsFragmentBinding

class ContactsFragment : Fragment() {

    private lateinit var binding: ContactsFragmentBinding
    private val contacts = ArrayList<Contact>()
    private lateinit var contactsAdapter : ContactsAdapter
    val MENU_ADD_CONTACT = 1

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ContactsFragmentBinding.inflate(inflater)

        setHasOptionsMenu(true)
        initData()
        initViews()
        initListeners()

        return binding.root
    }

    private fun initListeners() {
        contactsAdapter.onContactClickListener =
            object : ContactsAdapter.OnContactClickListener {
                override fun onContactClick(
                    contact: Contact,
                    position: Int,
                    contactsAdapter: ContactsAdapter
                ) {
                    addContactDetailsFragment(contact)
                }
            }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        menu.add(1, MENU_ADD_CONTACT, 0, "+")
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            MENU_ADD_CONTACT ->
                addAddContactFragment()
        }
        return true
    }

    private fun addAddContactFragment() {
        val addContactFragment = AddContactFragment()

        addContactFragment.onContactAddedListener =
            object : AddContactFragment.OnContactAddedListener {
                override fun onContactAdded(contact: Contact) {
                    contacts.add(contact)
                    contactsAdapter.notifyItemInserted(contacts.size - 1)
                }
            }

        parentFragmentManager.beginTransaction()
            .add(R.id.mainContainer, addContactFragment, null)
            .addToBackStack(null)
            .commit()
    }


    private fun initViews() {
        binding.recyclerContacts.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        contactsAdapter = ContactsAdapter(contacts)
        binding.recyclerContacts.adapter = contactsAdapter

    }

    private fun initData() {
        contacts.add(Contact("AA", "90909090", R.mipmap.ic_launcher))
        contacts.add(Contact("BB", "90909094", R.mipmap.ic_launcher))
        contacts.add(Contact("CC", "90909092", R.mipmap.ic_launcher))
        contacts.add(Contact("DD", "90909093", R.mipmap.ic_launcher))
    }

    private fun addContactDetailsFragment(contact : Contact) {
        val contactDetailsFragment = ContactDetailsFragment()

        val input = Bundle()
        input.putSerializable("contact", contact)
        contactDetailsFragment.arguments = input

        parentFragmentManager.beginTransaction()
            .add(R.id.mainContainer, contactDetailsFragment, null)
            .addToBackStack(null)
            .commit()

    }

}