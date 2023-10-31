package br.com.venuciacanalli.pan.evaluation.infrastructure.gateways;

import br.com.venuciacanalli.pan.evaluation.application.gateways.IAddressHelperGateway;
import br.com.venuciacanalli.pan.evaluation.domain.entities.CEPAddress;
import br.com.venuciacanalli.pan.evaluation.domain.exceptions.BadGatewayException;
import br.com.venuciacanalli.pan.evaluation.infrastructure.mapper.CEPAddressVendorMapper;
import br.com.venuciacanalli.pan.evaluation.infrastructure.vendors.CEPAddressVendor;
import br.com.venuciacanalli.pan.evaluation.infrastructure.vendors.IViaCepClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import java.util.Optional;

@Component
public class AddressHelperGateway implements IAddressHelperGateway {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressHelperGateway.class);

    @Autowired
    private IViaCepClient viaCepClient;

    @Autowired
    private CEPAddressVendorMapper cepAddressVendorMapper;

    @Override
    public Optional<CEPAddress> findAddressByCep(String cep)  {
        LOGGER.info(String.format("start find address by cep (%s)", cep));
        Call<CEPAddressVendor> call = viaCepClient.findAddressByCEP(cep);
        Response<CEPAddressVendor> response;

        try {
            response = call.execute();
        }  catch (Exception e) {
            throw new BadGatewayException("viacep", e.getMessage());
        }

        if( response.code() >= 400)
            throw new BadGatewayException("viacep", getErrorMessageFromResponse(response));
        CEPAddressVendor cepAddressVendor = response.body();
        if(cepAddressVendor == null)
            return Optional.empty();
        CEPAddress domain = this.cepAddressVendorMapper.toDomain(cepAddressVendor);
        return Optional.of(domain);
    }


    private String getErrorMessageFromResponse(Response<CEPAddressVendor> response) {
        String message = "";
        try{
            if(response.errorBody() != null)
                message = response.errorBody().toString();
        }catch (Exception e){
            LOGGER.warn(String.format("getMessageErrorFromResponse: Error reading message body [%s]", e.getMessage()));
        }

        return message;
    }
}