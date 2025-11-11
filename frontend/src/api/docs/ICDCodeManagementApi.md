# ICDCodeManagementApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**createIcdCode**](#createicdcode) | **POST** /api/common/icd-codes | |
|[**deleteIcdCode**](#deleteicdcode) | **DELETE** /api/common/icd-codes/{id} | |
|[**filter9**](#filter9) | **POST** /api/common/icd-codes/filter | |
|[**getIcdCodeById**](#geticdcodebyid) | **GET** /api/common/icd-codes/{id} | |
|[**updateIcdCode**](#updateicdcode) | **PUT** /api/common/icd-codes/{id} | |

# **createIcdCode**
> CustomApiResponseIcdCodeResponse createIcdCode(icdCodeRequest)


### Example

```typescript
import {
    ICDCodeManagementApi,
    Configuration,
    IcdCodeRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new ICDCodeManagementApi(configuration);

let icdCodeRequest: IcdCodeRequest; //

const { status, data } = await apiInstance.createIcdCode(
    icdCodeRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **icdCodeRequest** | **IcdCodeRequest**|  | |


### Return type

**CustomApiResponseIcdCodeResponse**

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

# **deleteIcdCode**
> CustomApiResponseVoid deleteIcdCode()


### Example

```typescript
import {
    ICDCodeManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new ICDCodeManagementApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.deleteIcdCode(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|


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

# **filter9**
> CustomApiResponsePageIcdCodeResponse filter9(searchFilter)


### Example

```typescript
import {
    ICDCodeManagementApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new ICDCodeManagementApi(configuration);

let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filter9(
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |


### Return type

**CustomApiResponsePageIcdCodeResponse**

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

# **getIcdCodeById**
> CustomApiResponseIcdCodeResponse getIcdCodeById()


### Example

```typescript
import {
    ICDCodeManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new ICDCodeManagementApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.getIcdCodeById(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseIcdCodeResponse**

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

# **updateIcdCode**
> CustomApiResponseIcdCodeResponse updateIcdCode(icdCodeRequest)


### Example

```typescript
import {
    ICDCodeManagementApi,
    Configuration,
    IcdCodeRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new ICDCodeManagementApi(configuration);

let id: string; // (default to undefined)
let icdCodeRequest: IcdCodeRequest; //

const { status, data } = await apiInstance.updateIcdCode(
    id,
    icdCodeRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **icdCodeRequest** | **IcdCodeRequest**|  | |
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseIcdCodeResponse**

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

