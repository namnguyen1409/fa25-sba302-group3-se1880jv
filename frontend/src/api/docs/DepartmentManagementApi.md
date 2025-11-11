# DepartmentManagementApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**create1**](#create1) | **POST** /api/organization/departments | |
|[**delete1**](#delete1) | **DELETE** /api/organization/departments/{id} | |
|[**filter2**](#filter2) | **POST** /api/organization/departments/filter | |
|[**filterRoomsByDepartment**](#filterroomsbydepartment) | **POST** /api/organization/departments/{deptId}/rooms | |
|[**getDepartmentById**](#getdepartmentbyid) | **GET** /api/organization/departments/{id} | |
|[**update1**](#update1) | **PUT** /api/organization/departments/{id} | |

# **create1**
> CustomApiResponseDepartmentResponse create1(departmentRequest)


### Example

```typescript
import {
    DepartmentManagementApi,
    Configuration,
    DepartmentRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new DepartmentManagementApi(configuration);

let departmentRequest: DepartmentRequest; //

const { status, data } = await apiInstance.create1(
    departmentRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **departmentRequest** | **DepartmentRequest**|  | |


### Return type

**CustomApiResponseDepartmentResponse**

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

# **delete1**
> CustomApiResponseVoid delete1()


### Example

```typescript
import {
    DepartmentManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new DepartmentManagementApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.delete1(
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

# **filter2**
> CustomApiResponsePageDepartmentResponse filter2(searchFilter)


### Example

```typescript
import {
    DepartmentManagementApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new DepartmentManagementApi(configuration);

let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filter2(
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |


### Return type

**CustomApiResponsePageDepartmentResponse**

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

# **filterRoomsByDepartment**
> CustomApiResponsePageRoomResponse filterRoomsByDepartment(searchFilter)


### Example

```typescript
import {
    DepartmentManagementApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new DepartmentManagementApi(configuration);

let deptId: string; // (default to undefined)
let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filterRoomsByDepartment(
    deptId,
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |
| **deptId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponsePageRoomResponse**

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

# **getDepartmentById**
> CustomApiResponseDepartmentResponse getDepartmentById()


### Example

```typescript
import {
    DepartmentManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new DepartmentManagementApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.getDepartmentById(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseDepartmentResponse**

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

# **update1**
> CustomApiResponseDepartmentResponse update1(departmentRequest)


### Example

```typescript
import {
    DepartmentManagementApi,
    Configuration,
    DepartmentRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new DepartmentManagementApi(configuration);

let id: string; // (default to undefined)
let departmentRequest: DepartmentRequest; //

const { status, data } = await apiInstance.update1(
    id,
    departmentRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **departmentRequest** | **DepartmentRequest**|  | |
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseDepartmentResponse**

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

