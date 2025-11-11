# PasskeyAuthenticationApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**finishLogin**](#finishlogin) | **POST** /api/auth/passkeys/login/finish | |
|[**finishRegistration**](#finishregistration) | **POST** /api/auth/passkeys/register/finish | |
|[**startLogin**](#startlogin) | **POST** /api/auth/passkeys/login/start | |
|[**startRegistration**](#startregistration) | **POST** /api/auth/passkeys/register/start | |

# **finishLogin**
> CustomApiResponseAuthResponse finishLogin(finishLoginRequest)


### Example

```typescript
import {
    PasskeyAuthenticationApi,
    Configuration,
    FinishLoginRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new PasskeyAuthenticationApi(configuration);

let finishLoginRequest: FinishLoginRequest; //

const { status, data } = await apiInstance.finishLogin(
    finishLoginRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **finishLoginRequest** | **FinishLoginRequest**|  | |


### Return type

**CustomApiResponseAuthResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **finishRegistration**
> CustomApiResponseVoid finishRegistration()


### Example

```typescript
import {
    PasskeyAuthenticationApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new PasskeyAuthenticationApi(configuration);

let credential: string; // (default to undefined)

const { status, data } = await apiInstance.finishRegistration(
    credential
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **credential** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseVoid**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **startLogin**
> CustomApiResponseStartPasskeyLoginResponse startLogin()


### Example

```typescript
import {
    PasskeyAuthenticationApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new PasskeyAuthenticationApi(configuration);

const { status, data } = await apiInstance.startLogin();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**CustomApiResponseStartPasskeyLoginResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **startRegistration**
> CustomApiResponsePublicKeyCredentialCreationOptions startRegistration()


### Example

```typescript
import {
    PasskeyAuthenticationApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new PasskeyAuthenticationApi(configuration);

const { status, data } = await apiInstance.startRegistration();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**CustomApiResponsePublicKeyCredentialCreationOptions**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

