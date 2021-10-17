package com.geekbrains.popularlibraries.baselogic.user_repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.CreateMethod
import by.kirich1409.viewbindingdelegate.viewBinding
import com.geekbrains.popularlibrares.databinding.FragmentRepositoryBinding
import com.geekbrains.popularlibraries.extentions.showToast
import com.geekbrains.popularlibraries.baselogic.BackButtonListener
import com.geekbrains.popularlibraries.di.BaseDaggerFragment
import com.geekbrains.popularlibraries.model.entites.GithubUsersRepository
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class RepositoryFragment : BaseDaggerFragment(), RepositoryView, BackButtonListener {

    @Inject
    lateinit var githubUsersRepository: GithubUsersRepository

    companion object {
        const val USER_LOGIN = "userLogin"
        const val REPOSITORY_NAME = "repositoryName"
        fun newInstance(userLogin: String, repositoryName: String): Fragment = RepositoryFragment()
            .also {
                it.arguments = bundleOf(
                    USER_LOGIN to userLogin,
                    REPOSITORY_NAME to repositoryName
                )
            }
    }

    private val userLogin by lazy { arguments?.getString(USER_LOGIN) }
    private val repositoryName by lazy { arguments?.getString(REPOSITORY_NAME) }
    private val binding: FragmentRepositoryBinding by viewBinding(createMethod = CreateMethod.INFLATE)

    private val presenter by moxyPresenter {
        RepositoryPresenter(
            githubUsersRepository,
            appSchedulers,
            router,
            userLogin,
            repositoryName
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        binding.root

    override fun showInfo(text: String) {
        binding.infoText.text = text
    }

    override fun showException(throwable: Throwable) {
        context?.showToast(throwable.message)
    }

    override fun backPressed() = presenter.backPressed()
}