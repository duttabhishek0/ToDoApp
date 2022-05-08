package com.abhishek.todoapp.screens.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.abhishek.todoapp.R
import com.abhishek.todoapp.databinding.FragmentTaskBinding
import com.abhishek.todoapp.model.Task
import com.abhishek.todoapp.screens.adapters.LoadingStateAdapter
import com.abhishek.todoapp.screens.adapters.TaskAdapter
import com.androidpoet.metaphor.metaphorStartFragmentWithoutAnimation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class TaskFragment : Fragment() {

    private var _binding: FragmentTaskBinding? = null
    private val binding get() = _binding!!
    private val viewModel: TaskViewModel by viewModels()
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        taskAdapter = TaskAdapter()

        taskAdapter.callback = object : TaskAdapter.Callback {
            override fun onClick(view: CardView, item: Task) {
                val extras = FragmentNavigatorExtras(view to item.taskId.toString())
                val action = TaskFragmentDirections.actionNavigationHomeToDetailFragment(item)
                findNavController().navigate(action, extras)
            }
        }

        taskAdapter.deletecallback = object : TaskAdapter.DeleteCallback {
            override fun onClick(item: Task) {
                viewModel.deleteTask(item)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        metaphorStartFragmentWithoutAnimation(binding.recyclerView)

        binding.recyclerView.apply {
            val layoutManager = layoutManager as GridLayoutManager
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (taskAdapter.getItemViewType(position) == TaskAdapter.LOADING_ITEM)
                        1 else 2
                }
            }
            adapter = taskAdapter.withLoadStateHeaderAndFooter(
                header = LoadingStateAdapter { taskAdapter.retry() },
                footer = LoadingStateAdapter { taskAdapter.retry() }
            )
        }
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = taskAdapter.apply {
                viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                    viewModel.getAllTasks.collectLatest(taskAdapter::submitData)
                }
            }
        }

        binding.fab.apply {
            setShowMotionSpecResource(R.animator.fab_show)
            setHideMotionSpecResource(R.animator.fab_hide)
            binding.fab.setOnClickListener {
                val action = TaskFragmentDirections.actionNavigationHomeToAddTaskFragment()
                findNavController().navigate(action)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
