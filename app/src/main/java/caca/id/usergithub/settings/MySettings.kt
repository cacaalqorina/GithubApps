package caca.id.usergithub.settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatDelegate
import caca.id.usergithub.R
import caca.id.usergithub.databinding.ActivityMySettingsBinding
import com.google.android.material.switchmaterial.SwitchMaterial

class MySettings : AppCompatActivity() {

    private val prefGithub by lazy { prefrence(this) }
    private lateinit var binding: ActivityMySettingsBinding

    companion object{
        const val MODE = "dark"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.switchTheme.isChecked = prefGithub.getBoolean(MODE)
        binding.switchTheme.setOnCheckedChangeListener{ _: CompoundButton?, isChecked ->
            when(isChecked){
                true->{
                    prefGithub.put(MODE,true)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                false ->{
                    prefGithub.put(MODE,false)
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }

            }

        }

    }
}