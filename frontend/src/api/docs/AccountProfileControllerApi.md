# AccountProfileControllerApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**getProfile**](#getprofile) | **GET** /api/account/profile | |
|[**updateAvatar**](#updateavatar) | **POST** /api/account/profile/avatar | |
|[**updateProfile**](#updateprofile) | **PUT** /api/account/profile | |

# **getProfile**
> CustomApiResponseUserProfileResponse getProfile()


### Example

```typescript
import {
    AccountProfileControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountProfileControllerApi(configuration);

const { status, data } = await apiInstance.getProfile();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**CustomApiResponseUserProfileResponse**

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

# **updateAvatar**
> CustomApiResponseUserProfileResponse updateAvatar()


### Example

```typescript
import {
    AccountProfileControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountProfileControllerApi(configuration);

let file: File; // (default to undefined)

const { status, data } = await apiInstance.updateAvatar(
    file
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **file** | [**File**] |  | defaults to undefined|


### Return type

**CustomApiResponseUserProfileResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**404** | Not Found |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **updateProfile**
> CustomApiResponseUserProfileResponse updateProfile(userProfileRequest)


### Example

```typescript
import {
    AccountProfileControllerApi,
    Configuration,
    UserProfileRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new AccountProfileControllerApi(configuration);

let userProfileRequest: UserProfileRequest; //

const { status, data } = await apiInstance.updateProfile(
    userProfileRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **userProfileRequest** | **UserProfileRequest**|  | |


### Return type

**CustomApiResponseUserProfileResponse**

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

