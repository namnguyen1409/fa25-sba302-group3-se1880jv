# DiagnosisControllerApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**addDiagnosis**](#adddiagnosis) | **POST** /api/examinations/{id}/diagnosis | |
|[**deleteDiagnosis**](#deletediagnosis) | **DELETE** /api/examinations/{id}/diagnosis/{diagnosisId} | |
|[**filterDiagnosis**](#filterdiagnosis) | **POST** /api/examinations/{id}/diagnosis/filter | |
|[**getDiagnosisList**](#getdiagnosislist) | **GET** /api/examinations/{id}/diagnosis | |

# **addDiagnosis**
> CustomApiResponseDiagnosisResponse addDiagnosis(diagnosisRequest)


### Example

```typescript
import {
    DiagnosisControllerApi,
    Configuration,
    DiagnosisRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new DiagnosisControllerApi(configuration);

let id: string; // (default to undefined)
let diagnosisRequest: DiagnosisRequest; //

const { status, data } = await apiInstance.addDiagnosis(
    id,
    diagnosisRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **diagnosisRequest** | **DiagnosisRequest**|  | |
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseDiagnosisResponse**

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

# **deleteDiagnosis**
> CustomApiResponseVoid deleteDiagnosis()


### Example

```typescript
import {
    DiagnosisControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new DiagnosisControllerApi(configuration);

let id: string; // (default to undefined)
let diagnosisId: string; // (default to undefined)

const { status, data } = await apiInstance.deleteDiagnosis(
    id,
    diagnosisId
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|
| **diagnosisId** | [**string**] |  | defaults to undefined|


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

# **filterDiagnosis**
> CustomApiResponsePageDiagnosisResponse filterDiagnosis(searchFilter)


### Example

```typescript
import {
    DiagnosisControllerApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new DiagnosisControllerApi(configuration);

let id: string; // (default to undefined)
let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filterDiagnosis(
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

**CustomApiResponsePageDiagnosisResponse**

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

# **getDiagnosisList**
> CustomApiResponseDiagnosisResponse getDiagnosisList()


### Example

```typescript
import {
    DiagnosisControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new DiagnosisControllerApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.getDiagnosisList(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseDiagnosisResponse**

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

