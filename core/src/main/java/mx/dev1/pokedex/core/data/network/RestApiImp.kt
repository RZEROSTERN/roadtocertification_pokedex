package mx.dev1.pokedex.core.data.network

import mx.dev1.pokedex.core.utils.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RestApiImp {
    companion object{
        var retrofit: Retrofit? = null
        val REQUEST_TIMEOUT: Long = 30
        var okHttpClient: OkHttpClient? = null

        fun getClient(): Retrofit {
            if(okHttpClient == null)
                initOkHttpClient()

            if(retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(Constant.MAIN_URL)
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit!!
        }

        private fun initOkHttpClient() {
            var httpClient: OkHttpClient.Builder = OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)

            var interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(interceptor)

            httpClient.addInterceptor { chain ->
                var original = chain.request()

                val newRequest = chain.request().newBuilder()
                    .addHeader("Accept", "application/json")
                    .build()

                chain.proceed(newRequest)
            }

            okHttpClient = httpClient.build()
        }
    }
}