# PrescriptionControllerApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**createPrescriptionForExamination**](#createprescriptionforexamination) | **POST** /api/prescriptions/{id}/items/filter | |
|[**createPrescriptionItem**](#createprescriptionitem) | **POST** /api/prescriptions/{id}/items | |
|[**deletePrescriptionItem**](#deleteprescriptionitem) | **DELETE** /api/prescriptions/items/{itemId} | |
|[**updatePrescriptionItem**](#updateprescriptionitem) | **PUT** /api/prescriptions/items/{itemId} | |

# **createPrescriptionForExamination**
> CustomApiResponsePagePrescriptionItemResponse createPrescriptionForExamination(searchFilter)


### Example

```typescript
import {
    PrescriptionControllerApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new PrescriptionControllerApi(configuration);

let id: string; // (default to undefined)
let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.createPrescriptionForExamination(
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

**CustomApiResponsePagePrescriptionItemResponse**

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

# **createPrescriptionItem**
> CustomApiResponsePrescriptionItemResponse createPrescriptionItem(prescriptionItemRequest)


### Example

```typescript
import {
    PrescriptionControllerApi,
    Configuration,
    PrescriptionItemRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new PrescriptionControllerApi(configuration);

let id: string; // (default to undefined)
let prescriptionItemRequest: PrescriptionItemRequest; //

const { status, data } = await apiInstance.createPrescriptionItem(
    id,
    prescriptionItemRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **prescriptionItemRequest** | **PrescriptionItemRequest**|  | |
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponsePrescriptionItemResponse**

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

# **deletePrescriptionItem**
> CustomApiResponseVoid deletePrescriptionItem()


### Example

```typescript
import {
    PrescriptionControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new PrescriptionControllerApi(configuration);

let itemId: string; // (default to undefined)

const { status, data } = await apiInstance.deletePrescriptionItem(
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

# **updatePrescriptionItem**
> CustomApiResponsePrescriptionItemResponse updatePrescriptionItem(prescriptionItemRequest)


### Example

```typescript
import {
    PrescriptionControllerApi,
    Configuration,
    PrescriptionItemRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new PrescriptionControllerApi(configuration);

let itemId: string; // (default to undefined)
let prescriptionItemRequest: PrescriptionItemRequest; //

const { status, data } = await apiInstance.updatePrescriptionItem(
    itemId,
    prescriptionItemRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **prescriptionItemRequest** | **PrescriptionItemRequest**|  | |
| **itemId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponsePrescriptionItemResponse**

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

