package br.com.borges.lucas.convidados.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import br.com.borges.lucas.convidados.viewmodel.GuestFormViewModel
import br.com.borges.lucas.convidados.databinding.ActivityGuestFormBinding
import br.com.borges.lucas.convidados.service.constants.GuestConstants

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
    loadData()
  }

  private fun loadData() {
    val bundle = intent.extras
    if ( bundle != null ) {
      val id = bundle.getInt( GuestConstants.GUESTID )
      mViewModel.load( id )
    }
  }

  private fun setListeners() {
    binding.buttonSave.setOnClickListener {
      val name = binding.editTextName.text.toString()
      val presence = binding.radioPresence.isChecked
      Toast.makeText( applicationContext, "$name", Toast.LENGTH_SHORT ).show()
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

    mViewModel.guest.observe( this, Observer {
      binding.editTextName.setText( it.name )
      if ( it.presence ) {
        binding.radioPresence.isChecked = true
      } else {
        binding.radioAbsente.isChecked = true
      }
    })
  }
}