package com.sina.contactappproject.fragments

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.sina.contactappproject.Contact
import com.sina.contactappproject.ContactAdapter
import com.sina.contactappproject.R
import kotlinx.android.synthetic.main.contact_box_ui.*
import kotlinx.android.synthetic.main.contact_box_ui.view.*
import kotlinx.android.synthetic.main.fragment_contact_list.*


class ContactListFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_contact_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnAddContact.setOnClickListener {

            findNavController().navigate(R.id.action_contactListFragment_to_addContactFragment)
        }
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
//        val verticalDecoration = DividerItemDecoration(
//            recyclerView.context,
//            DividerItemDecoration.HORIZONTAL
//        )
//        val verticalDivider = ContextCompat.getDrawable(requireActivity(), R.drawable.vertical_divider)
//        verticalDecoration.setDrawable(verticalDivider!!)
//        recyclerView.addItemDecoration(verticalDecoration)

        val horizontalDecoration = DividerItemDecoration(
            recyclerView.context,
            DividerItemDecoration.VERTICAL
        )
        val horizontalDivider = ContextCompat.getDrawable(requireActivity(), R.drawable.horizontal_divider)
        horizontalDecoration.setDrawable(horizontalDivider!!)
        recyclerView.addItemDecoration(horizontalDecoration)

        if (getListOfContacts().isEmpty()) {
            recyclerView.visibility=View.GONE
            tvEmptyList.visibility=View.VISIBLE
            return
        }
        recyclerView.visibility=View.VISIBLE
        tvEmptyList.visibility=View.GONE
        val adapter = ContactAdapter(getListOfContacts()){
            Log.e(TAG, "setupRecyclerView: ${it.Name}")

        }
        recyclerView.adapter=adapter


    }



    private fun getListOfContacts(): List<Contact> {
        val listOfContacts= mutableListOf<Contact>()
        val myPref = this.activity?.getPreferences(MODE_PRIVATE)
        val mapOfSharePrefs = myPref?.all
        mapOfSharePrefs?.let {
            for (item in it) {
                listOfContacts.add(Contact(item.key,item.value.toString()))
            }
        }
        return listOfContacts
    }
}