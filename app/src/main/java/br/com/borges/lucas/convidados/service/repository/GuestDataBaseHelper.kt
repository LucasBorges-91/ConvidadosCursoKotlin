//package br.com.borges.lucas.convidados.service.repository
//
//import android.content.Context
//import android.database.sqlite.SQLiteDatabase
//import android.database.sqlite.SQLiteOpenHelper
//import br.com.borges.lucas.convidados.service.constants.DataBaseConstants
//
//class GuestDataBaseHelper(context: Context) : SQLiteOpenHelper(
//  context,
//  DATABASE_NAME,
//  null,
//  DATABASE_VERSION
//) {
//
//  override fun onCreate(db: SQLiteDatabase) {
//    db.execSQL( CREATE_TABLE_GUEST )
//  }
//
//  override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
//    TODO("Not yet implemented")
//  }
//
//  companion object {
//    private const val DATABASE_VERSION = 1
//    private const val DATABASE_NAME = "Convidados.db"
//
//    private const val CREATE_TABLE_GUEST =
//      ( "create table " + DataBaseConstants.GUEST.TABLE_NAME + " ("
//          + DataBaseConstants.GUEST.COLUMNS.ID + " integer primary key autoincrement, "
//          + DataBaseConstants.GUEST.COLUMNS.NAME + " text , "
//          + DataBaseConstants.GUEST.COLUMNS.PRESENCE + " integer);" )
//  }
//
//}