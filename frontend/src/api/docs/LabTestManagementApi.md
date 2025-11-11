# LabTestManagementApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**create4**](#create4) | **POST** /api/lab-tests | |
|[**delete4**](#delete4) | **DELETE** /api/lab-tests/{testId} | |
|[**filter5**](#filter5) | **POST** /api/lab-tests/filter | |
|[**getById1**](#getbyid1) | **GET** /api/lab-tests/{testId} | |
|[**update4**](#update4) | **PUT** /api/lab-tests/{testId} | |

# **create4**
> CustomApiResponseLabTestResponse create4(labTestRequest)


### Example

```typescript
import {
    LabTestManagementApi,
    Configuration,
    LabTestRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new LabTestManagementApi(configuration);

let labTestRequest: LabTestRequest; //

const { status, data } = await apiInstance.create4(
    labTestRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **labTestRequest** | **LabTestRequest**|  | |


### Return type

**CustomApiResponseLabTestResponse**

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

# **delete4**
> CustomApiResponseVoid delete4()


### Example

```typescript
import {
    LabTestManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new LabTestManagementApi(configuration);

let testId: string; // (default to undefined)

const { status, data } = await apiInstance.delete4(
    testId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **testId** | [**string**] |  | defaults to undefined|


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

# **filter5**
> CustomApiResponsePageLabTestResponse filter5(searchFilter)


### Example

```typescript
import {
    LabTestManagementApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new LabTestManagementApi(configuration);

let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filter5(
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |


### Return type

**CustomApiResponsePageLabTestResponse**

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

# **getById1**
> CustomApiResponseLabTestResponse getById1()


### Example

```typescript
import {
    LabTestManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new LabTestManagementApi(configuration);

let testId: string; // (default to undefined)

const { status, data } = await apiInstance.getById1(
    testId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **testId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseLabTestResponse**

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

# **update4**
> CustomApiResponseLabTestResponse update4(labTestRequest)


### Example

```typescript
import {
    LabTestManagementApi,
    Configuration,
    LabTestRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new LabTestManagementApi(configuration);

let testId: string; // (default to undefined)
let labTestRequest: LabTestRequest; //

const { status, data } = await apiInstance.update4(
    testId,
    labTestRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **labTestRequest** | **LabTestRequest**|  | |
| **testId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseLabTestResponse**

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

