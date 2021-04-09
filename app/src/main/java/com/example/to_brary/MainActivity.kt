package com.example.to_brary

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    private val apiKey = "key"
    private val applicationID = "ID"
    private var tagsList = "thereIsNoGodDamnWaySomeoneIsPuttingThisIntoTHeTextView"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navController = this.findNavController(R.id.fragment_navhost_main)
        NavigationUI.setupActionBarWithNavController(this, navController)

    }

    //options menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        if (menu != null) {
            (menu.findItem(R.id.search).actionView as SearchView).apply {
                setSearchableInfo(searchManager.getSearchableInfo(componentName))
            }
        }

        val menuItem = menu!!.findItem(R.id.search)
        val searchView = menuItem.actionView as SearchView
        searchView.queryHint = "Insert Tags"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                val bundle = Bundle()
                bundle.putString("listOfTags", query)

                tagsList = query
                findNavController(R.id.fragment_navhost_main).navigate(R.id.homeFragment, bundle)

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {

                return false
            }
        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.loginPage -> findNavController(R.id.fragment_navhost_main).navigate(R.id.loginPage)
            R.id.imagePostingPage -> findNavController(R.id.fragment_navhost_main).navigate(R.id.imagePostingPage)
            R.id.tagCreationPage -> findNavController(R.id.fragment_navhost_main).navigate(R.id.tagCreationPage)
            R.id.home -> findNavController(R.id.fragment_navhost_main).navigate(R.id.homeFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    //up button
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.fragment_navhost_main)
        return navController.navigateUp()
    }

    public fun getItemsList(): String{
        return tagsList;
    }

}

