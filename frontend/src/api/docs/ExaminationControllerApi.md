# ExaminationControllerApi

All URIs are relative to *http://localhost:9999*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createExamination**](ExaminationControllerApi.md#createexamination) | **POST** /api/examinations |  |
| [**createLabOrder**](ExaminationControllerApi.md#createlaborderoperation) | **POST** /api/examinations/{id}/lab/orders |  |
| [**createOrders**](ExaminationControllerApi.md#createorders) | **POST** /api/examinations/{examId}/services/orders |  |
| [**createPrescription**](ExaminationControllerApi.md#createprescription) | **POST** /api/examinations/{id}/prescription |  |
| [**createServiceOrder**](ExaminationControllerApi.md#createserviceorder) | **POST** /api/examinations/{id}/services |  |
| [**deletePrescriptionItem1**](ExaminationControllerApi.md#deleteprescriptionitem1) | **DELETE** /api/examinations/prescription/{prescriptionId} |  |
| [**deleteServiceOrderItem**](ExaminationControllerApi.md#deleteserviceorderitem) | **DELETE** /api/examinations/{id}/services/item/{itemId} |  |
| [**filterLabOrders**](ExaminationControllerApi.md#filterlaborders) | **POST** /api/examinations/{id}/lab/orders/filter |  |
| [**filterServiceOrders**](ExaminationControllerApi.md#filterserviceorders) | **POST** /api/examinations/{examId}/services/filter |  |
| [**getExaminationDetail**](ExaminationControllerApi.md#getexaminationdetail) | **GET** /api/examinations/{id} |  |
| [**getExaminations**](ExaminationControllerApi.md#getexaminations) | **POST** /api/examinations/filter |  |
| [**getPrescription**](ExaminationControllerApi.md#getprescription) | **GET** /api/examinations/{id}/prescription |  |
| [**getServiceOrders**](ExaminationControllerApi.md#getserviceorders) | **GET** /api/examinations/{id}/services |  |
| [**saveOrUpdatePrescription**](ExaminationControllerApi.md#saveorupdateprescription) | **PUT** /api/examinations/{id}/prescription/{prescriptionId} |  |
| [**saveOrUpdateServiceOrders**](ExaminationControllerApi.md#saveorupdateserviceorders) | **PUT** /api/examinations/{id}/services/{serviceOrderId} |  |
| [**updateExamination**](ExaminationControllerApi.md#updateexamination) | **PUT** /api/examinations/{id} |  |



## createExamination

> CustomApiResponseExaminationResponse createExamination(examinationRequest)



### Example

```ts
import {
  Configuration,
  ExaminationControllerApi,
} from '';
import type { CreateExaminationRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ExaminationControllerApi(config);

  const body = {
    // ExaminationRequest
    examinationRequest: ...,
  } satisfies CreateExaminationRequest;

  try {
    const data = await api.createExamination(body);
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
| **examinationRequest** | [ExaminationRequest](ExaminationRequest.md) |  | |

### Return type

[**CustomApiResponseExaminationResponse**](CustomApiResponseExaminationResponse.md)

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


## createLabOrder

> CustomApiResponseListLabOrderResponse createLabOrder(id, createLabOrderRequest)



### Example

```ts
import {
  Configuration,
  ExaminationControllerApi,
} from '';
import type { CreateLabOrderOperationRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ExaminationControllerApi(config);

  const body = {
    // string
    id: id_example,
    // CreateLabOrderRequest
    createLabOrderRequest: ...,
  } satisfies CreateLabOrderOperationRequest;

  try {
    const data = await api.createLabOrder(body);
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
| **createLabOrderRequest** | [CreateLabOrderRequest](CreateLabOrderRequest.md) |  | |

### Return type

[**CustomApiResponseListLabOrderResponse**](CustomApiResponseListLabOrderResponse.md)

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


## createOrders

> CustomApiResponseListServiceOrderResponse createOrders(examId, createServiceOrderRequest)



### Example

```ts
import {
  Configuration,
  ExaminationControllerApi,
} from '';
import type { CreateOrdersRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ExaminationControllerApi(config);

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


## createPrescription

> CustomApiResponsePrescriptionResponse createPrescription(id, prescriptionRequest)



### Example

```ts
import {
  Configuration,
  ExaminationControllerApi,
} from '';
import type { CreatePrescriptionRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ExaminationControllerApi(config);

  const body = {
    // string
    id: id_example,
    // PrescriptionRequest
    prescriptionRequest: ...,
  } satisfies CreatePrescriptionRequest;

  try {
    const data = await api.createPrescription(body);
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


## createServiceOrder

> CustomApiResponseServiceOrderResponse createServiceOrder(id, serviceOrderRequest)



### Example

```ts
import {
  Configuration,
  ExaminationControllerApi,
} from '';
import type { CreateServiceOrderRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ExaminationControllerApi(config);

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


## deletePrescriptionItem1

> CustomApiResponseVoid deletePrescriptionItem1(prescriptionId)



### Example

```ts
import {
  Configuration,
  ExaminationControllerApi,
} from '';
import type { DeletePrescriptionItem1Request } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ExaminationControllerApi(config);

  const body = {
    // string
    prescriptionId: prescriptionId_example,
  } satisfies DeletePrescriptionItem1Request;

  try {
    const data = await api.deletePrescriptionItem1(body);
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


## deleteServiceOrderItem

> CustomApiResponseVoid deleteServiceOrderItem(id, itemId)



### Example

```ts
import {
  Configuration,
  ExaminationControllerApi,
} from '';
import type { DeleteServiceOrderItemRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ExaminationControllerApi(config);

  const body = {
    // string
    id: id_example,
    // string
    itemId: itemId_example,
  } satisfies DeleteServiceOrderItemRequest;

  try {
    const data = await api.deleteServiceOrderItem(body);
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


## filterLabOrders

> CustomApiResponsePageLabOrderResponse filterLabOrders(id, searchFilter)



### Example

```ts
import {
  Configuration,
  ExaminationControllerApi,
} from '';
import type { FilterLabOrdersRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ExaminationControllerApi(config);

  const body = {
    // string
    id: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // SearchFilter
    searchFilter: ...,
  } satisfies FilterLabOrdersRequest;

  try {
    const data = await api.filterLabOrders(body);
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


## filterServiceOrders

> CustomApiResponsePageServiceOrderResponse filterServiceOrders(examId, searchFilter)



### Example

```ts
import {
  Configuration,
  ExaminationControllerApi,
} from '';
import type { FilterServiceOrdersRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ExaminationControllerApi(config);

  const body = {
    // string
    examId: 38400000-8cf0-11bd-b23e-10b96e4ef00d,
    // SearchFilter
    searchFilter: ...,
  } satisfies FilterServiceOrdersRequest;

  try {
    const data = await api.filterServiceOrders(body);
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


## getExaminationDetail

> CustomApiResponseExaminationResponse getExaminationDetail(id)



### Example

```ts
import {
  Configuration,
  ExaminationControllerApi,
} from '';
import type { GetExaminationDetailRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ExaminationControllerApi(config);

  const body = {
    // string
    id: id_example,
  } satisfies GetExaminationDetailRequest;

  try {
    const data = await api.getExaminationDetail(body);
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

[**CustomApiResponseExaminationResponse**](CustomApiResponseExaminationResponse.md)

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


## getExaminations

> CustomApiResponsePageExaminationResponse getExaminations(searchFilter)



### Example

```ts
import {
  Configuration,
  ExaminationControllerApi,
} from '';
import type { GetExaminationsRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ExaminationControllerApi(config);

  const body = {
    // SearchFilter
    searchFilter: ...,
  } satisfies GetExaminationsRequest;

  try {
    const data = await api.getExaminations(body);
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

[**CustomApiResponsePageExaminationResponse**](CustomApiResponsePageExaminationResponse.md)

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


## getPrescription

> CustomApiResponsePrescriptionResponse getPrescription(id)



### Example

```ts
import {
  Configuration,
  ExaminationControllerApi,
} from '';
import type { GetPrescriptionRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ExaminationControllerApi(config);

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


## getServiceOrders

> CustomApiResponseServiceOrderResponse getServiceOrders(id)



### Example

```ts
import {
  Configuration,
  ExaminationControllerApi,
} from '';
import type { GetServiceOrdersRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ExaminationControllerApi(config);

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


## saveOrUpdatePrescription

> CustomApiResponsePrescriptionResponse saveOrUpdatePrescription(id, prescriptionId, prescriptionRequest)



### Example

```ts
import {
  Configuration,
  ExaminationControllerApi,
} from '';
import type { SaveOrUpdatePrescriptionRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ExaminationControllerApi(config);

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


## saveOrUpdateServiceOrders

> CustomApiResponseServiceOrderResponse saveOrUpdateServiceOrders(id, serviceOrderId, serviceOrderRequest)



### Example

```ts
import {
  Configuration,
  ExaminationControllerApi,
} from '';
import type { SaveOrUpdateServiceOrdersRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ExaminationControllerApi(config);

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


## updateExamination

> CustomApiResponseExaminationResponse updateExamination(id, examinationRequest)



### Example

```ts
import {
  Configuration,
  ExaminationControllerApi,
} from '';
import type { UpdateExaminationRequest } from '';

async function example() {
  console.log("🚀 Testing  SDK...");
  const config = new Configuration({ 
    // Configure HTTP bearer authorization: bearerAuth
    accessToken: "YOUR BEARER TOKEN",
  });
  const api = new ExaminationControllerApi(config);

  const body = {
    // string
    id: id_example,
    // ExaminationRequest
    examinationRequest: ...,
  } satisfies UpdateExaminationRequest;

  try {
    const data = await api.updateExamination(body);
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
| **examinationRequest** | [ExaminationRequest](ExaminationRequest.md) |  | |

### Return type

[**CustomApiResponseExaminationResponse**](CustomApiResponseExaminationResponse.md)

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

