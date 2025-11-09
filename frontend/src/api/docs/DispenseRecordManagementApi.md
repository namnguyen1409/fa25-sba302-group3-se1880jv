# DispenseRecordManagementApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**create8**](DispenseRecordManagementApi.md#create8) | **POST** /api/dispense-records |  |
| [**delete8**](DispenseRecordManagementApi.md#delete8) | **DELETE** /api/dispense-records/{recordId} |  |
| [**filter8**](DispenseRecordManagementApi.md#filter8) | **POST** /api/dispense-records/filter |  |
| [**getById4**](DispenseRecordManagementApi.md#getbyid4) | **GET** /api/dispense-records/{recordId} |  |
| [**getOrdersForStaffToday1**](DispenseRecordManagementApi.md#getordersforstafftoday1) | **GET** /api/dispense-records/staff/today |  |
| [**update8**](DispenseRecordManagementApi.md#update8) | **PUT** /api/dispense-records/{recordId} |  |



## create8

> CustomApiResponseDispenseRecordResponse create8(dispenseRecordRequest)



### Example

```ts
import {
  Configuration,
  DispenseRecordManagementApi,
} from '';
import type { Create8Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new DispenseRecordManagementApi(config);

  const body = {
    // DispenseRecordRequest
    dispenseRecordRequest: ...,
  } satisfies Create8Request;

  try {
    const data = await api.create8(body);
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
| **dispenseRecordRequest** | [DispenseRecordRequest](DispenseRecordRequest.md) |  | |

### Return type

[**CustomApiResponseDispenseRecordResponse**](CustomApiResponseDispenseRecordResponse.md)

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


## delete8

> CustomApiResponseVoid delete8(recordId)



### Example

```ts
import {
  Configuration,
  DispenseRecordManagementApi,
} from '';
import type { Delete8Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new DispenseRecordManagementApi(config);

  const body = {
    // string
    recordId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies Delete8Request;

  try {
    const data = await api.delete8(body);
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
| **recordId** | `string` |  | [Defaults to `undefined`] |

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


## filter8

> CustomApiResponsePageDispenseRecordResponse filter8(searchFilter)



### Example

```ts
import {
  Configuration,
  DispenseRecordManagementApi,
} from '';
import type { Filter8Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new DispenseRecordManagementApi(config);

  const body = {
    // SearchFilter
    searchFilter: ...,
  } satisfies Filter8Request;

  try {
    const data = await api.filter8(body);
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

[**CustomApiResponsePageDispenseRecordResponse**](CustomApiResponsePageDispenseRecordResponse.md)

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


## getById4

> CustomApiResponseDispenseRecordResponse getById4(recordId)



### Example

```ts
import {
  Configuration,
  DispenseRecordManagementApi,
} from '';
import type { GetById4Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new DispenseRecordManagementApi(config);

  const body = {
    // string
    recordId: recordId_example,
  } satisfies GetById4Request;

  try {
    const data = await api.getById4(body);
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
| **recordId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseDispenseRecordResponse**](CustomApiResponseDispenseRecordResponse.md)

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


## getOrdersForStaffToday1

> CustomApiResponseListDispenseRecordResponse getOrdersForStaffToday1()



### Example

```ts
import {
  Configuration,
  DispenseRecordManagementApi,
} from '';
import type { GetOrdersForStaffToday1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new DispenseRecordManagementApi(config);

  try {
    const data = await api.getOrdersForStaffToday1();
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

[**CustomApiResponseListDispenseRecordResponse**](CustomApiResponseListDispenseRecordResponse.md)

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


## update8

> CustomApiResponseDispenseRecordResponse update8(recordId, dispenseRecordRequest)



### Example

```ts
import {
  Configuration,
  DispenseRecordManagementApi,
} from '';
import type { Update8Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new DispenseRecordManagementApi(config);

  const body = {
    // string
    recordId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // DispenseRecordRequest
    dispenseRecordRequest: ...,
  } satisfies Update8Request;

  try {
    const data = await api.update8(body);
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
| **recordId** | `string` |  | [Defaults to `undefined`] |
| **dispenseRecordRequest** | [DispenseRecordRequest](DispenseRecordRequest.md) |  | |

### Return type

[**CustomApiResponseDispenseRecordResponse**](CustomApiResponseDispenseRecordResponse.md)

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

