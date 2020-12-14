package com.sina.contactappproject.fragments

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.sina.contactappproject.MainActivity
import com.sina.contactappproject.R
import kotlinx.android.synthetic.main.fragment_add_contact.*

const val TAG="AddContactFragment"
class AddContactFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSaveContact.setOnClickListener {
            val name=etNameFragmentAddContact.text.toString()
            val phone = etPhoneFragmentAddContact.text.toString()

            if (name == "" || phone == "") {
                Toast.makeText(requireContext(), "نام یا شماره خالی است ", Toast.LENGTH_SHORT).show()

                //Log.e(TAG, "onViewCreated: نام یا شماره خالی ")
            } else {
                saveKeyValueDataInSharedPref(name,phone)
            }
        }
    }

    @SuppressLint("CommitPrefEdits")
    private fun saveKeyValueDataInSharedPref(name: String, phone: String) {
        val myPref:SharedPreferences?=activity?.getPreferences(MODE_PRIVATE)
        myPref?.edit()?.putString(name,phone)?.apply()
        etNameFragmentAddContact.setText("")
        etPhoneFragmentAddContact.setText("")
        Toast.makeText(getActivity(), "با موفقیت ذخیره شد", Toast.LENGTH_SHORT).show()

        //Log.d(TAG, "saveKeyValueDataInSharedPref: ${myPref?.getString(name,"")}")
    }



}