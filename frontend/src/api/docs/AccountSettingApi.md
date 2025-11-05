# AccountSettingApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getAccountSettings**](AccountSettingApi.md#getaccountsettings) | **GET** /api/account/settings |  |
| [**updateEmailRequest**](AccountSettingApi.md#updateemailrequest) | **PATCH** /api/account/settings/email/request-change |  |
| [**updateUsername**](AccountSettingApi.md#updateusernameoperation) | **PATCH** /api/account/settings/username |  |
| [**verifyEmailChange**](AccountSettingApi.md#verifyemailchange) | **GET** /api/account/settings/email/verify-change |  |



## getAccountSettings

> CustomApiResponseAccountSettingResponse getAccountSettings()



### Example

```ts
import {
  Configuration,
  AccountSettingApi,
} from '';
import type { GetAccountSettingsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AccountSettingApi(config);

  try {
    const data = await api.getAccountSettings();
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

[**CustomApiResponseAccountSettingResponse**](CustomApiResponseAccountSettingResponse.md)

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


## updateEmailRequest

> CustomApiResponseVoid updateEmailRequest(updateEmailRequest)



### Example

```ts
import {
  Configuration,
  AccountSettingApi,
} from '';
import type { UpdateEmailRequestRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AccountSettingApi(config);

  const body = {
    // UpdateEmailRequest
    updateEmailRequest: ...,
  } satisfies UpdateEmailRequestRequest;

  try {
    const data = await api.updateEmailRequest(body);
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
| **updateEmailRequest** | [UpdateEmailRequest](UpdateEmailRequest.md) |  | |

### Return type

[**CustomApiResponseVoid**](CustomApiResponseVoid.md)

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


## updateUsername

> CustomApiResponseAccountSettingResponse updateUsername(updateUsernameRequest)



### Example

```ts
import {
  Configuration,
  AccountSettingApi,
} from '';
import type { UpdateUsernameOperationRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AccountSettingApi(config);

  const body = {
    // UpdateUsernameRequest
    updateUsernameRequest: ...,
  } satisfies UpdateUsernameOperationRequest;

  try {
    const data = await api.updateUsername(body);
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
| **updateUsernameRequest** | [UpdateUsernameRequest](UpdateUsernameRequest.md) |  | |

### Return type

[**CustomApiResponseAccountSettingResponse**](CustomApiResponseAccountSettingResponse.md)

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


## verifyEmailChange

> CustomApiResponseVoid verifyEmailChange(token)



### Example

```ts
import {
  Configuration,
  AccountSettingApi,
} from '';
import type { VerifyEmailChangeRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AccountSettingApi(config);

  const body = {
    // string
    token: token_example,
  } satisfies VerifyEmailChangeRequest;

  try {
    const data = await api.verifyEmailChange(body);
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
| **token** | `string` |  | [Defaults to `undefined`] |

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

