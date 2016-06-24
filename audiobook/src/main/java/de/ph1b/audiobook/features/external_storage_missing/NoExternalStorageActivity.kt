/*
 * This file is part of Material Audiobook Player.
 *
 * Material Audiobook Player is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or any later version.
 *
 * Material Audiobook Player is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Material Audiobook Player. If not, see <http://www.gnu.org/licenses/>.
 * /licenses/>.
 */

package de.ph1b.audiobook.features.external_storage_missing

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import de.ph1b.audiobook.R
import de.ph1b.audiobook.databinding.ActivityNoExternalBinding
import de.ph1b.audiobook.features.BaseActivity

class NoExternalStorageActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoExternalBinding

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_no_external)

        setSupportActionBar(binding.toolbarInclude.toolbar)
        supportActionBar!!.setTitle(R.string.no_external_storage_action_bar_title)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }

    override fun onBackPressed() {
        if (BaseActivity.storageMounted()) {
            super.onBackPressed()
        } else {
            val i = Intent(Intent.ACTION_MAIN)
            i.addCategory(Intent.CATEGORY_HOME)
            i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(i)
        }
    }

    public override fun onResume() {
        super.onResume()
        if (BaseActivity.storageMounted()) {
            onBackPressed()
        }
    }
}
