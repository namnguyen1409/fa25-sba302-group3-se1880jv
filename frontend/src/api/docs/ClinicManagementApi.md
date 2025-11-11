# ClinicManagementApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**create2**](ClinicManagementApi.md#create2) | **POST** /api/organization/clinics |  |
| [**delete2**](ClinicManagementApi.md#delete2) | **DELETE** /api/organization/clinics/{id} |  |
| [**filter3**](ClinicManagementApi.md#filter3) | **POST** /api/organization/clinics/filter |  |
| [**filterDepartmentsByClinic**](ClinicManagementApi.md#filterdepartmentsbyclinic) | **POST** /api/organization/clinics/{clinicId}/departments/filter |  |
| [**getClinicById**](ClinicManagementApi.md#getclinicbyid) | **GET** /api/organization/clinics/{id} |  |
| [**getDefaultClinic**](ClinicManagementApi.md#getdefaultclinic) | **GET** /api/organization/clinics |  |
| [**update2**](ClinicManagementApi.md#update2) | **PUT** /api/organization/clinics/{id} |  |



## create2

> CustomApiResponseClinicResponse create2(clinicRequest)



### Example

```ts
import {
  Configuration,
  ClinicManagementApi,
} from '';
import type { Create2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ClinicManagementApi(config);

  const body = {
    // ClinicRequest
    clinicRequest: ...,
  } satisfies Create2Request;

  try {
    const data = await api.create2(body);
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
| **clinicRequest** | [ClinicRequest](ClinicRequest.md) |  | |

### Return type

[**CustomApiResponseClinicResponse**](CustomApiResponseClinicResponse.md)

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


## delete2

> CustomApiResponseVoid delete2(id)



### Example

```ts
import {
  Configuration,
  ClinicManagementApi,
} from '';
import type { Delete2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ClinicManagementApi(config);

  const body = {
    // string
    id: id_example,
  } satisfies Delete2Request;

  try {
    const data = await api.delete2(body);
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


## filter3

> CustomApiResponsePageClinicResponse filter3(searchFilter)



### Example

```ts
import {
  Configuration,
  ClinicManagementApi,
} from '';
import type { Filter3Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ClinicManagementApi(config);

  const body = {
    // SearchFilter
    searchFilter: ...,
  } satisfies Filter3Request;

  try {
    const data = await api.filter3(body);
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

[**CustomApiResponsePageClinicResponse**](CustomApiResponsePageClinicResponse.md)

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


## filterDepartmentsByClinic

> CustomApiResponsePageDepartmentResponse filterDepartmentsByClinic(clinicId, searchFilter)



### Example

```ts
import {
  Configuration,
  ClinicManagementApi,
} from '';
import type { FilterDepartmentsByClinicRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ClinicManagementApi(config);

  const body = {
    // string
    clinicId: clinicId_example,
    // SearchFilter
    searchFilter: ...,
  } satisfies FilterDepartmentsByClinicRequest;

  try {
    const data = await api.filterDepartmentsByClinic(body);
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
| **clinicId** | `string` |  | [Defaults to `undefined`] |
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


## getClinicById

> CustomApiResponseClinicResponse getClinicById(id)



### Example

```ts
import {
  Configuration,
  ClinicManagementApi,
} from '';
import type { GetClinicByIdRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ClinicManagementApi(config);

  const body = {
    // string
    id: id_example,
  } satisfies GetClinicByIdRequest;

  try {
    const data = await api.getClinicById(body);
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

[**CustomApiResponseClinicResponse**](CustomApiResponseClinicResponse.md)

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


## getDefaultClinic

> CustomApiResponseClinicResponse getDefaultClinic()



### Example

```ts
import {
  Configuration,
  ClinicManagementApi,
} from '';
import type { GetDefaultClinicRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ClinicManagementApi(config);

  try {
    const data = await api.getDefaultClinic();
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

[**CustomApiResponseClinicResponse**](CustomApiResponseClinicResponse.md)

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


## update2

> CustomApiResponseClinicResponse update2(id, clinicRequest)



### Example

```ts
import {
  Configuration,
  ClinicManagementApi,
} from '';
import type { Update2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ClinicManagementApi(config);

  const body = {
    // string
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // ClinicRequest
    clinicRequest: ...,
  } satisfies Update2Request;

  try {
    const data = await api.update2(body);
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
| **clinicRequest** | [ClinicRequest](ClinicRequest.md) |  | |

### Return type

[**CustomApiResponseClinicResponse**](CustomApiResponseClinicResponse.md)

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

