package br.com.borges.lucas.convidados.service.repository

import android.content.Context
import br.com.borges.lucas.convidados.service.model.GuestModel

class GuestRepository private constructor( context: Context ) {

  //singleton

  private var mGuestDataBaseHelper: GuestDataBaseHelper = GuestDataBaseHelper( context )

  companion object {
    private lateinit var repository: GuestRepository

    fun getInstance( context: Context ): GuestRepository {
      if ( !::repository.isInitialized ) {
        return GuestRepository( context )
      }
      return repository
    }
  }


  fun getAll(): List<GuestModel> {
    val list: MutableList<GuestModel> = ArrayList()
      return list
  }

  fun getPresent(): List<GuestModel> {
    val list: MutableList<GuestModel> = ArrayList()
    return list
  }

  fun getAbsent(): List<GuestModel> {
    val list: MutableList<GuestModel> = ArrayList()
    return list
  }

  fun save( guest: GuestModel) {
    
  }

  fun update( guest: GuestModel) {
  }

  fun delete( guest: GuestModel) {
  }
}