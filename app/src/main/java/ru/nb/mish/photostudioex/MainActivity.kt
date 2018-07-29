package ru.nb.mish.photostudioex

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import ru.nb.mish.photostudioex.api.ServiceGenerator
import ru.nb.mish.photostudioex.adapter.ImageAdapter
import ru.nb.mish.photostudioex.components.IntentHelper
import java.net.UnknownHostException

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val PAGE_ID = "page_id"
    val TITLE_ID = "title_id"
    var currentPhotoCategory: Int = 6
    var currentCategoryTitle: String? = "Фотограф Дарья Луговая"

    // объект адаптера с картинками
    private var imageAdapter = ImageAdapter({
        startActivity(Intent(this, FullImageActivity::class.java)
                .putStringArrayListExtra(IntentHelper.IMAGE_GALLERY, ArrayList((rvImages.adapter as ImageAdapter).mData))
                .putExtra(IntentHelper.IMAGE_POS,(rvImages.adapter as ImageAdapter).mData?.indexOf(it) ))
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        // тут данные записываем (при перевороте экрана)
        if (savedInstanceState != null) {
            currentPhotoCategory = savedInstanceState?.getInt(PAGE_ID)
            currentCategoryTitle = savedInstanceState?.getString(TITLE_ID)
        }

            loadPhoto(currentPhotoCategory)



            rvImages.layoutManager = GridLayoutManager(this, 3)
            rvImages.adapter = imageAdapter


            val toggle = ActionBarDrawerToggle(
                    this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
            drawer_layout.addDrawerListener(toggle)
            toggle.syncState()
            nav_view.setNavigationItemSelectedListener(this) // устанавливаем слушатель на NavigationView(шторка) в main_activity

    }

    // перед уничтожением Активити сохраняем текущую позицию
    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putInt(PAGE_ID, currentPhotoCategory)
        outState?.putString(TITLE_ID, currentCategoryTitle)
        super.onSaveInstanceState(outState)

    }

    private fun loadPhoto(id_player: Int = 6) {
        launch(UI) {
            try{
                // получаем данные с сервера и присваиваем адаптеру
                val photoCategory = ServiceGenerator.serverApi.loadPhotoCategory(id_player).await()

                currentPhotoCategory = id_player // при перевороте экрана сохраняем данные
                supportActionBar?.setTitle(currentCategoryTitle)

                imageAdapter.mData = photoCategory.images.toList()

            } catch (ex: UnknownHostException) {
                ex.printStackTrace() // метод диагностики исключений
                Toast.makeText(this@MainActivity, R.string.error_no_connection_text, Toast.LENGTH_LONG).show()
            } catch (ex: Exception) {
                ex.printStackTrace()
                Toast.makeText(this@MainActivity, R.string.error_no_connection_button, Toast.LENGTH_LONG).show()

            }
        }
    }


    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.wedding_photo -> {

                    loadPhoto(6)
                currentCategoryTitle = resources.getString(R.string.wedding_photo)
                supportActionBar?.setTitle(currentCategoryTitle)


            }
            R.id.pregnancy_photo -> {

                loadPhoto(8)
                currentCategoryTitle = resources.getString(R.string.title_pregnancy)
                supportActionBar?.setTitle(currentCategoryTitle)

            }
            R.id.love_story_photo -> {

                loadPhoto(7)
                currentCategoryTitle = resources.getString(R.string.title_love_story)
                supportActionBar?.setTitle(currentCategoryTitle)

            }
            R.id.family_photo -> {
                loadPhoto(10)
                currentCategoryTitle = resources.getString(R.string.title_family)
                supportActionBar?.setTitle(currentCategoryTitle)

            }
            R.id.children_photo -> {
                loadPhoto(9)
                currentCategoryTitle = resources.getString(R.string.title_children)
                supportActionBar?.setTitle(currentCategoryTitle)

            }
            R.id.about_me-> {
             startActivity(Intent(this@MainActivity, AboutMeActivity::class.java))
                overridePendingTransition(R.anim.activity_in, R.anim.activity_exit)

            }

            R.id.communicate-> {
                startActivity(Intent(this@MainActivity, ContactsActivity::class.java))
                overridePendingTransition(R.anim.activity_in, R.anim.activity_exit)

            }

        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}