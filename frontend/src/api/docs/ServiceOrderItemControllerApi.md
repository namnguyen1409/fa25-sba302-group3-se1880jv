# ServiceOrderItemControllerApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**updateServiceOrderItem**](ServiceOrderItemControllerApi.md#updateserviceorderitem) | **PUT** /api/service-order-items/{id} |  |



## updateServiceOrderItem

> CustomApiResponseObject updateServiceOrderItem(id, serviceOrderItemRequest)



### Example

```ts
import {
  Configuration,
  ServiceOrderItemControllerApi,
} from '';
import type { UpdateServiceOrderItemRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ServiceOrderItemControllerApi(config);

  const body = {
    // string
    id: id_example,
    // ServiceOrderItemRequest
    serviceOrderItemRequest: ...,
  } satisfies UpdateServiceOrderItemRequest;

  try {
    const data = await api.updateServiceOrderItem(body);
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
| **serviceOrderItemRequest** | [ServiceOrderItemRequest](ServiceOrderItemRequest.md) |  | |

### Return type

[**CustomApiResponseObject**](CustomApiResponseObject.md)

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

