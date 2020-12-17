package com.example.myhogwartsdairy.mvvmtodo.ui.tasks

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myhogwartsdairy.mvvmtodo.util.onQueryTextChanged
import com.example.myhogwartsdairy.R
import com.example.myhogwartsdairy.databinding.FragmentTaskBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TasksFragment : Fragment(R.layout.fragment_task) {
    private  val viewModel: TasksViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentTaskBinding.bind(view)
        val tasksAdapter =TasksAdapter()
        binding.apply {
            recyclerViewTask.apply {
                adapter = tasksAdapter
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)

            }
        }
        viewModel.tasks.observe(viewLifecycleOwner){
            tasksAdapter.submitList(it)

        }

        setHasOptionsMenu(true)
                                                                         
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_fragmnent_tasks,menu)
        val searchItem = menu.findItem(R.id.search)
        val searchView = searchItem.actionView as SearchView

        searchView.onQueryTextChanged {
            viewModel.searchQuery.value = it
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_sort_by_name ->{
               true
            }
            R.id.action_sort_by_date_created->{
                true
            }
            R.id.action_hide_completed->{
                item.isChecked =! item.isChecked
                true
            }
            R.id.action_all_completed_tasks->{
                true
            }
            else->super.onOptionsItemSelected(item)

        }
    }
}