package br.com.borges.lucas.convidados.service.repository

import android.content.ContentValues
import android.content.Context
import br.com.borges.lucas.convidados.service.constants.DataBaseConstants
import br.com.borges.lucas.convidados.service.model.GuestModel
import java.lang.Exception

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

  fun save( guest: GuestModel): Boolean {
    return try {
      val db = mGuestDataBaseHelper.writableDatabase
      val contentValues = ContentValues()
      contentValues.put( DataBaseConstants.GUEST.COLUMNS.NAME, guest.name )
      contentValues.put( DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence )
      db.insert( DataBaseConstants.GUEST.TABLE_NAME, null, contentValues )
      true
    } catch ( exp: Exception ) {
      false
    }
  }

  fun update( guest: GuestModel ): Boolean {
    return try {
      val db = mGuestDataBaseHelper.writableDatabase
      val contentValues = ContentValues()
      contentValues.put( DataBaseConstants.GUEST.COLUMNS.NAME, guest.name )
      contentValues.put( DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence )

      val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
      val args = arrayOf( guest.id.toString() )

      db.update( DataBaseConstants.GUEST.TABLE_NAME, contentValues, selection, args )
      true
    } catch ( exp: Exception ) {
      false
    }
  }

  fun delete( guest: GuestModel) {
  }
}