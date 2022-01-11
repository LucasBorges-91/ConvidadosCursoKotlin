package br.com.borges.lucas.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.borges.lucas.convidados.viewmodel.GuestFormViewModel
import br.com.borges.lucas.convidados.databinding.ActivityGuestFormBinding

class GuestFormActivity : AppCompatActivity() {
  private lateinit var binding: ActivityGuestFormBinding
  private lateinit var mViewModel: GuestFormViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityGuestFormBinding.inflate(layoutInflater)
    setContentView(binding.root)

    mViewModel = ViewModelProvider( this ).get( GuestFormViewModel::class.java )

    setListeners()
    observe()
  }

  private fun setListeners() {
    val name = binding.editTextName.text.toString()
    val presence = binding.radioPresence.isChecked
    binding.buttonSave.setOnClickListener {
      mViewModel.save( name, presence )
    }
  }

  private fun observe() {
    mViewModel.saveGuest.observe( this, Observer {
      if ( it ) {
        Toast.makeText( applicationContext, "Sucesso", Toast.LENGTH_SHORT ).show()
      } else {
        Toast.makeText( applicationContext, "Falha", Toast.LENGTH_SHORT ).show()
      }
      finish()
    })
  }
}