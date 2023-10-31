package br.com.venuciacanalli.pan.evaluation.infrastructure.gateways;

import br.com.venuciacanalli.pan.evaluation.domain.entities.CEPAddress;
import br.com.venuciacanalli.pan.evaluation.domain.exceptions.BadGatewayException;
import br.com.venuciacanalli.pan.evaluation.infrastructure.mapper.CEPAddressVendorMapper;
import br.com.venuciacanalli.pan.evaluation.infrastructure.vendors.CEPAddressVendor;
import br.com.venuciacanalli.pan.evaluation.infrastructure.vendors.IViaCepClient;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class AddressHelperGatewayTest {

    @InjectMocks
    private AddressHelperGateway addressHelperGateway;


    @Mock
    private IViaCepClient viaCepClient;

    @Mock
    private CEPAddressVendorMapper cepAddressVendorMapper;

    @Test
    @DisplayName("When find address by cep it should return address")
    void whenFindAddressByCepItShouldReturnAddress() throws IOException {
        String cep = "01024900";

        CEPAddress cepAddressDomainMock = mock(CEPAddress.class);
        CEPAddressVendor cepAddressMockVendor = mock(CEPAddressVendor.class);
        Response<CEPAddressVendor> responseMock = mock(Response.class);
        when(responseMock.code()).thenReturn(200);
        when(responseMock.body()).thenReturn(cepAddressMockVendor);
        Call<CEPAddressVendor> callMock = mock(Call.class);
        when(callMock.execute()).thenReturn(responseMock);
        when(this.viaCepClient.findAddressByCEP(cep)).thenReturn(callMock);
        when(this.cepAddressVendorMapper.toDomain(cepAddressMockVendor)).thenReturn(cepAddressDomainMock);

        Optional<CEPAddress> addressByCep = this.addressHelperGateway.findAddressByCep(cep);

        assertNotNull(addressByCep);
        assertTrue(addressByCep.isPresent());
        assertEquals(cepAddressDomainMock, addressByCep.get());
        verify(viaCepClient, times(1)).findAddressByCEP(cep);
        verify(cepAddressVendorMapper, times(1)).toDomain(cepAddressMockVendor);

    }

    @Test
    @DisplayName("When not find address by cep it should return empty value")
    void whenFindAddressByCepItShouldReturnEmptyValue() throws IOException {
        String cep = "01024900";
        Response<CEPAddressVendor> responseMock = mock(Response.class);
        when(responseMock.code()).thenReturn(200);
        when(responseMock.body()).thenReturn(null);
        Call<CEPAddressVendor> callMock = mock(Call.class);
        when(callMock.execute()).thenReturn(responseMock);
        when(this.viaCepClient.findAddressByCEP(cep)).thenReturn(callMock);

        Optional<CEPAddress> addressByCep = this.addressHelperGateway.findAddressByCep(cep);

        assertNotNull(addressByCep);
        assertTrue(addressByCep.isEmpty());
        verify(viaCepClient, times(1)).findAddressByCEP(cep);
        verify(cepAddressVendorMapper, times(0)).toDomain(any());
    }

    @Test
    @DisplayName("When find address by cep given a error 400 it should throw BadGatewayException")
    void whenFindAddressByCepGivenAError400ItShouldThrowBadGatewayException() throws IOException {
        String cep = "01024900";
        Response<CEPAddressVendor> responseMock = mock(Response.class);
        when(responseMock.code()).thenReturn(400);
        ResponseBody errorBody = mock(ResponseBody.class);
        when(errorBody.toString()).thenReturn("Connection Error");
        when(responseMock.errorBody()).thenReturn(errorBody);
        Call<CEPAddressVendor> callMock = mock(Call.class);
        when(callMock.execute()).thenReturn(responseMock);
        when(this.viaCepClient.findAddressByCEP(cep)).thenReturn(callMock);

        Exception exception = assertThrows(BadGatewayException.class, () -> this.addressHelperGateway.findAddressByCep(cep));
        assertEquals("The application received an invalid response while acting with external application (viacep) : Connection Error", exception.getMessage());
        verify(viaCepClient, times(1)).findAddressByCEP(cep);
        verify(cepAddressVendorMapper, times(0)).toDomain(any());
    }

    @Test
    @DisplayName("When find address by cep given exception it should throw BadGatewayException")
    void whenFindAddressByCepGivenExceptionItShouldThrowBadGatewayException() throws IOException {
        String cep = "01024900";
        Call<CEPAddressVendor> callMock = mock(Call.class);
        when(this.viaCepClient.findAddressByCEP(cep)).thenReturn(callMock);
        doThrow(NullPointerException.class).when(callMock).execute();
        Exception exception = assertThrows(BadGatewayException.class, () -> this.addressHelperGateway.findAddressByCep(cep));
        assertEquals("The application received an invalid response while acting with external application (viacep) : ", exception.getMessage());
        verify(viaCepClient, times(1)).findAddressByCEP(cep);
        verify(cepAddressVendorMapper, times(0)).toDomain(any());
    }
}