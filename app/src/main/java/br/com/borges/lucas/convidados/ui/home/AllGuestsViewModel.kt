package br.com.borges.lucas.convidados.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AllGuestsViewModel : ViewModel() {

  private val _text = MutableLiveData<String>().apply {
    value = "This is all guest Fragment"
  }
  val text: LiveData<String> = _text
}