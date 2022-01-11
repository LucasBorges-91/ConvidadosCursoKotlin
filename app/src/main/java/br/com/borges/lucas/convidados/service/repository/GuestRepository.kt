package br.com.borges.lucas.convidados.service.repository

import android.content.ContentValues
import android.content.Context
import br.com.borges.lucas.convidados.service.constants.DataBaseConstants
import br.com.borges.lucas.convidados.service.model.GuestModel
import java.lang.Exception

class GuestRepository private constructor(context: Context) {

  //singleton

  private var mGuestDataBaseHelper: GuestDataBaseHelper = GuestDataBaseHelper(context)

  companion object {
    private lateinit var repository: GuestRepository

    fun getInstance(context: Context): GuestRepository {
      if (!::repository.isInitialized) {
        return GuestRepository(context)
      }
      return repository
    }
  }


  fun getAll(): List<GuestModel> {
    val list: MutableList<GuestModel> = ArrayList()
    return try {
      val db = mGuestDataBaseHelper.readableDatabase

      val projection = arrayOf(
        DataBaseConstants.GUEST.COLUMNS.ID,
        DataBaseConstants.GUEST.COLUMNS.NAME,
        DataBaseConstants.GUEST.COLUMNS.PRESENCE
      )

      val cursor = db.query(
        DataBaseConstants.GUEST.TABLE_NAME,
        projection,
        null,
        null,
        null,
        null,
        null
      )

      if ( cursor != null && cursor.count > 0 ) {
        while ( cursor.moveToNext() ) {
          val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
          val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
          val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1 )

          val guest = GuestModel( id, name, presence )
          list.add(guest)
        }
      }
      cursor.close()
      list
    } catch (exp: Exception) {
      list
    }
  }

  fun get(id: Int): GuestModel? {
    var guest: GuestModel? = null
    return try {
      val db = mGuestDataBaseHelper.readableDatabase

      //val cursor = db.rawQuery( "select * from Guest where id = $id", null )
      /** Não usamos rawQuery porque não é uma meio muito seguro */

      val projection = arrayOf(
        DataBaseConstants.GUEST.COLUMNS.NAME,
        DataBaseConstants.GUEST.COLUMNS.PRESENCE
      )

      val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
      val args = arrayOf(id.toString())

      val cursor = db.query(
        DataBaseConstants.GUEST.TABLE_NAME,
        projection,
        selection,
        args,
        null,
        null,
        null
      )

      if ( cursor != null && cursor.count > 0 ) {
        cursor.moveToFirst()
        val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
        val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1 )

        guest = GuestModel( id, name, presence )
      }
      cursor.close()
      guest
    } catch (exp: Exception) {
      guest
    }
  }

  fun getPresent(): List<GuestModel> {
    val list: MutableList<GuestModel> = ArrayList()
    return try {
      val db = mGuestDataBaseHelper.readableDatabase

      val cursor = db.rawQuery( "SELECT id, name, presence FROM Guest WHERE presence = 1", null)

      if ( cursor != null && cursor.count > 0 ) {
        while ( cursor.moveToNext() ) {
          val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
          val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
          val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1 )

          val guest = GuestModel( id, name, presence )
          list.add(guest)
        }
      }
      cursor.close()
      list
    } catch (exp: Exception) {
      list
    }
  }

  fun getAbsent(): List<GuestModel> {
    val list: MutableList<GuestModel> = ArrayList()
    return try {
      val db = mGuestDataBaseHelper.readableDatabase

      val cursor = db.rawQuery( "SELECT id, name, presence FROM Guest WHERE presence = 0", null)

      if ( cursor != null && cursor.count > 0 ) {
        while ( cursor.moveToNext() ) {
          val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
          val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
          val presence = (cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE)) == 1 )

          val guest = GuestModel( id, name, presence )
          list.add(guest)
        }
      }
      cursor.close()
      list
    } catch (exp: Exception) {
      list
    }
  }

  fun save(guest: GuestModel): Boolean {
    return try {
      val db = mGuestDataBaseHelper.writableDatabase
      val contentValues = ContentValues()
      contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
      contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)
      db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, contentValues)
      true
    } catch (exp: Exception) {
      false
    }
  }

  fun update(guest: GuestModel): Boolean {
    return try {
      val db = mGuestDataBaseHelper.writableDatabase
      val contentValues = ContentValues()
      contentValues.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
      contentValues.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, guest.presence)

      val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
      val args = arrayOf(guest.id.toString())

      db.update(DataBaseConstants.GUEST.TABLE_NAME, contentValues, selection, args)
      true
    } catch (exp: Exception) {
      false
    }
  }

  fun delete(id: Int): Boolean {
    return try {
      val db = mGuestDataBaseHelper.writableDatabase

      val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ?"
      val args = arrayOf(id.toString())

      db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
      true
    } catch (exp: Exception) {
      false
    }
  }
}