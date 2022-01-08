package br.com.borges.lucas.convidados.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class PresentViewModel : ViewModel() {

  private val _text = MutableLiveData<String>().apply {
    value = "This is Present Fragment"
  }
  val text: LiveData<String> = _text
}