package com.example.wed_homework


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_skills.*

class MainActivity : AppCompatActivity() {
    var skills: ArrayList<Skill> =
        arrayListOf(
            Skill("Kotlin", "< 1 year"),
            Skill("Java", "< 1 year"),
            Skill("Pascal", "5 years"),
            Skill("HTML", "3 years"),
            Skill("Figma", ">1 year")
        )
    private var skillsAdapter = ListDelegationAdapter(skillsAdapterDelegate())

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


        buttonF.setOnClickListener(){ val intent = Intent(this, Filter_Activity::class.java)
            startActivity(intent)

        }
    }




    private fun skillsAdapterDelegate() =
        adapterDelegateLayoutContainer<Skill, Skill>(R.layout.item_skills) {
            bind {
                skillName.text = item.name
                exp.text = item.exp
            }
        }



    }

