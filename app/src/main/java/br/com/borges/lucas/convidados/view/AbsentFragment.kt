package br.com.borges.lucas.convidados.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.borges.lucas.convidados.databinding.FragmentAbsentBinding
import br.com.borges.lucas.convidados.service.constants.GuestConstants
import br.com.borges.lucas.convidados.view.adapter.GuestAdapter
import br.com.borges.lucas.convidados.view.listener.GuestListener
import br.com.borges.lucas.convidados.viewmodel.GuestsViewModel

class AbsentFragment : Fragment() {

  private lateinit var mViewModel: GuestsViewModel
  private var _binding: FragmentAbsentBinding? = null
  private val mAdapter: GuestAdapter = GuestAdapter()
  private lateinit var mListener: GuestListener

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    mViewModel =
      ViewModelProvider(this).get(GuestsViewModel::class.java)

    _binding = FragmentAbsentBinding.inflate(inflater, container, false)
    val root: View = binding.root

    // RecyclerView
    // 1 - Obter a recycler
    //val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guests)
    val recycler = binding.recyclerAbsents
    // 2 - Definir um layout (define o layout do recycler e por padrão a orientação vem vertical)
    recycler.layoutManager = LinearLayoutManager( context )
    // 3 - Definir um adapter (cola entre elemento do banco e adapter)
    recycler.adapter = mAdapter


    mListener = object : GuestListener {
      override fun onClick(id: Int) {
        val intent = Intent( context, GuestFormActivity::class.java )

        val bundle = Bundle()
        bundle.putInt( GuestConstants.GUESTID, id )

        intent.putExtras( bundle )
        startActivity( intent )
      }

      override fun onDelete(id: Int) {
        mViewModel.delete( id )
        mViewModel.load( GuestConstants.FILTER.ABSENTE )
      }
    }

    mAdapter.attachListener( mListener )

    observer()

    return root
  }

  override fun onResume() {
    super.onResume()
    mViewModel.load( GuestConstants.FILTER.ABSENTE )
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }

  private fun observer() {
    mViewModel.guestList.observe( viewLifecycleOwner, Observer {
      mAdapter.updateGuest( it )
    })
  }
}