# QueueTicketManagementApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**callQueueTicket**](QueueTicketManagementApi.md#callqueueticket) | **POST** /api/queue-tickets/{queueTicketId}/call |  |
| [**doneQueueTicket**](QueueTicketManagementApi.md#donequeueticket) | **POST** /api/queue-tickets/{queueTicketId}/done |  |
| [**getQueueForDoctorToday**](QueueTicketManagementApi.md#getqueuefordoctortoday) | **GET** /api/queue-tickets/doctor/today |  |
| [**requeueQueueTicket**](QueueTicketManagementApi.md#requeuequeueticket) | **POST** /api/queue-tickets/{queueTicketId}/requeue |  |
| [**resumeQueueTicket**](QueueTicketManagementApi.md#resumequeueticket) | **POST** /api/queue-tickets/{queueTicketId}/resume |  |
| [**skipQueueTicket**](QueueTicketManagementApi.md#skipqueueticket) | **POST** /api/queue-tickets/{queueTicketId}/skip |  |
| [**startQueueTicket**](QueueTicketManagementApi.md#startqueueticket) | **POST** /api/queue-tickets/{queueTicketId}/start |  |
| [**waitResultQueueTicket**](QueueTicketManagementApi.md#waitresultqueueticket) | **POST** /api/queue-tickets/{queueTicketId}/wait-result |  |



## callQueueTicket

> CustomApiResponseQueueTicketResponse callQueueTicket(queueTicketId)



### Example

```ts
import {
  Configuration,
  QueueTicketManagementApi,
} from '';
import type { CallQueueTicketRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new QueueTicketManagementApi(config);

  const body = {
    // string
    queueTicketId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies CallQueueTicketRequest;

  try {
    const data = await api.callQueueTicket(body);
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
| **queueTicketId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseQueueTicketResponse**](CustomApiResponseQueueTicketResponse.md)

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


## doneQueueTicket

> CustomApiResponseQueueTicketResponse doneQueueTicket(queueTicketId)



### Example

```ts
import {
  Configuration,
  QueueTicketManagementApi,
} from '';
import type { DoneQueueTicketRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new QueueTicketManagementApi(config);

  const body = {
    // string
    queueTicketId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies DoneQueueTicketRequest;

  try {
    const data = await api.doneQueueTicket(body);
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
| **queueTicketId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseQueueTicketResponse**](CustomApiResponseQueueTicketResponse.md)

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


## getQueueForDoctorToday

> CustomApiResponseListQueueTicketResponse getQueueForDoctorToday()



### Example

```ts
import {
  Configuration,
  QueueTicketManagementApi,
} from '';
import type { GetQueueForDoctorTodayRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new QueueTicketManagementApi(config);

  try {
    const data = await api.getQueueForDoctorToday();
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

[**CustomApiResponseListQueueTicketResponse**](CustomApiResponseListQueueTicketResponse.md)

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


## requeueQueueTicket

> CustomApiResponseQueueTicketResponse requeueQueueTicket(queueTicketId)



### Example

```ts
import {
  Configuration,
  QueueTicketManagementApi,
} from '';
import type { RequeueQueueTicketRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new QueueTicketManagementApi(config);

  const body = {
    // string
    queueTicketId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies RequeueQueueTicketRequest;

  try {
    const data = await api.requeueQueueTicket(body);
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
| **queueTicketId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseQueueTicketResponse**](CustomApiResponseQueueTicketResponse.md)

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


## resumeQueueTicket

> CustomApiResponseQueueTicketResponse resumeQueueTicket(queueTicketId)



### Example

```ts
import {
  Configuration,
  QueueTicketManagementApi,
} from '';
import type { ResumeQueueTicketRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new QueueTicketManagementApi(config);

  const body = {
    // string
    queueTicketId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies ResumeQueueTicketRequest;

  try {
    const data = await api.resumeQueueTicket(body);
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
| **queueTicketId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseQueueTicketResponse**](CustomApiResponseQueueTicketResponse.md)

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


## skipQueueTicket

> CustomApiResponseQueueTicketResponse skipQueueTicket(queueTicketId)



### Example

```ts
import {
  Configuration,
  QueueTicketManagementApi,
} from '';
import type { SkipQueueTicketRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new QueueTicketManagementApi(config);

  const body = {
    // string
    queueTicketId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies SkipQueueTicketRequest;

  try {
    const data = await api.skipQueueTicket(body);
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
| **queueTicketId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseQueueTicketResponse**](CustomApiResponseQueueTicketResponse.md)

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


## startQueueTicket

> CustomApiResponseQueueTicketResponse startQueueTicket(queueTicketId)



### Example

```ts
import {
  Configuration,
  QueueTicketManagementApi,
} from '';
import type { StartQueueTicketRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new QueueTicketManagementApi(config);

  const body = {
    // string
    queueTicketId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies StartQueueTicketRequest;

  try {
    const data = await api.startQueueTicket(body);
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
| **queueTicketId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseQueueTicketResponse**](CustomApiResponseQueueTicketResponse.md)

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


## waitResultQueueTicket

> CustomApiResponseQueueTicketResponse waitResultQueueTicket(queueTicketId)



### Example

```ts
import {
  Configuration,
  QueueTicketManagementApi,
} from '';
import type { WaitResultQueueTicketRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new QueueTicketManagementApi(config);

  const body = {
    // string
    queueTicketId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies WaitResultQueueTicketRequest;

  try {
    const data = await api.waitResultQueueTicket(body);
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
| **queueTicketId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseQueueTicketResponse**](CustomApiResponseQueueTicketResponse.md)

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

