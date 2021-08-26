package net.harimurti.tv.dialog

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import net.harimurti.tv.R
import net.harimurti.tv.databinding.SettingAboutFragmentBinding
import net.harimurti.tv.extra.Preferences

class SettingAboutFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = SettingAboutFragmentBinding.inflate(inflater, container, false)

        binding.textUsers.text = Preferences().contributors
        binding.buttonWebsite.setOnClickListener {
            openWebsite(getString(R.string.website))
        }
        binding.buttonTelegram.setOnClickListener {
            openWebsite(getString(R.string.telegram_group))
        }

        return binding.root
    }

    private fun openWebsite(link: String) {
        val uriLink = Uri.parse(link)
        startActivity(
            Intent(Intent.ACTION_VIEW)
                .setData(uriLink)
        )
    }
}