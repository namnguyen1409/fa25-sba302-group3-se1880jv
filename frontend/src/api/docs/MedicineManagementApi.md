# MedicineManagementApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**create3**](#create3) | **POST** /api/medicines | |
|[**delete3**](#delete3) | **DELETE** /api/medicines/{medicineId} | |
|[**filter4**](#filter4) | **POST** /api/medicines/filter | |
|[**getById**](#getbyid) | **GET** /api/medicines/{medicineId} | |
|[**update3**](#update3) | **PUT** /api/medicines/{medicineId} | |

# **create3**
> CustomApiResponseMedicineResponse create3(medicineRequest)


### Example

```typescript
import {
    MedicineManagementApi,
    Configuration,
    MedicineRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new MedicineManagementApi(configuration);

let medicineRequest: MedicineRequest; //

const { status, data } = await apiInstance.create3(
    medicineRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **medicineRequest** | **MedicineRequest**|  | |


### Return type

**CustomApiResponseMedicineResponse**

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

# **delete3**
> CustomApiResponseVoid delete3()


### Example

```typescript
import {
    MedicineManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new MedicineManagementApi(configuration);

let medicineId: string; // (default to undefined)

const { status, data } = await apiInstance.delete3(
    medicineId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **medicineId** | [**string**] |  | defaults to undefined|


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

# **filter4**
> CustomApiResponsePageMedicineResponse filter4(searchFilter)


### Example

```typescript
import {
    MedicineManagementApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new MedicineManagementApi(configuration);

let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filter4(
    searchFilter
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **searchFilter** | **SearchFilter**|  | |


### Return type

**CustomApiResponsePageMedicineResponse**

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

# **getById**
> CustomApiResponseMedicineResponse getById()


### Example

```typescript
import {
    MedicineManagementApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new MedicineManagementApi(configuration);

let medicineId: string; // (default to undefined)

const { status, data } = await apiInstance.getById(
    medicineId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **medicineId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseMedicineResponse**

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

# **update3**
> CustomApiResponseMedicineResponse update3(medicineRequest)


### Example

```typescript
import {
    MedicineManagementApi,
    Configuration,
    MedicineRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new MedicineManagementApi(configuration);

let medicineId: string; // (default to undefined)
let medicineRequest: MedicineRequest; //

const { status, data } = await apiInstance.update3(
    medicineId,
    medicineRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **medicineRequest** | **MedicineRequest**|  | |
| **medicineId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseMedicineResponse**

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

