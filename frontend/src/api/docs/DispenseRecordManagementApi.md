# DispenseRecordManagementApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**create8**](#create8) | **POST** /api/dispense-records | |
|[**delete8**](#delete8) | **DELETE** /api/dispense-records/{recordId} | |
|[**filter8**](#filter8) | **POST** /api/dispense-records/filter | |
|[**getById4**](#getbyid4) | **GET** /api/dispense-records/{recordId} | |
|[**getOrdersForStaffToday1**](#getordersforstafftoday1) | **GET** /api/dispense-records/staff/today | |
|[**update8**](#update8) | **PUT** /api/dispense-records/{recordId} | |

# **create8**
> CustomApiResponseDispenseRecordResponse create8(dispenseRecordRequest)


### Example

```typescript
import {
    DispenseRecordManagementApi,
    Configuration,
    DispenseRecordRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new DispenseRecordManagementApi(configuration);

let dispenseRecordRequest: DispenseRecordRequest; //

const { status, data } = await apiInstance.create8(
    dispenseRecordRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **dispenseRecordRequest** | **DispenseRecordRequest**|  | |


### Return type

**CustomApiResponseDispenseRecordResponse**

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

# **delete8**
> CustomApiResponseVoid delete8()


### Example

```typescript
import {
    DispenseRecordManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new DispenseRecordManagementApi(configuration);

let recordId: string; // (default to undefined)

const { status, data } = await apiInstance.delete8(
    recordId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **recordId** | [**string**] |  | defaults to undefined|


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

# **filter8**
> CustomApiResponsePageDispenseRecordResponse filter8(searchFilter)


### Example

```typescript
import {
    DispenseRecordManagementApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new DispenseRecordManagementApi(configuration);

let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filter8(
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |


### Return type

**CustomApiResponsePageDispenseRecordResponse**

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

# **getById4**
> CustomApiResponseDispenseRecordResponse getById4()


### Example

```typescript
import {
    DispenseRecordManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new DispenseRecordManagementApi(configuration);

let recordId: string; // (default to undefined)

const { status, data } = await apiInstance.getById4(
    recordId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **recordId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseDispenseRecordResponse**

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

# **getOrdersForStaffToday1**
> CustomApiResponseListDispenseRecordResponse getOrdersForStaffToday1()


### Example

```typescript
import {
    DispenseRecordManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new DispenseRecordManagementApi(configuration);

const { status, data } = await apiInstance.getOrdersForStaffToday1();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**CustomApiResponseListDispenseRecordResponse**

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

# **update8**
> CustomApiResponseDispenseRecordResponse update8(dispenseRecordRequest)


### Example

```typescript
import {
    DispenseRecordManagementApi,
    Configuration,
    DispenseRecordRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new DispenseRecordManagementApi(configuration);

let recordId: string; // (default to undefined)
let dispenseRecordRequest: DispenseRecordRequest; //

const { status, data } = await apiInstance.update8(
    recordId,
    dispenseRecordRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **dispenseRecordRequest** | **DispenseRecordRequest**|  | |
| **recordId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseDispenseRecordResponse**

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

