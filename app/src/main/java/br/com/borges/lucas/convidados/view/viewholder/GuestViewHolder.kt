package br.com.borges.lucas.convidados.view.viewholder

import android.view.View
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import br.com.borges.lucas.convidados.R
import br.com.borges.lucas.convidados.service.model.GuestModel
import br.com.borges.lucas.convidados.view.listener.GuestListener

class GuestViewHolder( itemView: View, private val listener: GuestListener ) : RecyclerView.ViewHolder( itemView ) {
  //guarda as referencias dos elementos de layout

  fun bind( guest: GuestModel ) {
    val textName = itemView.findViewById<TextView>(R.id.text_name)
    textName.text = guest.name

    textName.setOnClickListener{
      listener.onClick( guest.id )
    }
  }
}