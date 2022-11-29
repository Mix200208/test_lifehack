package com.example.myapplication.rv

import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.data.companylist.CompanyListItem

class ListCompanyAdapter(private val listener: (CompanyListItem)->Unit): RecyclerView.Adapter<ListCompanyAdapter.ListCompanyAdapterViewHolder>() {

    private val listCompany = mutableListOf<CompanyListItem>()
    fun setData(newData: MutableList<CompanyListItem>){
        listCompany.clear()
        listCompany.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListCompanyAdapterViewHolder {
        return ListCompanyAdapterViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.rv_item,parent,false))
    }

    override fun onBindViewHolder(holder: ListCompanyAdapterViewHolder, position: Int) {
        holder.bind(listCompany[position],listener)
    }

    override fun getItemCount(): Int {
        return listCompany.size
    }




    class ListCompanyAdapterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val id = itemView.findViewById<TextView>(R.id.companyId)!!
        private val name = itemView.findViewById<TextView>(R.id.nameCompany)!!

        fun bind(company: CompanyListItem, listener: (CompanyListItem)->Unit ){
            id.text = company.id
            name.text = company.name
            itemView.setOnClickListener {
                listener(company)
            }
        }

    }


}