package br.com.borges.lucas.convidados.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import br.com.borges.lucas.convidados.R
import br.com.borges.lucas.convidados.service.model.GuestModel

class GuestViewHolder( itemView: View ) : RecyclerView.ViewHolder( itemView ) {
  //guarda as referencias dos elementos de layout

  fun bind( guest: GuestModel ) {
    val textName = itemView.findViewById<TextView>(R.id.text_name)
    textName.text = guest.name
  }
}