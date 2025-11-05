# LabOrderManagementApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**addOrderItem**](LabOrderManagementApi.md#addorderitem) | **POST** /api/lab-orders/{orderId}/results |  |
| [**create5**](LabOrderManagementApi.md#create5) | **POST** /api/lab-orders |  |
| [**delete5**](LabOrderManagementApi.md#delete5) | **DELETE** /api/lab-orders/{orderId} |  |
| [**deleteOrderItem**](LabOrderManagementApi.md#deleteorderitem) | **DELETE** /api/lab-orders/{orderId}/order-items/{itemId} |  |
| [**filter6**](LabOrderManagementApi.md#filter6) | **POST** /api/lab-orders/filter |  |
| [**filterOrderItems**](LabOrderManagementApi.md#filterorderitems) | **POST** /api/lab-orders/{orderId}/results/filter |  |
| [**getById2**](LabOrderManagementApi.md#getbyid2) | **GET** /api/lab-orders/{orderId} |  |
| [**update5**](LabOrderManagementApi.md#update5) | **PUT** /api/lab-orders/{orderId} |  |
| [**updateOrderItem**](LabOrderManagementApi.md#updateorderitem) | **PUT** /api/lab-orders/results/{itemId} |  |



## addOrderItem

> CustomApiResponseLabTestResultResponse addOrderItem(orderId, labTestResultRequest)



### Example

```ts
import {
  Configuration,
  LabOrderManagementApi,
} from '';
import type { AddOrderItemRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new LabOrderManagementApi(config);

  const body = {
    // string
    orderId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // LabTestResultRequest
    labTestResultRequest: ...,
  } satisfies AddOrderItemRequest;

  try {
    const data = await api.addOrderItem(body);
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
| **orderId** | `string` |  | [Defaults to `undefined`] |
| **labTestResultRequest** | [LabTestResultRequest](LabTestResultRequest.md) |  | |

### Return type

[**CustomApiResponseLabTestResultResponse**](CustomApiResponseLabTestResultResponse.md)

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


## create5

> CustomApiResponseLabOrderResponse create5(labOrderRequest)



### Example

```ts
import {
  Configuration,
  LabOrderManagementApi,
} from '';
import type { Create5Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new LabOrderManagementApi(config);

  const body = {
    // LabOrderRequest
    labOrderRequest: ...,
  } satisfies Create5Request;

  try {
    const data = await api.create5(body);
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
| **labOrderRequest** | [LabOrderRequest](LabOrderRequest.md) |  | |

### Return type

[**CustomApiResponseLabOrderResponse**](CustomApiResponseLabOrderResponse.md)

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


## delete5

> CustomApiResponseVoid delete5(orderId)



### Example

```ts
import {
  Configuration,
  LabOrderManagementApi,
} from '';
import type { Delete5Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new LabOrderManagementApi(config);

  const body = {
    // string
    orderId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies Delete5Request;

  try {
    const data = await api.delete5(body);
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
| **orderId** | `string` |  | [Defaults to `undefined`] |

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


## deleteOrderItem

> CustomApiResponseVoid deleteOrderItem(orderId, itemId)



### Example

```ts
import {
  Configuration,
  LabOrderManagementApi,
} from '';
import type { DeleteOrderItemRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new LabOrderManagementApi(config);

  const body = {
    // string
    orderId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // string
    itemId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies DeleteOrderItemRequest;

  try {
    const data = await api.deleteOrderItem(body);
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
| **orderId** | `string` |  | [Defaults to `undefined`] |
| **itemId** | `string` |  | [Defaults to `undefined`] |

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


## filter6

> CustomApiResponsePageLabOrderResponse filter6(searchFilter)



### Example

```ts
import {
  Configuration,
  LabOrderManagementApi,
} from '';
import type { Filter6Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new LabOrderManagementApi(config);

  const body = {
    // SearchFilter
    searchFilter: ...,
  } satisfies Filter6Request;

  try {
    const data = await api.filter6(body);
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

[**CustomApiResponsePageLabOrderResponse**](CustomApiResponsePageLabOrderResponse.md)

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


## filterOrderItems

> CustomApiResponsePageLabTestResultResponse filterOrderItems(orderId, searchFilter)



### Example

```ts
import {
  Configuration,
  LabOrderManagementApi,
} from '';
import type { FilterOrderItemsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new LabOrderManagementApi(config);

  const body = {
    // string
    orderId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // SearchFilter
    searchFilter: ...,
  } satisfies FilterOrderItemsRequest;

  try {
    const data = await api.filterOrderItems(body);
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
| **orderId** | `string` |  | [Defaults to `undefined`] |
| **searchFilter** | [SearchFilter](SearchFilter.md) |  | |

### Return type

[**CustomApiResponsePageLabTestResultResponse**](CustomApiResponsePageLabTestResultResponse.md)

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


## getById2

> CustomApiResponseLabOrderResponse getById2(orderId)



### Example

```ts
import {
  Configuration,
  LabOrderManagementApi,
} from '';
import type { GetById2Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new LabOrderManagementApi(config);

  const body = {
    // string
    orderId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
  } satisfies GetById2Request;

  try {
    const data = await api.getById2(body);
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
| **orderId** | `string` |  | [Defaults to `undefined`] |

### Return type

[**CustomApiResponseLabOrderResponse**](CustomApiResponseLabOrderResponse.md)

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


## update5

> CustomApiResponseLabOrderResponse update5(orderId, labOrderRequest)



### Example

```ts
import {
  Configuration,
  LabOrderManagementApi,
} from '';
import type { Update5Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new LabOrderManagementApi(config);

  const body = {
    // string
    orderId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // LabOrderRequest
    labOrderRequest: ...,
  } satisfies Update5Request;

  try {
    const data = await api.update5(body);
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
| **orderId** | `string` |  | [Defaults to `undefined`] |
| **labOrderRequest** | [LabOrderRequest](LabOrderRequest.md) |  | |

### Return type

[**CustomApiResponseLabOrderResponse**](CustomApiResponseLabOrderResponse.md)

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


## updateOrderItem

> CustomApiResponseLabTestResultResponse updateOrderItem(itemId, labTestResultRequest)



### Example

```ts
import {
  Configuration,
  LabOrderManagementApi,
} from '';
import type { UpdateOrderItemRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new LabOrderManagementApi(config);

  const body = {
    // string
    itemId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // LabTestResultRequest
    labTestResultRequest: ...,
  } satisfies UpdateOrderItemRequest;

  try {
    const data = await api.updateOrderItem(body);
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
| **itemId** | `string` |  | [Defaults to `undefined`] |
| **labTestResultRequest** | [LabTestResultRequest](LabTestResultRequest.md) |  | |

### Return type

[**CustomApiResponseLabTestResultResponse**](CustomApiResponseLabTestResultResponse.md)

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

