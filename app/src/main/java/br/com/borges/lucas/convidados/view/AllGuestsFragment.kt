package br.com.borges.lucas.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.borges.lucas.convidados.databinding.FragmentAllBinding
import br.com.borges.lucas.convidados.service.constants.GuestConstants
import br.com.borges.lucas.convidados.view.adapter.GuestAdapter
import br.com.borges.lucas.convidados.view.listener.GuestListener
import br.com.borges.lucas.convidados.viewmodel.GuestsViewModel

class AllGuestsFragment : Fragment() {

  private lateinit var guestsViewModel: GuestsViewModel
  private val mAdapter: GuestAdapter = GuestAdapter()
  private lateinit var mListener: GuestListener
  private var _binding: FragmentAllBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    guestsViewModel =
      ViewModelProvider(this).get(GuestsViewModel::class.java)

    _binding = FragmentAllBinding.inflate(inflater, container, false)
    val root: View = binding.root

    // RecyclerView
    // 1 - Obter a recycler
    //val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guests)
    val recycler = binding.recyclerAllGuests
    // 2 - Definir um layout (define o layout do recycler e por padrão a orientação vem vertical)
    recycler.layoutManager = LinearLayoutManager( context )
    // 3 - Definir um adapter (cola entre elemento do banco e adapter)
    recycler.adapter = mAdapter


    mListener = object : GuestListener{
      override fun onClick(id: Int) {
        val intent = Intent( context, GuestFormActivity::class.java )

        val bundle = Bundle()
        bundle.putInt( GuestConstants.GUESTID, id )

        intent.putExtras( bundle )
        startActivity( intent )
      }

      override fun onDelete(id: Int) {
        guestsViewModel.delete( id )
        guestsViewModel.load( GuestConstants.FILTER.EMPTY )
      }
    }

    mAdapter.attachListener( mListener )

    observer()

    return root
  }

  override fun onResume() {
    super.onResume()
    guestsViewModel.load( GuestConstants.FILTER.EMPTY )
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun observer() {
    guestsViewModel.guestList.observe( viewLifecycleOwner, Observer {
      mAdapter.updateGuest( it )
    })
  }
}