package com.giovana.firstapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.giovana.firstapp.R
import com.giovana.firstapp.databinding.FragmentAllPessoasBinding
import com.giovana.firstapp.view.adapter.PessoaAdapter
import com.giovana.firstapp.viewmodel.AllPessoasViewModel

class AllPessoasFragment : Fragment() {

//Chamar a viewModel
    private  val viewModel: AllPessoasViewModel by viewModels()

    //Chamar o adapter
    private lateinit var adapter: PessoaAdapter

//Criar o binding
    private var _binding: FragmentAllPessoasBinding? = null
    private val binding: FragmentAllPessoasBinding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Configurar o binding
        _binding = FragmentAllPessoasBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Quando clicar em algum item da lista
        adapter = PessoaAdapter(viewModel.pessoalist.value){

        }

        //configura a recycler
        val recycler = binding.rvPessoas
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        //observa para add um item na lista quando for adicionado
        viewModel.pessoalist.observe(viewLifecycleOwner){
            adapter.updatePessoas(it)
        }

        //Navegar para a tela de cadastro
        binding.btnAdd.setOnClickListener{
            findNavController().navigate(R.id.pessoaFragment)
        }

        //carregar as pessoas cadastradas
        viewModel.loadPessoas()
    }


}