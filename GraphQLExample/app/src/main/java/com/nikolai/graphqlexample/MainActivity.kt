package com.nikolai.graphqlexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.coroutines.toDeferred
import com.apollographql.apollo.exception.ApolloException
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    val apolloClient = ApolloClient.builder()
        .serverUrl("https://countries.trevorblades.com/")
        .build()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            val response = apolloClient.query(CountiresByContinentQuery("AF")).await()
            val countries = response.data?.continent?.countries ?: emptyList()

            println("Launch site: ${countries}")
        }
    }
}