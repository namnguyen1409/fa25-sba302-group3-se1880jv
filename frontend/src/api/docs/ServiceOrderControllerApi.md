# ServiceOrderControllerApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getServiceOrderDetail**](ServiceOrderControllerApi.md#getserviceorderdetail) | **GET** /api/service-orders/{id} |  |
| [**getServiceOrdersForFilters**](ServiceOrderControllerApi.md#getserviceordersforfilters) | **POST** /api/service-orders/filter |  |
| [**getServiceOrdersForStaffToday**](ServiceOrderControllerApi.md#getserviceordersforstafftoday) | **GET** /api/service-orders/staff/today |  |
| [**updateServiceOrder**](ServiceOrderControllerApi.md#updateserviceorder) | **PUT** /api/service-orders/{id} |  |



## getServiceOrderDetail

> CustomApiResponseServiceOrderResponse getServiceOrderDetail(id)



### Example

```ts
import {
  Configuration,
  ServiceOrderControllerApi,
} from '';
import type { GetServiceOrderDetailRequest } from '';

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
  } satisfies GetServiceOrderDetailRequest;

  try {
    const data = await api.getServiceOrderDetail(body);
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


## getServiceOrdersForFilters

> CustomApiResponsePageServiceOrderResponse getServiceOrdersForFilters(searchFilter)



### Example

```ts
import {
  Configuration,
  ServiceOrderControllerApi,
} from '';
import type { GetServiceOrdersForFiltersRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ServiceOrderControllerApi(config);

  const body = {
    // SearchFilter
    searchFilter: ...,
  } satisfies GetServiceOrdersForFiltersRequest;

  try {
    const data = await api.getServiceOrdersForFilters(body);
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

[**CustomApiResponsePageServiceOrderResponse**](CustomApiResponsePageServiceOrderResponse.md)

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


## getServiceOrdersForStaffToday

> CustomApiResponseListServiceOrderResponse getServiceOrdersForStaffToday()



### Example

```ts
import {
  Configuration,
  ServiceOrderControllerApi,
} from '';
import type { GetServiceOrdersForStaffTodayRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ServiceOrderControllerApi(config);

  try {
    const data = await api.getServiceOrdersForStaffToday();
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

[**CustomApiResponseListServiceOrderResponse**](CustomApiResponseListServiceOrderResponse.md)

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


## updateServiceOrder

> CustomApiResponseServiceOrderResponse updateServiceOrder(id, serviceOrderRequest)



### Example

```ts
import {
  Configuration,
  ServiceOrderControllerApi,
} from '';
import type { UpdateServiceOrderRequest } from '';

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
  } satisfies UpdateServiceOrderRequest;

  try {
    const data = await api.updateServiceOrder(body);
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

