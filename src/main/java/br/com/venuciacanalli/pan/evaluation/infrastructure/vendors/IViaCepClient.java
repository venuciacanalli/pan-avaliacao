package br.com.venuciacanalli.pan.evaluation.infrastructure.vendors;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IViaCepClient {
    @GET("http://viacep.com.br/ws/{cep}/json")
    Call<CEPAddressVendor> findAddressByCEP(@Path("cep") String cep);
}
