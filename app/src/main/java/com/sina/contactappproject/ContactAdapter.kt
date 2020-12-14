package com.sina.contactappproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact_box_ui.view.*

class ContactAdapter(private val listContact:List<Contact>
            ,private val listener: (Contact) -> Unit) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ContactViewHolder {
        val itemView=LayoutInflater.from(parent.context).inflate(R.layout.contact_box_ui, parent, false)
        return ContactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val item=listContact[position]
        holder.itemView.tvName.text=item.Name
        holder.itemView.tvPhoneNumber.text=item.phone
        holder.itemView.tvName.setOnClickListener { listener(item) }
    }

    override fun getItemCount(): Int {
        return listContact.size
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}