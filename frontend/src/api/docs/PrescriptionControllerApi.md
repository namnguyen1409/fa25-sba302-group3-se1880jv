# PrescriptionControllerApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**deletePrescriptionItem**](PrescriptionControllerApi.md#deleteprescriptionitem) | **DELETE** /api/examinations/{id}/prescription/{prescriptionId} |  |
| [**getPrescription**](PrescriptionControllerApi.md#getprescription) | **GET** /api/examinations/{id}/prescription |  |
| [**saveOrUpdatePrescription**](PrescriptionControllerApi.md#saveorupdateprescription) | **PUT** /api/examinations/{id}/prescription/{prescriptionId} |  |



## deletePrescriptionItem

> CustomApiResponseVoid deletePrescriptionItem(id, prescriptionId)



### Example

```ts
import {
  Configuration,
  PrescriptionControllerApi,
} from '';
import type { DeletePrescriptionItemRequest } from '';

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
    // string
    prescriptionId: prescriptionId_example,
  } satisfies DeletePrescriptionItemRequest;

  try {
    const data = await api.deletePrescriptionItem(body);
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
| **prescriptionId** | `string` |  | [Defaults to `undefined`] |

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


## getPrescription

> CustomApiResponsePrescriptionResponse getPrescription(id)



### Example

```ts
import {
  Configuration,
  PrescriptionControllerApi,
} from '';
import type { GetPrescriptionRequest } from '';

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
  } satisfies GetPrescriptionRequest;

  try {
    const data = await api.getPrescription(body);
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

[**CustomApiResponsePrescriptionResponse**](CustomApiResponsePrescriptionResponse.md)

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


## saveOrUpdatePrescription

> CustomApiResponsePrescriptionResponse saveOrUpdatePrescription(id, prescriptionId, prescriptionRequest)



### Example

```ts
import {
  Configuration,
  PrescriptionControllerApi,
} from '';
import type { SaveOrUpdatePrescriptionRequest } from '';

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
    // string
    prescriptionId: prescriptionId_example,
    // PrescriptionRequest
    prescriptionRequest: ...,
  } satisfies SaveOrUpdatePrescriptionRequest;

  try {
    const data = await api.saveOrUpdatePrescription(body);
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
| **prescriptionId** | `string` |  | [Defaults to `undefined`] |
| **prescriptionRequest** | [PrescriptionRequest](PrescriptionRequest.md) |  | |

### Return type

[**CustomApiResponsePrescriptionResponse**](CustomApiResponsePrescriptionResponse.md)

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

