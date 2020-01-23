package com.example.asynctask

import android.app.ProgressDialog
import android.app.SearchManager
import android.content.Context
import android.content.DialogInterface
import android.os.AsyncTask
import android.util.Log
import kotlin.properties.Delegates
import kotlin.Int as Int1

class MyAsyncTask: AsyncTask<String, Int1, Long>,
    SearchManager.OnCancelListener,DialogInterface.OnCancelListener {


    val TAG: String = "MyAsyncTask"
    var dialog: ProgressDialog by Delegates.notNull<ProgressDialog>()
    var context: Context by Delegates.notNull<Context>()
    override fun onCancel() {

    }

    override fun onCancel(dialog:DialogInterface) {
        Log.d(TAG, "Dialog on Cancell...calling cansel(true)")
        this.cancel(true)
    }
    constructor(context: Context) {
        this.context = context
    }

    override fun onPreExecute() {
        //super.onPreExecute()
        Log.d(TAG, "onPreExecute")
        dialog= ProgressDialog(context)
        dialog.setTitle("Pleace wait")
        dialog.setMessage("Loading data")
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
        dialog.setCancelable(true)
        dialog.setOnCancelListener(this)
        dialog.setMax(100)
        dialog.setProgress(0)
        dialog.show()


    }

    override fun doInBackground(vararg params: String?): Long {
        Log.d(TAG, "doInBackground - " + params[0])

        try {

            loop@ for (i in 0..9) {
                if (isCancelled()) {
                    Log.d(TAG, "Cancelled")
                    return@loop
                }

                Thread.sleep(1000)
                publishProgress((i + 1) * 10)
            }
        } catch (e: InterruptedException) {
            Log.d(TAG, "InterruptedException in doInbackground")
        }
        return 123L
    }

    override fun onProgressUpdate(vararg values: kotlin.Int?) {
        Log.d(TAG, "onProgressUpdate - " + values[0])
        dialog.setProgress(values[0]!!.toInt())
    }

    override protected fun onPostExecute(result: Long?) {
        Log.d(TAG, "onPostExecute - " + result)
        dialog.dismiss()
    }

}