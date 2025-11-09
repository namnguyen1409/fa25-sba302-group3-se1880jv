# PrescriptionControllerApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createPrescriptionForExamination**](PrescriptionControllerApi.md#createprescriptionforexamination) | **POST** /api/prescriptions/{id}/items/filter |  |
| [**createPrescriptionItem**](PrescriptionControllerApi.md#createprescriptionitem) | **POST** /api/prescriptions/{id}/items |  |
| [**updatePrescriptionItem**](PrescriptionControllerApi.md#updateprescriptionitem) | **PUT** /api/prescriptions/items/{itemId} |  |



## createPrescriptionForExamination

> CustomApiResponsePagePrescriptionItemResponse createPrescriptionForExamination(id, searchFilter)



### Example

```ts
import {
  Configuration,
  PrescriptionControllerApi,
} from '';
import type { CreatePrescriptionForExaminationRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new PrescriptionControllerApi(config);

  const body = {
    // string
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // SearchFilter
    searchFilter: ...,
  } satisfies CreatePrescriptionForExaminationRequest;

  try {
    const data = await api.createPrescriptionForExamination(body);
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
| **searchFilter** | [SearchFilter](SearchFilter.md) |  | |

### Return type

[**CustomApiResponsePagePrescriptionItemResponse**](CustomApiResponsePagePrescriptionItemResponse.md)

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


## createPrescriptionItem

> CustomApiResponsePrescriptionItemResponse createPrescriptionItem(id, prescriptionItemRequest)



### Example

```ts
import {
  Configuration,
  PrescriptionControllerApi,
} from '';
import type { CreatePrescriptionItemRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new PrescriptionControllerApi(config);

  const body = {
    // string
    id: id_example,
    // PrescriptionItemRequest
    prescriptionItemRequest: ...,
  } satisfies CreatePrescriptionItemRequest;

  try {
    const data = await api.createPrescriptionItem(body);
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
| **prescriptionItemRequest** | [PrescriptionItemRequest](PrescriptionItemRequest.md) |  | |

### Return type

[**CustomApiResponsePrescriptionItemResponse**](CustomApiResponsePrescriptionItemResponse.md)

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


## updatePrescriptionItem

> CustomApiResponsePrescriptionItemResponse updatePrescriptionItem(itemId, prescriptionItemRequest)



### Example

```ts
import {
  Configuration,
  PrescriptionControllerApi,
} from '';
import type { UpdatePrescriptionItemRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new PrescriptionControllerApi(config);

  const body = {
    // string
    itemId: itemId_example,
    // PrescriptionItemRequest
    prescriptionItemRequest: ...,
  } satisfies UpdatePrescriptionItemRequest;

  try {
    const data = await api.updatePrescriptionItem(body);
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
| **prescriptionItemRequest** | [PrescriptionItemRequest](PrescriptionItemRequest.md) |  | |

### Return type

[**CustomApiResponsePrescriptionItemResponse**](CustomApiResponsePrescriptionItemResponse.md)

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

