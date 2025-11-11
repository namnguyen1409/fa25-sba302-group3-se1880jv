# VitalSignControllerApi

All URIs are relative to *http://localhost:9999*

|Method | HTTP request | Description|
|------------- | ------------- | -------------|
|[**createVital**](#createvital) | **POST** /api/examinations/{id}/vitals | |
|[**filterVitals**](#filtervitals) | **POST** /api/examinations/{id}/vitals/filter | |
|[**getVitals**](#getvitals) | **GET** /api/examinations/{id}/vitals | |
|[**saveOrUpdateVitals**](#saveorupdatevitals) | **PUT** /api/examinations/{id}/vitals/{vitalId} | |

# **createVital**
> CustomApiResponseVitalSignResponse createVital(vitalSignRequest)


### Example

```typescript
import {
    VitalSignControllerApi,
    Configuration,
    VitalSignRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new VitalSignControllerApi(configuration);

let id: string; // (default to undefined)
let vitalSignRequest: VitalSignRequest; //

const { status, data } = await apiInstance.createVital(
    id,
    vitalSignRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **vitalSignRequest** | **VitalSignRequest**|  | |
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseVitalSignResponse**

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

# **filterVitals**
> CustomApiResponsePageVitalSignResponse filterVitals(searchFilter)


### Example

```typescript
import {
    VitalSignControllerApi,
    Configuration,
    SearchFilter
} from './api';

const configuration = new Configuration();
const apiInstance = new VitalSignControllerApi(configuration);

let id: string; // (default to undefined)
let searchFilter: SearchFilter; //

const { status, data } = await apiInstance.filterVitals(
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

**CustomApiResponsePageVitalSignResponse**

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

# **getVitals**
> CustomApiResponseVitalSignResponse getVitals()


### Example

```typescript
import {
    VitalSignControllerApi,
    Configuration
} from './api';

const configuration = new Configuration();
const apiInstance = new VitalSignControllerApi(configuration);

let id: string; // (default to undefined)

const { status, data } = await apiInstance.getVitals(
    id
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **id** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseVitalSignResponse**

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

# **saveOrUpdateVitals**
> CustomApiResponseVitalSignResponse saveOrUpdateVitals(vitalSignRequest)


### Example

```typescript
import {
    VitalSignControllerApi,
    Configuration,
    VitalSignRequest
} from './api';

const configuration = new Configuration();
const apiInstance = new VitalSignControllerApi(configuration);

let id: string; // (default to undefined)
let vitalId: string; // (default to undefined)
let vitalSignRequest: VitalSignRequest; //

const { status, data } = await apiInstance.saveOrUpdateVitals(
    id,
    vitalId,
    vitalSignRequest
);
```

### Parameters

|Name | Type | Description  | Notes|
|------------- | ------------- | ------------- | -------------|
| **vitalSignRequest** | **VitalSignRequest**|  | |
| **id** | [**string**] |  | defaults to undefined|
| **vitalId** | [**string**] |  | defaults to undefined|


### Return type

**CustomApiResponseVitalSignResponse**

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

