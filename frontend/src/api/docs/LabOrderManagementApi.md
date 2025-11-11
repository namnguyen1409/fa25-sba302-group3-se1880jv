# LabOrderManagementApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**addOrderItem**](#addorderitem) | **POST** /api/lab-orders/{orderId}/results | |
|[**create5**](#create5) | **POST** /api/lab-orders | |
|[**delete5**](#delete5) | **DELETE** /api/lab-orders/{orderId} | |
|[**deleteOrderItem**](#deleteorderitem) | **DELETE** /api/lab-orders/{orderId}/order-items/{itemId} | |
|[**filter6**](#filter6) | **POST** /api/lab-orders/filter | |
|[**filterOrderItems**](#filterorderitems) | **POST** /api/lab-orders/{orderId}/results/filter | |
|[**getById2**](#getbyid2) | **GET** /api/lab-orders/{orderId} | |
|[**getLabOrdersForStaffToday**](#getlabordersforstafftoday) | **GET** /api/lab-orders/staff/today | |
|[**update5**](#update5) | **PUT** /api/lab-orders/{orderId} | |
|[**updateOrderItem**](#updateorderitem) | **PUT** /api/lab-orders/results/{itemId} | |
|[**verifyOrderItem**](#verifyorderitem) | **POST** /api/lab-orders/result/{itemId}/verify | |

# **addOrderItem**
> CustomApiResponseLabTestResultResponse addOrderItem(labTestResultRequest)


### Example

```typescript
import {
    LabOrderManagementApi,
    Configuration,
    LabTestResultRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new LabOrderManagementApi(configuration);

let orderId: string; // (default to undefined)
let labTestResultRequest: LabTestResultRequest; //

const { status, data } = await apiInstance.addOrderItem(
    orderId,
    labTestResultRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **labTestResultRequest** | **LabTestResultRequest**|  | |
| **orderId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseLabTestResultResponse**

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

# **create5**
> CustomApiResponseLabOrderResponse create5(labOrderRequest)


### Example

```typescript
import {
    LabOrderManagementApi,
    Configuration,
    LabOrderRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new LabOrderManagementApi(configuration);

let labOrderRequest: LabOrderRequest; //

const { status, data } = await apiInstance.create5(
    labOrderRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **labOrderRequest** | **LabOrderRequest**|  | |


### Return type

**CustomApiResponseLabOrderResponse**

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

# **delete5**
> CustomApiResponseVoid delete5()


### Example

```typescript
import {
    LabOrderManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new LabOrderManagementApi(configuration);

let orderId: string; // (default to undefined)

const { status, data } = await apiInstance.delete5(
    orderId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **orderId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseVoid**

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

# **deleteOrderItem**
> CustomApiResponseVoid deleteOrderItem()


### Example

```typescript
import {
    LabOrderManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new LabOrderManagementApi(configuration);

let orderId: string; // (default to undefined)
let itemId: string; // (default to undefined)

const { status, data } = await apiInstance.deleteOrderItem(
    orderId,
    itemId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **orderId** | [**string**] |  | defaults to undefined|
| **itemId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseVoid**

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

# **filter6**
> CustomApiResponsePageLabOrderResponse filter6(searchFilter)


### Example

```typescript
import {
    LabOrderManagementApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new LabOrderManagementApi(configuration);

let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filter6(
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |


### Return type

**CustomApiResponsePageLabOrderResponse**

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

# **filterOrderItems**
> CustomApiResponsePageLabTestResultResponse filterOrderItems(searchFilter)


### Example

```typescript
import {
    LabOrderManagementApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new LabOrderManagementApi(configuration);

let orderId: string; // (default to undefined)
let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filterOrderItems(
    orderId,
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |
| **orderId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponsePageLabTestResultResponse**

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

# **getById2**
> CustomApiResponseLabOrderResponse getById2()


### Example

```typescript
import {
    LabOrderManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new LabOrderManagementApi(configuration);

let orderId: string; // (default to undefined)

const { status, data } = await apiInstance.getById2(
    orderId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **orderId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseLabOrderResponse**

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

# **getLabOrdersForStaffToday**
> CustomApiResponseListLabOrderResponse getLabOrdersForStaffToday()


### Example

```typescript
import {
    LabOrderManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new LabOrderManagementApi(configuration);

const { status, data } = await apiInstance.getLabOrdersForStaffToday();
```

### Parameters
This endpoint does not have any parameters.


### Return type

**CustomApiResponseListLabOrderResponse**

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

# **update5**
> CustomApiResponseLabOrderResponse update5(labOrderRequest)


### Example

```typescript
import {
    LabOrderManagementApi,
    Configuration,
    LabOrderRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new LabOrderManagementApi(configuration);

let orderId: string; // (default to undefined)
let labOrderRequest: LabOrderRequest; //

const { status, data } = await apiInstance.update5(
    orderId,
    labOrderRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **labOrderRequest** | **LabOrderRequest**|  | |
| **orderId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseLabOrderResponse**

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

# **updateOrderItem**
> CustomApiResponseLabTestResultResponse updateOrderItem(labTestResultRequest)


### Example

```typescript
import {
    LabOrderManagementApi,
    Configuration,
    LabTestResultRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new LabOrderManagementApi(configuration);

let itemId: string; // (default to undefined)
let labTestResultRequest: LabTestResultRequest; //

const { status, data } = await apiInstance.updateOrderItem(
    itemId,
    labTestResultRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **labTestResultRequest** | **LabTestResultRequest**|  | |
| **itemId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseLabTestResultResponse**

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

# **verifyOrderItem**
> CustomApiResponseVoid verifyOrderItem()


### Example

```typescript
import {
    LabOrderManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new LabOrderManagementApi(configuration);

let itemId: string; // (default to undefined)

const { status, data } = await apiInstance.verifyOrderItem(
    itemId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **itemId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseVoid**

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

