# AppointmentManagementApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**create10**](AppointmentManagementApi.md#create10) | **POST** /api/appointments |  |
| [**delete10**](AppointmentManagementApi.md#delete10) | **DELETE** /api/appointments/{appointmentId} |  |
| [**filter11**](AppointmentManagementApi.md#filter11) | **POST** /api/appointments/filter |  |
| [**getById6**](AppointmentManagementApi.md#getbyid6) | **GET** /api/appointments/{appointmentId} |  |
| [**update10**](AppointmentManagementApi.md#update10) | **PUT** /api/appointments/{appointmentId} |  |



## create10

> CustomApiResponseAppointmentResponse create10(appointmentRequest)



### Example

```ts
import {
  Configuration,
  AppointmentManagementApi,
} from '';
import type { Create10Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AppointmentManagementApi(config);

  const body = {
    // AppointmentRequest
    appointmentRequest: ...,
  } satisfies Create10Request;

  try {
    const data = await api.create10(body);
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
| **appointmentRequest** | [AppointmentRequest](AppointmentRequest.md) |  | |

### Return type

[**CustomApiResponseAppointmentResponse**](CustomApiResponseAppointmentResponse.md)

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


## delete10

> CustomApiResponseVoid delete10(appointmentId)



### Example

```ts
import {
  Configuration,
  AppointmentManagementApi,
} from '';
import type { Delete10Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AppointmentManagementApi(config);

  const body = {
    // string
    appointmentId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies Delete10Request;

  try {
    const data = await api.delete10(body);
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
| **appointmentId** | `string` |  | [Defaults to `undefined`] |

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


## filter11

> CustomApiResponsePageAppointmentResponse filter11(searchFilter)



### Example

```ts
import {
  Configuration,
  AppointmentManagementApi,
} from '';
import type { Filter11Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AppointmentManagementApi(config);

  const body = {
    // SearchFilter
    searchFilter: ...,
  } satisfies Filter11Request;

  try {
    const data = await api.filter11(body);
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

[**CustomApiResponsePageAppointmentResponse**](CustomApiResponsePageAppointmentResponse.md)

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


## getById6

> CustomApiResponseAppointmentResponse getById6(appointmentId)



### Example

```ts
import {
  Configuration,
  AppointmentManagementApi,
} from '';
import type { GetById6Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AppointmentManagementApi(config);

  const body = {
    // string
    appointmentId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies GetById6Request;

  try {
    const data = await api.getById6(body);
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
| **appointmentId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseAppointmentResponse**](CustomApiResponseAppointmentResponse.md)

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


## update10

> CustomApiResponseAppointmentResponse update10(appointmentId, appointmentRequest)



### Example

```ts
import {
  Configuration,
  AppointmentManagementApi,
} from '';
import type { Update10Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new AppointmentManagementApi(config);

  const body = {
    // string
    appointmentId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // AppointmentRequest
    appointmentRequest: ...,
  } satisfies Update10Request;

  try {
    const data = await api.update10(body);
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
| **appointmentId** | `string` |  | [Defaults to `undefined`] |
| **appointmentRequest** | [AppointmentRequest](AppointmentRequest.md) |  | |

### Return type

[**CustomApiResponseAppointmentResponse**](CustomApiResponseAppointmentResponse.md)

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

