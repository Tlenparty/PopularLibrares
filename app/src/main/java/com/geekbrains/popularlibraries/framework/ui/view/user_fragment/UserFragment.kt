package com.geekbrains.popularlibraries.framework.ui.view.user_fragment

/*
class UserFragment:MvpAppCompatFragment(),UserView{
    private lateinit var binding:FragmentUserBinding
    private val presenter by moxyPresenter { UserPresenter(
        this.arguments?.getParcelable(USER_LOGIN),
    App.instance.router) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun init(name: String?) {
        binding.userName.text = name
    }

    companion object{
        const val USER_LOGIN = "userLogin"
      */
/*  fun newInstance(user:GithubUser):MvpAppCompatFragment{
            val args = Bundle()
            args.putParcelable(USER_LOGIN,user)
            val fragment = UserFragment()
            fragment.arguments = args
            return fragment
        }*//*

        fun newInstance(user:GithubUser) = UserFragment()
    }
}
*/
