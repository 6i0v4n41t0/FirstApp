package com.giovana.firstapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.giovana.firstapp.databinding.ListItemPessoaBinding
import com.giovana.firstapp.service.model.Pessoa

class PessoaAdapter(pessoas: List<Pessoa>?, private val clickListListner: (Pessoa)-> Unit):
RecyclerView.Adapter<PessoaAdapter.PessoaViewHolder>(){

    private var pessoaList: List<Pessoa> = arrayListOf()

    class  PessoaViewHolder(private val binding: ListItemPessoaBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind (pessoa: Pessoa, clickListListner: (Pessoa)-> Unit){
                    binding.tvNome.text = pessoa.nome
                    binding.tvIdade.text = pessoa.idade.toString()
                    binding.tvFaixa.text = pessoa.faixa

                    if (pessoa.sexo == "feminino"){
                        binding.ivSexoF.visibility = View.VISIBLE
                        binding.ivSexoM.visibility = View.GONE
                    }
                    else{
                        binding.ivSexoM.visibility = View.VISIBLE
                        binding.ivSexoF.visibility = View.GONE
                    }

                    binding.root.setOnClickListener{
                        clickListListner(pessoa)
                    }

        }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PessoaViewHolder {
        val listItemPessoaBinding = ListItemPessoaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  PessoaViewHolder(listItemPessoaBinding)
    }

    override fun getItemCount(): Int {
        return pessoaList.count()
    }

    override fun onBindViewHolder(holder: PessoaViewHolder, position: Int) {
        holder.bind(pessoaList[position], clickListListner)
    }

    fun updatePessoas(list :List<Pessoa>){
        pessoaList = list
        notifyDataSetChanged()
    }
}