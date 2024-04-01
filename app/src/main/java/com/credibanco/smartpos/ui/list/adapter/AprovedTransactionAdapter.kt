package com.credibanco.smartpos.ui.list.adapter

import android.content.Context
import android.text.Layout.Directions
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.credibanco.smartpos.R
import com.credibanco.smartpos.model.TransactionModel
import com.credibanco.smartpos.ui.list.ApprovedTransactionFragment

class AprovedTransactionAdapter (
    var context: Context,
    var arrayTransactionModel: Array<TransactionModel>,
    var view: View,
)  : RecyclerView.Adapter<AprovedTransactionAdapter.ViewHolder?>() {

    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.item_transaction, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var textId: TextView
        var textCommerceCode: TextView
        var textTerminalCode: TextView
        var textAmount: TextView
        var textCard: TextView
        var textReceiptId: TextView
        var textRrn: TextView
        var textStatusCode: TextView
        var textStatusDescription: TextView

        init {
            textId = itemView.findViewById(R.id.textId)
            textCommerceCode = itemView.findViewById(R.id.textCommerceCode)
            textTerminalCode = itemView.findViewById(R.id.textTerminalCode)
            textAmount = itemView.findViewById(R.id.textAmount)
            textCard = itemView.findViewById(R.id.textCard)
            textReceiptId = itemView.findViewById(R.id.textReceiptId)
            textRrn = itemView.findViewById(R.id.textRrn)
            textStatusCode = itemView.findViewById(R.id.textStatusCode)
            textStatusDescription = itemView.findViewById(R.id.textStatusDescription)
            itemView.setOnClickListener(this)
        }

        override fun onClick(view: View) {

        }
    }

    override fun getItemCount(): Int {
        return arrayTransactionModel.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textId.text = arrayTransactionModel[position].id
        holder.textCommerceCode.text = arrayTransactionModel[position].commerceCode
        holder.textTerminalCode.text = arrayTransactionModel[position].terminalCode
        holder.textAmount.text = arrayTransactionModel[position].amount
        holder.textCard.text = arrayTransactionModel[position].card
        holder.textReceiptId.text = arrayTransactionModel[position].receiptId
        holder.textRrn.text = arrayTransactionModel[position].rrn
        holder.textStatusCode.text = arrayTransactionModel[position].statusCode
        holder.textStatusDescription.text = arrayTransactionModel[position].statusDescription

        holder.itemView.setOnClickListener {
            
            /*val action = ApprovedTransactionFragment.actionListApprovedTransactionToTransaction(
                arrayTransactionModel[position].id
            )
            view.findNavController().navigate(action)*/
        }
    }

}