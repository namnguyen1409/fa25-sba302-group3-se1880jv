# ExaminationControllerApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**createExamination**](#createexamination) | **POST** /api/examinations | |
|[**createLabOrder**](#createlaborder) | **POST** /api/examinations/{id}/lab/orders | |
|[**createOrders**](#createorders) | **POST** /api/examinations/{examId}/services/orders | |
|[**createPrescription**](#createprescription) | **POST** /api/examinations/{id}/prescription | |
|[**createServiceOrder**](#createserviceorder) | **POST** /api/examinations/{id}/services | |
|[**deletePrescriptionItem1**](#deleteprescriptionitem1) | **DELETE** /api/examinations/prescription/{prescriptionId} | |
|[**deleteServiceOrderItem**](#deleteserviceorderitem) | **DELETE** /api/examinations/{id}/services/item/{itemId} | |
|[**filterLabOrders**](#filterlaborders) | **POST** /api/examinations/{id}/lab/orders/filter | |
|[**filterServiceOrders**](#filterserviceorders) | **POST** /api/examinations/{examId}/services/filter | |
|[**getExaminationDetail**](#getexaminationdetail) | **GET** /api/examinations/{id} | |
|[**getExaminations**](#getexaminations) | **POST** /api/examinations/filter | |
|[**getPrescription**](#getprescription) | **GET** /api/examinations/{id}/prescription | |
|[**getServiceOrders**](#getserviceorders) | **GET** /api/examinations/{id}/services | |
|[**saveOrUpdatePrescription**](#saveorupdateprescription) | **PUT** /api/examinations/{id}/prescription/{prescriptionId} | |
|[**saveOrUpdateServiceOrders**](#saveorupdateserviceorders) | **PUT** /api/examinations/{id}/services/{serviceOrderId} | |
|[**updateExamination**](#updateexamination) | **PUT** /api/examinations/{id} | |

# **createExamination**
> CustomApiResponseExaminationResponse createExamination(examinationRequest)


### Example

```typescript
import {
    ExaminationControllerApi,
    Configuration,
    ExaminationRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new ExaminationControllerApi(configuration);

let examinationRequest: ExaminationRequest; //

const { status, data } = await apiInstance.createExamination(
    examinationRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **examinationRequest** | **ExaminationRequest**|  | |


### Return type

**CustomApiResponseExaminationResponse**

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

# **createLabOrder**
> CustomApiResponseListLabOrderResponse createLabOrder(createLabOrderRequest)


### Example

```typescript
import {
    ExaminationControllerApi,
    Configuration,
    CreateLabOrderRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new ExaminationControllerApi(configuration);

let id: string; // (default to undefined)
let createLabOrderRequest: CreateLabOrderRequest; //

const { status, data } = await apiInstance.createLabOrder(
    id,
    createLabOrderRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **createLabOrderRequest** | **CreateLabOrderRequest**|  | |
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseListLabOrderResponse**

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

# **createOrders**
> CustomApiResponseListServiceOrderResponse createOrders(createServiceOrderRequest)


### Example

```typescript
import {
    ExaminationControllerApi,
    Configuration,
    CreateServiceOrderRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new ExaminationControllerApi(configuration);

let examId: string; // (default to undefined)
let createServiceOrderRequest: CreateServiceOrderRequest; //

const { status, data } = await apiInstance.createOrders(
    examId,
    createServiceOrderRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **createServiceOrderRequest** | **CreateServiceOrderRequest**|  | |
| **examId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseListServiceOrderResponse**

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

# **createPrescription**
> CustomApiResponsePrescriptionResponse createPrescription(prescriptionRequest)


### Example

```typescript
import {
    ExaminationControllerApi,
    Configuration,
    PrescriptionRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new ExaminationControllerApi(configuration);

let id: string; // (default to undefined)
let prescriptionRequest: PrescriptionRequest; //

const { status, data } = await apiInstance.createPrescription(
    id,
    prescriptionRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **prescriptionRequest** | **PrescriptionRequest**|  | |
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponsePrescriptionResponse**

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

# **createServiceOrder**
> CustomApiResponseServiceOrderResponse createServiceOrder(serviceOrderRequest)


### Example

```typescript
import {
    ExaminationControllerApi,
    Configuration,
    ServiceOrderRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new ExaminationControllerApi(configuration);

let id: string; // (default to undefined)
let serviceOrderRequest: ServiceOrderRequest; //

const { status, data } = await apiInstance.createServiceOrder(
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

# **deletePrescriptionItem1**
> CustomApiResponseVoid deletePrescriptionItem1()


### Example

```typescript
import {
    ExaminationControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new ExaminationControllerApi(configuration);

let prescriptionId: string; // (default to undefined)

const { status, data } = await apiInstance.deletePrescriptionItem1(
    prescriptionId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **prescriptionId** | [**string**] |  | defaults to undefined|


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

# **deleteServiceOrderItem**
> CustomApiResponseVoid deleteServiceOrderItem()


### Example

```typescript
import {
    ExaminationControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new ExaminationControllerApi(configuration);

let id: string; // (default to undefined)
let itemId: string; // (default to undefined)

const { status, data } = await apiInstance.deleteServiceOrderItem(
    id,
    itemId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|
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

# **filterLabOrders**
> CustomApiResponsePageLabOrderResponse filterLabOrders(searchFilter)


### Example

```typescript
import {
    ExaminationControllerApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new ExaminationControllerApi(configuration);

let id: string; // (default to undefined)
let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filterLabOrders(
    id,
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |
| **id** | [**string**] |  | defaults to undefined|


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

# **filterServiceOrders**
> CustomApiResponsePageServiceOrderResponse filterServiceOrders(searchFilter)


### Example

```typescript
import {
    ExaminationControllerApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new ExaminationControllerApi(configuration);

let examId: string; // (default to undefined)
let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filterServiceOrders(
    examId,
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |
| **examId** | [**string**] |  | defaults to undefined|


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

# **getExaminationDetail**
> CustomApiResponseExaminationResponse getExaminationDetail()


### Example

```typescript
import {
    ExaminationControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new ExaminationControllerApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.getExaminationDetail(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseExaminationResponse**

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

# **getExaminations**
> CustomApiResponsePageExaminationResponse getExaminations(searchFilter)


### Example

```typescript
import {
    ExaminationControllerApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new ExaminationControllerApi(configuration);

let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.getExaminations(
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |


### Return type

**CustomApiResponsePageExaminationResponse**

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

# **getPrescription**
> CustomApiResponsePrescriptionResponse getPrescription()


### Example

```typescript
import {
    ExaminationControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new ExaminationControllerApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.getPrescription(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponsePrescriptionResponse**

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

# **getServiceOrders**
> CustomApiResponseServiceOrderResponse getServiceOrders()


### Example

```typescript
import {
    ExaminationControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new ExaminationControllerApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.getServiceOrders(
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

# **saveOrUpdatePrescription**
> CustomApiResponsePrescriptionResponse saveOrUpdatePrescription(prescriptionRequest)


### Example

```typescript
import {
    ExaminationControllerApi,
    Configuration,
    PrescriptionRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new ExaminationControllerApi(configuration);

let id: string; // (default to undefined)
let prescriptionId: string; // (default to undefined)
let prescriptionRequest: PrescriptionRequest; //

const { status, data } = await apiInstance.saveOrUpdatePrescription(
    id,
    prescriptionId,
    prescriptionRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **prescriptionRequest** | **PrescriptionRequest**|  | |
| **id** | [**string**] |  | defaults to undefined|
| **prescriptionId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponsePrescriptionResponse**

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

# **saveOrUpdateServiceOrders**
> CustomApiResponseServiceOrderResponse saveOrUpdateServiceOrders(serviceOrderRequest)


### Example

```typescript
import {
    ExaminationControllerApi,
    Configuration,
    ServiceOrderRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new ExaminationControllerApi(configuration);

let id: string; // (default to undefined)
let serviceOrderId: string; // (default to undefined)
let serviceOrderRequest: ServiceOrderRequest; //

const { status, data } = await apiInstance.saveOrUpdateServiceOrders(
    id,
    serviceOrderId,
    serviceOrderRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **serviceOrderRequest** | **ServiceOrderRequest**|  | |
| **id** | [**string**] |  | defaults to undefined|
| **serviceOrderId** | [**string**] |  | defaults to undefined|


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

# **updateExamination**
> CustomApiResponseExaminationResponse updateExamination(examinationRequest)


### Example

```typescript
import {
    ExaminationControllerApi,
    Configuration,
    ExaminationRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new ExaminationControllerApi(configuration);

let id: string; // (default to undefined)
let examinationRequest: ExaminationRequest; //

const { status, data } = await apiInstance.updateExamination(
    id,
    examinationRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **examinationRequest** | **ExaminationRequest**|  | |
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseExaminationResponse**

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

