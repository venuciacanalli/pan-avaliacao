package br.com.venuciacanalli.pan.evaluation.infrastructure.config;

import br.com.venuciacanalli.pan.evaluation.infrastructure.vendors.IViaCepClient;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfiguration {

    @Bean
    public IViaCepClient viaCepClient(){
        final OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://viacep.com.br/ws/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(IViaCepClient.class);
    }

}
