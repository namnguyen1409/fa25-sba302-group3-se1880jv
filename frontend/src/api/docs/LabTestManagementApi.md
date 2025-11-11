# LabTestManagementApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**create4**](LabTestManagementApi.md#create4) | **POST** /api/lab-tests |  |
| [**delete4**](LabTestManagementApi.md#delete4) | **DELETE** /api/lab-tests/{testId} |  |
| [**filter5**](LabTestManagementApi.md#filter5) | **POST** /api/lab-tests/filter |  |
| [**getById1**](LabTestManagementApi.md#getbyid1) | **GET** /api/lab-tests/{testId} |  |
| [**update4**](LabTestManagementApi.md#update4) | **PUT** /api/lab-tests/{testId} |  |



## create4

> CustomApiResponseLabTestResponse create4(labTestRequest)



### Example

```ts
import {
  Configuration,
  LabTestManagementApi,
} from '';
import type { Create4Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new LabTestManagementApi(config);

  const body = {
    // LabTestRequest
    labTestRequest: ...,
  } satisfies Create4Request;

  try {
    const data = await api.create4(body);
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
| **labTestRequest** | [LabTestRequest](LabTestRequest.md) |  | |

### Return type

[**CustomApiResponseLabTestResponse**](CustomApiResponseLabTestResponse.md)

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


## delete4

> CustomApiResponseVoid delete4(testId)



### Example

```ts
import {
  Configuration,
  LabTestManagementApi,
} from '';
import type { Delete4Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new LabTestManagementApi(config);

  const body = {
    // string
    testId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies Delete4Request;

  try {
    const data = await api.delete4(body);
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
| **testId** | `string` |  | [Defaults to `undefined`] |

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


## filter5

> CustomApiResponsePageLabTestResponse filter5(searchFilter)



### Example

```ts
import {
  Configuration,
  LabTestManagementApi,
} from '';
import type { Filter5Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new LabTestManagementApi(config);

  const body = {
    // SearchFilter
    searchFilter: ...,
  } satisfies Filter5Request;

  try {
    const data = await api.filter5(body);
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

[**CustomApiResponsePageLabTestResponse**](CustomApiResponsePageLabTestResponse.md)

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


## getById1

> CustomApiResponseLabTestResponse getById1(testId)



### Example

```ts
import {
  Configuration,
  LabTestManagementApi,
} from '';
import type { GetById1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new LabTestManagementApi(config);

  const body = {
    // string
    testId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies GetById1Request;

  try {
    const data = await api.getById1(body);
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
| **testId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseLabTestResponse**](CustomApiResponseLabTestResponse.md)

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


## update4

> CustomApiResponseLabTestResponse update4(testId, labTestRequest)



### Example

```ts
import {
  Configuration,
  LabTestManagementApi,
} from '';
import type { Update4Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new LabTestManagementApi(config);

  const body = {
    // string
    testId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // LabTestRequest
    labTestRequest: ...,
  } satisfies Update4Request;

  try {
    const data = await api.update4(body);
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
| **testId** | `string` |  | [Defaults to `undefined`] |
| **labTestRequest** | [LabTestRequest](LabTestRequest.md) |  | |

### Return type

[**CustomApiResponseLabTestResponse**](CustomApiResponseLabTestResponse.md)

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

