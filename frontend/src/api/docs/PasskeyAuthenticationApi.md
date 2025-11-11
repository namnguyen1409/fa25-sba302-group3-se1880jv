# PasskeyAuthenticationApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**finishLogin**](PasskeyAuthenticationApi.md#finishloginoperation) | **POST** /api/auth/passkeys/login/finish |  |
| [**finishRegistration**](PasskeyAuthenticationApi.md#finishregistration) | **POST** /api/auth/passkeys/register/finish |  |
| [**startLogin**](PasskeyAuthenticationApi.md#startlogin) | **POST** /api/auth/passkeys/login/start |  |
| [**startRegistration**](PasskeyAuthenticationApi.md#startregistration) | **POST** /api/auth/passkeys/register/start |  |



## finishLogin

> CustomApiResponseAuthResponse finishLogin(finishLoginRequest)



### Example

```ts
import {
  Configuration,
  PasskeyAuthenticationApi,
} from '';
import type { FinishLoginOperationRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new PasskeyAuthenticationApi(config);

  const body = {
    // FinishLoginRequest
    finishLoginRequest: ...,
  } satisfies FinishLoginOperationRequest;

  try {
    const data = await api.finishLogin(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **finishLoginRequest** | [FinishLoginRequest](FinishLoginRequest.md) |  | |

### Return type

[**CustomApiResponseAuthResponse**](CustomApiResponseAuthResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: `application/json`
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## finishRegistration

> CustomApiResponseVoid finishRegistration(credential)



### Example

```ts
import {
  Configuration,
  PasskeyAuthenticationApi,
} from '';
import type { FinishRegistrationRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new PasskeyAuthenticationApi(config);

  const body = {
    // string
    credential: credential_example,
  } satisfies FinishRegistrationRequest;

  try {
    const data = await api.finishRegistration(body);
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **credential** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseVoid**](CustomApiResponseVoid.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## startLogin

> CustomApiResponseStartPasskeyLoginResponse startLogin()



### Example

```ts
import {
  Configuration,
  PasskeyAuthenticationApi,
} from '';
import type { StartLoginRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new PasskeyAuthenticationApi(config);

  try {
    const data = await api.startLogin();
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**CustomApiResponseStartPasskeyLoginResponse**](CustomApiResponseStartPasskeyLoginResponse.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)


## startRegistration

> CustomApiResponsePublicKeyCredentialCreationOptions startRegistration()



### Example

```ts
import {
  Configuration,
  PasskeyAuthenticationApi,
} from '';
import type { StartRegistrationRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new PasskeyAuthenticationApi(config);

  try {
    const data = await api.startRegistration();
    console.log(data);
  } catch (error) {
    console.error(error);
  }
}

// Run the test
example().catch(console.error);
```

### Parameters

This endpoint does not need any parameter.

### Return type

[**CustomApiResponsePublicKeyCredentialCreationOptions**](CustomApiResponsePublicKeyCredentialCreationOptions.md)

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: `*/*`


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | Bad Request |  -  |
| **401** | Unauthorized |  -  |
| **200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#api-endpoints) [[Back to Model list]](../README.md#models) [[Back to README]](../README.md)

