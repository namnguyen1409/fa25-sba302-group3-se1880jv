# MedicineManagementApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**create3**](MedicineManagementApi.md#create3) | **POST** /api/medicines |  |
| [**delete3**](MedicineManagementApi.md#delete3) | **DELETE** /api/medicines/{medicineId} |  |
| [**filter4**](MedicineManagementApi.md#filter4) | **POST** /api/medicines/filter |  |
| [**getById**](MedicineManagementApi.md#getbyid) | **GET** /api/medicines/{medicineId} |  |
| [**update3**](MedicineManagementApi.md#update3) | **PUT** /api/medicines/{medicineId} |  |



## create3

> CustomApiResponseMedicineResponse create3(medicineRequest)



### Example

```ts
import {
  Configuration,
  MedicineManagementApi,
} from '';
import type { Create3Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new MedicineManagementApi(config);

  const body = {
    // MedicineRequest
    medicineRequest: ...,
  } satisfies Create3Request;

  try {
    const data = await api.create3(body);
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
| **medicineRequest** | [MedicineRequest](MedicineRequest.md) |  | |

### Return type

[**CustomApiResponseMedicineResponse**](CustomApiResponseMedicineResponse.md)

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


## delete3

> CustomApiResponseVoid delete3(medicineId)



### Example

```ts
import {
  Configuration,
  MedicineManagementApi,
} from '';
import type { Delete3Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new MedicineManagementApi(config);

  const body = {
    // string
    medicineId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies Delete3Request;

  try {
    const data = await api.delete3(body);
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
| **medicineId** | `string` |  | [Defaults to `undefined`] |

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


## filter4

> CustomApiResponsePageMedicineResponse filter4(searchFilter)



### Example

```ts
import {
  Configuration,
  MedicineManagementApi,
} from '';
import type { Filter4Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new MedicineManagementApi(config);

  const body = {
    // SearchFilter
    searchFilter: ...,
  } satisfies Filter4Request;

  try {
    const data = await api.filter4(body);
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

[**CustomApiResponsePageMedicineResponse**](CustomApiResponsePageMedicineResponse.md)

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


## getById

> CustomApiResponseMedicineResponse getById(medicineId)



### Example

```ts
import {
  Configuration,
  MedicineManagementApi,
} from '';
import type { GetByIdRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new MedicineManagementApi(config);

  const body = {
    // string
    medicineId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies GetByIdRequest;

  try {
    const data = await api.getById(body);
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
| **medicineId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseMedicineResponse**](CustomApiResponseMedicineResponse.md)

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


## update3

> CustomApiResponseMedicineResponse update3(medicineId, medicineRequest)



### Example

```ts
import {
  Configuration,
  MedicineManagementApi,
} from '';
import type { Update3Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new MedicineManagementApi(config);

  const body = {
    // string
    medicineId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // MedicineRequest
    medicineRequest: ...,
  } satisfies Update3Request;

  try {
    const data = await api.update3(body);
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
| **medicineId** | `string` |  | [Defaults to `undefined`] |
| **medicineRequest** | [MedicineRequest](MedicineRequest.md) |  | |

### Return type

[**CustomApiResponseMedicineResponse**](CustomApiResponseMedicineResponse.md)

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

