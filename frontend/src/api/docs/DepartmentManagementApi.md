# DepartmentManagementApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**create1**](DepartmentManagementApi.md#create1) | **POST** /api/organization/departments |  |
| [**delete1**](DepartmentManagementApi.md#delete1) | **DELETE** /api/organization/departments/{id} |  |
| [**filter2**](DepartmentManagementApi.md#filter2) | **POST** /api/organization/departments/filter |  |
| [**filterRoomsByDepartment**](DepartmentManagementApi.md#filterroomsbydepartment) | **POST** /api/organization/departments/{deptId}/rooms |  |
| [**getDepartmentById**](DepartmentManagementApi.md#getdepartmentbyid) | **GET** /api/organization/departments/{id} |  |
| [**update1**](DepartmentManagementApi.md#update1) | **PUT** /api/organization/departments/{id} |  |



## create1

> CustomApiResponseDepartmentResponse create1(departmentRequest)



### Example

```ts
import {
  Configuration,
  DepartmentManagementApi,
} from '';
import type { Create1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new DepartmentManagementApi(config);

  const body = {
    // DepartmentRequest
    departmentRequest: ...,
  } satisfies Create1Request;

  try {
    const data = await api.create1(body);
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
| **departmentRequest** | [DepartmentRequest](DepartmentRequest.md) |  | |

### Return type

[**CustomApiResponseDepartmentResponse**](CustomApiResponseDepartmentResponse.md)

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


## delete1

> CustomApiResponseVoid delete1(id)



### Example

```ts
import {
  Configuration,
  DepartmentManagementApi,
} from '';
import type { Delete1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new DepartmentManagementApi(config);

  const body = {
    // string
    id: id_example,
  } satisfies Delete1Request;

  try {
    const data = await api.delete1(body);
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
| **id** | `string` |  | [Defaults to `undefined`] |

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


## filter2

> CustomApiResponsePageDepartmentResponse filter2(searchFilter)



### Example

```ts
import {
  Configuration,
  DepartmentManagementApi,
} from '';
import type { Filter2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new DepartmentManagementApi(config);

  const body = {
    // SearchFilter
    searchFilter: ...,
  } satisfies Filter2Request;

  try {
    const data = await api.filter2(body);
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
| **searchFilter** | [SearchFilter](SearchFilter.md) |  | |

### Return type

[**CustomApiResponsePageDepartmentResponse**](CustomApiResponsePageDepartmentResponse.md)

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


## filterRoomsByDepartment

> CustomApiResponsePageRoomResponse filterRoomsByDepartment(deptId, searchFilter)



### Example

```ts
import {
  Configuration,
  DepartmentManagementApi,
} from '';
import type { FilterRoomsByDepartmentRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new DepartmentManagementApi(config);

  const body = {
    // string
    deptId: deptId_example,
    // SearchFilter
    searchFilter: ...,
  } satisfies FilterRoomsByDepartmentRequest;

  try {
    const data = await api.filterRoomsByDepartment(body);
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
| **deptId** | `string` |  | [Defaults to `undefined`] |
| **searchFilter** | [SearchFilter](SearchFilter.md) |  | |

### Return type

[**CustomApiResponsePageRoomResponse**](CustomApiResponsePageRoomResponse.md)

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


## getDepartmentById

> CustomApiResponseDepartmentResponse getDepartmentById(id)



### Example

```ts
import {
  Configuration,
  DepartmentManagementApi,
} from '';
import type { GetDepartmentByIdRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new DepartmentManagementApi(config);

  const body = {
    // string
    id: id_example,
  } satisfies GetDepartmentByIdRequest;

  try {
    const data = await api.getDepartmentById(body);
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
| **id** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseDepartmentResponse**](CustomApiResponseDepartmentResponse.md)

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


## update1

> CustomApiResponseDepartmentResponse update1(id, departmentRequest)



### Example

```ts
import {
  Configuration,
  DepartmentManagementApi,
} from '';
import type { Update1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new DepartmentManagementApi(config);

  const body = {
    // string
    id: id_example,
    // DepartmentRequest
    departmentRequest: ...,
  } satisfies Update1Request;

  try {
    const data = await api.update1(body);
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
| **id** | `string` |  | [Defaults to `undefined`] |
| **departmentRequest** | [DepartmentRequest](DepartmentRequest.md) |  | |

### Return type

[**CustomApiResponseDepartmentResponse**](CustomApiResponseDepartmentResponse.md)

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

