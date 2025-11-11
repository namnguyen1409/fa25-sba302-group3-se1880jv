# AccountSettingApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**getAccountSettings**](#getaccountsettings) | **GET** /api/account/settings | |
|[**updateEmailRequest**](#updateemailrequest) | **PATCH** /api/account/settings/email/request-change | |
|[**updateUsername**](#updateusername) | **PATCH** /api/account/settings/username | |
|[**verifyEmailChange**](#verifyemailchange) | **GET** /api/account/settings/email/verify-change | |

# **getAccountSettings**
> CustomApiResponseAccountSettingResponse getAccountSettings()


### Example

```typescript
import {
    AccountSettingApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountSettingApi(configuration);

const { status, data } = await apiInstance.getAccountSettings();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**CustomApiResponseAccountSettingResponse**

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

# **updateEmailRequest**
> CustomApiResponseVoid updateEmailRequest(updateEmailRequest)


### Example

```typescript
import {
    AccountSettingApi,
    Configuration,
    UpdateEmailRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountSettingApi(configuration);

let updateEmailRequest: UpdateEmailRequest; //

const { status, data } = await apiInstance.updateEmailRequest(
    updateEmailRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **updateEmailRequest** | **UpdateEmailRequest**|  | |


### Return type

**CustomApiResponseVoid**

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

# **updateUsername**
> CustomApiResponseAccountSettingResponse updateUsername(updateUsernameRequest)


### Example

```typescript
import {
    AccountSettingApi,
    Configuration,
    UpdateUsernameRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountSettingApi(configuration);

let updateUsernameRequest: UpdateUsernameRequest; //

const { status, data } = await apiInstance.updateUsername(
    updateUsernameRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **updateUsernameRequest** | **UpdateUsernameRequest**|  | |


### Return type

**CustomApiResponseAccountSettingResponse**

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

# **verifyEmailChange**
> CustomApiResponseVoid verifyEmailChange()


### Example

```typescript
import {
    AccountSettingApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountSettingApi(configuration);

let token: string; // (default to undefined)

const { status, data } = await apiInstance.verifyEmailChange(
    token
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **token** | [**string**] |  | defaults to undefined|


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

