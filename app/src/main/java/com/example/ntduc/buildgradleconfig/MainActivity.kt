package com.example.ntduc.buildgradleconfig

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.io.File

class MainActivity : AppCompatActivity() {

    var filepath = ArrayList<String>()//contains list of all files ending with

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun walkdir(dir: File) {
        val listFile = dir.listFiles()

        if (listFile != null) {
            for (i in listFile.indices) {

                if (listFile[i].isDirectory) {// if its a directory need to get the files under that directory
                    walkdir(listFile[i])
                } else {// add path of  files to your arraylist for later use

                    //Do what ever u want
                    filepath.add(listFile[i].absolutePath)

                }
            }
        }
    }
}
