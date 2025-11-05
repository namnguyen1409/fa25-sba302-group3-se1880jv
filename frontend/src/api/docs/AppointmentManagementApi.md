# AppointmentManagementApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**create9**](AppointmentManagementApi.md#create9) | **POST** /api/appointments |  |
| [**delete9**](AppointmentManagementApi.md#delete9) | **DELETE** /api/appointments/{appointmentId} |  |
| [**filter10**](AppointmentManagementApi.md#filter10) | **POST** /api/appointments/filter |  |
| [**getById5**](AppointmentManagementApi.md#getbyid5) | **GET** /api/appointments/{appointmentId} |  |
| [**update9**](AppointmentManagementApi.md#update9) | **PUT** /api/appointments/{appointmentId} |  |



## create9

> CustomApiResponseAppointmentResponse create9(appointmentRequest)



### Example

```ts
import {
  Configuration,
  AppointmentManagementApi,
} from '';
import type { Create9Request } from '';

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
  } satisfies Create9Request;

  try {
    const data = await api.create9(body);
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


## delete9

> CustomApiResponseVoid delete9(appointmentId)



### Example

```ts
import {
  Configuration,
  AppointmentManagementApi,
} from '';
import type { Delete9Request } from '';

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
  } satisfies Delete9Request;

  try {
    const data = await api.delete9(body);
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


## filter10

> CustomApiResponsePageAppointmentResponse filter10(searchFilter)



### Example

```ts
import {
  Configuration,
  AppointmentManagementApi,
} from '';
import type { Filter10Request } from '';

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
  } satisfies Filter10Request;

  try {
    const data = await api.filter10(body);
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


## getById5

> CustomApiResponseAppointmentResponse getById5(appointmentId)



### Example

```ts
import {
  Configuration,
  AppointmentManagementApi,
} from '';
import type { GetById5Request } from '';

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
  } satisfies GetById5Request;

  try {
    const data = await api.getById5(body);
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


## update9

> CustomApiResponseAppointmentResponse update9(appointmentId, appointmentRequest)



### Example

```ts
import {
  Configuration,
  AppointmentManagementApi,
} from '';
import type { Update9Request } from '';

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
  } satisfies Update9Request;

  try {
    const data = await api.update9(body);
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

