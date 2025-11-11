# ServiceOrderControllerApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**getServiceOrderDetail**](#getserviceorderdetail) | **GET** /api/service-orders/{id} | |
|[**getServiceOrdersForFilters**](#getserviceordersforfilters) | **POST** /api/service-orders/filter | |
|[**getServiceOrdersForStaffToday**](#getserviceordersforstafftoday) | **GET** /api/service-orders/staff/today | |
|[**updateServiceOrder**](#updateserviceorder) | **PUT** /api/service-orders/{id} | |

# **getServiceOrderDetail**
> CustomApiResponseServiceOrderResponse getServiceOrderDetail()


### Example

```typescript
import {
    ServiceOrderControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new ServiceOrderControllerApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.getServiceOrderDetail(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseServiceOrderResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getServiceOrdersForFilters**
> CustomApiResponsePageServiceOrderResponse getServiceOrdersForFilters(searchFilter)


### Example

```typescript
import {
    ServiceOrderControllerApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new ServiceOrderControllerApi(configuration);

let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.getServiceOrdersForFilters(
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |


### Return type

**CustomApiResponsePageServiceOrderResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **getServiceOrdersForStaffToday**
> CustomApiResponseListServiceOrderResponse getServiceOrdersForStaffToday()


### Example

```typescript
import {
    ServiceOrderControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new ServiceOrderControllerApi(configuration);

const { status, data } = await apiInstance.getServiceOrdersForStaffToday();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**CustomApiResponseListServiceOrderResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **updateServiceOrder**
> CustomApiResponseServiceOrderResponse updateServiceOrder(serviceOrderRequest)


### Example

```typescript
import {
    ServiceOrderControllerApi,
    Configuration,
    ServiceOrderRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new ServiceOrderControllerApi(configuration);

let id: string; // (default to undefined)
let serviceOrderRequest: ServiceOrderRequest; //

const { status, data } = await apiInstance.updateServiceOrder(
    id,
    serviceOrderRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **serviceOrderRequest** | **ServiceOrderRequest**|  | |
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseServiceOrderResponse**

### Authorization

[bearerAuth](../README.md#bearerAuth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: */*


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
|**400** | Bad Request |  -  |
|**401** | Unauthorized |  -  |
|**200** | OK |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

