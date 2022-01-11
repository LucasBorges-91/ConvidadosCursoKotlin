package br.com.borges.lucas.convidados.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.borges.lucas.convidados.R
import br.com.borges.lucas.convidados.databinding.FragmentAllBinding
import br.com.borges.lucas.convidados.view.adapter.GuestAdapter
import br.com.borges.lucas.convidados.viewmodel.AllGuestsViewModel

class AllGuestsFragment : Fragment() {

  private lateinit var allGuestsViewModel: AllGuestsViewModel
  private var _binding: FragmentAllBinding? = null

  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    allGuestsViewModel =
      ViewModelProvider(this).get(AllGuestsViewModel::class.java)

    _binding = FragmentAllBinding.inflate(inflater, container, false)
    val root: View = binding.root

    // RecyclerView
    // 1 - Obter a recycler
    //val recycler = root.findViewById<RecyclerView>(R.id.recycler_all_guests)
    val recycler = binding.recyclerAllGuests
    // 2 - Definir um layout (define o layout do recycler e por padrão a orientação vem vertical)
    recycler.layoutManager = LinearLayoutManager( context )
    // 3 - Definir um adapter (cola entre elemento do banco e adapter)
    recycler.adapter = GuestAdapter()
    return root
  }

  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}