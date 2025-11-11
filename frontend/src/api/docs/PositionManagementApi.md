# PositionManagementApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**create13**](#create13) | **POST** /api/admin/positions | |
|[**delete13**](#delete13) | **DELETE** /api/admin/positions/{id} | |
|[**filter15**](#filter15) | **POST** /api/admin/positions/filter | |
|[**getById9**](#getbyid9) | **GET** /api/admin/positions/{id} | |
|[**update13**](#update13) | **PUT** /api/admin/positions/{id} | |

# **create13**
> CustomApiResponsePositionResponse create13(positionRequest)


### Example

```typescript
import {
    PositionManagementApi,
    Configuration,
    PositionRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new PositionManagementApi(configuration);

let positionRequest: PositionRequest; //

const { status, data } = await apiInstance.create13(
    positionRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **positionRequest** | **PositionRequest**|  | |


### Return type

**CustomApiResponsePositionResponse**

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

# **delete13**
> CustomApiResponseVoid delete13()


### Example

```typescript
import {
    PositionManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new PositionManagementApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.delete13(
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

# **filter15**
> CustomApiResponsePagePositionResponse filter15(searchFilter)


### Example

```typescript
import {
    PositionManagementApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new PositionManagementApi(configuration);

let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filter15(
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |


### Return type

**CustomApiResponsePagePositionResponse**

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

# **getById9**
> CustomApiResponsePositionResponse getById9()


### Example

```typescript
import {
    PositionManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new PositionManagementApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.getById9(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponsePositionResponse**

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

# **update13**
> CustomApiResponsePositionResponse update13(positionRequest)


### Example

```typescript
import {
    PositionManagementApi,
    Configuration,
    PositionRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new PositionManagementApi(configuration);

let id: string; // (default to undefined)
let positionRequest: PositionRequest; //

const { status, data } = await apiInstance.update13(
    id,
    positionRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **positionRequest** | **PositionRequest**|  | |
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponsePositionResponse**

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

