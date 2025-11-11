# ServiceOrderItemControllerApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**updateServiceOrderItem**](#updateserviceorderitem) | **PUT** /api/service-order-items/{id} | |

# **updateServiceOrderItem**
> CustomApiResponseObject updateServiceOrderItem(serviceOrderItemRequest)


### Example

```typescript
import {
    ServiceOrderItemControllerApi,
    Configuration,
    ServiceOrderItemRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new ServiceOrderItemControllerApi(configuration);

let id: string; // (default to undefined)
let serviceOrderItemRequest: ServiceOrderItemRequest; //

const { status, data } = await apiInstance.updateServiceOrderItem(
    id,
    serviceOrderItemRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **serviceOrderItemRequest** | **ServiceOrderItemRequest**|  | |
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseObject**

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

