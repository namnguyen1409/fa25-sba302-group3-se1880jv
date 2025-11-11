# SpecialtyManagementApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**create12**](#create12) | **POST** /api/admin/specialties | |
|[**delete12**](#delete12) | **DELETE** /api/admin/specialties/{id} | |
|[**filter14**](#filter14) | **POST** /api/admin/specialties/filter | |
|[**getById8**](#getbyid8) | **GET** /api/admin/specialties/{id} | |
|[**update12**](#update12) | **PUT** /api/admin/specialties/{id} | |

# **create12**
> CustomApiResponseSpecialtyResponse create12(specialtyRequest)


### Example

```typescript
import {
    SpecialtyManagementApi,
    Configuration,
    SpecialtyRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new SpecialtyManagementApi(configuration);

let specialtyRequest: SpecialtyRequest; //

const { status, data } = await apiInstance.create12(
    specialtyRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **specialtyRequest** | **SpecialtyRequest**|  | |


### Return type

**CustomApiResponseSpecialtyResponse**

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

# **delete12**
> CustomApiResponseVoid delete12()


### Example

```typescript
import {
    SpecialtyManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new SpecialtyManagementApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.delete12(
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

# **filter14**
> CustomApiResponsePageSpecialtyResponse filter14(searchFilter)


### Example

```typescript
import {
    SpecialtyManagementApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new SpecialtyManagementApi(configuration);

let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filter14(
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |


### Return type

**CustomApiResponsePageSpecialtyResponse**

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

# **getById8**
> CustomApiResponseSpecialtyResponse getById8()


### Example

```typescript
import {
    SpecialtyManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new SpecialtyManagementApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.getById8(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseSpecialtyResponse**

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

# **update12**
> CustomApiResponseSpecialtyResponse update12(specialtyRequest)


### Example

```typescript
import {
    SpecialtyManagementApi,
    Configuration,
    SpecialtyRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new SpecialtyManagementApi(configuration);

let id: string; // (default to undefined)
let specialtyRequest: SpecialtyRequest; //

const { status, data } = await apiInstance.update12(
    id,
    specialtyRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **specialtyRequest** | **SpecialtyRequest**|  | |
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseSpecialtyResponse**

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

