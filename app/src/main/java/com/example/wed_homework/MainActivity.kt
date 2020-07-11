package com.example.wed_homework


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_filter.*
import kotlinx.android.synthetic.main.item_skills.*

class MainActivity : AppCompatActivity() {
    var skills: ArrayList<Skill> =
            arrayListOf(
                    Skill("Kotlin", 0.2),
                    Skill("Java", 0.1),
                    Skill("Pascal", 5.0),
                    Skill("HTML", 3.0),
                    Skill("Figma", 1.0),
                    Skill("Фотография", 5.0),
                    Skill("Черный юмор", 6.0),
                    Skill("Цитирование Маяковского", 5.0),
                    Skill("Семь бед..один ответ", 1.0),
                    Skill("Мама, я прокрастинатор", 11.0),
                    Skill("Ночной дожор в стиле ниндзя", 3.0 )
            )
    private var skillsAdapter = ListDelegationAdapter(skillsAdapterDelegate())

    private var filter = skills.map { it.exp.toInt() }.toIntArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        toGithub.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://github.com/miracaptured")
            startActivityForResult(openURL, 1)
        }
        skillsAdapter.items = skills
        with(skillsContainer) {
            adapter = skillsAdapter
            layoutManager = LinearLayoutManager(context)
        }


        buttonF.setOnClickListener() {
            val intent = Intent(this, Filter_Activity::class.java)
            intent.putExtra("my years", filter)
            startActivity(intent)

        }
    }


    private fun skillsAdapterDelegate() =
            adapterDelegateLayoutContainer<Skill, Skill>(R.layout.item_skills) {
                bind {
                    skillName.text = item.name
                    if (item.exp < 1.0) {  exp.text = " <1 year"} else {
                    exp.text = " " + item.exp.toString() + " years"}
                }
            }
}
