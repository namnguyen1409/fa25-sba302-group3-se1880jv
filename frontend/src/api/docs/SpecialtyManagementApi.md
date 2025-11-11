# SpecialtyManagementApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**create12**](SpecialtyManagementApi.md#create12) | **POST** /api/admin/specialties |  |
| [**delete12**](SpecialtyManagementApi.md#delete12) | **DELETE** /api/admin/specialties/{id} |  |
| [**filter14**](SpecialtyManagementApi.md#filter14) | **POST** /api/admin/specialties/filter |  |
| [**getById8**](SpecialtyManagementApi.md#getbyid8) | **GET** /api/admin/specialties/{id} |  |
| [**update12**](SpecialtyManagementApi.md#update12) | **PUT** /api/admin/specialties/{id} |  |



## create12

> CustomApiResponseSpecialtyResponse create12(specialtyRequest)



### Example

```ts
import {
  Configuration,
  SpecialtyManagementApi,
} from '';
import type { Create12Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new SpecialtyManagementApi(config);

  const body = {
    // SpecialtyRequest
    specialtyRequest: ...,
  } satisfies Create12Request;

  try {
    const data = await api.create12(body);
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
| **specialtyRequest** | [SpecialtyRequest](SpecialtyRequest.md) |  | |

### Return type

[**CustomApiResponseSpecialtyResponse**](CustomApiResponseSpecialtyResponse.md)

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


## delete12

> CustomApiResponseVoid delete12(id)



### Example

```ts
import {
  Configuration,
  SpecialtyManagementApi,
} from '';
import type { Delete12Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new SpecialtyManagementApi(config);

  const body = {
    // string
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies Delete12Request;

  try {
    const data = await api.delete12(body);
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


## filter14

> CustomApiResponsePageSpecialtyResponse filter14(searchFilter)



### Example

```ts
import {
  Configuration,
  SpecialtyManagementApi,
} from '';
import type { Filter14Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new SpecialtyManagementApi(config);

  const body = {
    // SearchFilter
    searchFilter: ...,
  } satisfies Filter14Request;

  try {
    const data = await api.filter14(body);
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

[**CustomApiResponsePageSpecialtyResponse**](CustomApiResponsePageSpecialtyResponse.md)

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


## getById8

> CustomApiResponseSpecialtyResponse getById8(id)



### Example

```ts
import {
  Configuration,
  SpecialtyManagementApi,
} from '';
import type { GetById8Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new SpecialtyManagementApi(config);

  const body = {
    // string
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies GetById8Request;

  try {
    const data = await api.getById8(body);
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

[**CustomApiResponseSpecialtyResponse**](CustomApiResponseSpecialtyResponse.md)

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


## update12

> CustomApiResponseSpecialtyResponse update12(id, specialtyRequest)



### Example

```ts
import {
  Configuration,
  SpecialtyManagementApi,
} from '';
import type { Update12Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new SpecialtyManagementApi(config);

  const body = {
    // string
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // SpecialtyRequest
    specialtyRequest: ...,
  } satisfies Update12Request;

  try {
    const data = await api.update12(body);
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
| **specialtyRequest** | [SpecialtyRequest](SpecialtyRequest.md) |  | |

### Return type

[**CustomApiResponseSpecialtyResponse**](CustomApiResponseSpecialtyResponse.md)

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

