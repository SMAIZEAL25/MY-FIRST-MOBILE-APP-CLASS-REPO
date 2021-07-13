package com.solomon.myphonebook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.solomon.myphonebook.activities.ContactDetailsActivity
import com.solomon.myphonebook.adapters.PhoneAdapter
import com.solomon.myphonebook.databinding.ActivityMainBinding
import com.solomon.myphonebook.models.Contact
import com.solomon.myphonebook.models.PhoneDataBase
import com.solomon.myphonebook.viewmodels.PhoneBookView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var phoneAdapter: PhoneAdapter
    private lateinit var database: PhoneDataBase
    private lateinit var viewModel: PhoneBookView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = Room.databaseBuilder(this,
            PhoneDataBase::class.java,
            "phone_database"
        ).allowMainThreadQueries().build()

        // initializing the view model
        viewModel = ViewModelProvider(this)[PhoneBookView::class.java]
        viewModel.getContact(database)


        phoneAdapter = PhoneAdapter(database.contactDoa().getAllContacts()){
            val intent = Intent(this@MainActivity, ContactDetailsActivity::class.java)
            intent.run {
                //putExtra("id",it.id)
                putExtra("name", it.name)
                putExtra("number", it.number)
            }
            startActivity(intent)

        }
        // binding the RecyclerView
        binding.phoneRecycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = phoneAdapter
        }

        // Observing livedata
        viewModel.phonebookLiveData.observe(this,{ contacts ->
            phoneAdapter.contacts = contacts
            phoneAdapter.notifyDataSetChanged()
        })
        // binding the save button
        binding.btnSave.setOnClickListener {
            val name = binding.txNameInput.text.toString()
            val number = binding.txNumberInput.text.toString()
            saveContact(name, number)
        }
    }
    private fun saveContact(name: String, number: String){
        val contact = Contact(id = 0, name, number)
        viewModel.addContact(database,contact)
        binding.apply {
            txNameInput.text.clear()
            txNumberInput.text.clear()
        }

    }
}