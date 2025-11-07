# ServiceOrderControllerApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createOrders**](ServiceOrderControllerApi.md#createorders) | **POST** /api/examinations/{examId}/orders |  |
| [**createServiceOrder**](ServiceOrderControllerApi.md#createserviceorder) | **POST** /api/examinations/{id}/services |  |
| [**getServiceOrders**](ServiceOrderControllerApi.md#getserviceorders) | **GET** /api/examinations/{id}/services |  |
| [**saveOrUpdateServiceOrders**](ServiceOrderControllerApi.md#saveorupdateserviceorders) | **PUT** /api/examinations/{id}/services/{serviceOrderId} |  |



## createOrders

> CustomApiResponseListServiceOrderResponse createOrders(examId, createServiceOrderRequest)



### Example

```ts
import {
  Configuration,
  ServiceOrderControllerApi,
} from '';
import type { CreateOrdersRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ServiceOrderControllerApi(config);

  const body = {
    // string
    examId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // CreateServiceOrderRequest
    createServiceOrderRequest: ...,
  } satisfies CreateOrdersRequest;

  try {
    const data = await api.createOrders(body);
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
| **examId** | `string` |  | [Defaults to `undefined`] |
| **createServiceOrderRequest** | [CreateServiceOrderRequest](CreateServiceOrderRequest.md) |  | |

### Return type

[**CustomApiResponseListServiceOrderResponse**](CustomApiResponseListServiceOrderResponse.md)

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


## createServiceOrder

> CustomApiResponseServiceOrderResponse createServiceOrder(id, serviceOrderRequest)



### Example

```ts
import {
  Configuration,
  ServiceOrderControllerApi,
} from '';
import type { CreateServiceOrderRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ServiceOrderControllerApi(config);

  const body = {
    // string
    id: id_example,
    // ServiceOrderRequest
    serviceOrderRequest: ...,
  } satisfies CreateServiceOrderRequest;

  try {
    const data = await api.createServiceOrder(body);
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
| **serviceOrderRequest** | [ServiceOrderRequest](ServiceOrderRequest.md) |  | |

### Return type

[**CustomApiResponseServiceOrderResponse**](CustomApiResponseServiceOrderResponse.md)

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


## getServiceOrders

> CustomApiResponseServiceOrderResponse getServiceOrders(id)



### Example

```ts
import {
  Configuration,
  ServiceOrderControllerApi,
} from '';
import type { GetServiceOrdersRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ServiceOrderControllerApi(config);

  const body = {
    // string
    id: id_example,
  } satisfies GetServiceOrdersRequest;

  try {
    const data = await api.getServiceOrders(body);
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

[**CustomApiResponseServiceOrderResponse**](CustomApiResponseServiceOrderResponse.md)

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


## saveOrUpdateServiceOrders

> CustomApiResponseServiceOrderResponse saveOrUpdateServiceOrders(id, serviceOrderId, serviceOrderRequest)



### Example

```ts
import {
  Configuration,
  ServiceOrderControllerApi,
} from '';
import type { SaveOrUpdateServiceOrdersRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ServiceOrderControllerApi(config);

  const body = {
    // string
    id: id_example,
    // string
    serviceOrderId: serviceOrderId_example,
    // ServiceOrderRequest
    serviceOrderRequest: ...,
  } satisfies SaveOrUpdateServiceOrdersRequest;

  try {
    const data = await api.saveOrUpdateServiceOrders(body);
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
| **serviceOrderId** | `string` |  | [Defaults to `undefined`] |
| **serviceOrderRequest** | [ServiceOrderRequest](ServiceOrderRequest.md) |  | |

### Return type

[**CustomApiResponseServiceOrderResponse**](CustomApiResponseServiceOrderResponse.md)

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

