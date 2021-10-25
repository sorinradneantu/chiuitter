package ro.upt.ac.chiuitter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.item_chiuit.*
import kotlinx.android.synthetic.main.view_home.*
import ro.upt.ac.chiuitter.ComposeActivity.Companion.EXTRA_TEXT

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_home)

        ibt_share.setOnClickListener { shareChiuit(txv_content.text.toString()) }
        fab_add.setOnClickListener { composeChiuit() }
    }

    /*
    Defines text sharing/sending *implicit* intent, opens the application chooser menu,
    and starts a new activity which supports sharing/sending text.
     */
    private fun shareChiuit(text: String) {
        val sendIntent = Intent().apply {
            // TODO 1: Configure to support text sending/sharing and then attach the text as intent's extra.

            action = Intent.ACTION_SEND;
            type = "text/plain";
            putExtra(Intent.EXTRA_TEXT, text);

        }

        val intentChooser = Intent.createChooser(sendIntent, "")

        startActivity(intentChooser)
    }

    /*
    Defines an *explicit* intent which will be used to start ComposeActivity.
     */
    private fun composeChiuit() {
        // TODO 2: Create an explicit intent which points to ComposeActivity.

        val composeActivityIntent = Intent(this,ComposeActivity::class.java).apply {

        }

        // TODO 3: Start a new activity with the previously defined intent.
        // We start a new activity that we expect to return the acquired text as the result.

        startActivityForResult(composeActivityIntent, COMPOSE_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            COMPOSE_REQUEST_CODE -> if (resultCode == Activity.RESULT_OK) extractText(data)
            else -> super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun extractText(data: Intent?) {
        data?.let {
            // TODO 5: Extract the text from result intent.

            val extractedText = data.getStringExtra(EXTRA_TEXT);

            // TODO 6: Check if text is not null or empty, then set the new "chiuit" content.


            if(extractedText.isNullOrEmpty()){
                Toast.makeText(this, "The text is null or empty, please try again !", Toast.LENGTH_SHORT).show();
            }else{
                txv_content.text = extractedText;
            }

        }
    }

    companion object {
        const val COMPOSE_REQUEST_CODE = 1213
    }

}
