package br.com.borges.lucas.convidados.view.viewholder

import android.app.AlertDialog
import android.view.View
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import br.com.borges.lucas.convidados.R
import br.com.borges.lucas.convidados.service.model.GuestModel
import br.com.borges.lucas.convidados.view.listener.GuestListener

class GuestViewHolder(itemView: View, private val listener: GuestListener) :
  RecyclerView.ViewHolder(itemView) {
  //guarda as referencias dos elementos de layout

  fun bind(guest: GuestModel) {
    val textName = itemView.findViewById<TextView>(R.id.text_name)
    textName.text = guest.name

    textName.setOnClickListener {
      listener.onClick(guest.id)
    }

    textName.setOnLongClickListener {
      AlertDialog.Builder(itemView.context)
        .setTitle(R.string.remocao_convidado)
        .setMessage(R.string.deseja_remover)
        .setPositiveButton(R.string.remover) { dialog, which ->
          listener.onDelete(guest.id)
        }
        .setNeutralButton(R.string.cancelar, null)
        .show()
      true
    }
  }
}